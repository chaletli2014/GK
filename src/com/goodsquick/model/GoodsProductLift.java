package com.goodsquick.model;

import java.util.Date;

/**
 * 产品-电梯
 * @author chalet
 */
public class GoodsProductLift extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145935695481144891L;

	private int id;
	
	/**
	 * 电梯编码
	 */
	private String code;
	
	/**
	 * 电梯品牌
	 */
	private String liftBrand;
	
	/**
	 * 电梯用途code
	 */
	private String liftPurpose;
	
	/**
	 * 电梯用途名称
	 */
	private String liftPurposeDesc;
	
	/**
	 * 电梯款型
	 */
	private String liftStyle;
	
	/**
	 * 电梯款型名称
	 */
	private String liftStyleDesc;
	
	/**
	 * 载重量
	 */
	private Double liftCT;
	
	/**
	 * 额定速度
	 */
	private Double liftNS;
	
	/**
	 * 质保
	 */
	private int liftQA;
	
	/**
	 * 价格
	 */
	private Double price;
	
	/** 以下为主要参数 */
	
	/**
	 * 井道尺寸（宽*深）(mm)
	 */
	private Double holeSize;
	/**
	 * 底坑深度(mm)
	 */
	private int pitDepth;
	/**
	 * 顶层高度(mm)
	 */
	private int overheadHeight;
	/**
	 * 门洞预留（宽*高）(mm)
	 */
	private String reservation;
	/**
	 * 机房尺寸（宽*深）(mm)
	 */
	private String roomSize;
	/**
	 * 机房净高(mm)
	 */
	private int roomHeight;
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
	private int mainPower;
	
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	
	/**
	 * 制造日期
	 */
	private Date madeDate;
	
	private String repositoryCode;
	
	private String status;
	
	private String remark;
	
	
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
	public String getLiftBrand() {
		return liftBrand;
	}
	public void setLiftBrand(String liftBrand) {
		this.liftBrand = liftBrand;
	}
	public String getLiftPurpose() {
		return liftPurpose;
	}
	public void setLiftPurpose(String liftPurpose) {
		this.liftPurpose = liftPurpose;
	}
	public String getLiftStyle() {
		return liftStyle;
	}
	public void setLiftStyle(String liftStyle) {
		this.liftStyle = liftStyle;
	}
	public Double getLiftCT() {
		return liftCT;
	}
	public void setLiftCT(Double liftCT) {
		this.liftCT = liftCT;
	}
	public Double getLiftNS() {
		return liftNS;
	}
	public void setLiftNS(Double liftNS) {
		this.liftNS = liftNS;
	}
	public int getLiftQA() {
		return liftQA;
	}
	public void setLiftQA(int liftQA) {
		this.liftQA = liftQA;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getHoleSize() {
		return holeSize;
	}
	public void setHoleSize(Double holeSize) {
		this.holeSize = holeSize;
	}
	public int getPitDepth() {
		return pitDepth;
	}
	public void setPitDepth(int pitDepth) {
		this.pitDepth = pitDepth;
	}
	public int getOverheadHeight() {
		return overheadHeight;
	}
	public void setOverheadHeight(int overheadHeight) {
		this.overheadHeight = overheadHeight;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public int getRoomHeight() {
		return roomHeight;
	}
	public void setRoomHeight(int roomHeight) {
		this.roomHeight = roomHeight;
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
	public int getMainPower() {
		return mainPower;
	}
	public void setMainPower(int mainPower) {
		this.mainPower = mainPower;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getLiftPurposeDesc() {
		return liftPurposeDesc;
	}
	public void setLiftPurposeDesc(String liftPurposeDesc) {
		this.liftPurposeDesc = liftPurposeDesc;
	}
	public String getLiftStyleDesc() {
		return liftStyleDesc;
	}
	public void setLiftStyleDesc(String liftStyleDesc) {
		this.liftStyleDesc = liftStyleDesc;
	}
	
}
