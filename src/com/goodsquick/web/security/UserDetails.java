/**
 * 
 */
package com.goodsquick.web.security;

import java.util.List;
import java.util.Locale;

import org.springframework.security.core.Authentication;

/**
 * 基本用户信息的接口。
 * <p>
 * 该接口定义对系统安全和基本权限控制而言必须的属性，{@link UserDetails}必须在用户认证阶段被产生出来并设置到{@link Authentication}对象的details，
 * 详情请查看{@link com.vip.lf.web.security.filter.UsernamePasswordAuthenticationFilter}和{@link com.vip.lf.web.security.authentication.UserDetailsService}。
 * </p>
 * 
 * @author Evan Wu
 *
 */
public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {
    
    List<IMenuItem> getMenuItems();
    
    Locale getDefaultLang();

    String getPrincipalGroupCode();
    
    Object getUserEntity();
}
