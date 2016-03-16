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
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsDictionaryType;
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
			String spTypeCode = request.getParameter("spTypeCode_h");
			String partCode = request.getParameter("partCode_h");
			
			moduleSP.setRepositoryCode(repositoryCode);
        	moduleSP.setSpTypeCode(spTypeCode);
        	moduleSP.setPartCode(partCode);
        	
        	request.getSession().setAttribute("spTypeCode", spTypeCode);
        	request.getSession().setAttribute("partCode", partCode);
        	
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
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		List<GoodsHouseModuleSP> houseModuleSPList = new ArrayList<GoodsHouseModuleSP>();
    		String partCode = request.getParameter("partCode");
    		if( StringUtils.isBlank(partCode) ){
    			partCode = (String)request.getSession().getAttribute("partCode");
    		}
    		String spTypeCode = request.getParameter("spTypeCode");
    		if( StringUtils.isBlank(spTypeCode) ){
    			spTypeCode = (String)request.getSession().getAttribute("spTypeCode");
    		}
    		
    		GoodsDictionary dic = dictionaryService.getDictionaryByCode(spTypeCode,partCode);
    		List<GoodsDictionary> spTypes = dictionaryService.getDictionaryByType(partCode);
    		
    		houseModuleSPList = relationshipPropertyService.getModuleSPByHouseCodeAndType(repositoryCode, spTypeCode, partCode);
    		view.addObject("houseModuleSPList", houseModuleSPList);
    		view.addObject("partCode", partCode);
    		view.addObject("spTypes", spTypes);
    		view.addObject("spTypeCode", spTypeCode);
    		view.addObject("spTypeName", dic.getDicName());
		} catch (Exception e) {
			logger.error("fail to get the moduleSPManagement,",e);
		}
		view.addObject("opened", ",serviceCustomer,");
        view.addObject("actived", ",moduleSPManagement,");
		return view;
	}
	
	private void populateModuleSP(GoodsHouseModuleSP moduleSP, WebUserInfo currentUser, HttpServletRequest request){
		String moduleSPId = request.getParameter("moduleSPId");
    	if( StringUtils.isNotBlank(moduleSPId) ){
    		moduleSP.setId(GoodsQuickUtils.parseIntegerFromString(moduleSPId));
    	}
    	moduleSP.setSpName(request.getParameter("spName"));
    	moduleSP.setSpTel(request.getParameter("spTel"));
    	moduleSP.setRemark(request.getParameter("remark"));
    	moduleSP.setCreateUser(currentUser.getLoginName());
    	moduleSP.setUpdateUser(currentUser.getLoginName());
	}
}