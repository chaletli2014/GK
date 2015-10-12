package com.goodsquick.model;

import java.util.Date;

/**
 * 电梯
 * @author chalet
 */
public class GoodsDeviceLift {

	private int id;
	
	/**
	 * 电梯编码
	 */
	private String code;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 电梯分类
	 */
	private String liftType;
	
	/**
	 * 电梯品牌
	 */
	private String brandName;
	
	/**
	 * 单位编号
	 */
	private String companyCode;
	
	/**
	 * 规格型号
	 */
	private String model;
	
	/**
	 * 主要参数
	 */
	private String mainArguments;
	
	/**
	 * 制造厂家
	 */
	private String manufacturer;
	
	/**
	 * 制造日期
	 */
	private Date madeDate;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLiftType() {
		return liftType;
	}
	public void setLiftType(String liftType) {
		this.liftType = liftType;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMainArguments() {
		return mainArguments;
	}
	public void setMainArguments(String mainArguments) {
		this.mainArguments = mainArguments;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
