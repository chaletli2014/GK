package com.goodsquick.webservice.rest.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.goodsquick.webservice.entity.User;
import com.goodsquick.webservice.rest.service.UserRestService;

@Path(value = "/user/")  
public class UserRestServiceImpl implements UserRestService {
    @GET  
    @Path("/user/{id}")  
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public User getUser(@PathParam("id") int id) {  
        User user = new User();
        user.setId(id);
        user.setName("test_name");
        return user;
    }  
}
