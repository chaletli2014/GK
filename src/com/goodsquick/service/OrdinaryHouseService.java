package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

public interface OrdinaryHouseService {

	public List<GoodsOrdinaryHouse> getOrdinaryHouseByUserCode(String userCode) throws Exception;
	public GoodsOrdinaryHouse getOrdinaryHouseByRepositoryCode(String repositoryCode) throws Exception;
	public List<GoodsRelationshipProperty> getAllHouseRelationshipByUserCode(String userCode) throws Exception;
	public void saveOrUpdateOrdinaryHouse(GoodsRelationshipProperty relationshipProperty, GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser) throws Exception;
	public void deleteOrdinaryHouse(int ordinaryHouseId) throws Exception;
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseById(int ordinaryHouseId) throws Exception;
	public GoodsOrdinaryHouse getOwnedGoodsOrdinaryHouseById(int ordinaryHouseId) throws Exception;
	
	public void saveOrdinaryHousesFromFile(List<GoodsOrdinaryHouse> houses) throws Exception;
	
	public void saveOrUpdateHouseDevice(GoodsHouseDevice houseDevice, WebUserInfo currentUser) throws Exception;
	public List<GoodsHouseDevice> getAllHouseDeviceByUser(GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser) throws Exception;
	public List<GoodsHouseDevice> getAllHouseDeviceByRepositoryCode(String repositoryCode) throws Exception;
	
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseByName(String houseName) throws Exception;
}
