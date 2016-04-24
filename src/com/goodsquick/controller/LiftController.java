package com.goodsquick.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsProductLiftService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class LiftController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("goodsProductLiftService")
	private GoodsProductLiftService goodsProductLiftService;
    
    /**
     * 新增或修改乘客电梯
     * @param request
     * @return
     */
    @RequestMapping("/saveLift")
    public String saveLift(HttpServletRequest request){
    	try {
    		GoodsProductLift goodsProductLift = new GoodsProductLift();
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		goodsProductLift.setRepositoryCode(repositoryCode);
    		
    		populateLiftInfo(goodsProductLift, request);
    		
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	
        	if( null == currentUser ){
        		logger.error("fail to add or update ordinary house, can not get the current user.");
        		request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE, GoodsQuickAttributes.MESSAGE_NO_LOGIN_USER);
        		return "redirect:addordinaryhouse";
        	}
    		
    		goodsProductLiftService.saveOrUpdateGoodsProductLift(goodsProductLift, currentUser);
    	} catch (Exception e) {
    		logger.error("fail to save or update lift,",e);
    	}
    	return "redirect:productlist";
    }
    
	@RequestMapping("/getLiftInfoById")
	@ResponseBody
	public Map<String,Object> getLiftInfoById(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			int liftId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("liftId"));
			GoodsProductLift liftObj = goodsProductLiftService.getGoodsProductLiftById(liftId);
			
			result.put("liftObj", liftObj);
			result.put("result", "Y");
		} catch (Exception e) {
			logger.error("getLiftInfoById: 根据ID获取电梯信息失败",e);
			result.put("result", "N");
			result.put("message", e.getMessage());
		}
		
		return result;
	}
    
    private void populateLiftInfo(GoodsProductLift lift, HttpServletRequest request){

    	String liftId = request.getParameter("liftId");
    	if( null != liftId && !"".equalsIgnoreCase(liftId) ){
    		lift.setId(Integer.valueOf(liftId));
    	}
    	
    	lift.setLiftBrand(request.getParameter("liftBrand"));
    	lift.setLiftPurpose(request.getParameter("liftPurpose"));
    	lift.setLiftStyle(request.getParameter("liftStyle"));
    	lift.setLiftCT(GoodsQuickUtils.parseDoubleFromString(request.getParameter("liftCT")));
    	lift.setLiftNS(GoodsQuickUtils.parseDoubleFromString(request.getParameter("liftNS")));
    	lift.setLiftQA(GoodsQuickUtils.parseIntegerFromString(request.getParameter("liftQA")));
    	lift.setPrice(GoodsQuickUtils.parseDoubleFromString(request.getParameter("liftPrice")));
    	lift.setHoleSize(GoodsQuickUtils.parseDoubleFromString(request.getParameter("holeSize")));
    	lift.setPitDepth(GoodsQuickUtils.parseIntegerFromString(request.getParameter("pitDepth")));
    	lift.setOverheadHeight(GoodsQuickUtils.parseIntegerFromString(request.getParameter("overheadHeight")));
    	lift.setReservation(request.getParameter("reservation"));
    	lift.setRoomSize(request.getParameter("roomSize"));
    	lift.setRoomHeight(GoodsQuickUtils.parseIntegerFromString(request.getParameter("roomHeight")));
    	lift.setCarSize(request.getParameter("carSize"));
    	lift.setCarHeight(GoodsQuickUtils.parseIntegerFromString(request.getParameter("carHeight")));
    	lift.setDoorSize(request.getParameter("doorSize"));
    	lift.setMainPower(GoodsQuickUtils.parseIntegerFromString(request.getParameter("mainPower")));
    	lift.setMadeDate(new Date());
    }
}
