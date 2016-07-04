package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsChartObj1;


public interface GoodsDataService {
	public List<GoodsChartObj1> getAssetYearData(String userCode);
	public List<GoodsChartObj1> getLiftYearData(String userCode);
	public List<GoodsChartObj1> getDensityData(String userCode, String densityType);
}
