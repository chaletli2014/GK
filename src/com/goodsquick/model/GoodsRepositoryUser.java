package com.goodsquick.model;

public class GoodsRepositoryUser extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String repositoryCode;
	private String userCode;
	private String priv;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPriv() {
		return priv;
	}
	public void setPriv(String priv) {
		this.priv = priv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
