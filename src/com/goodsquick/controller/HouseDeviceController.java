package com.goodsquick.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
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
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

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
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("liftService")
	private LiftService liftService;

	@RequestMapping("/houseDeviceList")
	public ModelAndView houseDeviceList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("houseDevice/houseDeviceList");
		try {
			List<GoodsDictionary> assetDeviceTypes = dictionaryService.getDictionaryByType("device_type");
			view.addObject("assetDeviceTypes", assetDeviceTypes);
			
			Gson gson = new Gson();
			String assetDeviceTypeArray = gson.toJson(assetDeviceTypes);
			view.addObject("assetDeviceTypeArray", assetDeviceTypeArray);
			
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
	

	@RequestMapping("/getassetdevicelist")
    @ResponseBody
    public String getassetdevicelist(HttpServletRequest request){
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		JSONObject getObj = new JSONObject();
		try {
			JSONArray jsonarray = JSONArray.fromObject(request.getParameter("aoData"));
			
			String sEcho = null;
	        int iDisplayStart = 0; // 起始索引
	        int iDisplayLength = 0; // 每页显示的行数
	        String eqTypeCode = "";
	     
	        for (int i = 0; i < jsonarray.size(); i++) {
	            JSONObject obj = (JSONObject) jsonarray.get(i);
	            if ("sEcho".equals(obj.get("name"))){
	            	sEcho = obj.get("value").toString();
	            }
	     
	            if ("iDisplayStart".equals(obj.get("name"))){
	            	iDisplayStart = obj.getInt("value");
	            }
	     
	            if ("iDisplayLength".equals(obj.get("name"))){
	            	iDisplayLength = obj.getInt("value");
	            }
	            
	            if ( "eqTypeCode".equals(obj.get("name")) ){
	            	eqTypeCode = obj.getString("value");
	            }
	        }
	        
			getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
			
			List<GoodsHouseDevice> houseDevices = new ArrayList<GoodsHouseDevice>();
			if( StringUtils.isBlank(eqTypeCode) || "all".equalsIgnoreCase(eqTypeCode) ){
				houseDevices.addAll(goodsHouseDeviceService.getAllDevice(repositoryCode));
			}else{
				houseDevices.addAll(goodsHouseDeviceService.getDeviceByEqTypeCode(repositoryCode, eqTypeCode));
			}
			
			int totalRecords = 0;
			int totalDisplayRecords = 0;
			if( !CollectionUtils.isEmpty(houseDevices) ){
				if( houseDevices.size() <= iDisplayStart + iDisplayLength ){
					houseDevices = houseDevices.subList(iDisplayStart,houseDevices.size());
				}else{
					houseDevices = houseDevices.subList(iDisplayStart,iDisplayStart + iDisplayLength);
				}
				
				totalRecords = houseDevices.size();
				totalDisplayRecords = houseDevices.size();
			}
			getObj.put("iTotalRecords", totalRecords);//实际的行数
		    getObj.put("iTotalDisplayRecords", totalDisplayRecords);
			
		    GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(String.class, new TypeAdapter<String>() {  
	            @Override  
	            public void write(JsonWriter out, String value) throws IOException {  
	                if (value == null) {  
	                    out.value("");
	                } else {  
	                    out.value(value);  
	                }  
	            }  
	            @Override  
	            public String read(JsonReader in) throws IOException {  
	                if (in.peek() == JsonToken.NULL) {  
	                    in.nextNull();  
	                    return null;  
	                }  
	                // return in.nextString();  
	                String str = in.nextString();  
	                if (str.equals("")) { // 反序列化时将 "" 转为 null  
	                    return null;  
	                } else {  
	                    return str;  
	                }  
	            }  
	        }); 
		    
		    Gson gson = gsonBuilder.create();
			getObj.put("aaData", gson.toJson(houseDevices));
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		return getObj.toString();
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
    		GoodsHouseDevice houseDevice = goodsHouseDeviceService.getDeviceInfoById(deviceId);
			result.put("deviceObj", houseDevice);
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
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
    		String deviceObj = request.getParameter("deviceObj");
    		
    		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();
    		JsonParser parser = new JsonParser();
    		JsonElement el = parser.parse(deviceObj);
    		
    		GoodsHouseDevice houseDevice = gson.fromJson(el, GoodsHouseDevice.class);
    		List<GoodsHouseDevice> deviceList = new ArrayList<GoodsHouseDevice>();
    		deviceList.add(houseDevice);
			goodsHouseDeviceService.saveOrUpdateHouseDevice(deviceList, currentUser.getLoginName(), repositoryCode);
			
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseDevice: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
	
	@ResponseBody
	@RequestMapping("/deviceNodes")
	public Map<String,Object> deviceNodes(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			int subjectId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("subjectId"));
			int moduleId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("moduleId"));
			
			List<GoodsHouseDevice> devicesList = new ArrayList<GoodsHouseDevice>();
			
			if( subjectId != 0 ){
				devicesList = goodsHouseDeviceService.getDeviceBySubjectId(subjectId);
			}else if( moduleId != 0 ){
				devicesList = goodsHouseDeviceService.getDeviceByModuleId(moduleId);
			}
			resultMap.put("devices", devicesList);
		} catch (Exception e) {
			logger.error("fail to get the house devices,",e);
		}
		
		return resultMap;
	}
}
