package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsCompanyInfo;

public interface GoodsRadarDAO {
	public List<GoodsCompanyInfo> getCompanyInfoByLiftBrands(List<String> liftBrands) throws Exception;
}
