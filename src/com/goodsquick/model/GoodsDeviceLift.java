package com.goodsquick.model;

import java.util.Date;

/**
 * 电梯
 * @author chalet
 */
public class GoodsDeviceLift extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8768221187952118660L;

	private int id;
	
	/**
	 * 电梯分类
	 */
	private String liftType;
	
	/**
	 * 电梯分类名称
	 */
	private String liftTypeName;
	
	/**
	 * 电梯编码
	 */
	private String liftCode;
	
	/**
	 * 设备名称
	 */
	private String liftName;
	
	/**
	 * 设备描述
	 */
	private String liftDesc;
	
	/**
	 * 所属主体
	 */
	private int subjectId;
	/**
	 * 所属主体名称
	 */
	private String subjectName;
	
	/**
	 * 所属构件
	 */
	private int moduleId;
	
	/**
	 * 所属构件名称
	 */
	private String moduleName;
	
	/**
	 * 交付日期
	 */
	private Date deliveryDate;
	
	/**
	 * 采购价格
	 */
	private double purchasePrice;
	
	/**
	 * 使用人
	 */
	private String userName;
	
	/**
	 * 电梯品牌编码
	 */
	private String brandCode;
	
	/**
	 * 电梯品牌名称
	 */
	private String brandName;
	
	/**
	 * 电梯用途编码
	 */
	private String liftPurpose;
	
	/**
	 * 电梯用途名称
	 */
	private String liftPurposeName;
	
	/**
	 * 电梯款型编码
	 */
	private String liftStyle;
	
	/**
	 * 电梯款型名称
	 */
	private String liftStyleName;
	
	/**
	 * 载重量
	 */
	private double liftCT;
	
	/**
	 * 额定速度
	 */
	private double liftNS;
	
	/**
	 * 保修期限
	 */
	private String liftQA;
	
	/**
	 * 保修期限名称
	 */
	private String liftQAName;
	
	/**
	 * 轿厢尺寸（宽*深）(mm)
	 */
	private String carSize;
	
	/**
	 * 轿厢净高(mm)
	 */
	private int carHeight;
	
	/**
	 * 开门尺寸（宽*高）(mm)
	 */
	private String doorSize;
	
	/**
	 * 主机功率(kw)
	 */
	private String mainPower;
	
	/**
	 * 制造日期
	 */
	private Date madeDate;
	
	/**
	 * 使用期限
	 */
	private String lifeTime;
	
	/**
	 * 使用期限名称
	 */
	private String lifeTimeName;
	
	private String status;
	
	/**
	 * 物库编码
	 */
	private String repositoryCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLiftType() {
		return liftType;
	}
	public void setLiftType(String liftType) {
		this.liftType = liftType;
	}
	public String getLiftTypeName() {
		return liftTypeName;
	}
	public void setLiftTypeName(String liftTypeName) {
		this.liftTypeName = liftTypeName;
	}
	public String getLiftCode() {
		return liftCode;
	}
	public void setLiftCode(String liftCode) {
		this.liftCode = liftCode;
	}
	public String getLiftName() {
		return liftName;
	}
	public void setLiftName(String liftName) {
		this.liftName = liftName;
	}
	public String getLiftDesc() {
		return liftDesc;
	}
	public void setLiftDesc(String liftDesc) {
		this.liftDesc = liftDesc;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getLiftPurpose() {
		return liftPurpose;
	}
	public void setLiftPurpose(String liftPurpose) {
		this.liftPurpose = liftPurpose;
	}
	public String getLiftPurposeName() {
		return liftPurposeName;
	}
	public void setLiftPurposeName(String liftPurposeName) {
		this.liftPurposeName = liftPurposeName;
	}
	public String getLiftStyle() {
		return liftStyle;
	}
	public void setLiftStyle(String liftStyle) {
		this.liftStyle = liftStyle;
	}
	public String getLiftStyleName() {
		return liftStyleName;
	}
	public void setLiftStyleName(String liftStyleName) {
		this.liftStyleName = liftStyleName;
	}
	public double getLiftCT() {
		return liftCT;
	}
	public void setLiftCT(double liftCT) {
		this.liftCT = liftCT;
	}
	public double getLiftNS() {
		return liftNS;
	}
	public void setLiftNS(double liftNS) {
		this.liftNS = liftNS;
	}
	public String getLiftQA() {
		return liftQA;
	}
	public void setLiftQA(String liftQA) {
		this.liftQA = liftQA;
	}
	public String getLiftQAName() {
		return liftQAName;
	}
	public void setLiftQAName(String liftQAName) {
		this.liftQAName = liftQAName;
	}
	public String getCarSize() {
		return carSize;
	}
	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}
	public int getCarHeight() {
		return carHeight;
	}
	public void setCarHeight(int carHeight) {
		this.carHeight = carHeight;
	}
	public String getDoorSize() {
		return doorSize;
	}
	public void setDoorSize(String doorSize) {
		this.doorSize = doorSize;
	}
	public String getMainPower() {
		return mainPower;
	}
	public void setMainPower(String mainPower) {
		this.mainPower = mainPower;
	}
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	public String getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}
	public String getLifeTimeName() {
		return lifeTimeName;
	}
	public void setLifeTimeName(String lifeTimeName) {
		this.lifeTimeName = lifeTimeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	
}
