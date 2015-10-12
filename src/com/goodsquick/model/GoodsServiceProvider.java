package com.goodsquick.model;

import java.util.Date;

/**
 * 服务商信息
 * @author chalet
 */
public class GoodsServiceProvider {

	private int id;
	
	/**
	 * 服务商名称
	 */
	private String name;
	
	/**
	 * 服务商编码
	 */
	private String code;
	
	/**
	 * 服务商地址
	 */
	private String address;
	
	/**
	 * 服务商电话
	 */
	private String telephone;
	
	/**
	 * 服务商座机号码
	 */
	private String contractNumber;
	
	/**
	 * 服务内容
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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
}
