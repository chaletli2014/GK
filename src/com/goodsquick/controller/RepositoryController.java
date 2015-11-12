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
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.CategoryService;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.utils.GoodsCollectionUtils;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class RepositoryController {

    Logger logger = Logger.getLogger(RepositoryController.class);
    
    @Autowired
    @Qualifier("repositoryService")
    private RepositoryService repositoryService;
    
	@RequestMapping("/saveOrUpdateRepository")
	@ResponseBody
	public Map<String,Object> saveOrUpdateRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsRepository goodsRepositoryFromPage = new GoodsRepository();
			String repositoryId = request.getParameter("repositoryId");
			String repositoryName = request.getParameter("repositoryName");
			String repositoryDesc = request.getParameter("repositoryDesc");
			String repositoryType = request.getParameter("repositoryType");
			
			goodsRepositoryFromPage.setId(GoodsQuickUtils.parseIntegerFromString(repositoryId));
			goodsRepositoryFromPage.setRepositoryName(repositoryName);
			goodsRepositoryFromPage.setRepositoryDesc(repositoryDesc);
			goodsRepositoryFromPage.setRepositoryType(repositoryType);
			goodsRepositoryFromPage.setCreateUser(currentUser.getLoginName());
			goodsRepositoryFromPage.setUpdateUser(currentUser.getLoginName());
			
			List<GoodsRepository> repositoryList = repositoryService.saveOrUpdateRepository(request, goodsRepositoryFromPage);
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_LIST, repositoryList);
			resultMap.put("result", "Y");
			resultMap.put("repositoryCode", request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE));
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
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			List<GoodsRepository> repositoryList = repositoryService.getRepositoryByLoginName(currentUser.getLoginName());
		
			view.addObject("repositoryList", repositoryList);
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		view.addObject("opened", ",system,");
        view.addObject("actived", ",repository,");
		return view;
	}
}