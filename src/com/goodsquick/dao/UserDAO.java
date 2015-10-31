package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.WebUserInfo;

/**
 * @author Chalet
 * 类说明
 */

public interface UserDAO {

	public WebUserInfo getWebUser(String username, String password) throws Exception;
	
	/**
	 * 获取所有的用户信息
	 * @return List<WebUserInfo>
	 * @throws Exception
	 */
	public List<WebUserInfo> getAllUserByQueryInfo(String queryCondition, Object[] queryParam) throws Exception;
	
	/**
	 * 根据ID获取所有的用户信息
	 * @param userId userId
	 * @return WebUserInfo
	 * @throws Exception
	 */
	public WebUserInfo getUserProfileById(String userId) throws Exception;
	
	/**
	 * 新增用户信息
	 * @param userInfo userInfo
	 * @return int 用户主键ID
	 * @throws Exception Exception
	 */
	public int addUserInfo(WebUserInfo userInfo) throws Exception;
	
	/**
	 * 新增用户信息
	 * @param companyInfo companyInfo
	 * @return int 公司主键ID
	 * @throws Exception Exception
	 */
	public int addCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param userInfo userInfo
	 * @throws Exception Exception
	 */
	public void updateUserInfo(WebUserInfo userInfo) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param companyInfo companyInfo
	 * @throws Exception Exception
	 */
	public void updateCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception;
	
	/**
	 * 注销用户
	 * @param userId userId
	 * @throws Exception Exception
	 */
	public void disableUser(String userId) throws Exception;
	
	/**
	 * 根据登录名获取注册用户信息
	 * @param loginName
	 * @return 注册用户
	 * @throws Exception
	 */
	public WebUserInfo getUserProfileByLoginName(String loginName) throws Exception;
	
	/**
	 * 根据登录名获取未读消息个数
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public int getMessageNumByLoginName(String loginName) throws Exception;
	
	/**
	 * 根据用户ID查询公司信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public GoodsCompanyInfo getCompanyInfoByUserID(int userId) throws Exception;
	
	public void insertCompanyUserRelationship(int companyId, int userId, String loginName) throws Exception;
}
