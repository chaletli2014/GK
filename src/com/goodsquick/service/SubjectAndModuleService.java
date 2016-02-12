package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;

public interface SubjectAndModuleService {

	public List<GoodsSubject> getChildSubjectByParentId(int parentId) throws Exception;
	public List<GoodsSubject> getChildSubjectByParentCode(String parentCode) throws Exception;
	
	public List<GoodsSubject> getSubjectByLevel(String level, String repositoryCode) throws Exception;
	public List<GoodsSubject> getAllSubject(String repositoryCode) throws Exception;
	public List<GoodsSubject> getAllSubjectWithRoot(String repositoryCode) throws Exception;
	
	public GoodsSubject getSubjectInfoById(int subjectId) throws Exception;
	
	public void saveOrupdateSubject(List<CategoryJsonObj> subjects, String repositoryCode) throws Exception;
	public void saveOrUpdateSubject(GoodsSubject goodsSubject, String repositoryCode) throws Exception;
	public void saveOrUpdateSubject(List<GoodsSubject> goodsSubjects, String currentUser, String repositoryCode) throws Exception;
	
	public String getSubjectCodeByName(String subjectName) throws Exception;
	
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectId(int subjectId,String repositoryCode) throws Exception;
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectIdAndModuleType(int subjectId, String moduleType) throws Exception;
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId) throws Exception;
	public void saveOrUpdateSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void deleteSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
}
