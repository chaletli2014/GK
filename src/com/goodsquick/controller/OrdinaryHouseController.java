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
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.service.RelationshipPropertyService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class OrdinaryHouseController {
	
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@Autowired
	@Qualifier("relationshipPropertyService")
	private RelationshipPropertyService relationshipPropertyService;
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;

    @RequestMapping("/ordinaryhouse")
    public ModelAndView ordinaryhouse(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	List<GoodsOrdinaryHouse> orHouses = ordinaryHouseService.getOrdinaryHouseByUserCode(currentUser.getLoginName()); 
        	
        	view.addObject("orHouses", orHouses);
        	view.addObject("opened", ",estate,resident,");
			view.addObject("actived", ",ordinaryhouse,");
		} catch (Exception e) {
			
		}
        
        view.setViewName("ep/ordinaryhouse");
        return view;
    }
    
    @RequestMapping("/newProductPre")
    public ModelAndView newProductPre(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
		view.addObject("opened", ",estate,resident,");
		view.addObject("actived", ",ordinaryhouse,");
    	view.setViewName("ep/ordinaryhouse_popadd_pre");
    	return view;
    }
    
    @RequestMapping("/newProductPreOne")
    public ModelAndView newProductPre1(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	view.addObject("opened", ",estate,resident,");
    	view.addObject("actived", ",ordinaryhouse,");
    	String viewName = "ep/ordinaryhouse_popadd_pre_1";
    	
    	List<GoodsDictionary> goodsCategory = new ArrayList<GoodsDictionary>();
    	try {
    		String productType = request.getParameter("type");
    		if( StringUtils.endsWith(productType, "2") ){
    			viewName = "ep/ordinaryhouse_popadd_pre_2";
    		}
			goodsCategory = dictionaryService.getDictionaryByType(productType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	view.addObject("goodsCategory", goodsCategory);
    	
    	view.setViewName(viewName);
    	return view;
    }
    
    @RequestMapping("/ordinaryHousedevice")
    public ModelAndView ordinaryHousedevice(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String orHouseId = request.getParameter("orHouseId");
    		
    		if( StringUtils.isEmpty(orHouseId) ){
    			orHouseId = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_HOUSE_ID);
    		}
    		GoodsOrdinaryHouse orHouse = new GoodsOrdinaryHouse();
    		if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    			orHouse = ordinaryHouseService.getGoodsOrdinaryHouseById(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    			view.addObject("orHouse", orHouse);
    		}
    		
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		List<GoodsDictionary> goodsDics = dictionaryService.getDictionaryByType(GoodsQuickAttributes.DICTIONRY_TYPE_DEVICE);
    		view.addObject("goodsDics", goodsDics);
    		
    		List<GoodsHouseDevice> deviceList = ordinaryHouseService.getAllHouseDeviceByUser(orHouse, currentUser);
    		view.addObject("deviceList", deviceList);
    		
    		view.addObject("opened", ",estate,resident,");
    		view.addObject("actived", ",ordinaryhouse,");
    		
    		Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
    		
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 获取设备失败",e);
    	}
    	
    	view.setViewName("ep/ordinaryhousedevice");
    	return view;
    }
    
    @RequestMapping("/addDeviceToOrdinaryHouse")
    public String addDevice2OrdinaryHouse(HttpServletRequest request){
    	try {
    		String orHouseId = request.getParameter("orHouseId");
    		
    		GoodsOrdinaryHouse orHouse = new GoodsOrdinaryHouse();
    		if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    			orHouse = ordinaryHouseService.getGoodsOrdinaryHouseById(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    		}
    		
    		GoodsHouseDevice houseDevice = new GoodsHouseDevice();
    		houseDevice.setOrHouseCode(orHouse.getHouseCode());
    		populateDeviceInfo(houseDevice, request);
    		
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		ordinaryHouseService.saveOrUpdateHouseDevice(houseDevice, orHouse, currentUser);
    		
    		request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_HOUSE_ID,orHouseId);
    	} catch (Exception e) {
    		logger.error(String.format("保存不动产设备失败,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:ordinaryHousedevice";
    }
    
    @RequestMapping("/addordinaryhouse")
    public ModelAndView addordinaryhouse(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String orHouseId = request.getParameter("orHouseId");
    		
    		if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    			GoodsOrdinaryHouse orHouse = ordinaryHouseService.getGoodsOrdinaryHouseById(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    			view.addObject("orHouse", orHouse);
    			
    			GoodsRelationshipProperty relationshipProperty = relationshipPropertyService.getRelationshipProperty("tbl_goods_ordinary_house", GoodsQuickUtils.parseIntegerFromString(orHouseId));
    			view.addObject("relationshipProperty", relationshipProperty);
    		}
    		
    		view.addObject("opened", ",estate,resident,");
			view.addObject("actived", ",ordinaryhouse,");
			
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
			if( null != errorMessage ){
				view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
				request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
			}
			
    	} catch (Exception e) {
    		
    	}
    	
    	view.setViewName("ep/addordinaryhouse");
    	return view;
    }
    
    @RequestMapping("saveordinaryhouse")
    public String doAddordinaryhouse(HttpServletRequest request){
    	GoodsOrdinaryHouse ordinaryHouse = new GoodsOrdinaryHouse();
    	GoodsRelationshipProperty relationshipProperty = new GoodsRelationshipProperty();
    	populateOrdinaryHouseInfo(ordinaryHouse, request);
    	populateRelationshipInfo(relationshipProperty, request);
    	
    	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    	
    	if( null == currentUser ){
    		logger.error("fail to add or update ordinary house, can not get the current user.");
    		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
    		return "redirect:addordinaryhouse";
    	}
    	
    	try {
    		ordinaryHouseService.saveOrUpdateOrdinaryHouse(relationshipProperty, ordinaryHouse, currentUser);
    	}catch(Exception e){
    		logger.error("fail to add or update ordinary house,",e);
    	}
    	
    	return "redirect:ordinaryhouse";
    }
    

    @RequestMapping("/houseSP")
    public ModelAndView houseSP(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String orHouseId = request.getParameter("orHouseId");
    		
    		if( StringUtils.isEmpty(orHouseId) ){
    			orHouseId = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_HOUSE_ID);
    		}
    		GoodsOrdinaryHouse orHouse = new GoodsOrdinaryHouse();
    		if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    			orHouse = ordinaryHouseService.getGoodsOrdinaryHouseById(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    			view.addObject("orHouse", orHouse);
    		}
    		
    		GoodsRelationshipProperty relationshipProperty = relationshipPropertyService.getRelationshipProperty("tbl_goods_ordinary_house", GoodsQuickUtils.parseIntegerFromString(orHouseId));
			view.addObject("relationshipProperty", relationshipProperty);
    		
    		view.addObject("opened", ",estate,resident,");
    		view.addObject("actived", ",ordinaryhouse,");
    		
    		Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
    		
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 获取不动产服务商信息失败",e);
    	}
    	
    	view.setViewName("ep/ordinaryhouseSP");
    	return view;
    }
    
    @RequestMapping("/myHouseSP")
    public ModelAndView myHouseSP(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		List<GoodsRelationshipProperty> houses = ordinaryHouseService.getAllHouseRelationshipByUserCode(currentUser.getLoginName());
			view.addObject("houses", houses);
    		view.addObject("opened", ",estate,resident,");
    		view.addObject("actived", ",myHouseSP,");
    		Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 获取不动产服务商信息失败",e);
    	}
    	
    	view.setViewName("ep/myHouseSP");
    	return view;
    }
    
    @RequestMapping("/savehouseSP")
    @ResponseBody
    public Map<String,String> savehouseSP(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		
    		GoodsRelationshipProperty relationshipProperty = new GoodsRelationshipProperty();
    		populateRelationshipInfoFromSP(relationshipProperty, request);
        	
        	relationshipProperty.setSourceId(GoodsQuickUtils.parseIntegerFromString(request.getParameter("orHouseId")));
    		
    		relationshipPropertyService.saveOrUpdateRelationshipProperty(relationshipProperty, currentUser);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 保存服务商信息失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
    
    private void populateOrdinaryHouseInfo(GoodsOrdinaryHouse ordinaryHouse, HttpServletRequest request){
    	
    	String orHouseId = request.getParameter("orHouseId");
    	if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    		ordinaryHouse.setId(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    	}
    	ordinaryHouse.setBuildingName(request.getParameter("buildingName"));
    	ordinaryHouse.setProvince(request.getParameter("province"));
    	ordinaryHouse.setCity(request.getParameter("city"));
    	ordinaryHouse.setBuildingStatus(request.getParameter("buildingStatus"));
    	ordinaryHouse.setCompany(request.getParameter("company"));
    	ordinaryHouse.setContacterName(request.getParameter("contacterName"));
    	ordinaryHouse.setContacterPosition(request.getParameter("contacterPosition"));
    	ordinaryHouse.setContacterTelephone(request.getParameter("contacterTelephone"));
    	ordinaryHouse.setPropertyName(request.getParameter("propertyName"));
    	ordinaryHouse.setLocation(request.getParameter("location"));
    	ordinaryHouse.setProjectPositionE(request.getParameter("projectPositionE"));
    	ordinaryHouse.setProjectPositionW(request.getParameter("projectPositionW"));
    	ordinaryHouse.setProjectPositionS(request.getParameter("projectPositionS"));
    	ordinaryHouse.setProjectPositionN(request.getParameter("projectPositionN"));
    	ordinaryHouse.setPropertyType(request.getParameter("propertyType"));
    	ordinaryHouse.setPropertytypeO(request.getParameter("propertytypeO"));
    	ordinaryHouse.setStartYear(GoodsQuickUtils.parseIntegerFromString(request.getParameter("startYear")));
    	ordinaryHouse.setStartMonth(GoodsQuickUtils.parseIntegerFromString(request.getParameter("startMonth")));
    	ordinaryHouse.setStartDate(GoodsQuickUtils.parseIntegerFromString(request.getParameter("startDate")));
    	ordinaryHouse.setCheckinYear(GoodsQuickUtils.parseIntegerFromString(request.getParameter("checkinYear")));
    	ordinaryHouse.setCheckinMonth(GoodsQuickUtils.parseIntegerFromString(request.getParameter("checkinMonth")));
    	ordinaryHouse.setCheckinDate(GoodsQuickUtils.parseIntegerFromString(request.getParameter("checkinDate")));
    	ordinaryHouse.setFloorSpace(GoodsQuickUtils.parseIntegerFromString(request.getParameter("floorSpace")));
    	ordinaryHouse.setBuildingNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("buildingNumber")));
    	ordinaryHouse.setHasLiftNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("hasLiftNumber")));
    	ordinaryHouse.setNonLiftNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("nonLiftNumber")));
    	ordinaryHouse.setLobbyNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("lobbyNumber")));
    	ordinaryHouse.setLiftLobbyNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("liftLobbyNumber")));
    	ordinaryHouse.setNonLiftLobbyNumber(GoodsQuickUtils.parseIntegerFromString(request.getParameter("nonLiftLobbyNumber")));
    	ordinaryHouse.setOwnerHouseholds(GoodsQuickUtils.parseIntegerFromString(request.getParameter("ownerHouseholds")));
    	ordinaryHouse.setTenantHouseholds(GoodsQuickUtils.parseIntegerFromString(request.getParameter("tenantHouseholds")));
    	ordinaryHouse.setDeliveryHouseholds(GoodsQuickUtils.parseIntegerFromString(request.getParameter("deliveryHouseholds")));
    	ordinaryHouse.setNonDeliveryHouseholds(GoodsQuickUtils.parseIntegerFromString(request.getParameter("nonDeliveryHouseholds")));
    	ordinaryHouse.setCoveredArea(GoodsQuickUtils.parseIntegerFromString(request.getParameter("coveredArea")));
    	ordinaryHouse.setPeriod(GoodsQuickUtils.parseIntegerFromString(request.getParameter("period")));
    	ordinaryHouse.setWestEastLength(GoodsQuickUtils.parseIntegerFromString(request.getParameter("westEastLength")));
    	ordinaryHouse.setSouthNorthLength(GoodsQuickUtils.parseIntegerFromString(request.getParameter("southNorthLength")));
    	ordinaryHouse.setPlanSidewayNum(GoodsQuickUtils.parseIntegerFromString(request.getParameter("planSidewayNum")));
    	ordinaryHouse.setPlanCarwayNum(GoodsQuickUtils.parseIntegerFromString(request.getParameter("planCarwayNum")));
    	ordinaryHouse.setActualSidewayNum(GoodsQuickUtils.parseIntegerFromString(request.getParameter("actualSidewayNum")));
    	ordinaryHouse.setActualCarwayNum(GoodsQuickUtils.parseIntegerFromString(request.getParameter("actualCarwayNum")));
    }
    
    private void populateDeviceInfo(GoodsHouseDevice houseDevice, HttpServletRequest request){
    	houseDevice.setOrHouseId(request.getParameter("orHouseId"));
    	houseDevice.setDeviceType(request.getParameter("deviceType"));
    	houseDevice.setDeviceName(request.getParameter("deviceName"));
    	houseDevice.setManufacturerName(request.getParameter("manufacturerName"));
    	houseDevice.setBrandName(request.getParameter("brandName"));
    	houseDevice.setModel(request.getParameter("model"));
    	
    	String deviceNum = request.getParameter("deviceNum");
    	if( null != deviceNum && !"".equalsIgnoreCase(deviceNum) ){
    		houseDevice.setDeviceNum(GoodsQuickUtils.parseIntegerFromString(deviceNum));
    	}
    }
    
    private void populateRelationshipInfo(GoodsRelationshipProperty relationshipProperty, HttpServletRequest request){
    	
    	String relationshipPropertyId = request.getParameter("relationshipPropertyId");
    	if( null != relationshipPropertyId && !"".equalsIgnoreCase(relationshipPropertyId) ){
    		relationshipProperty.setId(GoodsQuickUtils.parseIntegerFromString(relationshipPropertyId));
    	}
    	
    	String orHouseId = request.getParameter("orHouseId");
    	if( null != orHouseId && !"".equalsIgnoreCase(orHouseId) ){
    		relationshipProperty.setSourceId(GoodsQuickUtils.parseIntegerFromString(orHouseId));
    	}
    	
    	relationshipProperty.setSourceTable("tbl_goods_ordinary_house");
    	
    	relationshipProperty.setLocation(request.getParameter("location"));
    	/** 开发公司为商品品牌商  */
    	relationshipProperty.setBrandName(request.getParameter("company"));
    	/** 建筑设计单位 为 商品设计商  */
    	relationshipProperty.setDesignName(request.getParameter("designName"));
    	/** 建筑施工单位 为 生产制造商  */
    	relationshipProperty.setManufacturerName(request.getParameter("manufacturerName"));
    	/** 检测认证单位 为检测认证商  */
    	relationshipProperty.setCertificationName(request.getParameter("certificationName"));
    	/** 不动产所有者为商品所有人  */
    	relationshipProperty.setOwnerName(request.getParameter("ownerName"));
    	/** 物业公司为商品托管人  */
    	relationshipProperty.setTrusteeshipName(request.getParameter("propertyName"));
    }
    
    private void populateRelationshipInfoFromSP(GoodsRelationshipProperty relationshipProperty, HttpServletRequest request){
    	
    	relationshipProperty.setId(GoodsQuickUtils.parseIntegerFromString(request.getParameter("relationshipPropertyId")));
    	
		relationshipProperty.setSourceId(GoodsQuickUtils.parseIntegerFromString(request.getParameter("orHouseId")));
    	
    	relationshipProperty.setSourceTable("tbl_goods_ordinary_house");
    	
    	relationshipProperty.setBrandName(request.getParameter("brandName"));
    	relationshipProperty.setDesignName(request.getParameter("designName"));
    	relationshipProperty.setCertificationName(request.getParameter("certificationName"));
    	relationshipProperty.setChannelName(request.getParameter("channelName"));
    	relationshipProperty.setLogisticsName(request.getParameter("logisticsName"));
    	relationshipProperty.setOwnerName(request.getParameter("ownerName"));
    	relationshipProperty.setTrusteeshipName(request.getParameter("trusteeshipName"));
    	relationshipProperty.setSupervisionName(request.getParameter("supervisionName"));
    	relationshipProperty.setRecyclingName(request.getParameter("recyclingName"));
    }
}
