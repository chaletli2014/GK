package com.goodsquick.model;

import java.util.List;

public class GoodsRolePrivs {

	private String roleCode;
	
	private List<GoodsPrivilege> privs;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<GoodsPrivilege> getPrivs() {
		return privs;
	}

	public void setPrivs(List<GoodsPrivilege> privs) {
		this.privs = privs;
	}
}
