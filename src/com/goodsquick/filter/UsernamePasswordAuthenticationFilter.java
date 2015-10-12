package com.goodsquick.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goodsquick.model.GoodsRole;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.SystemManagerService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.web.security.authentication.UserDetailsService;


/**
 * 使用用户名与密码鉴权的过滤器。为必须配置的过滤器。
 */
public class UsernamePasswordAuthenticationFilter extends org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter {
    private UserDetailsService userDetailsService;
    
    @Autowired
    @Qualifier("systemManagerService")
    private SystemManagerService systemManagerService;
    
    /**
     * Provided so that subclasses may configure what is put into the authentication request's details
     * property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details set
     */
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(userDetailsService.loadUserByUsername(authRequest.getName()));
    }
    
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
    		FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	HttpServletResponse httpResponse = (HttpServletResponse)response;
    	Object currentUser = httpRequest.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    	
    	if( null == currentUser && !httpRequest.getRequestURI().endsWith("dologin") 
    			&& !httpRequest.getRequestURI().endsWith("registerUser")
    			&& !httpRequest.getRequestURI().endsWith("doRegisterUser")){
    		httpResponse.sendRedirect(httpRequest.getContextPath());
    		return;
    	}
    	
    	if( null != currentUser ){
    		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(currentUser, null);
    		
    		WebUserInfo userInfo = (WebUserInfo)currentUser;
    		
    		List<GoodsRole> roles = null;
			try {
				roles = systemManagerService.getRolesByUserId(String.valueOf(userInfo.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		auth.setDetails(roles);
    		
    		SecurityContextHolder.getContext().setAuthentication(auth);
    		System.out.println("setAuth");
    	}
    	
    	if( null != SecurityContextHolder.getContext() && null != SecurityContextHolder.getContext().getAuthentication() ){
    		WebUserInfo webUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		System.out.println("auth:"+webUser.getLoginName());
    	}
    	
    	super.doFilter(request, response, chain);
    }
}
