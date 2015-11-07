/**
 * 
 */
package com.goodsquick.web.security;

import java.util.Locale;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.goodsquick.web.security.authentication.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	return null;
    }

}
