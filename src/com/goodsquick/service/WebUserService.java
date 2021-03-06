package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.QueryInfo;

/**
 * @author Chalet
 * 类说明
 */

public interface WebUserService {

	/**
	 * 根据登录名和密码获取用户信息
	 * @param username 登录名
	 * @param password 密码
	 * @return 符合条件的用户对象
	 * @throws Exception
	 */
	public WebUserInfo getWebUser(String username, String password) throws Exception;
	
	/**
	 * 获取所有的用户信息
	 * @return List<WebUserInfo>
	 * @throws Exception
	 */
	public List<WebUserInfo> getAllUserByQueryInfo(QueryInfo queryInfo) throws Exception;
	
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
	 * @throws Exception Exception
	 */
	public int addUserInfo(WebUserInfo userInfo) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param userInfo userInfo
	 * @throws Exception Exception
	 */
	public void updateUserInfo(WebUserInfo userInfo) throws Exception;
	
	/**
	 * 新增用户信息
	 * @param companyInfo companyInfo
	 * @throws Exception Exception
	 */
	public int addCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception;
	
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
	
	public void registerUserCompanyInfo(WebUserInfo userInfo, GoodsCompanyInfo companyInfo) throws Exception;
	
	public void registUser2Company(int userId, int companyId, String loginName) throws Exception;
	
	/**
	 * 根据用户名查找用户信息
	 * (用于物链关联用户时，查找用户)
	 * @param name 用户名
	 * @return 用户信息
	 * @throws Exception
	 */
	public WebUserInfo getUserProfileByUserName(String name) throws Exception;
	/**
	 * 根据用户Id查找用户信息
	 * (用于物讯发送消息时，查找用户)
	 * @param name 用户名
	 * @return List<WebUserInfo> 用户信息
	 * @throws Exception
	 */
	public List<WebUserInfo> getUserProfileByIds(String ids) throws Exception;
}
