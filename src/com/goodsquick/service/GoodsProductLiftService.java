package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;

public interface GoodsProductLiftService {
	public List<GoodsProductLift> getGoodsProductLiftByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsProductLift getGoodsProductLiftById(int productLiftId) throws Exception;
	public void saveOrUpdateGoodsProductLift(GoodsProductLift goodsProductLift, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsProductLift(GoodsProductLift goodsProductLift) throws Exception;
}
