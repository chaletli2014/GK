/**
 * 
 */
package com.goodsquick.web.security;

import java.util.Locale;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.goodsquick.model.User;
import com.goodsquick.model.UserInfo;
import com.goodsquick.web.security.authentication.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser = null;
        if (sysUser != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(sysUser.getPassword());
            userInfo.setUserEntity(sysUser);
            userInfo.setDefaultLang(new Locale("zh", "CN"));
            userInfo.setPrincipalGroupCode("00000000000");
            return userInfo;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
