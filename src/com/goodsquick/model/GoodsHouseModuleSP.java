package com.goodsquick.model;

public class GoodsHouseModuleSP extends BaseObject{

	private static final long serialVersionUID = 6061165700227655805L;
	private int id;
	private String houseCode;
	private String moduleType;
	private String moduleSPName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleSPName() {
		return moduleSPName;
	}
	public void setModuleSPName(String moduleSPName) {
		this.moduleSPName = moduleSPName;
	}
	
}
