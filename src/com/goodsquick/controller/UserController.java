package com.goodsquick.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.Category;
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
    
    @RequestMapping("/saveOrUpdateprofile")
    public String saveprofile(HttpServletRequest request){
        try {
        	String userId = request.getParameter("userId");
        	String loginName = request.getParameter("login_name");
        	String realName = request.getParameter("realName");
        	String oldPassword = request.getParameter("password");
        	String newPassword = request.getParameter("newPassword");
        	String telephone = request.getParameter("telephone");
        	String hasHouse = request.getParameter("hasHouse");
        	String hasService = request.getParameter("hasService");
        	
        	if( null != userId && !"".equalsIgnoreCase(userId) ){
        		
        		WebUserInfo userprofile = webUserService.getUserProfileById(userId);
        		
        		userprofile.setLoginName(loginName);
        		userprofile.setName(realName);
        		if( StringUtils.isNotBlank(newPassword) ){
        			userprofile.setPassword(GoodsQuickMD5Utils.MD5(newPassword));
        		}
        		userprofile.setHasHouse(StringUtils.isEmpty(hasHouse)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasHouse);
        		userprofile.setHasService(StringUtils.isEmpty(hasService)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasService);
        		userprofile.setTelephone(telephone);
        		
            	webUserService.updateUserInfo(userprofile);
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
            	userprofile.setHasHouse(StringUtils.isEmpty(hasHouse)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasHouse);
        		userprofile.setHasService(StringUtils.isEmpty(hasService)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasService);
            	
            	webUserService.addUserInfo(userprofile);
        	}
        	
		} catch (Exception e) {
			logger.error(String.format("fail to add or update userprofile,%s",e.getMessage()),e);
		}
        
        return "redirect:userlist";
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
    		String userId = request.getParameter("userId");
    		WebUserInfo userprofile = webUserService.getUserProfileById(userId);
    		
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
    	view.setViewName("sys/registerUser");
    	return view;
    }
    
    @RequestMapping("/doRegisterUser")
    public String doRegisterUser(HttpServletRequest request){
    	try {
        	String password = request.getParameter("password");
        	
        	WebUserInfo userInfo = new WebUserInfo();
        	userInfo.setLoginName(request.getParameter("login_name"));
        	userInfo.setName(request.getParameter("name"));
        	userInfo.setPassword(GoodsQuickMD5Utils.MD5(password));
        	userInfo.setTelephone(request.getParameter("telephone"));
        	
        	String hasHouse = request.getParameter("hasHouse");
        	userInfo.setHasHouse(StringUtils.isEmpty(hasHouse)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasHouse);
        	
        	String hasService = request.getParameter("hasService");
        	userInfo.setHasService(StringUtils.isEmpty(hasService)?GoodsQuickAttributes.GOODS_STATUS_OFF:hasService);
        	
        	webUserService.addUserInfo(userInfo);
        	
        	WebUserInfo webUser = webUserService.getUserProfileByLoginName(request.getParameter("login_name"));
        	request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_LOGIN_USER, webUser);
    	} catch (Exception e) {
    		logger.error(String.format("fail to register user,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:index";
    }
}
