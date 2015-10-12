package com.goodsquick.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OfficeBuilding {


    @RequestMapping("/officebuilding")
    public ModelAndView officebuilding(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	view.addObject("opened", ",estate,business,");
			view.addObject("actived", ",officebuilding,");
		} catch (Exception e) {
			
		}
        
        view.setViewName("officebuilding");
        return view;
    }
    
    @RequestMapping("/addofficebuilding")
    public ModelAndView addofficebuilding(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		view.addObject("opened", ",estate,business,");
    		view.addObject("active", ",officebuilding,");
    	} catch (Exception e) {
    		
    	}
    	
    	view.setViewName("addofficebuilding");
    	return view;
    }
    
    @RequestMapping("/officebuildingdevice")
    public ModelAndView officebuildingdevice(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		view.addObject("opened", ",estate,business,");
    		view.addObject("active", ",officebuilding,");
    	} catch (Exception e) {
    		
    	}
    	
    	view.setViewName("officebuildingdevice");
    	return view;
    }
}
