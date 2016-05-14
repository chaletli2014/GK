package com.goodsquick.model;

import java.util.Date;

/**
 * 实物类产品库明细
 * @author chalet
 */
public class GoodsProductObj extends BaseObject{

	private static final long serialVersionUID = -2945594335082827661L;
	
	private int id;
	private String productType;
	private String productTypeDesc;
	private String productName;
	private String productNameDesc;
	private double productPrice;
	private String repositoryCode;
	private String status;
	private String remark;
	
	private String productBrand;
	private String productModel;
	private String itemCode;
	private Date productDom;
	private int productQA;
	
	private long mainPicId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
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
	public String getProductTypeDesc() {
		return productTypeDesc;
	}
	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}
	public String getProductNameDesc() {
		return productNameDesc;
	}
	public void setProductNameDesc(String productNameDesc) {
		this.productNameDesc = productNameDesc;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Date getProductDom() {
		return productDom;
	}
	public void setProductDom(Date productDom) {
		this.productDom = productDom;
	}
	public int getProductQA() {
		return productQA;
	}
	public void setProductQA(int productQA) {
		this.productQA = productQA;
	}
	public long getMainPicId() {
		return mainPicId;
	}
	public void setMainPicId(long mainPicId) {
		this.mainPicId = mainPicId;
	}
}
