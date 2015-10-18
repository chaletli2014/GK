package com.goodsquick.model;

import java.util.Date;

/**
 * 服务详细信息
 * @author chalet
 */
public class GoodsServiceDetail {

	private int id;
	
	private String serviceName;
	
	/**
	 * 服务范围编码
	 */
	private String serviceRangeCode;
	
	/**
	 * 服务范围名称
	 */
	private String serviceRangeName;
	
	/**
	 * 服务价格
	 */
	private double price;
	
	/**
	 * 服务详细内容
	 */
	private String serviceContent;
	
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceRangeCode() {
		return serviceRangeCode;
	}

	public void setServiceRangeCode(String serviceRangeCode) {
		this.serviceRangeCode = serviceRangeCode;
	}

	public String getServiceRangeName() {
		return serviceRangeName;
	}

	public void setServiceRangeName(String serviceRangeName) {
		this.serviceRangeName = serviceRangeName;
	}
}
