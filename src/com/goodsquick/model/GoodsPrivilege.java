package com.goodsquick.model;

import java.util.Date;

public class GoodsPrivilege {

	private int id;
	private String privilegeName;
	private String privilegeCode;
	private String privilegeDesc;
	
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getPrivilegeCode() {
		return privilegeCode;
	}
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	public String getPrivilegeDesc() {
		return privilegeDesc;
	}
	public void setPrivilegeDesc(String privilegeDesc) {
		this.privilegeDesc = privilegeDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
