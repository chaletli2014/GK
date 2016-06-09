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
import com.goodsquick.model.GoodsCompanyInfo;
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
        	if( null != userInfo ){
				try{
					userInfo.setCompany(userDAO.getCompanyInfoByUserID(userInfo.getId()));
				}catch(EmptyResultDataAccessException dae){}
			}
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
			WebUserInfo userInfo = userDAO.getUserProfileById(userId);
			if( null != userInfo ){
				try{
					userInfo.setCompany(userDAO.getCompanyInfoByUserID(userInfo.getId()));
				}catch(EmptyResultDataAccessException dae){}
			}
            return userInfo;
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return new WebUserInfo();
        } catch(Exception e){
            logger.error("fail to get the web user info by userId - " + userId,e);
            return new WebUserInfo();
        }
	}

	@Override
	public int addUserInfo(WebUserInfo userInfo) throws Exception {
		return userDAO.addUserInfo(userInfo);
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
			WebUserInfo userInfo = userDAO.getUserProfileByLoginName(loginName);
			
			if( null != userInfo ){
				try{
					userInfo.setCompany(userDAO.getCompanyInfoByUserID(userInfo.getId()));
				}catch(EmptyResultDataAccessException dae){}
			}
			
            return userInfo;
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return new WebUserInfo();
        } catch(Exception e){
            logger.error("fail to get the web user info by loginName - " + loginName,e);
            return new WebUserInfo();
        }
	}

	@Override
	public void registerUserCompanyInfo(WebUserInfo userInfo,
			GoodsCompanyInfo companyInfo) throws Exception {
		int userId = userDAO.addUserInfo(userInfo);
		int companyId = userDAO.addCompanyInfo(companyInfo);
		userDAO.insertCompanyUserRelationship(companyId, userId, userInfo.getLoginName());
	}

	@Override
	public int addCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception {
		return userDAO.addCompanyInfo(companyInfo);
	}

	@Override
	public void updateCompanyInfo(GoodsCompanyInfo companyInfo)
			throws Exception {
		userDAO.updateCompanyInfo(companyInfo);
	}

	@Override
	public void registUser2Company(int userId, int companyId, String loginName)
			throws Exception {
		userDAO.insertCompanyUserRelationship(companyId, userId, loginName);
	}

	@Override
	public WebUserInfo getUserProfileByUserName(String name) throws Exception {
		try{
			WebUserInfo userInfo = userDAO.getUserProfileByUserName(name);
			if( null != userInfo ){
				try{
					userInfo.setCompany(userDAO.getCompanyInfoByUserID(userInfo.getId()));
				}catch(EmptyResultDataAccessException dae){}
			}
            return userInfo;
        } catch(EmptyResultDataAccessException erd){
            logger.info("there is no web user found.");
            return null;
        } catch(Exception e){
            logger.error("fail to get the web user info by name - " + name,e);
            return null;
        }
	}
}
