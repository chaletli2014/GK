package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.service.RelationshipPropertyService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class ModuleSPController {

    Logger logger = Logger.getLogger(ModuleSPController.class);

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@Autowired
	@Qualifier("relationshipPropertyService")
	private RelationshipPropertyService relationshipPropertyService;
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
    
	@RequestMapping("/saveOrUpdateModuleSP")
	public String saveOrUpdateRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsHouseModuleSP moduleSP = new GoodsHouseModuleSP();
			
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			String spTypeCode = (String)request.getSession().getAttribute("spTypeCode");
			moduleSP.setModuleType1(request.getParameter("modifyModuleType1"));
			moduleSP.setModuleType2(request.getParameter("modifyModuleType2"));
			
			moduleSP.setRepositoryCode(repositoryCode);
        	moduleSP.setSpTypeCode(spTypeCode);
        	moduleSP.setFromSource("手动添加");
        	
        	request.getSession().setAttribute("spTypeCode", spTypeCode);
        	
        	populateModuleSP(moduleSP, currentUser, request);
			
			relationshipPropertyService.saveModuleSP(moduleSP);
			
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		return "redirect:moduleSPManagement";
	}
	
	@RequestMapping("/moduleSPManagement")
	public ModelAndView modulesplist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sp/moduleSPManagement");
		
		String spTypeCode = request.getParameter("spTypeCode");
		if( StringUtils.isBlank(spTypeCode) ){
			spTypeCode = (String)request.getSession().getAttribute("spTypeCode");
		}else{
			request.getSession().setAttribute("spTypeCode", spTypeCode);
		}
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		List<GoodsHouseModuleSP> houseModuleSPList = new ArrayList<GoodsHouseModuleSP>();
    		List<GoodsDictionary> moduleType1List = new ArrayList<GoodsDictionary>();
    		
    		GoodsDictionary dic = dictionaryService.getDictionaryByCode(spTypeCode,"sp_type");
    		List<GoodsDictionary> spTypes = dictionaryService.getDictionaryByType("sp_type");
    		
    		houseModuleSPList = relationshipPropertyService.getModuleSPByHouseCodeAndType(repositoryCode, spTypeCode);
    		
    		moduleType1List = dictionaryService.getDictionaryByType("moduletype1");
    		
    		view.addObject("houseModuleSPList", houseModuleSPList);
    		view.addObject("moduleType1", moduleType1List);
    		view.addObject("spTypes", spTypes);
    		view.addObject("spTypeCode", spTypeCode);
    		view.addObject("spTypeName", dic.getDicName());
		} catch (Exception e) {
			logger.error("fail to get the moduleSPManagement,",e);
		}
		view.addObject("opened", ",serviceCustomer,");
        view.addObject("actived", ",moduleSPList,");
		return view;
	}
	

	@RequestMapping("/deleteModuleSP")
	public String deleteModuleSP(HttpServletRequest request){
		String spId = request.getParameter("spId");
		GoodsHouseModuleSP houseModuleSP = new GoodsHouseModuleSP();
		houseModuleSP.setId(GoodsQuickUtils.parseIntegerFromString(spId));
		relationshipPropertyService.removeModuleSP(houseModuleSP);
		return "redirect:moduleSPManagement";
	}
	
	@RequestMapping("/getModuleSPByModuleType")
    @ResponseBody
    public Map<String,Object> getModuleSPByModuleType(HttpServletRequest request){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		String moduleType2 = request.getParameter("moduleType2");
    		String spTypeCode = request.getParameter("spTypeCode");
    		
    		List<GoodsHouseModuleSP> houseModuleSPList = relationshipPropertyService.getModuleSPByModuleType(repositoryCode, spTypeCode, moduleType2);
    		
    		result.put("spList", houseModuleSPList);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("getModuleSPByModuleType: 根据组件类型获取组件商失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
	
	@RequestMapping("/getModuleSPInfoById")
	@ResponseBody
	public Map<String,Object> getModuleSPInfoById(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String spId = request.getParameter("spId");
			GoodsHouseModuleSP moduleSP = relationshipPropertyService.getModuleSPById(spId);
			
			result.put("moduleSP", moduleSP);
			result.put("result", "Y");
		} catch (Exception e) {
			logger.error("getModuleSPInfoById: 根据ID获取组件商失败",e);
			result.put("result", "N");
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	
	private void populateModuleSP(GoodsHouseModuleSP moduleSP, WebUserInfo currentUser, HttpServletRequest request){
		String moduleSPId = request.getParameter("moduleSPId");
    	if( StringUtils.isBlank(moduleSPId) ){
    		moduleSPId = request.getParameter("moduleSPId_equ");
    	}
    	
    	moduleSP.setId(GoodsQuickUtils.parseIntegerFromString(moduleSPId));
    	moduleSP.setSpName(request.getParameter("spName"));
    	moduleSP.setSpTel(request.getParameter("spTel"));
    	moduleSP.setSpPhone(request.getParameter("spPhone"));
    	moduleSP.setRemark(request.getParameter("remark"));
    	moduleSP.setCreateUser(currentUser.getLoginName());
    	moduleSP.setUpdateUser(currentUser.getLoginName());
	}
}