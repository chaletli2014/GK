package com.goodsquick.model;

import java.util.Date;

/**
 * 服务商客户信息
 * @author chalet
 */
public class GoodsSPCustomer {

	private int id;
	
	/**
	 * 服务商编码
	 */
	private String spCode;
	
	/**
	 * 服务分类编码
	 */
	private String categoryCode;
	
	/**
	 * 服务分类名称
	 */
	private String categoryName;
	
	/**
	 * 服务范围编码
	 */
	private String serviceTypeCode;
	
	/**
	 * 服务范围名称
	 */
	private String serviceTypeName;
	
	/**
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 客户编码
	 */
	private String customerCode;
	
	/**
	 * 当前状态
	 */
	private String status;
	
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
