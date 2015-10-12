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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsPrivilege;
import com.goodsquick.model.GoodsRole;
import com.goodsquick.service.SystemManagerService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class SystemManagerController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
    
    @RequestMapping("/rolelist")
    public ModelAndView rolelist(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
			List<GoodsRole> allRoles = systemManagerService.getAllRoles();
			
			view.addObject("allRoles", allRoles);
			view.addObject("opened", ",system,");
			view.addObject("actived", ",rolelist,");
		} catch (Exception e) {
			logger.error(String.format("fail to get all the roles,%s",e.getMessage()),e);
		}
        
        view.setViewName("sys/rolelist");
        return view;
    }
    
    @RequestMapping("/addOrUpdateRole")
    public String addOrUpdateRole(HttpServletRequest request){
        try {
			String roleName = request.getParameter("roleName");
			String roleCode = request.getParameter("roleCode");
			String roleDesc = request.getParameter("roleDesc");
			String roleId = request.getParameter("roleId");
			
			int roleIdInt = -1;
			if( null != roleId && !"".equalsIgnoreCase(roleId) ){
				roleIdInt = Integer.valueOf(roleId);
			}
			
			GoodsRole goodsRole = new GoodsRole();
			goodsRole.setId(roleIdInt);
			goodsRole.setRoleCode(roleCode);
			goodsRole.setRoleName(roleName);
			goodsRole.setRoleDesc(roleDesc);
			
			systemManagerService.saveOrUpdateRole(goodsRole);
			
		} catch (Exception e) {
			logger.error(String.format("fail to get all the roles,%s",e.getMessage()),e);
		}
        
        return "redirect:rolelist";
    }
    
    @RequestMapping("/deleteRole")
    public String deleteRole(HttpServletRequest request){
    	try {
    		String roleId = request.getParameter("roleId");
    		
    		int roleIdInt = -1;
    		if( null != roleId && !"".equalsIgnoreCase(roleId) ){
    			roleIdInt = Integer.valueOf(roleId);
    		}
    		
    		if( roleIdInt != -1 ){
    			systemManagerService.deleteRole(roleIdInt);
    		}else{
    			logger.error("fail to delete role");
    		}
    		
    	} catch (Exception e) {
    		logger.error(String.format("fail to get all the roles,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:rolelist";
    }
    
    @ResponseBody
    @RequestMapping("/getRoleById")
    public Map<String,Object> getRoleById(@RequestParam("roleId") String roleId){
    	Map<String, Object> message = new HashMap<String, Object>();
		String returnMessage = null;
		try {
			GoodsRole goodsRole = systemManagerService.getRoleById(Integer.valueOf(roleId));
			
			if( null != goodsRole ){
				message.put("status", "success");
				message.put("obj", goodsRole);
			}else{
				returnMessage = "角色编码或者名称已经存在，不能重复添加";
				message.put("status", "failure");
				message.put(GoodsQuickAttributes.WEB_INFO_MESSAGE, returnMessage);
			}
			
		} catch (Exception e) {
			logger.error("fail to get role by id,",e);
		}
		
		return message;
    }
    

    @RequestMapping("/privilegelist")
    public ModelAndView privilegelist(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		List<GoodsPrivilege> allPrivs = systemManagerService.getAllPrivileges();
    		
    		view.addObject("allPrivs", allPrivs);
    		view.addObject("opened", ",system,");
    		view.addObject("actived", ",privilegelist,");
    	} catch (Exception e) {
    		logger.error(String.format("fail to get all the Privs,%s",e.getMessage()),e);
    	}
    	
    	view.setViewName("sys/privilegelist");
    	return view;
    }
    
    @RequestMapping("/addOrUpdatePrivilege")
    public String addOrUpdatePrivilege(HttpServletRequest request){
    	try {
    		String privName = request.getParameter("privName");
    		String privCode = request.getParameter("privCode");
    		String privDesc = request.getParameter("privDesc");
    		String privId = request.getParameter("privId");
    		
    		int privIdInt = -1;
    		if( null != privId && !"".equalsIgnoreCase(privId) ){
    			privIdInt = Integer.valueOf(privId);
    		}
    		
    		GoodsPrivilege goodsPriv = new GoodsPrivilege();
    		goodsPriv.setId(privIdInt);
    		goodsPriv.setPrivilegeCode(privCode);
    		goodsPriv.setPrivilegeName(privName);
    		goodsPriv.setPrivilegeDesc(privDesc);
    		
    		systemManagerService.saveOrUpdatePrivilege(goodsPriv);
    		
    	} catch (Exception e) {
    		logger.error(String.format("fail to add or update the priv,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:privilegelist";
    }
    

    @RequestMapping("/deletePriv")
    public String deletePriv(HttpServletRequest request){
    	try {
    		String privId = request.getParameter("privId");
    		
    		int privIdInt = -1;
    		if( null != privId && !"".equalsIgnoreCase(privId) ){
    			privIdInt = Integer.valueOf(privId);
    		}
    		
    		if( privIdInt != -1 ){
    			systemManagerService.deletePrivilege(privIdInt);
    		}else{
    			logger.error("fail to delete priv, the privId is null");
    		}
    		
    	} catch (Exception e) {
    		logger.error(String.format("fail to delete priv,%s",e.getMessage()),e);
    	}
    	
    	return "redirect:privilegelist";
    }
    
    @ResponseBody
    @RequestMapping("/getPrivilegeById")
    public Map<String,Object> getPrivilegeById(@RequestParam("privId") String privId){
    	Map<String, Object> message = new HashMap<String, Object>();
		String returnMessage = null;
		try {
			GoodsPrivilege goodsPrivilege = systemManagerService.getPrivilegeById(Integer.valueOf(privId));
			
			if( null != goodsPrivilege ){
				message.put("status", "success");
				message.put("obj", goodsPrivilege);
			}else{
				returnMessage = "权限编码或者名称已经存在，不能重复添加";
				message.put("status", "failure");
				message.put(GoodsQuickAttributes.WEB_INFO_MESSAGE, returnMessage);
			}
			
		} catch (Exception e) {
			logger.error("fail to get priv by id,",e);
		}
		
		return message;
    }
}
