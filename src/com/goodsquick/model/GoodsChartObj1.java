package com.goodsquick.model;

/**
 * xName yCategory yValue
 * @author chalet
 *
 */
public class GoodsChartObj1 implements Comparable{
	
	private String xName;
	private int yValue;
	private double yValue2;
	
	public String getxName() {
		return xName;
	}
	public void setxName(String xName) {
		this.xName = xName;
	}
	public int getyValue() {
		return yValue;
	}
	public void setyValue(int yValue) {
		this.yValue = yValue;
	}
	public double getyValue2() {
		return yValue2;
	}
	public void setyValue2(double yValue2) {
		this.yValue2 = yValue2;
	}
	@Override
	public int compareTo(Object o) {
		return this.xName.compareTo(((GoodsChartObj1)o).getxName());
	}
}
