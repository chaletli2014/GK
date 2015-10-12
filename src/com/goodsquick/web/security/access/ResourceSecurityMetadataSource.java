/**
 * 
 */
package com.goodsquick.web.security.access;

import java.awt.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import com.goodsquick.web.security.IMenuItem;


public class ResourceSecurityMetadataSource extends GenericFilterBean implements FilterInvocationSecurityMetadataSource {
    
    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    private Map<String, SpelExpression> spelMap;
    
    private Map<String, String> additionalConfigAttributes;
    private SpelExpressionParser spelPareser = new SpelExpressionParser(new SpelParserConfiguration(false, false));
    
    /**
     * Which urls will trigger resource - configAttris mapping reload.
     */
    private List<String> triggerReloadUrls;
    
    public ResourceSecurityMetadataSource() {
        triggerReloadUrls = new LinkedList<String>();
        triggerReloadUrls.add("/authFunction/saveModule.action");
        triggerReloadUrls.add("/authFunction//saveMenuGroup.action");
        triggerReloadUrls.add("/authFunction/saveMenuItem.action");
        triggerReloadUrls.add("/authFunction/saveViewButton.action");
    }
    
    protected void initFilterBean() throws ServletException {
        loadResourceMap();
        if (additionalConfigAttributes != null) {
            spelMap = new HashMap<String, SpelExpression>();
            for (String url : additionalConfigAttributes.keySet()) {
                String config = additionalConfigAttributes.get(url);
                SpelExpression express = (SpelExpression)spelPareser.parseExpression(config);
                spelMap.put(url, express);
            }
        }
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadResourceMap() {
        Map<String, Collection<ConfigAttribute>> newMap = new HashMap<String, Collection<ConfigAttribute>>();
        
        List<IMenuItem> menuItems = new ArrayList<IMenuItem>();
        for (IMenuItem i : menuItems) {
            Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
            attrs.add(new SecurityConfig(i.getViewId()));
            newMap.put(i.getMenuItemUrl(), attrs);
            
        }
        resourceMap = newMap;
    }
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl().substring(1); // remove the starting '/'
        if (url.indexOf('?')>0)
            url = url.substring(0,url.indexOf('?'));
        Collection<ConfigAttribute> configs = resourceMap.get(url);
        if (configs == null) {
            for (String pattern : spelMap.keySet()) {
                AntPathRequestMatcher urlPathMatcher = new AntPathRequestMatcher(pattern);
                if (urlPathMatcher.matches(((FilterInvocation) object).getHttpRequest())) {
                    configs = new LinkedList<ConfigAttribute>();
                    configs.add(new WebExpressionConfigAttribute(spelMap.get(pattern)));
                    return configs;
                }
            }
        }
        return configs;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public List<String> getTriggerReloadUrls() {
        return triggerReloadUrls;
    }

    public void setTriggerReloadUrls(List<String> triggerReloadUrls) {
        this.triggerReloadUrls = triggerReloadUrls;
    }
    
    public Map<String, String> getAdditionalConfigAttributes() {
        return additionalConfigAttributes;
    }

    public void setAdditionalConfigAttributes(Map<String, String> additionalConfigAttributes) {
        this.additionalConfigAttributes = additionalConfigAttributes;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        chain.doFilter(req, response);
        if (checkReloadNeeded(request)) {
            loadResourceMap();
        }
    }
    
    private boolean checkReloadNeeded(HttpServletRequest request) {
        if (triggerReloadUrls!=null) {
            for (String url : triggerReloadUrls) {
                if (request.getRequestURI().endsWith(request.getContextPath() + url))
                    return true;
            }
        }
        return false;
    }
}
