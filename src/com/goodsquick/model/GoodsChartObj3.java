package com.goodsquick.model;

import java.util.List;

/**
 * String xName 
 * List<GoodsChartObj4> yValue
 * @author chalet
 *
 */
public class GoodsChartObj3 {

	private String xName;
	private List<GoodsChartObj4> yValue;
	
	public String getxName() {
		return xName;
	}
	public void setxName(String xName) {
		this.xName = xName;
	}
	public List<GoodsChartObj4> getyValue() {
		return yValue;
	}
	public void setyValue(List<GoodsChartObj4> yValue) {
		this.yValue = yValue;
	}
}
