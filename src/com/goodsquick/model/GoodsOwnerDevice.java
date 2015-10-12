package com.goodsquick.model;

import java.util.Date;

/**
 * 居住物业设施设备表
 * @author chalet
 */
public class GoodsOwnerDevice {

	private int id;
	
	/**
	 * 拥有设备的主体编码，如居住物业
	 */
	private String ownerCode;
	
	/**
	 * 设备编码
	 */
	private String deviceCode;
	
	/**
	 * 指定到某一个具体的设备的编码
	 */
	private String ownerDeviceCode;
	
	/**
	 * 安装地点
	 */
	private String installationSite;
	
	/**
	 * 投用日期
	 */
	private Date useDate;
	
	/**
	 * 登记日期
	 */
	private Date loginDate;
	
	private String createUser;
	
	private Date createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getOwnerDeviceCode() {
		return ownerDeviceCode;
	}

	public void setOwnerDeviceCode(String ownerDeviceCode) {
		this.ownerDeviceCode = ownerDeviceCode;
	}

	public String getInstallationSite() {
		return installationSite;
	}

	public void setInstallationSite(String installationSite) {
		this.installationSite = installationSite;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
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
}
