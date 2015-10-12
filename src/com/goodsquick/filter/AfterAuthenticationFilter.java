package com.goodsquick.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.goodsquick.web.SessionManager;
import com.goodsquick.web.security.IMenuItem;
import com.goodsquick.web.security.UserDetails;

public class AfterAuthenticationFilter extends GenericFilterBean {
    
    private String extraAuthenticationUrl;
    private RequestMatcher extraAuthenticationRequestMatcher;
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            if (extraAuthenticationRequestMatcher.matches(request)) {
                Authentication newAuth = doExtraAuthentication(request, response);
                SecurityContextHolder.getContext().setAuthentication(newAuth);
                
                return; // request has been processed, end the chain.
            } else {
                // 2. set SessionManager needed info.
                if (SecurityContextHolder.getContext().getAuthentication()!=null && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
                    initSessionManagerAttributes(request);
            }
        }
        chain.doFilter(req, res);
    }

    /**
     * Override this method to do further authentication
     * @param request
     * @param response
     * @return Authentication new Authentication object, don't forget to set UserDetails to it.
     */
    protected Authentication doExtraAuthentication(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("this method must be implemented to support extra authentication");
    }

    /**
     * Initialize authorization attributes to SessionManager.
     * @param request
     */
    protected void initSessionManagerAttributes(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)auth.getDetails();
        SessionManager.setSession(request.getSession());
        
        if (SessionManager.getAttribute("initialized")==null || !((Boolean)SessionManager.getAttribute("initialized"))) {
            SessionManager.setUserDetails(userDetails);
            
            Set<String> viewIds = new HashSet<String>();
            for (IMenuItem mi : userDetails.getMenuItems()) {
                viewIds.add(mi.getViewId());
            }
            SessionManager.setAttribute("viewIds", viewIds); // Constants.SESSION_USER_VIEW_IDS
            SessionManager.setAttribute("themes", "ui-lightness"); //SessionConstants.THEMES
            SessionManager.setAttribute("lang", "zh_CN"); //SessionConstants.LANGUAGE
            initExtraSessionManagerAttributes(request);
            SessionManager.setAttribute("initialized", true);
        }
    }
    
    /**
     * Override this method to set optional SessionManager attributes.
     * 
     * @param request
     */
    protected void initExtraSessionManagerAttributes(HttpServletRequest request) {
    }

    
    public String getExtraAuthenticationUrl() {
        return extraAuthenticationUrl;
    }

    public void setExtraAuthenticationUrl(String extraAuthenticationUrl) {
        this.extraAuthenticationUrl = extraAuthenticationUrl;
        this.extraAuthenticationRequestMatcher = new FilterProcessUrlRequestMatcher(extraAuthenticationUrl);
    }

    public RequestMatcher getExtraAuthenticationRequestMatcher() {
        return extraAuthenticationRequestMatcher;
    }
    
    public void setExtraAuthenticationRequestMatcher(RequestMatcher extraAuthenticationRequestMatcher) {
        this.extraAuthenticationRequestMatcher = extraAuthenticationRequestMatcher;
    }
}
