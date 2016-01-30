package com.goodsquick.model;

/**
 * 主体构件
 * @author chalet
 *
 */
public class GoodsHouseSubjectModule extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456152820482411664L;
	private int id;
	private int subjectId;
	private String moduleTypeCode;
	private String moduleName;
	private String moduleCode;
	private String moduleDesc;
	
	private String moduleTypeName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getModuleTypeCode() {
		return moduleTypeCode;
	}
	public void setModuleTypeCode(String moduleTypeCode) {
		this.moduleTypeCode = moduleTypeCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getModuleTypeName() {
		return moduleTypeName;
	}
	public void setModuleTypeName(String moduleTypeName) {
		this.moduleTypeName = moduleTypeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}