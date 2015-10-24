package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;

public interface CategoryDAO {

	public List<Category> getChildCategoryByParentId(int parentId) throws Exception;
	public List<Category> getChildCategoryByParentCode(String parentCode) throws Exception;
	
	public List<Category> getAllCategory() throws Exception;
	
	public Category getCategoryInfoById(int categoryId) throws Exception;
	
	public void updateCategory(CategoryJsonObj obj) throws Exception;
	public void addCategory(CategoryJsonObj obj) throws Exception;
	public void deleteCategory(CategoryJsonObj obj) throws Exception;
	
	public String getCategoryCodeByName(String categoryName) throws Exception;
}
