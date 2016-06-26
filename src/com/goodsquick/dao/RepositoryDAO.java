package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsRepositoryUser;

public interface RepositoryDAO {

	public void saveRepository(GoodsRepository goodsRepository) throws Exception;
	public void updateRepository(GoodsRepository goodsRepository) throws Exception;
	
	/**
	 * 根据当前登录用户名获取物库列表
	 * @param loginName
	 * @param hideDeleted 是否获取删除的物库，true代表不获取
	 * @return 物库列表
	 * @throws Exception
	 */
	public List<GoodsRepository> getRepositoryByLoginName(String loginName, boolean hideDeleted) throws Exception;
	/**
	 * 根据当前登录用户名和物库类型获取物库列表
	 * @param loginName
	 * @param type 物库类型
	 * @param hideDeleted 是否获取删除的物库，true代表不获取
	 * @param excludeSelf 是否排除自己创建的库
	 * @return 物库列表
	 * @throws Exception
	 */
	public List<GoodsRepository> getRepositoryByLoginNameAndType(String loginName, String type, boolean hideDeleted, boolean excludeSelf) throws Exception;
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser) throws Exception;
	public void saveRepositoryUser(GoodsRepositoryUser repositoryUser) throws Exception;
	public GoodsRepository getRepositoryByCode(String repositoryCode) throws Exception;
	public void removeRepository(GoodsRepository goodsRepositoryFromPage) throws Exception;
	
	/**
	 * 根据物库编码查询库用户
	 * @param repositoryCode
	 * @param currentUser currentUser
	 * @return
	 * @throws Exception
	 */
	public List<GoodsRepositoryUser> getRepositoryUserByRepositoryCode(String repositoryCode, String currentUser) throws Exception;
	
	public List<GoodsRepositoryUser> getRepositoryUserByUserId(String repositoryCode, String userId) throws Exception;
	
	public void removeRepositoryUser(GoodsRepositoryUser repositoryUser) throws Exception;
	
	public List<GoodsOrdinaryHouse> getRepositoryAssetByRepositoryList(String repositoryCodes) throws Exception;
}
