package com.goodsquick.model;


public class GoodsHousePaint extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8267467197205417938L;

	private int id;
	
	/**
	 * 材料类别1编码
	 */
	private String type1Code;
	
	/**
	 * 材料类别1名称
	 */
	private String type1Name;
	
	/**
	 * 材料类别2编码
	 */
	private String type2Code;
	
	/**
	 * 材料类别2名称
	 */
	private String type2Name;
	
	/**
	 * 名称
	 */
	private String paintName;
	
	/**
	 * 描述
	 */
	private String paintDesc;
	
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
	
	/**
	 * 材料品牌
	 */
	private String paintBrand;
	
	/**
	 * 材料型号
	 */
	private String paintModel;
	
	/**
	 * 材料类型
	 */
	private String paintStyle;
	
	/**
	 * 液态类型
	 */
	private String liquidType;
	
	/**
	 * 成膜物性质
	 */
	private String filmFormer;
	
	/**
	 * 储存器
	 */
	private String paintStorage;
	
	/**
	 * 固体含量
	 */
	private double solidProportion;
	
	/**
	 * 涂刷面积
	 */
	private double brushArea;
	
	/**
	 * 拉伸强度
	 */
	private double tensileStrength;
	
	
	/**
	 * 断裂伸长率
	 */
	private double breakElongation;
	
	/**
	 * 表干时间
	 */
	private String surfaceDryTime;
	
	/**
	 * 干燥时间
	 */
	private String dryingTime;
	
	/**
	 * 漆膜颜色
	 */
	private String coatingFilmColor;
	
	/**
	 * 产地
	 */
	private String originPlace;
	
	/**
	 * 物库编码
	 */
	private String repositoryCode;
	
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType1Code() {
		return type1Code;
	}
	public void setType1Code(String type1Code) {
		this.type1Code = type1Code;
	}
	public String getType1Name() {
		return type1Name;
	}
	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}
	public String getType2Code() {
		return type2Code;
	}
	public void setType2Code(String type2Code) {
		this.type2Code = type2Code;
	}
	public String getType2Name() {
		return type2Name;
	}
	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}
	public String getPaintName() {
		return paintName;
	}
	public void setPaintName(String paintName) {
		this.paintName = paintName;
	}
	public String getPaintDesc() {
		return paintDesc;
	}
	public void setPaintDesc(String paintDesc) {
		this.paintDesc = paintDesc;
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
	public String getPaintBrand() {
		return paintBrand;
	}
	public void setPaintBrand(String paintBrand) {
		this.paintBrand = paintBrand;
	}
	public String getPaintModel() {
		return paintModel;
	}
	public void setPaintModel(String paintModel) {
		this.paintModel = paintModel;
	}
	public String getPaintStyle() {
		return paintStyle;
	}
	public void setPaintStyle(String paintStyle) {
		this.paintStyle = paintStyle;
	}
	public String getLiquidType() {
		return liquidType;
	}
	public void setLiquidType(String liquidType) {
		this.liquidType = liquidType;
	}
	public String getFilmFormer() {
		return filmFormer;
	}
	public void setFilmFormer(String filmFormer) {
		this.filmFormer = filmFormer;
	}
	public String getPaintStorage() {
		return paintStorage;
	}
	public void setPaintStorage(String paintStorage) {
		this.paintStorage = paintStorage;
	}
	public double getSolidProportion() {
		return solidProportion;
	}
	public void setSolidProportion(double solidProportion) {
		this.solidProportion = solidProportion;
	}
	public double getBrushArea() {
		return brushArea;
	}
	public void setBrushArea(double brushArea) {
		this.brushArea = brushArea;
	}
	public double getTensileStrength() {
		return tensileStrength;
	}
	public void setTensileStrength(double tensileStrength) {
		this.tensileStrength = tensileStrength;
	}
	public double getBreakElongation() {
		return breakElongation;
	}
	public void setBreakElongation(double breakElongation) {
		this.breakElongation = breakElongation;
	}
	public String getSurfaceDryTime() {
		return surfaceDryTime;
	}
	public void setSurfaceDryTime(String surfaceDryTime) {
		this.surfaceDryTime = surfaceDryTime;
	}
	public String getDryingTime() {
		return dryingTime;
	}
	public void setDryingTime(String dryingTime) {
		this.dryingTime = dryingTime;
	}
	public String getCoatingFilmColor() {
		return coatingFilmColor;
	}
	public void setCoatingFilmColor(String coatingFilmColor) {
		this.coatingFilmColor = coatingFilmColor;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
