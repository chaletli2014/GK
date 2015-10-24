package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;

public interface CategoryService {

	public List<Category> getChildCategoryByParentId(int parentId) throws Exception;
	public List<Category> getChildCategoryByParentCode(String parentCode) throws Exception;
	
	public List<Category> getAllCategory() throws Exception;
	
	public Category getCategoryInfoById(int categoryId) throws Exception;
	
	public void saveOrupdateCategory(List<CategoryJsonObj> categorys) throws Exception;
	
	public String getCategoryCodeByName(String categoryName) throws Exception;
}
