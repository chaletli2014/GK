package com.goodsquick.model;

public class GoodsHouseSP extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061165700227655805L;
	private int id;
	private String houseCode;
	private String moduleSPType;
	private String moduleSPValue;
	private String status;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getModuleSPType() {
		return moduleSPType;
	}
	public void setModuleSPType(String moduleSPType) {
		this.moduleSPType = moduleSPType;
	}
	public String getModuleSPValue() {
		return moduleSPValue;
	}
	public void setModuleSPValue(String moduleSPValue) {
		this.moduleSPValue = moduleSPValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
