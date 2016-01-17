package com.goodsquick.model;

/**
 * 服务类产品库明细
 * @author chalet
 */
public class GoodsProduct extends BaseObject{

	/**
	 * 
	 */
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
}
