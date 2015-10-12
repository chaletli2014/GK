package com.goodsquick.model;

import java.util.Date;

public class GoodsRelatedRequest {

	private int id;
	
	private int spCustomerId;
	
	/**
	 * 不动产名称
	 */
	private String orHouseName;
	
	/**
	 * 服务商编码
	 */
	private String spCode;
	
	/**
	 * 服务商名称
	 */
	private String spName;
	
	/**
	 * 服务范围
	 */
	private String serviceTypeCode;
	
	/**
	 * 服务范围
	 */
	private String serviceTypeName;
	
	private String status;
	
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrHouseName() {
		return orHouseName;
	}
	public void setOrHouseName(String orHouseName) {
		this.orHouseName = orHouseName;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public int getSpCustomerId() {
		return spCustomerId;
	}
	public void setSpCustomerId(int spCustomerId) {
		this.spCustomerId = spCustomerId;
	}
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	
}
