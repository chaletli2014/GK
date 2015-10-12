/*
 * Copyright (c) 2008-2014 VIP.COM, All rights reserved.
 */

package com.goodsquick.model;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;

import com.goodsquick.web.SessionManager;
import com.goodsquick.web.security.IMenuItem;
import com.goodsquick.web.security.UserDetails;

public class UserInfo implements UserDetails {
	
	private static final long serialVersionUID = 326000000000000L;

	/**
	 * 当前登录角色编码
	 */
	private String roleCode;

	/**
	 * 用户当前操作的模块
	 */
	private String currentModuleCode;

	private String errorMessage;

	private String viewButtonUrl;

	public String getViewButtonUrl() {
		return viewButtonUrl;
	}

	public void setViewButtonUrl(String viewButtonUrl) {
		this.viewButtonUrl = viewButtonUrl;
	}


	/**
	 * 返回用户当前操作的模块代码
	 * 
	 * @return
	 */
	public String getCurrentModuleCode() {
		return currentModuleCode;
	}

	public void setCurrentModuleCode(String currentModuleCode) {
		this.currentModuleCode = currentModuleCode;
	}

	/**
	 * 返回当前用户登录信息，如果没有用户登录（后台运行程序）或用户注销、用户超时，返回 null.
	 * 
	 * @return
	 */
	public static UserInfo getInstance() {
		return (UserInfo) SessionManager.getUserDetails();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    private String password;
    @Override
    public String getPassword() {
        return password;
    }

    private String username;
    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List menuItems;
    
    @Override
    public List<IMenuItem> getMenuItems() {
        return menuItems;
    }

    private List viewButtons;
    
    private Locale defaultLang;
    
    @Override
    public Locale getDefaultLang() {
        return defaultLang;
    }

    private String principalGroupCode;
    @Override
    public String getPrincipalGroupCode() {
        return principalGroupCode;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDefaultLang(Locale defaultLang) {
        this.defaultLang = defaultLang;
    }

    public void setPrincipalGroupCode(String principalGroupCode) {
        this.principalGroupCode = principalGroupCode;
    }

    private User userEntity;
    @Override
    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
