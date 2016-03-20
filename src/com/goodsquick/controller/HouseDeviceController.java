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

import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsHouseDeviceService;
import com.goodsquick.service.LiftService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class HouseDeviceController {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@Autowired
	@Qualifier("goodsHouseDeviceService")
	private GoodsHouseDeviceService goodsHouseDeviceService;
	
	@Autowired
	@Qualifier("liftService")
	private LiftService liftService;

	@RequestMapping("/houseDeviceList")
	public ModelAndView houseDeviceList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("houseDevice/houseDeviceList");
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		try {
			List<GoodsHouseDevice> houseDevices = goodsHouseDeviceService.getAllDevice(repositoryCode);
			view.addObject("houseDevices", houseDevices);
			
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",houseDevice,");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		
		return view;
	}

	@RequestMapping("/deleteDeviceByIdAndType")
	public String deleteDeviceByIdAndType(HttpServletRequest request){
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		String deviceId = request.getParameter("deviceId");
		String deviceType = request.getParameter("deviceType");
		
		GoodsHouseDevice obj = new GoodsHouseDevice();
		obj.setId(GoodsQuickUtils.parseIntegerFromString(deviceId));
		obj.setEqTypeCode(deviceType);
		obj.setUpdateUser(currentUser.getLoginName());
		try {
			goodsHouseDeviceService.deleteHouseDevice(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:houseDeviceList";
	}
	
	@RequestMapping("/saveOrUpdateDevice")
    @ResponseBody
    public Map<String,String> saveOrUpdateDevice(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
    		String deviceListFromPage = request.getParameter("deviceList");
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(deviceListFromPage);
			
			JsonArray deviceArray = new JsonArray();
			if(el.isJsonArray()){
				deviceArray = el.getAsJsonArray();
			}
			
			Iterator it = deviceArray.iterator();
			List<GoodsHouseDevice> deviceList = new ArrayList<GoodsHouseDevice>();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				//JsonElement转换为JavaBean对象
				deviceList.add(gson.fromJson(e, GoodsHouseDevice.class));
			}
    		
			goodsHouseDeviceService.saveOrUpdateHouseDevice(deviceList, currentUser.getLoginName(), repositoryCode);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseDevice: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }

	@RequestMapping("/getDeviceByIdAndType")
    @ResponseBody
    public Map<String,Object> getDeviceByIdAndType(HttpServletRequest request){
    	Map<String,Object> result = new HashMap<String,Object>();
    	try {
    		int deviceId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("deviceId"));
    		String deviceType = request.getParameter("deviceType");
    		
    		if( "dt".equalsIgnoreCase(deviceType) ){
    			GoodsDeviceLift deviceObj = liftService.getGoodsDeviceLiftById(deviceId);
    			result.put("deviceObj", deviceObj);
    		}
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseDevice: 根据ID和类型获取设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
	
	@RequestMapping("/modifyDevice")
    @ResponseBody
    public Map<String,String> modifyDevice(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		String deviceType = request.getParameter("deviceType");
    		String deviceObj = request.getParameter("deviceObj");
    		
    		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();
    		JsonParser parser = new JsonParser();
    		JsonElement el = parser.parse(deviceObj);
    		
    		if( "dt".equalsIgnoreCase(deviceType) ){
    			GoodsDeviceLift lift = gson.fromJson(el, GoodsDeviceLift.class);
    			liftService.saveOrUpdateDeviceLift(lift, currentUser);
    		}
			
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseDevice: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
}
