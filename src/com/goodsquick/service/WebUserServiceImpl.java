package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.UserDAO;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.GoodsQuickMD5Utils;
import com.goodsquick.utils.QueryInfo;

/**
 * @author Chalet
 * 类说明
 */

@Service("webUserService")
public class WebUserServiceImpl implements WebUserService {

    Logger logger = Logger.getLogger(WebUserServiceImpl.class);
    
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	@Override
    public WebUserInfo getWebUser(String username, String password) throws Exception {
        try{
        	WebUserInfo userInfo = userDAO.getWebUser(username, GoodsQuickMD5Utils.MD5(password));
        	
        	/**
        	 * 加载消息个数
        	 */
        	userInfo.setMessageNum(userDAO.getMessageNumByLoginName(userInfo.getLoginName()));
            return userInfo;
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return null;
        } catch(Exception e){
            logger.error("fail to get the web user info by username - " + username,e);
            return null;
        }
    }

	@Override
	public List<WebUserInfo> getAllUserByQueryInfo(QueryInfo queryInfo) throws Exception {
		try{
			StringBuilder conditionSB = new StringBuilder(" where 1=1 ");
			List<String> params = new ArrayList<String>();
			
			if( null != queryInfo.getLevel() && !"".equalsIgnoreCase(queryInfo.getLevel()) ){
				conditionSB.append(" and level = ? ");
				params.add(queryInfo.getLevel());
			}
			
			if( null != queryInfo.getType() && !"".equalsIgnoreCase(queryInfo.getType()) ){
				conditionSB.append(" and type = ? ");
				params.add(queryInfo.getType());
			}
			
            return userDAO.getAllUserByQueryInfo(conditionSB.toString(),params.toArray());
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no user found.");
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the user,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public WebUserInfo getUserProfileById(String userId) throws Exception {
		try{
            return userDAO.getUserProfileById(userId);
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return new WebUserInfo();
        } catch(Exception e){
            logger.error("fail to get the web user info by userId - " + userId,e);
            return new WebUserInfo();
        }
	}

	@Override
	public void addUserInfo(WebUserInfo userInfo) throws Exception {
		userDAO.addUserInfo(userInfo);
	}
	
	@Override
	public void updateUserInfo(WebUserInfo userInfo) throws Exception {
		userDAO.updateUserInfo(userInfo);
	}
	
	@Override
	public void disableUser(String userId) throws Exception {
		userDAO.disableUser(userId);
	}

	@Override
	public WebUserInfo getUserProfileByLoginName(String loginName)
			throws Exception {
		try{
            return userDAO.getUserProfileByLoginName(loginName);
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return new WebUserInfo();
        } catch(Exception e){
            logger.error("fail to get the web user info by loginName - " + loginName,e);
            return new WebUserInfo();
        }
	}
}
