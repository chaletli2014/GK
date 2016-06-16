package com.goodsquick.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.GoodsRole;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.SystemManagerService;
import com.goodsquick.service.WebUserService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickMD5Utils;
import com.goodsquick.utils.QueryInfo;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    @Qualifier("webUserService")
    private WebUserService webUserService;
	
	@Autowired
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
    
    @RequestMapping("/userlist")
    public ModelAndView userlist(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        QueryInfo queryInfo = new QueryInfo();
        try {
			List<WebUserInfo> userlist = webUserService.getAllUserByQueryInfo(queryInfo);
			
			view.addObject("userlist", userlist);
			view.addObject("opened", ",system,");
			view.addObject("actived", ",userlist,");
		} catch (Exception e) {
			logger.error(String.format("fail to get userlist,%s",e.getMessage()),e);
		}
        
        view.setViewName("sys/userlist");
        return view;
    }
    
    @RequestMapping("checkAccount")
    @ResponseBody
    public Map<String,String> checkAccount(HttpServletRequest request){
    	Map<String, String> result = new HashMap<String, String>();
    	try{
    		String loginName = request.getParameter("login_name");
    		String password = request.getParameter("password");
    		
    		WebUserInfo currentUser = webUserService.getWebUser(loginName, password);
    		if( null == currentUser ){
    			result.put("result", "N");
    		}else{
    			result.put("result", "Y");
    		}
    	}catch(Exception e){
    		logger.error(String.format("fail to check account,%s",e.getMessage()),e);
    		result.put("result", "N");
    	}
		
		return result;
    }
    
    @RequestMapping("checkCompanyInfo")
    @ResponseBody
    public Map<String,String> checkCompanyInfo(HttpServletRequest request){
    	Map<String, String> result = new HashMap<String, String>();
    	try{
    		
    		WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		if( currentUser.getCompany() == null || "".equalsIgnoreCase(currentUser.getCompany().getCompanyName()) ){
    			result.put("result", "N");
    		}else{
    			result.put("result", "Y");
    		}
    	}catch(Exception e){
    		logger.error(String.format("fail to check account,%s",e.getMessage()),e);
    		result.put("result", "N");
    	}
    	
    	return result;
    }
    
    @RequestMapping("/saveOrUpdateprofile")
    public String saveprofile(HttpServletRequest request){
        try {
        	String userId = request.getParameter("userId");
        	String companyId = request.getParameter("companyId");
        	String loginName = request.getParameter("login_name");
        	String realName = request.getParameter("realName");
        	String oldPassword = request.getParameter("password");
        	String newPassword = request.getParameter("newPassword");
        	String telephone = request.getParameter("telephone");
        	String companyName = request.getParameter("companyName");
        	String companyEmail = request.getParameter("companyEmail");
        	
        	if( null != userId && !"".equalsIgnoreCase(userId) ){
        		
        		WebUserInfo userprofile = webUserService.getUserProfileById(userId);
        		
        		userprofile.setLoginName(loginName);
        		userprofile.setName(realName);
        		if( StringUtils.isNotBlank(newPassword) ){
        			userprofile.setPassword(GoodsQuickMD5Utils.MD5(newPassword));
        		}
        		userprofile.setTelephone(telephone);
        		
            	webUserService.updateUserInfo(userprofile);
            	
            	GoodsCompanyInfo companyInfo = new GoodsCompanyInfo();
            	companyInfo.setCompanyName(companyName);
            	companyInfo.setCompanyEmail(companyEmail);
            	companyInfo.setUpdateUser(loginName);
            	if( StringUtils.isBlank(companyId) ){
            		companyInfo.setCreateUser(loginName);
            		int companyId_i = webUserService.addCompanyInfo(companyInfo);
            		webUserService.registUser2Company(Integer.parseInt(userId), companyId_i, loginName);
            	}else{
            		companyInfo.setId(Integer.parseInt(companyId));
            		webUserService.updateCompanyInfo(companyInfo);
            	}
        	}else{
            	String level = request.getParameter("level");
            	
            	WebUserInfo userprofile = new WebUserInfo();
            	userprofile.setLoginName(loginName);
            	userprofile.setName(realName);
            	if( StringUtils.isNotBlank(newPassword) ){
            		userprofile.setPassword(GoodsQuickMD5Utils.MD5(newPassword));
            	}else{
        			userprofile.setPassword(GoodsQuickMD5Utils.MD5(oldPassword));
        		}
            	userprofile.setLevel(level);
            	userprofile.setTelephone(telephone);
            	userprofile.setHasHouse(GoodsQuickAttributes.GOODS_STATUS_ON);
        		userprofile.setHasService(GoodsQuickAttributes.GOODS_STATUS_ON);
            	
        		userId = String.valueOf(webUserService.addUserInfo(userprofile));
        	}
        	
        	WebUserInfo userprofile = webUserService.getUserProfileById(userId);
        	
        	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userprofile, null);
        	SecurityContextHolder.getContext().setAuthentication(auth);
        	
		} catch (Exception e) {
			logger.error(String.format("fail to add or update userprofile,%s",e.getMessage()),e);
		}
        
        return "redirect:index";
    }

    @RequestMapping("/addprofile")
    public ModelAndView addprofile(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	view.addObject("opened", ",system,");
			view.addObject("actived", ",userlist,");
		} catch (Exception e) {
			logger.error(String.format("fail to add userprofile,%s",e.getMessage()),e);
		}
        
        view.setViewName("sys/userprofile");
        return view;
    }
    
    @RequestMapping("/editprofile")
    public ModelAndView editprofile(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		WebUserInfo userprofile = new WebUserInfo();
    		
    		String userId = request.getParameter("userId");
    		if( null == userId || "".equalsIgnoreCase(userId) ){
    			userprofile = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		}else{
    			userprofile = webUserService.getUserProfileById(userId);
    		}
    		
    		view.addObject("userprofile", userprofile);
    		view.addObject("opened", ",system,");
    		view.addObject("actived", ",userlist,");
    		
    	} catch (Exception e) {
    		logger.error(String.format("fail to add userprofile,%s",e.getMessage()),e);
    	}
    	
    	view.setViewName("sys/userprofile");
    	return view;
    }
    
    @RequestMapping("/disableUser")
    public String disableUser(HttpServletRequest request){
    	try {
    		String userId = request.getParameter("userId");
    		webUserService.disableUser(userId);
    	} catch (Exception e) {
    		logger.error(String.format("fail to disable user,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:userlist";
    }
    

    @RequestMapping("/userrole")
    public ModelAndView userrole(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String userId = request.getParameter("userId");
    		if( null == userId || "".equalsIgnoreCase(userId) ){
    			userId = String.valueOf(request.getSession().getAttribute("userrole_userId"));
    		}
    		
    		List<GoodsRole> userRoles = systemManagerService.getRolesByUserId(userId);
    		List<GoodsRole> allRoles = systemManagerService.getAllRoles();
    		
    		for( GoodsRole role : allRoles ){
    			for( GoodsRole userRole : userRoles ){
    				if( userRole.getRoleCode().equalsIgnoreCase(role.getRoleCode()) ){
    					role.setIsOwn("Y");
    					break;
    				}
    			}
    		}
    		
    		view.addObject("allRoles", allRoles);
    		view.addObject("userRoles", userRoles);
    		view.addObject("userId", userId);
    		view.addObject("opened", ",system,");
    		view.addObject("actived", ",userlist,");
    		
    	} catch (Exception e) {
    		logger.error(String.format("fail to get the role of the user,%s",e.getMessage()),e);
    	}
    	
    	view.setViewName("sys/userrole");
    	return view;
    }
    
    @RequestMapping("/saveUserRole")
    public String saveUserRole(HttpServletRequest request){
    	try {
    		String checkedRoles = request.getParameter("checkedRoles");
    		String userId = request.getParameter("userId");
    		
    		List<String> newRoles = CollectionUtils.arrayToList(checkedRoles.split(","));
    		systemManagerService.saveUserRole(userId, newRoles);
    		
    		request.getSession().setAttribute("userrole_userId", userId);
    	} catch (Exception e) {
    		logger.error(String.format("fail to get the role of the user,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:userrole";
    }
    
    @RequestMapping("/registerUser")
    public ModelAndView registerUser(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	view.setViewName("registerUser");
    	return view;
    }
    
    @RequestMapping("/doRegisterUser")
    public String doRegisterUser(HttpServletRequest request){
    	try {
    		String industry = request.getParameter("industry");
    		String companyName = request.getParameter("companyName");
    		String companyProvince = request.getParameter("companyProvince");
    		String companyCity = request.getParameter("companyCity");
    		String companyEmail = request.getParameter("companyEmail");
    		
    		GoodsCompanyInfo newCompany = new GoodsCompanyInfo();
    		newCompany.setIndustry(industry);
    		newCompany.setCompanyName(companyName);
    		newCompany.setCompanyProvince(companyProvince);
    		newCompany.setCompanyCity(companyCity);
    		newCompany.setCompanyEmail(companyEmail);
    		newCompany.setCreateUser(request.getParameter("loginName"));
    		newCompany.setUpdateUser(request.getParameter("loginName"));
    		
    		WebUserInfo userInfo = new WebUserInfo();
        	String password = request.getParameter("password");
        	userInfo.setLoginName(request.getParameter("loginName"));
        	userInfo.setName(request.getParameter("realName"));
        	userInfo.setPassword(GoodsQuickMD5Utils.MD5(password));
        	userInfo.setTelephone(request.getParameter("telephone"));
        	
        	webUserService.registerUserCompanyInfo(userInfo, newCompany);
    	} catch (Exception e) {
    		logger.error(String.format("fail to register user,%s",e.getMessage()),e);
    	}
    	
    	request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE,"注册成功，请登录");
    	return "redirect:login";
    }
    
    @RequestMapping("searchUserByName")
    @ResponseBody
    public Map<String,Object> searchUserByName(HttpServletRequest request){
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		String name = request.getParameter("searchStr");
    		WebUserInfo dbUser = webUserService.getUserProfileByUserName(name);
			result.put("searchUser", dbUser);
    	}catch(Exception e){
    		logger.error(String.format("fail to get the user by name,%s",e.getMessage()),e);
    	}
		
		return result;
    }
}
