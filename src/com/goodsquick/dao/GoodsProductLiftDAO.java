package com.goodsquick.dao;

import java.util.List;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;

public interface GoodsProductLiftDAO {
	public List<GoodsProductLift> getGoodsProductLiftByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsProductLift getGoodsProductLiftById(int productId) throws Exception;
	public void saveGoodsProductLift(GoodsProductLift goodsProductLift, WebUserInfo currentUser) throws Exception;
	public void updateGoodsProductLift(GoodsProductLift goodsProductLift, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsProductLift(GoodsProductLift goodsProductLift) throws Exception;
}
