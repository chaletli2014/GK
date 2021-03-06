package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.model.WebUserInfo;

public interface LiftService {

	public List<GoodsDeviceLift> getDeviceLiftByUserCode(String userCode, String liftType) throws Exception;
	public List<GoodsDeviceLift> getDeviceLiftByRepositoryCode(String repositoryCode) throws Exception;
	public void saveOrUpdateDeviceLift(GoodsDeviceLift lift, WebUserInfo currentUser) throws Exception;
	public void deleteDeviceLift(int ordinaryHouseId) throws Exception;
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception;
	
	public List<String> getLiftBrandsByRepositoryCode(String repositoryCode) throws Exception;
}
