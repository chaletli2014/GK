package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;

public interface SubjectAndModuleDAO {

	public List<GoodsSubject> getChildSubjectByParentId(int parentId) throws Exception;
	public List<GoodsSubject> getChildSubjectByParentCode(String parentCode) throws Exception;
	
	public List<GoodsSubject> getSubjectByLevel(String level,String repositoryCode) throws Exception;
	public List<GoodsSubject> getAllSubject(String repositoryCode) throws Exception;
	public List<GoodsSubject> getAllSubjectWithRoot(String repositoryCode) throws Exception;
	
	public GoodsSubject getSubjectInfoById(int subjectId) throws Exception;
	
	public void addSubject(CategoryJsonObj obj, String repositoryCode) throws Exception;
	public void updateSubject(CategoryJsonObj obj) throws Exception;
	public void deleteSubject(CategoryJsonObj obj) throws Exception;

	public void addSubject(GoodsSubject obj, String repositoryCode) throws Exception;
	public void updateSubject(GoodsSubject obj) throws Exception;
	
	public String getSubjectCodeByName(String subjectName) throws Exception;
	
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectId(int subjectId) throws Exception;
	public List<GoodsHouseSubjectModule> getAllSubjectModulesByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId) throws Exception;
	public void updateSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void addSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void deleteSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void deleteSubject(GoodsSubject obj) throws Exception;
	
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectIdAndModuleType(int subjectId, String moduleType) throws Exception;
}
