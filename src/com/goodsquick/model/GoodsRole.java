package com.goodsquick.model;

public class GoodsRole {

	private int id;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	
	private String isOwn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
	}
}
