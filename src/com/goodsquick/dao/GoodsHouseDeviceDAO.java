package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;

public interface GoodsHouseDeviceDAO {

	public int saveHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	public void updateHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	
	public List<GoodsHouseDevice> getAllHouseDeviceByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception;
	
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception;
}
