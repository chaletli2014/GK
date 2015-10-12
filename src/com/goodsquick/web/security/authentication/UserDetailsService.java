/**
 * 
 */
package com.goodsquick.web.security.authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用于返回{@link com.vip.lf.web.security.UserDetails}的接口。
 * <p>
 * 在用户认证阶段需要根据用户名来查找用户，如果用户不存在{@link #loadUserByUsername(String)}方法需要抛出{@link UsernameNotFoundException}异常。
 * </p>
 * 
 * @author Evan Wu
 *
 */
public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * insensitive, or case insensitive depending on how the implementation instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    com.goodsquick.web.security.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
