package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.service.CategoryService;
import com.goodsquick.utils.GoodsCollectionUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class CategoryController {

    Logger logger = Logger.getLogger(CategoryController.class);
    
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	@RequestMapping("/category")
    public ModelAndView category(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("sys/category");
        
        List<Category> categoryList = new ArrayList<Category>();
        
        try {
        	categoryList = categoryService.getChildCategoryByParentId(0);
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
        
        view.addObject("opened", ",system,");
        view.addObject("actived", ",category,");
        view.addObject("categoryList", categoryList);
        return view;
    }
	
	@RequestMapping("/categoryiframe")
	public ModelAndView categoryiframe(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/categoryiframe");
		
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			categoryList = categoryService.getChildCategoryByParentId(0);
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		
		view.addObject("categoryList", categoryList);
		return view;
	}
	
	@RequestMapping("/updateCategory")
	public String updateCategory(HttpServletRequest request){
		String treeNodes = request.getParameter("treeNodes");
		
		Gson gson = new Gson();
		
		List<CategoryJsonObj> list = gson.fromJson(treeNodes, new TypeToken<List<CategoryJsonObj>>(){}.getType());
		
		List<CategoryJsonObj> existslist = (List<CategoryJsonObj>)request.getSession().getAttribute("categoryList");
		
		List<CategoryJsonObj> diffCategory = null;
		try {
			diffCategory = GoodsCollectionUtils.getDifferentCategoryList(existslist, list);
			categoryService.saveOrupdateCategory(diffCategory);
		} catch (Exception e) {
			logger.error("fail to get different category,",e);
		}
		
		return "redirect:category";
	}
	
	@ResponseBody
	@RequestMapping("/getcategorybyid")
	public Map<String, Category> getcategorybyid(HttpServletRequest request){
		Map<String, Category> categoryMap = new HashMap<String, Category>();
		
		Category category = new Category();
		category.setId("01");
		category.setName(request.getParameter("categoryId"));
		category.setCode("dc");
		category.setDesc("该分类是第一大分类");
		category.setParentId("0");
		
		categoryMap.put("category", category);
		return categoryMap;
	}
	
	@ResponseBody
	@RequestMapping("/getcategorybypid")
	public Map<String, List<Category>> getcategorybypid(HttpServletRequest request){
		Map<String, List<Category>> categoryMap = new HashMap<String, List<Category>>();
		
		List<Category> childCategory = new ArrayList<Category>();
		try {
			int parentId = Integer.parseInt(request.getParameter("pid"));
			childCategory = categoryService.getChildCategoryByParentId(parentId);
		} catch (Exception e) {
			logger.error("fail to get the child category,",e);
		}
		
		categoryMap.put("categoryList", childCategory);
		
		return categoryMap;
	}
	
	@ResponseBody
	@RequestMapping("/getcategorybypcode")
	public Map<String, List<Category>> getcategorybypcode(HttpServletRequest request){
		Map<String, List<Category>> categoryMap = new HashMap<String, List<Category>>();
		
		List<Category> childCategory = new ArrayList<Category>();
		try {
			String parentCode = request.getParameter("parentCode");
			childCategory = categoryService.getChildCategoryByParentCode(parentCode);
		} catch (Exception e) {
			logger.error("fail to get the child category,",e);
		}
		
		categoryMap.put("categoryList", childCategory);
		
		return categoryMap;
	}
	
	@ResponseBody
	@RequestMapping("/categorylist")
	public List<CategoryJsonObj> categorylist(HttpServletRequest request){
		
		List<CategoryJsonObj> categoryJsons = new ArrayList<CategoryJsonObj>();
		try {
			List<Category> categoryList = categoryService.getAllCategory();
			for( Category cat : categoryList ){
				CategoryJsonObj cateJson = new CategoryJsonObj();
				cateJson.setId(cat.getId());
				cateJson.setName(cat.getName());
				cateJson.setpId(cat.getParentId());
				categoryJsons.add(cateJson);
			}
			
			request.getSession().setAttribute("categoryList", categoryJsons);
		} catch (Exception e) {
			logger.error("fail to get the child category,",e);
		}
		
		return categoryJsons;
	}
}
