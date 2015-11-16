package com.goodsquick.model;

import java.util.Date;
import java.util.List;

public class GoodsOrdinaryHouse {

	private int id;
	
	/**
	 * 编码
	 */
	private String houseCode;
	
	/**
	 * 项目名称
	 */
	private String buildingName;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 目前状况
	 */
	private String buildingStatus;
	
	/**
	 * 开发公司
	 */
	private String company;
	
	/**
	 * 主要联系人
	 */
	private String contacterName;
	
	/**
	 * 职务
	 */
	private String contacterPosition;
	
	/**
	 * 联系电话
	 */
	private String contacterTelephone;
	
	/**
	 * 物业名称
	 */
	private String propertyName;
	
	/**
	 * 坐落位置
	 */
	private String location;
	
	/**
	 * 项目方位 - 东至
	 */
	private String projectPositionE;
	
	/**
	 * 项目方位 - 西至
	 */
	private String projectPositionW;
	
	/**
	 * 项目方位 - 南至
	 */
	private String projectPositionS;
	
	/**
	 * 项目方位 - 北至
	 */
	private String projectPositionN;
	
	/**
	 * 物业类型
	 */
	private String propertyType;
	
	/**
	 * 物业类型 - 其他
	 */
	private String propertytypeO;
	
	/**
	 * 竣工时间
	 */
	private int finishYear;
	private int finishMonth;
	private int finishDate;
	
	/**
	 * 开盘时间
	 */
	private int startYear;
	private int startMonth;
	private int startDate;
	
	/**
	 * 入住时间
	 */
	private int checkinYear;
	private int checkinMonth;
	private int checkinDate;
	
	/**
	 * 占地面积
	 */
	private double floorSpace;
	
	/**
	 * 建筑总栋数
	 */
	private int buildingNumber;
	
	/**
	 * 有电梯栋数
	 */
	private int hasLiftNumber;
	
	/**
	 * 无电梯栋数
	 */
	private int nonLiftNumber;
	
	/**
	 * 大堂数量
	 */
	private int lobbyNumber;
	
	/**
	 * 电梯大堂个数
	 */
	private int liftLobbyNumber;
	
	/**
	 * 无电梯大堂个数
	 */
	private int nonLiftLobbyNumber;
	
	/**
	 * 业主总户数
	 */
	private int ownerHouseholds;
	
	/**
	 * 租户总户数
	 */
	private int tenantHouseholds;
	
	/**
	 * 已经交房户数
	 */
	private int deliveryHouseholds;
	
	/**
	 * 未交房户数
	 */
	private int nonDeliveryHouseholds;
	
	/**
	 * 总建筑面积
	 */
	private int coveredArea;
	
	/**
	 * 开发期数
	 */
	private int period;
	
	/**
	 * 周界长度
	 */
	private int westEastLength;
	private int southNorthLength;
	
	/**
	 * 规划出入口数量
	 */
	private int planSidewayNum;
	private int planCarwayNum;
	
	/**
	 * 实际使用出入口数量
	 */
	private int actualSidewayNum;
	private int actualCarwayNum;
	
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private Date lastLoginTime;
	
	private List<GoodsHouseDevice> houseDevices;
	
	private String repositoryCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBuildingStatus() {
		return buildingStatus;
	}
	public void setBuildingStatus(String buildingStatus) {
		this.buildingStatus = buildingStatus;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContacterName() {
		return contacterName;
	}
	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}
	public String getContacterPosition() {
		return contacterPosition;
	}
	public void setContacterPosition(String contacterPosition) {
		this.contacterPosition = contacterPosition;
	}
	public String getContacterTelephone() {
		return contacterTelephone;
	}
	public void setContacterTelephone(String contacterTelephone) {
		this.contacterTelephone = contacterTelephone;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProjectPositionE() {
		return projectPositionE;
	}
	public void setProjectPositionE(String projectPositionE) {
		this.projectPositionE = projectPositionE;
	}
	public String getProjectPositionW() {
		return projectPositionW;
	}
	public void setProjectPositionW(String projectPositionW) {
		this.projectPositionW = projectPositionW;
	}
	public String getProjectPositionS() {
		return projectPositionS;
	}
	public void setProjectPositionS(String projectPositionS) {
		this.projectPositionS = projectPositionS;
	}
	public String getProjectPositionN() {
		return projectPositionN;
	}
	public void setProjectPositionN(String projectPositionN) {
		this.projectPositionN = projectPositionN;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertytypeO() {
		return propertytypeO;
	}
	public void setPropertytypeO(String propertytypeO) {
		this.propertytypeO = propertytypeO;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getCheckinYear() {
		return checkinYear;
	}
	public void setCheckinYear(int checkinYear) {
		this.checkinYear = checkinYear;
	}
	public int getCheckinMonth() {
		return checkinMonth;
	}
	public void setCheckinMonth(int checkinMonth) {
		this.checkinMonth = checkinMonth;
	}
	public int getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(int checkinDate) {
		this.checkinDate = checkinDate;
	}
	public double getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(double floorSpace) {
		this.floorSpace = floorSpace;
	}
	public int getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public int getHasLiftNumber() {
		return hasLiftNumber;
	}
	public void setHasLiftNumber(int hasLiftNumber) {
		this.hasLiftNumber = hasLiftNumber;
	}
	public int getNonLiftNumber() {
		return nonLiftNumber;
	}
	public void setNonLiftNumber(int nonLiftNumber) {
		this.nonLiftNumber = nonLiftNumber;
	}
	public int getLobbyNumber() {
		return lobbyNumber;
	}
	public void setLobbyNumber(int lobbyNumber) {
		this.lobbyNumber = lobbyNumber;
	}
	public int getLiftLobbyNumber() {
		return liftLobbyNumber;
	}
	public void setLiftLobbyNumber(int liftLobbyNumber) {
		this.liftLobbyNumber = liftLobbyNumber;
	}
	public int getNonLiftLobbyNumber() {
		return nonLiftLobbyNumber;
	}
	public void setNonLiftLobbyNumber(int nonLiftLobbyNumber) {
		this.nonLiftLobbyNumber = nonLiftLobbyNumber;
	}
	public int getOwnerHouseholds() {
		return ownerHouseholds;
	}
	public void setOwnerHouseholds(int ownerHouseholds) {
		this.ownerHouseholds = ownerHouseholds;
	}
	public int getTenantHouseholds() {
		return tenantHouseholds;
	}
	public void setTenantHouseholds(int tenantHouseholds) {
		this.tenantHouseholds = tenantHouseholds;
	}
	public int getDeliveryHouseholds() {
		return deliveryHouseholds;
	}
	public void setDeliveryHouseholds(int deliveryHouseholds) {
		this.deliveryHouseholds = deliveryHouseholds;
	}
	public int getNonDeliveryHouseholds() {
		return nonDeliveryHouseholds;
	}
	public void setNonDeliveryHouseholds(int nonDeliveryHouseholds) {
		this.nonDeliveryHouseholds = nonDeliveryHouseholds;
	}
	public int getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(int coveredArea) {
		this.coveredArea = coveredArea;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getWestEastLength() {
		return westEastLength;
	}
	public void setWestEastLength(int westEastLength) {
		this.westEastLength = westEastLength;
	}
	public int getSouthNorthLength() {
		return southNorthLength;
	}
	public void setSouthNorthLength(int southNorthLength) {
		this.southNorthLength = southNorthLength;
	}
	public int getPlanSidewayNum() {
		return planSidewayNum;
	}
	public void setPlanSidewayNum(int planSidewayNum) {
		this.planSidewayNum = planSidewayNum;
	}
	public int getPlanCarwayNum() {
		return planCarwayNum;
	}
	public void setPlanCarwayNum(int planCarwayNum) {
		this.planCarwayNum = planCarwayNum;
	}
	public int getActualSidewayNum() {
		return actualSidewayNum;
	}
	public void setActualSidewayNum(int actualSidewayNum) {
		this.actualSidewayNum = actualSidewayNum;
	}
	public int getActualCarwayNum() {
		return actualCarwayNum;
	}
	public void setActualCarwayNum(int actualCarwayNum) {
		this.actualCarwayNum = actualCarwayNum;
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
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public List<GoodsHouseDevice> getHouseDevices() {
		return houseDevices;
	}
	public void setHouseDevices(List<GoodsHouseDevice> houseDevices) {
		this.houseDevices = houseDevices;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public int getFinishYear() {
		return finishYear;
	}
	public void setFinishYear(int finishYear) {
		this.finishYear = finishYear;
	}
	public int getFinishMonth() {
		return finishMonth;
	}
	public void setFinishMonth(int finishMonth) {
		this.finishMonth = finishMonth;
	}
	public int getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(int finishDate) {
		this.finishDate = finishDate;
	}
	
}
