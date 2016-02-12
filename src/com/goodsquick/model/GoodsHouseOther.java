package com.goodsquick.model;


public class GoodsHouseOther extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3900785899561048589L;

	private int id;
	
	/**
	 * 材料装饰类型编码
	 */
	private String typeCode;
	
	/**
	 * 材料装饰类型名称
	 */
	private String typeName;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 主体ID
	 */
	private int subjectId;
	
	/**
	 * 构件ID
	 */
	private int moduleId;
	
	/**
	 * 主体名称
	 */
	private String subjectName;
	/**
	 * 构件名称
	 */
	private String moduleName;
	
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
