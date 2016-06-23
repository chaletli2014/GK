package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;

public interface GoodsHouseDeviceService {

	public List<GoodsHouseDevice> getAllDevice(String repositoryCode) throws Exception;
	
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception;
	
	public void saveOrUpdateHouseDevice(List<GoodsHouseDevice> goodsSubjects, String currentUser, String repositoryCode) throws Exception;
	
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception;
	
	/**
	 * 根据主体ID查出该主体以及子主体下的所有设备
	 * @param subjectId
	 * @return List<GoodsHouseDevice> 设备列表
	 * @throws Exception
	 */
	public List<GoodsHouseDevice> getHouseDeviceBySubjectId(int subjectId) throws Exception;
	
	/**
	 * 根据构件ID查出该构件下的所有设备
	 * @param moduleId
	 * @return List<GoodsHouseDevice> 设备列表
	 * @throws Exception
	 */
	public List<GoodsHouseDevice> getHouseDeviceByModuleId(int moduleId) throws Exception;
	
	/**
	 * 根据物库编码和设备分类获取设备列表
	 * @param repositoryCode
	 * @param eqTypeCode
	 * @return
	 * @throws Exception
	 */
	public List<GoodsHouseDevice> getDeviceByEqTypeCode(String repositoryCode, String eqTypeCode) throws Exception;
}
