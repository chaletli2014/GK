package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

public interface OrdinaryHouseDAO {

	public List<GoodsOrdinaryHouse> getOrdinaryHouseByUserCode(String userCode) throws Exception;
	public GoodsOrdinaryHouse getOrdinaryHouseByRepositoryCode(String repositoryCode) throws Exception;
	public int saveOrdinaryHouse(GoodsOrdinaryHouse ordinaryHouse) throws Exception;
	public void updateOrdinaryHouse(GoodsOrdinaryHouse ordinaryHouse) throws Exception;
	public void deleteOrdinaryHouse(int ordinaryHouseId) throws Exception;
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseById(int ordinaryHouseId) throws Exception;
	public GoodsOrdinaryHouse getOwnedGoodsOrdinaryHouseById(int ordinaryHouseId) throws Exception;
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseByName(String houseName) throws Exception;
	public GoodsOrdinaryHouse getOwnedGoodsOrdinaryHouseByName(String houseName) throws Exception;
	
	public String getMaxHouseCode() throws Exception;
	
	public void saveOrdinaryHousesFromFile(List<GoodsOrdinaryHouse> houses) throws Exception;
	public void saveOrdinaryHouseDeviceFromFile(List<GoodsHouseDevice> houseDevices) throws Exception;
	
	public int saveHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	public void updateHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	
	public List<GoodsHouseDevice> getAllHouseDeviceByUser(GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser) throws Exception;
	
	public void updateSP2House(String updateSQL, List<Object> params) throws Exception;
	
	public List<GoodsRelationshipProperty> getAllHouseRelationshipByUserCode(String userCode) throws Exception;
	
	public List<String> getAllExistsHouses() throws Exception;
}
