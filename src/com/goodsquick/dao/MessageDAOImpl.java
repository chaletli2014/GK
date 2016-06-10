package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsMessageRowMapper;
import com.goodsquick.mapper.GoodsRelatedRequestRowMapper;
import com.goodsquick.model.GoodsMessage;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;

@Repository("messageDAO")
public class MessageDAOImpl extends BaseDAOImpl implements MessageDAO {
	
	private static final StringBuilder MESSAGE_SQL_SELECT = new StringBuilder("select ")
						.append(" gm.id,sur.name as source_user, gm.target_user, gm.message_type, gd.dic_name as message_type_name ")
						.append(" ,gm.message_title, gm.message_content, gm.repository_code, gm.status ")
						.append(" ,gm.create_date, gm.create_user, gm.update_date, gm.update_user");
	
	private static final StringBuilder MESSAGE_SQL_FROM = new StringBuilder(" from tbl_goods_message gm, tbl_goods_dictionary gd, tbl_web_userinfo sur ");
	
	private static final StringBuilder MESSAGE_SQL_COMMON_WHERE = new StringBuilder("")
        .append(" and gm.message_type = gd.dic_code ")
        .append(" and gm.source_user = sur.id ")
        .append(" and gd.type_code = 'message_type' ");

	@Override
	public List<GoodsRelatedRequest> getMessageList(String loginName) throws Exception {
		List<GoodsRelatedRequest> messageList = new ArrayList<GoodsRelatedRequest>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select sr.id,sr.sp_customer_id,sr.customer_name,sr.sp_code, gc.company_name as sp_name ");
		sql.append(" ,sr.status,sr.service_type as service_type_code, gd.dic_name as service_type_name  ");
		sql.append(" ,sr.create_date,sr.update_date,sr.create_user,sr.update_user  ");
        sql.append(" from tbl_goods_ordinary_house oh ");
        sql.append(" , tbl_goods_sp_request sr ");
        sql.append(" , tbl_goods_dictionary gd ");
        sql.append(" , tbl_web_userinfo user ");
        sql.append(" , tbl_goods_company_user gcu ");
        sql.append(" , tbl_goods_company gc ");
        sql.append(" where oh.create_user = ? and oh.building_name = sr.customer_name ");
        sql.append(" and sr.service_type = gd.dic_code and gd.type_code='serviceTypes' ");
        sql.append(" and sr.create_user = user.login_name ");
        sql.append(" and user.id = gcu.user_id ");
        sql.append(" and gcu.company_id = gc.id ");
        sql.append(" order by sr.create_date desc ");
        messageList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{loginName}, new GoodsRelatedRequestRowMapper());
        return messageList;
	}

	@Override
	public void handleMessage(int messageId, String status, WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_sp_request ");
        sql.append("set status = ?,update_date = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(currentUser.getLoginName());
		params.add(messageId);
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public GoodsRelatedRequest getMessageById(int messageId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sr.id,sr.sp_customer_id,sr.customer_name,sr.sp_code,gs.name as sp_name");
		sql.append(" ,sr.status,sr.service_type as service_type_code, gd.dic_name as service_type_name  ");
		sql.append(" ,sr.create_date,sr.update_date,sr.create_user,sr.update_user  ");
        sql.append(" from tbl_goods_ordinary_house oh, tbl_goods_sp_request sr, tbl_goods_service gs, tbl_goods_dictionary gd ");
        sql.append(" where sr.id = ? and oh.building_name = sr.customer_name and sr.sp_code = gs.code");
        sql.append(" and sr.service_type = gd.dic_code and gd.type_code='serviceTypes'");
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{messageId},new GoodsRelatedRequestRowMapper());
	}

	@Override
	public void createNewMessage(String sourceUser, String targetUser,
			String content) throws Exception {
		List<String> params = new ArrayList<String>(5);
		StringBuilder insertSQL = new StringBuilder(200);
		insertSQL.append(" insert into tbl_goods_message( source_user,target_user,message_content,create_user,create_date,update_user,update_date )");
		insertSQL.append(" values (?,?,?,?,now(),?,now() )");
		
		params.add(sourceUser);
		params.add(targetUser);
		params.add(content);
		params.add(sourceUser);
		params.add(sourceUser);
		
		dataBean.getJdbcTemplate().update(insertSQL.toString(), params.toArray());
	}

	@Override
	public GoodsMessage getMessageByHouseName(String sourceUser, String content)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from tbl_goods_message where source_user = ? and message_content = ? ");
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{sourceUser,content},new GoodsMessageRowMapper());
	}
	
	@Override
	public List<GoodsMessage> getMessageListByRepo(String repositoryCode, int userId, String boxType) throws Exception {
		List<GoodsMessage> messageList = new ArrayList<GoodsMessage>();
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(MESSAGE_SQL_SELECT);
        sql.append(MESSAGE_SQL_FROM);
        if( "inbox".equalsIgnoreCase(boxType) ){
        	sql.append(" where find_in_set(?,gm.target_user) ");
        }else{
        	sql.append(" where gm.repository_code = ? ");
        	sql.append(" and gm.source_user = ? ");
        	params.add(repositoryCode);
        }
        params.add(userId);
        sql.append(MESSAGE_SQL_COMMON_WHERE);
        sql.append(" order by gm.create_date desc ");
        messageList = dataBean.getJdbcTemplate().query(sql.toString(), params.toArray(), new GoodsMessageRowMapper());
        return messageList;
	}

	@Override
	public void createNewMessage(GoodsMessage msg) throws Exception {
		List<String> params = new ArrayList<String>(5);
		StringBuilder insertSQL = new StringBuilder(200);
		insertSQL.append(" insert into tbl_goods_message( source_user,target_user,message_type,message_title,message_content,repository_code,status,create_user,create_date,update_user,update_date )");
		insertSQL.append(" values (?,?,?,?,?,?,'1',?,now(),?,now() )");
		
		params.add(msg.getSourceUser());
		params.add(msg.getTargetUser());
		params.add(msg.getMessageType());
		params.add(msg.getMessageTitle());
		params.add(msg.getMessageContent());
		params.add(msg.getRepositoryCode());
		params.add(msg.getCreateUser());
		params.add(msg.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(insertSQL.toString(), params.toArray());
	}

	@Override
	public GoodsMessage getMessageById(long messageId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(MESSAGE_SQL_SELECT);
        sql.append(MESSAGE_SQL_FROM);
        sql.append(" where gm.id = ? ");
        sql.append(MESSAGE_SQL_COMMON_WHERE);
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{messageId},new GoodsMessageRowMapper());
	}
}
