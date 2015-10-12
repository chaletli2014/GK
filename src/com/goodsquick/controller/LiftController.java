package com.goodsquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.LiftService;
import com.goodsquick.utils.GoodsDateUtil;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class LiftController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("liftService")
	private LiftService liftService;

    @RequestMapping("/passengerlift")
    public ModelAndView passengerlift(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        	}
        	
        	List<GoodsDeviceLift> lifts = liftService.getDeviceLiftByUserCode(currentUser.getLoginName(),"passengerlift");
        	
        	view.addObject("lifts", lifts);
        	view.addObject("opened", ",product,machine,specialmachine,lift,");
			view.addObject("actived", ",passengerlift,");
		} catch (Exception e) {
			
		}
        view.setViewName("pp/passengerlift");
        return view;
    }
    
    @RequestMapping("/addpassengerlift")
    public ModelAndView addpassengerlift(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		String liftId = request.getParameter("liftId");
    		
    		if( null != liftId && !"".equalsIgnoreCase(liftId) ){
    			GoodsDeviceLift lift = liftService.getGoodsDeviceLiftById(Integer.valueOf(liftId));
    			view.addObject("lift", lift);
    		}
    		
    		view.addObject("opened", ",product,machine,specialmachine,lift,");
    		view.addObject("actived", ",passengerlift,");
    	} catch (Exception e) {
    		
    	}
    	view.setViewName("pp/addpassengerlift");
    	return view;
    }
    
    /**
     * 新增或修改乘客电梯
     * @param request
     * @return
     */
    @RequestMapping("/saveorupdatepl")
    public String saveorupdatepassengerlift(HttpServletRequest request){
    	try {
    		GoodsDeviceLift lift = new GoodsDeviceLift();
    		lift.setLiftType("passengerlift");
    		populateLiftInfo(lift, request);
    		
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("fail to add or update ordinary house, can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        		return "redirect:addordinaryhouse";
        	}
    		
    		liftService.saveOrUpdateDeviceLift(lift, currentUser);
    	} catch (Exception e) {
    		logger.error("fail to save or update lift,",e);
    	}
    	return "redirect:passengerlift";
    }
    
    @RequestMapping("/relatelift")
    public ModelAndView relatelift(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try {
    		view.addObject("opened", ",product,machine,specialmachine,lift,");
    		view.addObject("actived", ",passengerlift,");
    	} catch (Exception e) {
    		
    	}
    	view.setViewName("pp/relatelift");
    	return view;
    }
    
    private void populateLiftInfo(GoodsDeviceLift lift, HttpServletRequest request){

    	String liftId = request.getParameter("liftId");
    	if( null != liftId && !"".equalsIgnoreCase(liftId) ){
    		lift.setId(Integer.valueOf(liftId));
    	}
    	
    	lift.setName(request.getParameter("liftName"));
    	lift.setCompanyCode(request.getParameter("companyCode"));
    	lift.setModel(request.getParameter("model"));
    	lift.setMainArguments(request.getParameter("mainArguments"));
    	lift.setManufacturer(request.getParameter("manufacturer"));
    	lift.setMadeDate(GoodsDateUtil.getDateFormat(request.getParameter("madeDate")));
    }
}
