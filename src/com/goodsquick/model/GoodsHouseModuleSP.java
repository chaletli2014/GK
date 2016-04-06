package com.goodsquick.model;

public class GoodsHouseModuleSP extends BaseObject{

	private static final long serialVersionUID = 6061165700227655805L;
	private int id;
	private String repositoryCode;
	private String fromSource;
	private String moduleType1;
	private String moduleType2;
	private String spTypeCode;
	private String spTypeName;
	private String spName;
	private int spId;
	private String spTel;
	private String spPhone;
	private String relationStatus;
	private String status;
	private String remark;
	
	/**
	 * 产品与服务名称
	 */
	private String proServiceName;
	
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
	public String getFromSource() {
		return fromSource;
	}
	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}
	public String getSpPhone() {
		return spPhone;
	}
	public void setSpPhone(String spPhone) {
		this.spPhone = spPhone;
	}
	public String getProServiceName() {
		return proServiceName;
	}
	public void setProServiceName(String proServiceName) {
		this.proServiceName = proServiceName;
	}
}
