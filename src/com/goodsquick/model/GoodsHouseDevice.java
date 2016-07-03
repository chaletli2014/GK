package com.goodsquick.model;

import java.util.Date;


public class GoodsHouseDevice extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900785899561048589L;

	private int id;
	
	/**
	 * 设备类型编码
	 */
	private String eqTypeCode;
	
	/**
	 * 设备类型名称
	 */
	private String eqTypeName;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 设备品牌
	 */
	private String brand;
	
	/**
	 * 款型
	 */
	private String style;
	
	/**
	 * 描述
	 */
	private String eqDesc;
	
	/**
	 * 主体ID
	 */
	private int subjectId;
	
	/**
	 * 构件ID
	 */
	private int moduleId;
	
	/**
	 * 主体名称
	 */
	private String subjectName;
	/**
	 * 构件名称
	 */
	private String moduleName;
	
	private String status;
	
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	
	/**
	 * 速度功率
	 */
	private double velocity; 
	
	/**
	 * 设备数量
	 */
	private int num;
	
	/**
	 * 启动时间
	 */
	private Date enableTime;
	
	/**
	 * 维保单位
	 */
	private String maintenanceUnit;
	
	/**
	 * 联系电话
	 */
	private String telephone;
	
	/**
	 * 备注
	 */
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEqTypeCode() {
		return eqTypeCode;
	}
	public void setEqTypeCode(String eqTypeCode) {
		this.eqTypeCode = eqTypeCode;
	}
	public String getEqTypeName() {
		return eqTypeName;
	}
	public void setEqTypeName(String eqTypeName) {
		this.eqTypeName = eqTypeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getEqDesc() {
		return eqDesc;
	}
	public void setEqDesc(String eqDesc) {
		this.eqDesc = eqDesc;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}
	public String getMaintenanceUnit() {
		return maintenanceUnit;
	}
	public void setMaintenanceUnit(String maintenanceUnit) {
		this.maintenanceUnit = maintenanceUnit;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
