package com.goodsquick.webservice.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.goodsquick.webservice.entity.User;
import com.goodsquick.webservice.service.UserService;

public class SpringUsersWsClient {

	public static void main(String[] args) {  

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        factory.setServiceClass(UserService.class);  
        factory.setAddress("http://localhost:8080/WebServiceTest/Users");  
  
        UserService service = (UserService) factory.create();  
  
        System.out.println("#############Client getUserByName##############");  
        User user = service.getUserByName("hoojo");  
        System.out.println(user.getName());  
  
        user.setAddress("China-Guangzhou");  
        service.setUser(user);  
    }
}
