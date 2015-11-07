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
public class RepositoryController {

    Logger logger = Logger.getLogger(RepositoryController.class);
    
	
	@RequestMapping("/addRepository")
    public ModelAndView addRepository(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("rep/addRepository");
        try {
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
        
        view.addObject("opened", ",system,");
        view.addObject("actived", ",repository,");
        return view;
    }
	
	@RequestMapping("/saveOrUpdateRepository")
	@ResponseBody
	public Map<String,Object> saveOrUpdateRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			request.getSession().setAttribute("repository_code", "a");
			resultMap.put("result", "Y");
			resultMap.put("repositoryCode", "a");
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/repositorylist")
	public ModelAndView repositorylist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("rep/repositorylist");
		
		try {
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		view.addObject("opened", ",system,");
        view.addObject("actived", ",repository,");
		return view;
	}
}