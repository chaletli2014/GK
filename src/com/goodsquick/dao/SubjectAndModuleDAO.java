package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;

public interface SubjectAndModuleDAO {

	public List<Category> getChildSubjectByParentId(int parentId) throws Exception;
	public List<Category> getChildSubjectByParentCode(String parentCode) throws Exception;
	
	public List<Category> getAllSubject() throws Exception;
	
	public Category getSubjectInfoById(int subjectId) throws Exception;
	
	public void updateSubject(CategoryJsonObj obj) throws Exception;
	public void addSubject(CategoryJsonObj obj) throws Exception;
	public void deleteSubject(CategoryJsonObj obj) throws Exception;
	
	public String getSubjectCodeByName(String subjectName) throws Exception;
	
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectId(int subjectId) throws Exception;
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId) throws Exception;
	public void updateSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void addSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
	public void deleteSubjectModule(GoodsHouseSubjectModule obj) throws Exception;
}
