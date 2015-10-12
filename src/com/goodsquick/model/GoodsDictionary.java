package com.goodsquick.model;

public class GoodsDictionary {

	private String id;
	private String typeName;
	private String typeCode;
	private String dicName;
	private String dicCode;
	private String dicDesc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicDesc() {
		return dicDesc;
	}
	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
}
