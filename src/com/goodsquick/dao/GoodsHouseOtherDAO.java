package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseOther;

public interface GoodsHouseOtherDAO {

	public int saveHouseOther(GoodsHouseOther houseOther) throws Exception;
	public void updateHouseOther(GoodsHouseOther houseOther) throws Exception;
	
	public List<GoodsHouseOther> getAllHouseOtherByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsHouseOther getOtherInfoById(int deviceId) throws Exception;
	
	public void deleteHouseOther(GoodsHouseOther obj) throws Exception;
}
