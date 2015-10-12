package com.goodsquick.webservice.service.impl;

import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.goodsquick.webservice.entity.User;
import com.goodsquick.webservice.service.UserService;

@WebService
@SOAPBinding(style = Style.RPC)
public class UserServiceImpl implements UserService {

	@Override
	public User getUserByName(@WebParam(name="name") String name) {
		User user = new User();  
        user.setId(new Date().getTime());  
        user.setName(name);  
        user.setAddress("china");  
        user.setEmail(name + "@test.com");  
        return user;
	}

	@Override
	public void setUser(User user) {
		System.out.println("############Server setUser###########");  
        System.out.println("setUser:" + user);
	}

}
