package com.goodsquick.model;

public class GoodsHouseModuleSP extends BaseObject{

	private static final long serialVersionUID = 6061165700227655805L;
	private int id;
	private String repositoryCode;
	private String houseCode;
	private String partCode;
	private String brandCode;
	private String brandName;
	private String moduleType1;
	private String moduleType2;
	private String spTypeCode;
	private String spTypeName;
	private String spName;
	private int spId;
	private String spTel;
	private String relationStatus;
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
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getSpTypeCode() {
		return spTypeCode;
	}
	public void setSpTypeCode(String spTypeCode) {
		this.spTypeCode = spTypeCode;
	}
	public String getSpTypeName() {
		return spTypeName;
	}
	public void setSpTypeName(String spTypeName) {
		this.spTypeName = spTypeName;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getSpTel() {
		return spTel;
	}
	public void setSpTel(String spTel) {
		this.spTel = spTel;
	}
	public String getRelationStatus() {
		return relationStatus;
	}
	public void setRelationStatus(String relationStatus) {
		this.relationStatus = relationStatus;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModuleType1() {
		return moduleType1;
	}
	public void setModuleType1(String moduleType1) {
		this.moduleType1 = moduleType1;
	}
	public String getModuleType2() {
		return moduleType2;
	}
	public void setModuleType2(String moduleType2) {
		this.moduleType2 = moduleType2;
	}
}
