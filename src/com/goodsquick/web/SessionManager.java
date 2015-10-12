/*
 * Copyright (c) 2008-2014 VIP.COM, All rights reserved.
 */

package com.goodsquick.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goodsquick.web.security.UserDetails;

public class SessionManager {
	private static Logger logger = LoggerFactory.getLogger(SessionManager.class);
	private static final ThreadLocal<HttpSession> httpSession = new ThreadLocal<HttpSession>();
	public static final String USER_DETAIL_KEY = "userDetails";

	public static void setSession(HttpSession paramHttpSession) {
		httpSession.set(paramHttpSession);
	}

	public static HttpSession getSession() {
		return (HttpSession) httpSession.get();
	}

	public static void setAttribute(String paramString, Object paramObject) {
		HttpSession localHttpSession = (HttpSession) httpSession.get();
		if (localHttpSession != null)
			localHttpSession.setAttribute(paramString, paramObject);
		else
			logger.warn("session not exist can not set attribute with key = '" + paramString + "' value = [" + paramObject + "]");
	}

	public static Object getAttribute(String paramString) {
	    try {
    		HttpSession localHttpSession = (HttpSession) httpSession.get();
    		if (localHttpSession != null)
    			return localHttpSession.getAttribute(paramString);
	    } catch (IllegalStateException e) {
	        logger.warn("Error while trying to get attribute '" + paramString + "':" + e);
	    }
		return null;
	}

	public static UserDetails getUserDetails() {
		return (UserDetails) getAttribute(USER_DETAIL_KEY);
	}

	public static void setUserDetails(UserDetails paramUserDetail) {
		if (logger.isDebugEnabled())
			logger.debug("bind user [" + paramUserDetail.getUsername() + "] to current session :" + httpSession.get());
		setAttribute(USER_DETAIL_KEY, paramUserDetail);
	}

	public static String getUsername() {
		UserDetails localUserDetail = getUserDetails();
		if (localUserDetail != null)
			return localUserDetail.getUsername();
		logger.debug("user details is null return null user code");
		return null;
	}
}

