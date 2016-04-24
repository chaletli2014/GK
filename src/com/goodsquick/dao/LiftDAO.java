package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsDeviceLift;

public interface LiftDAO {

	public List<GoodsDeviceLift> getDeviceLiftByUserCode(String userCode, String liftType) throws Exception;
	public List<GoodsDeviceLift> getDeviceLiftByRepositoryCode(String repositoryCode) throws Exception;
	public void saveDeviceLift(GoodsDeviceLift lift ) throws Exception;
	public void saveDeviceLiftQuick(GoodsDeviceLift lift ) throws Exception;
	public void updateDeviceLift(GoodsDeviceLift lift ) throws Exception;
	public void deleteDeviceLift(int liftId) throws Exception;
	public void deleteDeviceLift(GoodsDeviceLift liftObj) throws Exception;
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception;
	
	public String getMaxCode() throws Exception;
	
	/**
	 * 根据主体ID获取该主体及子主体下的所有设备
	 * @param subjectId 主体ID
	 * @return List<GoodsDeviceLift>
	 * @throws Exception Exception
	 */
	public List<GoodsDeviceLift> getDeviceLiftBySubjectId(int subjectId) throws Exception;
	
	/**
	 * 根据构件ID获取该构件下的所有设备
	 * @param moduleId 构件ID
	 * @return List<GoodsDeviceLift>
	 * @throws Exception Exception
	 */
	public List<GoodsDeviceLift> getDeviceLiftByModuleId(int moduleId) throws Exception;
	
	/**
	 * 根据物库编码查找当前物库维护的所有电梯品牌，用于物库雷达搜索
	 * @param repositoryCode 物库编码
	 * @return List<String> 电梯品牌集合
	 * @throws Exception
	 */
	public List<String> getLiftBrandsByRepositoryCode(String repositoryCode) throws Exception;
}
