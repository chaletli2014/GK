package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.GoodsConfigurationDAO;
import com.goodsquick.dao.GoodsServiceDAO;
import com.goodsquick.dao.MessageDAO;
import com.goodsquick.dao.OrdinaryHouseDAO;
import com.goodsquick.dao.RelationshipPropertyDAO;
import com.goodsquick.dao.UserDAO;
import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.GoodsMessage;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.GoodsQuickStringUtils;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	@Qualifier("messageDAO")
	private MessageDAO messageDAO;
	
	@Autowired
	@Qualifier("goodsServiceDAO")
	private GoodsServiceDAO goodsServiceDAO;
	
	@Autowired
	@Qualifier("goodsConfigurationDAO")
	private GoodsConfigurationDAO goodsConfigurationDAO;
	
	@Autowired
	@Qualifier("ordinaryHouseDAO")
	private OrdinaryHouseDAO ordinaryHouseDAO;
	
	@Autowired
	@Qualifier("relationshipPropertyDAO")
	private RelationshipPropertyDAO relationshipPropertyDAO;
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public List<GoodsRelatedRequest> getMessageList() throws Exception {
		try{
			WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return messageDAO.getMessageList(currentUser.getLoginName());
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the ordinary house by user code,",e);
            return Collections.emptyList();
        }
	}

	@Override
	@Transactional
	public void handleMessage(int messageId, String status) throws Exception {
		WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		messageDAO.handleMessage(messageId, status, currentUser);
		
		/**
		 * 同意关联，
		 */
		if( "2".equals(status)){
			/**对已经绑定的服务商进行解绑*/
			goodsServiceDAO.rejectCustomer2SP(messageId, currentUser);
			/**将物业code更新到最新的服务商关系表中*/
			goodsServiceDAO.updateCustomer2SP(messageId, currentUser);
			
			GoodsRelatedRequest requestMessage = messageDAO.getMessageById(messageId);
			String spType = requestMessage.getServiceTypeCode();
			GoodsConfiguration config = goodsConfigurationDAO.getConfigByCode(spType);
			String spCodeColumn = config.getConfigValue();
			String spNameColumn = spCodeColumn.substring(0,spCodeColumn.indexOf('_'))+"_name";
			
			GoodsOrdinaryHouse house = ordinaryHouseDAO.getGoodsOrdinaryHouseByName(requestMessage.getOrHouseName());
			
			/**获取要关联物业的物联属性*/
			GoodsRelationshipProperty relationProperty = null;
			try{
				relationProperty = relationshipPropertyDAO.getRelationshipProperty("tbl_goods_ordinary_house", house.getId());
			}catch(EmptyResultDataAccessException erd){
				
			}
			
			/**将服务商更新到物业的物联关系表中*/
			List<Object> params = new ArrayList<Object>();
			StringBuilder updateSQL = getUpdateSQL(params, null==relationProperty, spCodeColumn, spNameColumn
					, house, currentUser.getLoginName(), requestMessage.getSpCode(), messageId);
			ordinaryHouseDAO.updateSP2House(updateSQL.toString(),params);
		}
	}
	
	/**
	 * 获取物业关联表的更新或者插入语句
	 * @param params
	 * @param isInsert
	 * @param spCodeColumn
	 * @param spNameColumn
	 * @param house
	 * @param loginName
	 * @param spCode
	 * @return
	 */
	private StringBuilder getUpdateSQL(List<Object> params
			, boolean isInsert, String spCodeColumn, String spNameColumn
			, GoodsOrdinaryHouse house, String loginName, String spCode, int messageId){
		StringBuilder localUpdateSQL = new StringBuilder();
		if( isInsert ){
			localUpdateSQL.append(" insert into tbl_goods_relationship_property(id,source_table,source_id,location");
			localUpdateSQL.append(" ,").append(spCodeColumn).append(",").append(spNameColumn);
			localUpdateSQL.append(" ,createdate,create_user,updatedate,update_user )");
			localUpdateSQL.append(" select null,'tbl_goods_ordinary_house',?,?,gs.code,gs.name,now(),?,now(),?");
			localUpdateSQL.append(" from tbl_goods_sp_request gsr, tbl_goods_service gs ");
			localUpdateSQL.append(" where gsr.id=? and gsr.sp_code = gs.code ");
			
			params.add(house.getId());
			params.add(house.getLocation());
			params.add(loginName);
			params.add(loginName);
			params.add(messageId);
		}else{
			localUpdateSQL.append(" update tbl_goods_relationship_property rp, tbl_goods_service gs ");
			localUpdateSQL.append(" set rp.").append(spCodeColumn).append(" = ?");
			localUpdateSQL.append(" ,rp.").append(spNameColumn).append(" = gs.name ");
			localUpdateSQL.append(" where rp.source_table = 'tbl_goods_ordinary_house' and rp.source_id = ?");
			localUpdateSQL.append(" and gs.code = ? ");
			
			params.add(spCode);
			params.add(house.getId());
			params.add(spCode);
		}
		return localUpdateSQL;
	}

	@Override
	public void updateCustomer2SP(int messageId, WebUserInfo currentUser)
			throws Exception {
		goodsServiceDAO.updateCustomer2SP(messageId, currentUser);
	}

	@Override
	public void createNewMessage(String sourceUser, String targetUser,
			String content) throws Exception {
		messageDAO.createNewMessage(sourceUser, targetUser, content);
	}

	@Override
	public GoodsMessage getMessageByHouseName(String sourceUser, String content)
			throws Exception {
		try{
			return messageDAO.getMessageByHouseName(sourceUser, content);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	@Override
	public List<GoodsMessage> getInBoxMessageListByRepo(int userId) throws Exception {
		try{
			return messageDAO.getMessageListByRepo(userId, "inbox");
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the inbox MESSAGE list by user id,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsMessage> getOutBoxMessageListByRepo(int userId) throws Exception {
		List<GoodsMessage> outMessage = new ArrayList<GoodsMessage>();
		try{
			List<GoodsMessage> dbOutMessage = messageDAO.getMessageListByRepo(userId,"outbox");
			outMessage = populateMessage(dbOutMessage);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the outbox MESSAGE list by user id,",e);
			return Collections.emptyList();
		}
		return outMessage;
	}

	@Override
	public void createNewMessage(GoodsMessage msg) throws Exception {
		messageDAO.createNewMessage(msg);
	}

	@Override
	public GoodsMessage getMessageById(long messageId) throws Exception {
		List<GoodsMessage> dbMsg = messageDAO.getMessageById(messageId);
		List<GoodsMessage> returnMsg = populateMessage(dbMsg);
		if( CollectionUtils.isEmpty(returnMsg) ){
			return null;
		}else{
			return returnMsg.get(0);
		}
	}
	
	private List<GoodsMessage> populateMessage(List<GoodsMessage> dbMsg) throws Exception{
		List<GoodsMessage> msgList = new ArrayList<GoodsMessage>();
		Map<Long,GoodsMessage> msgMap = new HashMap<Long, GoodsMessage>();
		if( !CollectionUtils.isEmpty(dbMsg) ){
			for( GoodsMessage msg : dbMsg){
				if( msgMap.containsKey(msg.getId()) ){
					List<Long> targetUserList = msgMap.get(msg.getId()).getTargetUsers();
					targetUserList.add(msg.getReceiverId());
				}else{
					List<Long> initList = new ArrayList<Long>();
					initList.add(msg.getReceiverId());
					msg.setTargetUsers(initList);
					msgMap.put(msg.getId(), msg);
				}
			}
			
			Iterator<GoodsMessage> it = msgMap.values().iterator();
			while( it.hasNext() ){
				GoodsMessage message = it.next();
				List<Long> targetUserList = message.getTargetUsers();
				String targetUserName = getTargetUserName(targetUserList);
				if( StringUtils.isNotBlank(targetUserName) ){
					message.setReceiverNames(targetUserName);
				}
				msgList.add(message);
			}
		}
		return msgList;
	}
	
	private String getTargetUserName(List<Long> targetUserList) throws Exception{
		StringBuilder targetUserNames = new StringBuilder("");
		List<String> queryParam = new ArrayList<String>();
		StringBuilder queryCondition = new StringBuilder(" where 1=1 and id in (")
		.append(GoodsQuickStringUtils.getInParameterByLongList(targetUserList))
		.append(") ");
		List<WebUserInfo> userInfos = userDAO.getAllUserByQueryInfo(queryCondition.toString(), queryParam.toArray());
		if( !CollectionUtils.isEmpty(userInfos) ){
			for( WebUserInfo user : userInfos ){
				targetUserNames.append(user.getName()).append(",");
			}
		}
		return targetUserNames.substring(0,targetUserNames.length()-1);
	}

	@Override
	public void updateMessageStatus(long messageId, long userId, String status, String boxType) {
		messageDAO.updateMessageStatus(messageId, userId, status, boxType);
	}
	
	
}
