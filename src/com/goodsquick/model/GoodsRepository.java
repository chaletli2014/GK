package com.goodsquick.model;

public class GoodsRepository extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String repositoryName;
	private String repositoryCode;
	private String repositoryDesc;
	private String repositoryType;
	private String status;
	
	/**
	 * 字段指明是动产还是不动产
	 */
	private String reposCategory;
	
	public String getReposCategory() {
		return reposCategory;
	}
	public void setReposCategory(String reposCategory) {
		this.reposCategory = reposCategory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getRepositoryDesc() {
		return repositoryDesc;
	}
	public void setRepositoryDesc(String repositoryDesc) {
		this.repositoryDesc = repositoryDesc;
	}
	public String getRepositoryType() {
		return repositoryType;
	}
	public void setRepositoryType(String repositoryType) {
		this.repositoryType = repositoryType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
