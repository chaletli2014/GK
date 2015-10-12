package com.goodsquick.webservice.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.goodsquick.webservice.entity.User;

@Path(value = "/rest/")
public interface UserRestService {

	@GET  
    @Path("/user/{id}")  
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public User getUser(@PathParam("id") int id);
}
