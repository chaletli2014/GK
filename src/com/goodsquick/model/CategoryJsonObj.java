package com.goodsquick.model;

public class CategoryJsonObj {

	private String id;
	private String name;
	private String pId;
	
	/**
	 * save or update or delete
	 * "s" or "u" or "d"
	 */
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
