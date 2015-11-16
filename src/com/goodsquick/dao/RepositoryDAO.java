package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsRepositoryUser;

public interface RepositoryDAO {

	public void saveRepository(GoodsRepository goodsRepository) throws Exception;
	public void updateRepository(GoodsRepository goodsRepository) throws Exception;
	public List<GoodsRepository> getRepositoryByLoginName(String loginName) throws Exception;
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser) throws Exception;
	public void saveRepositoryUser(GoodsRepositoryUser repositoryUser) throws Exception;
	public GoodsRepository getRepositoryByCode(String repositoryCode) throws Exception;
}
