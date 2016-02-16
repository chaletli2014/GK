package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsDeviceLift;

public interface LiftDAO {

	public List<GoodsDeviceLift> getDeviceLiftByUserCode(String userCode, String liftType) throws Exception;
	public List<GoodsDeviceLift> getDeviceLiftByRepositoryCode(String repositoryCode) throws Exception;
	public void saveDeviceLift(GoodsDeviceLift lift ) throws Exception;
	public void saveDeviceLiftQuick(GoodsDeviceLift lift ) throws Exception;
	public void updateDeviceLift(GoodsDeviceLift lift ) throws Exception;
	public void deleteDeviceLift(int ordinaryHouseId) throws Exception;
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception;
	
	public String getMaxCode() throws Exception;
}
