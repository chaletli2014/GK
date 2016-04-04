package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsHouseOther;
import com.goodsquick.model.GoodsHousePaint;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsHouseOtherService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class HouseOtherController {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@Autowired
	@Qualifier("goodsHouseOtherService")
	private GoodsHouseOtherService goodsHouseOtherService;

	@RequestMapping("/houseOtherList")
	public ModelAndView houseOtherList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("houseOther/houseOtherList");
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		try {
			List<GoodsHouseOther> houseOthers = goodsHouseOtherService.getAllOther(repositoryCode);
			view.addObject("houseOthers", houseOthers);
			
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",houseOther,");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		
		return view;
	}

	@RequestMapping("/deleteOtherByIdAndType")
	public String deleteDeviceByIdAndType(HttpServletRequest request){
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		String otherId = request.getParameter("otherId");
		String otherType = request.getParameter("otherType");
		
		GoodsHouseOther obj = new GoodsHouseOther();
		obj.setId(GoodsQuickUtils.parseIntegerFromString(otherId));
		obj.setTypeCode(otherType);
		obj.setUpdateUser(currentUser.getLoginName());
		try {
			goodsHouseOtherService.deleteHouseOther(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:houseOtherList";
	}
	
	@RequestMapping("/saveOrUpdateOther")
    @ResponseBody
    public Map<String,String> saveOrUpdateOther(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
    		String otherListFromPage = request.getParameter("otherList");
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(otherListFromPage);
			
			JsonArray otherArray = new JsonArray();
			if(el.isJsonArray()){
				otherArray = el.getAsJsonArray();
			}
			
			Iterator it = otherArray.iterator();
			List<GoodsHouseOther> otherList = new ArrayList<GoodsHouseOther>();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				//JsonElement转换为JavaBean对象
				otherList.add(gson.fromJson(e, GoodsHouseOther.class));
			}
    		
			goodsHouseOtherService.saveOrUpdateHouseOther(otherList, currentUser.getLoginName(), repositoryCode);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseOther: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
	
	@RequestMapping("/getOtherByIdAndType")
    @ResponseBody
    public Map<String,Object> getDeviceByIdAndType(HttpServletRequest request){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
    		int otherId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("otherId"));
    		String otherType = request.getParameter("otherType");
    		
    		if( "wpp".equalsIgnoreCase(otherType) ){
    			GoodsHousePaint otherObj = goodsHouseOtherService.getPaintInfoById(otherId);
    			result.put("otherObj", otherObj);
    		}
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseOther: 根据ID和类型获取材料装饰失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
	
	@RequestMapping("/modifyOther")
	@ResponseBody
	public Map<String,String> modifyOther(HttpServletRequest request){
		Map<String,String> result = new HashMap<String,String>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String otherType = request.getParameter("otherType");
    		String otherObj = request.getParameter("otherObj");
    		
    		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();
    		JsonParser parser = new JsonParser();
    		JsonElement el = parser.parse(otherObj);
    		
    		if( "wpp".equalsIgnoreCase(otherType) ){
    			GoodsHousePaint housePaint = gson.fromJson(el, GoodsHousePaint.class);
    			goodsHouseOtherService.saveOrUpdateHousePaint(housePaint, currentUser, repositoryCode);
    		}
			result.put("result", "Y");
		} catch (Exception e) {
			logger.error("saveHouseOther: 保存设施设备失败",e);
			result.put("result", "N");
			result.put("message", e.getMessage());
		}
		
		return result;
	}
	

	@ResponseBody
	@RequestMapping("/otherNodes")
	public Map<String,Object> otherNodes(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
			int moduleId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("moduleId"));
			
			List<GoodsHouseOther> otherList = new ArrayList<GoodsHouseOther>();
			
			if( subjectId != 0 ){
				otherList = goodsHouseOtherService.getHouseOtherBySubjectId(subjectId);
			}else if( moduleId != 0 ){
				otherList = goodsHouseOtherService.getHouseOtherByModuleId(moduleId);
			}
			resultMap.put("others", otherList);
		} catch (Exception e) {
			logger.error("fail to get the house others,",e);
		}
		
		return resultMap;
	}
}
