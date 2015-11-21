package com.goodsquick.model;

/**
 * 二级服务商
 */
public class GoodsHouseSP2nd extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061165700227655805L;
	private int id;
	/**
	 * 不动产编码
	 */
	private String houseCode;
	/**
	 * 二级服务商分类，如二级托管人
	 */
	private String spType;
	/**
	 * 托管组件类别，如电梯
	 */
	private String moduleType;
	/**
	 * 托管人名称
	 */
	private String spName;
	/**
	 * 状态
	 */
	private String status;
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
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getSpType() {
		return spType;
	}
	public void setSpType(String spType) {
		this.spType = spType;
	}
	
}
