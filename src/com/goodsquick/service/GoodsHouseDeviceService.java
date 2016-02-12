package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;

public interface GoodsHouseDeviceService {

	public List<GoodsHouseDevice> getAllDevice(String repositoryCode) throws Exception;
	
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception;
	
	public void saveOrUpdateHouseDevice(List<GoodsHouseDevice> goodsSubjects, String currentUser, String repositoryCode) throws Exception;
	
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception;
}
