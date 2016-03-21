package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.SubjectAndModuleService;
import com.goodsquick.utils.GoodsCollectionUtils;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
		
		List<GoodsSubject> houseSubjectList = new ArrayList<GoodsSubject>();
		
		try {
			houseSubjectList = subjectAndModuleService.getChildSubjectByParentId(0);
		} catch (Exception e) {
			logger.error("fail to get the top level subjects,",e);
		}
		
		view.addObject("houseSubjectList", houseSubjectList);
		return view;
	}
	

	@RequestMapping("/subjectView")
	public ModelAndView subjectView(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("subjectModule/subjectView");
		view.addObject("opened", ",productManagement,subjectModule,");
		view.addObject("actived", ",subjectView,");
		return view;
	}
	

	@ResponseBody
	@RequestMapping("/subjectViewList")
	public List<CategoryJsonObj> subjectViewList(HttpServletRequest request){
		List<CategoryJsonObj> subjectView = new ArrayList<CategoryJsonObj>();
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		try{
			List<GoodsSubject> subjectList = subjectAndModuleService.getAllSubject(repositoryCode);
			for( GoodsSubject sub : subjectList ){
				CategoryJsonObj cateJson = new CategoryJsonObj();
				cateJson.setId(String.valueOf(sub.getId()));
				cateJson.setName(sub.getName());
				cateJson.setpId(String.valueOf(sub.getParentId()));
				cateJson.setLevel(sub.getLevel());
				subjectView.add(cateJson);
			}
		}catch(Exception e){
			logger.error("fail to get subject view list", e);
		}
		return subjectView;
	}
	
	@RequestMapping("/subjectList")
	public ModelAndView subjectList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("subjectModule/subjectList");
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		try {
			String level = request.getParameter("level");
			if( StringUtils.isNotBlank(level) ){
				request.getSession().setAttribute("subjectLevel", level);
			}else{
				level = (String)request.getSession().getAttribute("subjectLevel");
			}
			List<GoodsSubject> subjectList = subjectAndModuleService.getSubjectByLevel(level, repositoryCode);
			List<GoodsDictionary> moduleTypes = dictionaryService.getDictionaryByType("subjectModule");
			
			view.addObject("moduleTypes", moduleTypes);
			view.addObject("subjectList", subjectList);
			view.addObject("level", level);
			
			view.addObject("opened", ",productManagement,subjectModule,");
			view.addObject("actived", ",subject"+level+",");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		
		return view;
	}
	
	@RequestMapping("/deleteSubjectById")
	public String deleteSubjectById(HttpServletRequest request){
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		String subjectId = request.getParameter("subjectId");
		GoodsSubject obj = new GoodsSubject();
		obj.setId(GoodsQuickUtils.parseIntegerFromString(subjectId));
		obj.setUpdateUser(currentUser.getLoginName());
		try {
			subjectAndModuleService.deleteSubject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:subjectList";
	}
	
	@ResponseBody
	@RequestMapping("/getSubjectList")
	public Map<String,Object> getSubjectList(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			List<GoodsSubject> subjectList = subjectAndModuleService.getAllSubject(repositoryCode);
			resultMap.put("subjectList", subjectList);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
			resultMap.put("result", "N");
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/getChildSubjectByParentId")
	public Map<String,Object> getChildSubjectByParentId(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			String parentId = request.getParameter("parentId");
			List<GoodsSubject> subjectList = subjectAndModuleService.getChildSubjectByParentId(GoodsQuickUtils.parseIntegerFromString(parentId));
			resultMap.put("subjectList", subjectList);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the house subject by parent id,",e);
			resultMap.put("result", "N");
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/updateSubject")
	public Map<String,String> updateSubject(HttpServletRequest request){
		String treeNodes = request.getParameter("treeNodes");
		Map<String,String> returnedMap = new HashMap<String,String>();
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		Gson gson = new Gson();
		List<CategoryJsonObj> list = gson.fromJson(treeNodes, new TypeToken<List<CategoryJsonObj>>(){}.getType());
		
		List<CategoryJsonObj> existslist = (List<CategoryJsonObj>)request.getSession().getAttribute("houseSubjectList");
		
		List<CategoryJsonObj> diffSubject = null;
		try {
			diffSubject = GoodsCollectionUtils.getDifferentCategoryList(existslist, list);
			subjectAndModuleService.saveOrupdateSubject(diffSubject,repositoryCode);
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
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			List<GoodsHouseSubjectModule> subjectModuleList = subjectAndModuleService.getSubjectModulesBySubjectId(subjectId,repositoryCode);
			
			String subjectName = subjectAndModuleService.getSubjectInfoById(subjectId).getName();
			
			resultMap.put("modules", subjectModuleList);
			resultMap.put("subjectName", subjectName);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the house subject module,",e);
			resultMap.put("result", "N");
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
    

	@ResponseBody
	@RequestMapping("/getSubjectById")
	public Map<String,Object> getSubjectById(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
			resultMap.put("subjectObj", subjectAndModuleService.getSubjectInfoById(subjectId));
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping("/saveOrUpdateSubject")
	public Map<String,Object> saveOrUpdateSubject(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String subjectListFromPage = request.getParameter("subjectList");
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(subjectListFromPage);
			
			JsonArray subjectArray = new JsonArray();
			if(el.isJsonArray()){
				subjectArray = el.getAsJsonArray();
			}
			
			Iterator it = subjectArray.iterator();
			List<GoodsSubject> subjectList = new ArrayList<GoodsSubject>();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				//JsonElement转换为JavaBean对象
				subjectList.add(gson.fromJson(e, GoodsSubject.class));
			}
			
			subjectAndModuleService.saveOrUpdateSubject(subjectList,currentUser.getLoginName(),repositoryCode);
			
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to modify the house subject,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping("/getParentSubjectListByLevel")
	public Map<String,Object> getParentSubjectListByLevel(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			String subjectLevel = request.getParameter("subjectLevel");
			if( "2".equalsIgnoreCase(subjectLevel) ){
				subjectLevel = "1";
			}else if( "3".equalsIgnoreCase(subjectLevel) ){
				subjectLevel = "2";
			}
			
			List<GoodsSubject> subjectList = subjectAndModuleService.getSubjectByLevel(subjectLevel,repositoryCode);
			
			resultMap.put("result", "Y");
			resultMap.put("subjectList", subjectList);
		} catch (Exception e) {
			logger.error("fail to modify the house subject,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}
}
