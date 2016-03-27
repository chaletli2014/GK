package com.goodsquick.model;

/**
 * 不动产主体
 * @author chalet
 *
 */
public class GoodsSubject extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -724718416942200936L;
	private int id;
	private String name;
	private String code;
	private String desc;
	private int parentId;
	private String level;
	private String repositoryCode;
	
	private String subject1Name;
	private String subject2Name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSubject1Name() {
		return subject1Name;
	}
	public void setSubject1Name(String subject1Name) {
		this.subject1Name = subject1Name;
	}
	public String getSubject2Name() {
		return subject2Name;
	}
	public void setSubject2Name(String subject2Name) {
		this.subject2Name = subject2Name;
	}
}
