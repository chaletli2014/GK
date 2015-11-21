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

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.WebUserInfo;
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
    
	@RequestMapping("/saveOrUpdateModuleSP")
	public String saveOrUpdateRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsHouseModuleSP moduleSP = new GoodsHouseModuleSP();
			
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
        	GoodsOrdinaryHouse orHouse = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
        	moduleSP.setHouseCode(orHouse.getHouseCode());
        	
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
    		String moduleType = request.getParameter("type");
    		
        	GoodsOrdinaryHouse orHouse = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
        	if( null != orHouse ){
        		houseModuleSPList = relationshipPropertyService.getModuleSPByHouseCodeAndType(orHouse.getHouseCode(), moduleType);
        		view.addObject("houseModuleSPList", houseModuleSPList);
        	}
		} catch (Exception e) {
			logger.error("fail to get the moduleSPManagement,",e);
		}
		view.addObject("opened", ",system,");
        view.addObject("actived", ",moduleSPManagement,");
		return view;
	}
	
	private void populateModuleSP(GoodsHouseModuleSP moduleSP, WebUserInfo currentUser, HttpServletRequest request){
		String moduleSPId = request.getParameter("moduleSPId");
    	if( StringUtils.isNotBlank(moduleSPId) ){
    		moduleSP.setId(GoodsQuickUtils.parseIntegerFromString(moduleSPId));
    	}
    	moduleSP.setModuleType(request.getParameter("moduleType"));
    	moduleSP.setModuleSPName(request.getParameter("spName"));
    	moduleSP.setRemark(request.getParameter("remark"));
    	moduleSP.setCreateUser(currentUser.getLoginName());
    	moduleSP.setUpdateUser(currentUser.getLoginName());
	}
}