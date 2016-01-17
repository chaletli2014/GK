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
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseSubjectModule;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.SubjectAndModuleService;
import com.goodsquick.utils.GoodsCollectionUtils;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class SubjectAndModuleController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("subjectAndModuleService")
	private SubjectAndModuleService subjectAndModuleService;
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;

	@RequestMapping("/houseSubjectiframe")
	public ModelAndView houseSubjectiframe(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("common/subjectListIframe");
		
		List<Category> houseSubjectList = new ArrayList<Category>();
		
		try {
			houseSubjectList = subjectAndModuleService.getChildSubjectByParentId(0);
		} catch (Exception e) {
			logger.error("fail to get the top level subjects,",e);
		}
		
		view.addObject("houseSubjectList", houseSubjectList);
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/subjectlist")
	public List<CategoryJsonObj> subjectlist(HttpServletRequest request){
		
		List<CategoryJsonObj> houseSubjectJsons = new ArrayList<CategoryJsonObj>();
		try {
			List<Category> subjectList = subjectAndModuleService.getAllSubject();
			for( Category cat : subjectList ){
				CategoryJsonObj houseSubjectJson = new CategoryJsonObj();
				houseSubjectJson.setId(cat.getId());
				houseSubjectJson.setName(cat.getName());
				houseSubjectJson.setpId(cat.getParentId());
				houseSubjectJsons.add(houseSubjectJson);
			}
			
			request.getSession().setAttribute("houseSubjectList", houseSubjectJsons);
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		
		return houseSubjectJsons;
	}
	
	@ResponseBody
	@RequestMapping("/updateSubject")
	public Map<String,String> updateSubject(HttpServletRequest request){
		String treeNodes = request.getParameter("treeNodes");
		Map<String,String> returnedMap = new HashMap<String,String>();
		
		Gson gson = new Gson();
		
		List<CategoryJsonObj> list = gson.fromJson(treeNodes, new TypeToken<List<CategoryJsonObj>>(){}.getType());
		
		List<CategoryJsonObj> existslist = (List<CategoryJsonObj>)request.getSession().getAttribute("houseSubjectList");
		
		List<CategoryJsonObj> diffSubject = null;
		try {
			diffSubject = GoodsCollectionUtils.getDifferentCategoryList(existslist, list);
			subjectAndModuleService.saveOrupdateSubject(diffSubject);
			returnedMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get different subject,",e);
		}
		
		return returnedMap;
	}

	@ResponseBody
	@RequestMapping("/subjectmodulelist")
	public Map<String,Object> subjectmodulelist(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
			List<GoodsHouseSubjectModule> subjectModuleList = subjectAndModuleService.getSubjectModulesBySubjectId(subjectId);
			resultMap.put("modules", subjectModuleList);
		} catch (Exception e) {
			logger.error("fail to get the house subject module,",e);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/modifySubjectModule")
	public Map<String,Object> modifySubjectModule(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
			String moduleType = request.getParameter("moduleType");
			String moduleName = request.getParameter("moduleName");
			String moduleDesc = request.getParameter("moduleDesc");
			
			GoodsHouseSubjectModule obj = new GoodsHouseSubjectModule();
			obj.setSubjectId(subjectId);
			obj.setModuleTypeCode(moduleType);
			obj.setModuleName(moduleName);
			obj.setModuleDesc(moduleDesc);
			obj.setCreateUser(currentUser.getLoginName());
			obj.setUpdateUser(currentUser.getLoginName());
			subjectAndModuleService.saveOrUpdateSubjectModule(obj);
			
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to modify the house subject module,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/moduletypelist")
	public Map<String,Object> moduletypelist(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			List<GoodsDictionary> moduleTypes = dictionaryService.getDictionaryByType("subjectModule");
			resultMap.put("moduleTypes", moduleTypes);
		} catch (Exception e) {
			logger.error("fail to get the house module type,",e);
		}
		
		return resultMap;
	}
	
    @RequestMapping("/getBodyDetailsByType")
    @ResponseBody
    public Map<String,Object> getBodyDetailsByType(HttpServletRequest request){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
    		String moduleType = request.getParameter("moduleType");
    		int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
    		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
    		
    		moduleList = subjectAndModuleService.getSubjectModulesBySubjectIdAndModuleType(subjectId, moduleType);
    		
    		result.put("result", "Y");
    		result.put("dataList", moduleList);
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 获取不动产服务商信息失败",e);
    	}
    	
    	return result;
    }
}
