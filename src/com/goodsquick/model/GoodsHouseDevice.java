package com.goodsquick.model;

import java.util.Date;

public class GoodsHouseDevice {

	private int id;
	
	/**
	 * 不动产ID
	 */
	private String orHouseId;
	
	/**
	 * 不动产编码
	 */
	private String orHouseCode;
	
	/**
	 * 设备分类编码
	 */
	private String deviceType;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 制造商名称
	 */
	private String manufacturerName;
	
	/**
	 * 品牌
	 */
	private String brandName;
	
	/**
	 * 规格型号
	 */
	private String model;
	
	/**
	 * 数量
	 */
	private int deviceNum;
	
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
	public String getOrHouseId() {
		return orHouseId;
	}
	public void setOrHouseId(String orHouseId) {
		this.orHouseId = orHouseId;
	}
	public String getOrHouseCode() {
		return orHouseCode;
	}
	public void setOrHouseCode(String orHouseCode) {
		this.orHouseCode = orHouseCode;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(int deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
