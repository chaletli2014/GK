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
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.CategoryService;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsServiceService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class ServiceController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("goodsServiceService")
	private GoodsServiceService goodsServiceService;
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	/**
	 * 我的服务库
	 * @param request
	 * @return
	 */
	@RequestMapping("/serviceprovider")
    public ModelAndView serviceprovider(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        	}
        	
        	List<GoodsServiceProvider> serviceproviders = goodsServiceService.getGoodsServiceProviderByUserCode(currentUser.getLoginName());
        	
        	view.addObject("serviceproviders", serviceproviders);
        	view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
		} catch (Exception e) {
			
		}
        view.setViewName("service/serviceproviderlist");
        return view;
    }
	

    @RequestMapping("/addserviceprovider")
    public ModelAndView addserviceprovider(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String serviceId = request.getParameter("serviceId");
    		
    		if( null != serviceId && !"".equalsIgnoreCase(serviceId) ){
    			GoodsServiceProvider serviceProvider = goodsServiceService.getGoodsServiceProviderById(Integer.valueOf(serviceId));
    			view.addObject("serviceProvider", serviceProvider);
    		}
    		
    		view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
    	} catch (Exception e) {
    		
    	}
    	view.setViewName("service/addgoodsservice");
    	return view;
    }
    
    @RequestMapping("/saveorupdateservice")
    public String saveorupdateservice(HttpServletRequest request){
    	try {
    		GoodsServiceDetail serviceDetail = new GoodsServiceDetail();
    		populateServiceDetail(serviceDetail, request);
    		
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("fail to add or update ordinary house, can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        		return "redirect:servicelist";
        	}
    		
    		goodsServiceService.saveOrUpdateGoodsServiceDetail(serviceDetail, currentUser);
    	} catch (Exception e) {
    		logger.error("fail to save or update goods service,",e);
    	}
    	return "redirect:ordinaryhouse";
    }
    
    /**
	 * 服务商的服务列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/servicedetails")
    public ModelAndView servicedetails(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        	}
        	
        	String providerCode = request.getParameter("providerCode");
        	if( ( null == providerCode || "".equalsIgnoreCase(providerCode) )
        			&& null != request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE)){
        		providerCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE);
        	}else{
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE, providerCode);
        	}
        	
        	if( null == providerCode || "".equalsIgnoreCase(providerCode) ){
        		view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_PROVIDER);
        	}else{
        		List<GoodsServiceDetail> serviceDetails = goodsServiceService.getGoodsServiceDetailsByProviderCode(providerCode);
        		view.addObject("serviceDetails", serviceDetails);
        	}
        	
        	view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
		} catch (Exception e) {
			
		}
        view.setViewName("service/servicedetails");
        return view;
    }
	

    @RequestMapping("/addservicedetail")
    public ModelAndView addservicedetail(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String serviceDetailId = request.getParameter("serviceDetailId");
    		
    		if( null != serviceDetailId && !"".equalsIgnoreCase(serviceDetailId) ){
    			GoodsServiceDetail serviceDetail = goodsServiceService.getGoodsServiceDetailById(Integer.valueOf(serviceDetailId));
    			view.addObject("serviceDetail", serviceDetail);
    		}
    		
    		List<GoodsDictionary> serviceCodes = dictionaryService.getDictionaryByType("serviceRange");
    		
    		view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
			view.addObject("serviceCodes", serviceCodes);
    	} catch (Exception e) {
    		logger.error("fail to get service detail,",e);
    	}
    	view.setViewName("service/addservicedetail");
    	return view;
    }
    
//    @RequestMapping("/saveorupdateservicedetail")
//    public String saveorupdateservicedetail(HttpServletRequest request){
//    	try {
//    		GoodsServiceDetail serviceDetail = new GoodsServiceDetail();
//    		populateServiceDetail(serviceDetail, request);
//    		
//    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
//        	
//        	if( null == currentUser ){
//        		logger.error("fail to add or update ordinary house, can not get the current user.");
//        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
//        		return "redirect:servicelist";
//        	}
//        	
//        	String productCategory = categoryService.getCategoryCodeByName(serviceDetail.getCategoryName());
//        	serviceDetail.setProductCategory(productCategory);
//    		
//        	Object sessionProviderCode = request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE);
//        	if( null != sessionProviderCode ){
//        		serviceDetail.setProviderCode(String.valueOf(sessionProviderCode));
//        		goodsServiceService.saveOrUpdateGoodsServiceDetail(serviceDetail, currentUser);
//        	}else{
//        		
//        	}
//    	} catch (Exception e) {
//    		logger.error("fail to save or update goods service,",e);
//    	}
//    	return "redirect:servicedetails";
//    }
    
//    @RequestMapping("/saveorupdateservice")
//    public String saveorupdateservice(HttpServletRequest request){
//    	try {
//    		GoodsServiceDetail serviceDetail = new GoodsServiceDetail();
//    		populateServiceDetail(serviceDetail, request);
//    		
//    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
//    		
//    		if( null == currentUser ){
//    			logger.error("fail to add or update ordinary house, can not get the current user.");
//    			request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
//    			return "redirect:servicelist";
//    		}
//    		
//    		String productCategory = categoryService.getCategoryCodeByName(serviceDetail.getCategoryName());
//    		serviceDetail.setProductCategory(productCategory);
//    		
//    		Object sessionProviderCode = request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE);
//    		if( null != sessionProviderCode ){
//    			serviceDetail.setProviderCode(String.valueOf(sessionProviderCode));
//    			goodsServiceService.saveOrUpdateGoodsServiceDetail(serviceDetail, currentUser);
//    		}else{
//    			
//    		}
//    	} catch (Exception e) {
//    		logger.error("fail to save or update goods service,",e);
//    	}
//    	return "redirect:servicedetails";
//    }
    
    /**
	 * 服务商的客户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/serviceCustomer")
    public ModelAndView serviceCustomer(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        	}
        	
        	String providerCode = request.getParameter("providerCode");
        	if( ( null == providerCode || "".equalsIgnoreCase(providerCode) )
        			&& null != request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE)){
        		providerCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE);
        	}else{
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE, providerCode);
        	}
        	
        	if( null == providerCode || "".equalsIgnoreCase(providerCode) ){
        		view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_PROVIDER);
        	}else{
        		GoodsServiceProvider sp = goodsServiceService.getGoodsServiceProviderByCode(providerCode);
        		List<GoodsSPCustomer> spCustomers = goodsServiceService.getGoodsSPCustomerByProviderCode(providerCode);
        		view.addObject("serviceCustomers", spCustomers);
        		view.addObject("serviceProvider", sp);
        	}
        	
        	view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
		} catch (Exception e) {
			
		}
        view.setViewName("service/serviceCustomer");
        return view;
    }
	
	/**
	 * 新增客户
	 * @param request
	 * @return
	 */
	@RequestMapping("/addserviceCustomer")
	public ModelAndView addserviceCustomer(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			String providerCode = request.getParameter("providerCode");
			if( ( null == providerCode || "".equalsIgnoreCase(providerCode) )
					&& null != request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE)){
				providerCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE);
			}
			
			if( null == providerCode || "".equalsIgnoreCase(providerCode) ){
				view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_PROVIDER);
			}else{
				GoodsServiceProvider sp = goodsServiceService.getGoodsServiceProviderByCode(providerCode);
				view.addObject("serviceProvider", sp);
			}
			
			String serviceCustomerId = request.getParameter("serviceCustomerId");
    		
    		if( null != serviceCustomerId && !"".equalsIgnoreCase(serviceCustomerId) ){
    			GoodsSPCustomer spCustomer = goodsServiceService.getGoodsSPCustomerBySPCustomerId(GoodsQuickUtils.parseIntegerFromString(serviceCustomerId));
    			view.addObject("spCustomer", spCustomer);
    		}
    		
    		List<GoodsDictionary> serviceTypes = dictionaryService.getDictionaryByType("serviceTypes");
    		view.addObject("serviceTypes", serviceTypes);
			
			view.addObject("opened", ",service,");
			view.addObject("actived", ",serviceprovider,");
		} catch (Exception e) {
			
		}
		view.setViewName("service/addservicecustomer");
		return view;
	}
	
	/**
	 * 新增客户
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveSPCustomer")
	public String saveSPCustomer(HttpServletRequest request){
		try {
			String spCode = request.getParameter("spCode");
			String productCategory = request.getParameter("productCategory");
			String serviceTypeCode = request.getParameter("serviceTypeCode");
			String customerName = request.getParameter("customerName");
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_PROVIDER_CODE,spCode);
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsSPCustomer spCustomer = new GoodsSPCustomer();
			spCustomer.setSpCode(spCode);
			String categoryCode = categoryService.getCategoryCodeByName(productCategory);
			spCustomer.setCategoryCode(categoryCode);
			spCustomer.setServiceTypeCode(serviceTypeCode);
			spCustomer.setCustomerName(customerName);
			spCustomer.setStatus("1");
			
			goodsServiceService.saveSPCustomer(spCustomer, currentUser);
			
		} catch (Exception e) {
			logger.error("保存服务商的客户失败，",e);
		}
		return "redirect:serviceCustomer";
	}
	/**
	 * 删除客户
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteSPCustomer")
	public String deleteSPCustomer(HttpServletRequest request){
		try {
			String spCustomerId = request.getParameter("spCustomerId");
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsSPCustomer spCustomer = new GoodsSPCustomer();
			spCustomer.setId(GoodsQuickUtils.parseIntegerFromString(spCustomerId));
			
			goodsServiceService.deleteSPCustomer(spCustomer, currentUser);
			
		} catch (Exception e) {
			logger.error("服务商删除客户失败,",e);
		}
		return "redirect:serviceCustomer";
	}
	/**
	 * 发送审核消息给客户，关联服务商
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendRequesttoCustomer")
	@ResponseBody
	public Map<String,String> sendRequesttoCustomer(HttpServletRequest request){
		Map<String,String> requestMap = new HashMap<String,String>();
		try {
			String spCustomerId = request.getParameter("spCustomerId");
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsSPCustomer spCustomer = goodsServiceService.getGoodsSPCustomerBySPCustomerId(GoodsQuickUtils.parseIntegerFromString(spCustomerId));
			
			goodsServiceService.sendMessage2Customer(spCustomer, currentUser);
			requestMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("发送关联需求失败,",e);
			requestMap.put("result", "N");
			requestMap.put("message", String.format("发送请求失败：%s", e.getMessage()));
		}
		return requestMap;
	}
    
    private void populateServiceInfo(GoodsServiceProvider goodsService, HttpServletRequest request){

    	String serviceId = request.getParameter("serviceId");
    	if( null != serviceId && !"".equalsIgnoreCase(serviceId) ){
    		goodsService.setId(Integer.valueOf(serviceId));
    	}
    	
    	goodsService.setName(request.getParameter("serviceName"));
    	goodsService.setAddress(request.getParameter("address"));
    	goodsService.setTelephone(request.getParameter("telephone"));
    	goodsService.setContractNumber(request.getParameter("contractNumber"));
    	goodsService.setServiceContent(request.getParameter("serviceContent"));
    }
    
    private void populateServiceDetail(GoodsServiceDetail serviceDetail, HttpServletRequest request){
    	
    	String serviceDetailId = request.getParameter("serviceDetailId");
    	if( null != serviceDetailId && !"".equalsIgnoreCase(serviceDetailId) ){
    		serviceDetail.setId(Integer.valueOf(serviceDetailId));
    	}
    	
    	serviceDetail.setServiceName(request.getParameter("serviceName"));
    	serviceDetail.setServiceRangeCode(request.getParameter("serviceRangeCode"));
    	serviceDetail.setServiceRangeName(request.getParameter("serviceRangeName"));
    	serviceDetail.setPrice(Double.valueOf(request.getParameter("price")));
    	serviceDetail.setServiceContent(request.getParameter("serviceContent"));
    }
}
