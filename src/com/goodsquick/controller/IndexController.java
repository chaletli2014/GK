package com.goodsquick.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.WebUserInfo;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        String viewName = "index";
        WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( null != currentUser ){
        	if( "ON".equalsIgnoreCase(currentUser.getHasHouse()) && "ON".equalsIgnoreCase(currentUser.getHasService()) ){
        		viewName = "goodsServiceIndex";
        	}else if( "ON".equalsIgnoreCase(currentUser.getHasHouse()) ){
        		viewName = "goodsIndex";
        	}else if( "ON".equalsIgnoreCase(currentUser.getHasService()) ){
        		viewName = "serviceIndex";
        	}
        }
        view.setViewName(viewName);
        return view;
    }
}
