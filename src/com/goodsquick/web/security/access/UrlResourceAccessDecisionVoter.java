/**
 * 
 */
package com.goodsquick.web.security.access;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import com.goodsquick.web.SessionManager;


/**
 * 检查当前用户是否被授权访问URL资源。
 * <p>
 * URL资源关联着一定的viewId，而登录用户的请求经过{@link com.vip.lf.web.security.filter.AfterAuthenticationFilter}后
 * {@link SessionManager}含有用户可访问的所有viewId。
 * </p>
 * 
 * @author Evan Wu
 *
 */
public class UrlResourceAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    private SecurityExpressionHandler<FilterInvocation> expressionHandler = new DefaultWebSecurityExpressionHandler();
    
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return ((attribute instanceof SecurityConfig) || (attribute instanceof WebExpressionConfigAttribute));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz == FilterInvocation.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        if (authentication instanceof AnonymousAuthenticationToken)
            throw new InsufficientAuthenticationException("User Not Authenticated");

        Iterator<ConfigAttribute> ite=attributes.iterator();
        while(ite.hasNext()) {
            ConfigAttribute ca=ite.next();
            if (ca instanceof SecurityConfig) {
                String urlId=((SecurityConfig)ca).getAttribute();
                Set<String> viewIds = (Set<String>)SessionManager.getAttribute("viewIds");
                if (viewIds.contains(urlId))
                    return ACCESS_GRANTED;
            } else {
                EvaluationContext ctx = expressionHandler.createEvaluationContext(authentication, object);
                return ExpressionUtils.evaluateAsBoolean(((WebExpressionConfigAttribute)ca).getAuthorizeExpression(), ctx) ?
                        ACCESS_GRANTED : ACCESS_DENIED;
            }
        }
        return ACCESS_DENIED;
    }

}
