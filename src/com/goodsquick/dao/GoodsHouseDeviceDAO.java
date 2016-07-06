package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;

public interface GoodsHouseDeviceDAO {

	public int saveHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	public void updateHouseDevice(GoodsHouseDevice houseDevice) throws Exception;
	
	public List<GoodsHouseDevice> getAllHouseDeviceByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception;
	
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception;
	
	/**
	 * 根据物库编码和设备分类获取设备列表
	 * @param repositoryCode
	 * @param eqTypeCode
	 * @return
	 * @throws Exception
	 */
	public List<GoodsHouseDevice> getDeviceByEqTypeCode(String repositoryCode, String eqTypeCode) throws Exception;
	
	public List<GoodsHouseDevice> getDeviceBySubjectId(int subjectId) throws Exception;
	public List<GoodsHouseDevice> getDeviceByModuleId(int moduleId) throws Exception;
}
