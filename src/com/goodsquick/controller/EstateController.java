package com.goodsquick.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstateController {
	
	Logger logger = Logger.getLogger(EstateController.class);

	@RequestMapping("/myestate")
    public ModelAndView myestate(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	view.addObject("opened", ",estategoods,");
			view.addObject("actived", ",myestate,");
		} catch (Exception e) {
			
		}
        
        view.setViewName("myestate");
        return view;
    }
    
    @RequestMapping("/addmyestate")
    public ModelAndView addofficebuilding(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		view.addObject("opened", ",estategoods,");
			view.addObject("actived", ",myestate,");
    	} catch (Exception e) {
    		
    	}
    	
    	view.setViewName("addmyestate");
    	return view;
    }
    
    @RequestMapping("/myestatedetail")
    public ModelAndView myestatedetail(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		view.addObject("opened", ",estategoods,");
    		view.addObject("actived", ",myestate,");
    	} catch (Exception e) {
    		
    	}
    	
    	view.setViewName("myestatedetail");
    	return view;
    }
    
    @ResponseBody
    @RequestMapping("checkifestateexsits")
    public Map<String, Object> checkifestateexsits(@RequestParam("estateName") String estateName){
    	String message = "";
    	String isExists = "N";
    	try {
			Thread.sleep(1000);
			if( "a".equalsIgnoreCase(estateName) ){
				message = "检索到符合条件的产品";
				isExists = "Y";
			}else if("b".equalsIgnoreCase(estateName)){
				message = "未检索到符合条件的产品";
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	Map<String, Object> modelMap = new HashMap<String, Object>(1);  
        modelMap.put("message",message);
        modelMap.put("isExists",isExists);
        return modelMap;
    }
}
