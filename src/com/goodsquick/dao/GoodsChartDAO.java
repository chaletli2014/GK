package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsChartObj2;

public interface GoodsChartDAO {

	/**
	 * 获取不动产的竣工年代分布
	 * @return
	 * @throws Exception
	 */
	public List<GoodsChartObj1> getHouseVolumePerYear() throws Exception;
	
	public List<GoodsChartObj1> getliftArea() throws Exception;
	
	public List<GoodsChartObj1> getliftBrandDetail(String cities) throws Exception;
	
	public List<GoodsChartObj2> getliftSalesDetail() throws Exception;
}
