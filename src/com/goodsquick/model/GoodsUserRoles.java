package com.goodsquick.model;

import java.util.List;

public class GoodsUserRoles {

	private String userCode;
	private List<GoodsRole> roles;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List<GoodsRole> getRoles() {
		return roles;
	}
	public void setRoles(List<GoodsRole> roles) {
		this.roles = roles;
	}
}
