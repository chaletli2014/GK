package com.goodsquick.controller;

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

import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsHouseSubjectModule;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class HouseDeviceController {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@ResponseBody
	@RequestMapping("/houseDevicelist")
	public Map<String,Object> houseDevicelist(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			List<GoodsHouseDevice> devices = ordinaryHouseService.getAllHouseDeviceByRepositoryCode(repositoryCode);
			resultMap.put("devices", devices);
		} catch (Exception e) {
			logger.error("fail to get the house devices,",e);
		}
		
		return resultMap;
	}
	
	@RequestMapping("/saveHouseDevice")
    @ResponseBody
    public Map<String,String> saveHouseDevice(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		GoodsHouseDevice houseDevice = new GoodsHouseDevice();
    		populateDeviceInfo(houseDevice, request);
    		
    		ordinaryHouseService.saveOrUpdateHouseDevice(houseDevice, currentUser);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseDevice: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }

    private void populateDeviceInfo(GoodsHouseDevice houseDevice, HttpServletRequest request){
    	String deviceId = request.getParameter("deviceId");
    	if( null != deviceId && !"".equalsIgnoreCase(deviceId) ){
    		houseDevice.setId(GoodsQuickUtils.parseIntegerFromString(deviceId));
    	}
    	
    	houseDevice.setEqTypeCode(request.getParameter("eqType"));
    	houseDevice.setName(request.getParameter("eqName"));
    	houseDevice.setBrand(request.getParameter("eqBrand"));
    	houseDevice.setStyle(request.getParameter("eqStyle"));
    	houseDevice.setEqDesc(request.getParameter("eqDesc"));
    	houseDevice.setSubjectId(GoodsQuickUtils.parseIntegerFromString(request.getParameter("eqSubject")));
    	houseDevice.setModuleId(GoodsQuickUtils.parseIntegerFromString(request.getParameter("eqModule")));
    }
}
