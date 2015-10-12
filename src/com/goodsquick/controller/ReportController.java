package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsChartObj3;
import com.goodsquick.service.ReportService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class ReportController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("reportService")
	private ReportService reportService;
	
	@RequestMapping("/myReport")
    public ModelAndView myReport(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
			
			view.addObject("opened", ",system,");
			view.addObject("actived", ",myReport,");
			
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
    		
		} catch (Exception e) {
			logger.error(String.format("fail to get the report list,%s",e.getMessage()),e);
		}
        
        view.setViewName("report/myReport");
        return view;
    }
	
	@RequestMapping("/houseData")
	@ResponseBody
    public Map<String,Object> houseData(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
			List<GoodsChartObj1> chart1Objs = reportService.getHouseVolumePerYear();
			resultMap.put("chart1Objs", chart1Objs);
		} catch (Exception e) {
			logger.error("获取不动产建筑年代数据失败",e);
		}
        return resultMap;
    }
	
	@RequestMapping("/liftArea")
	@ResponseBody
	public Map<String,Object> liftArea(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			List<GoodsChartObj1> liftArea = reportService.getliftArea();
			List<String> cities = new ArrayList<String>();
			for( GoodsChartObj1 area : liftArea ){
				cities.add(area.getxName());
			}
			resultMap.put("liftArea", liftArea);
		} catch (Exception e) {
			logger.error("获取电梯分布图失败",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/liftBrandDetail")
	@ResponseBody
	public Map<String,Object> liftBrandDetail(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String city = request.getParameter("city");
			List<GoodsChartObj1> brandDetails = reportService.getliftBrandDetail(city);
			resultMap.put("brandDetails", brandDetails);
		} catch (Exception e) {
			logger.error("获取电梯品牌分布图失败",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/liftSalesDetail")
	@ResponseBody
	public Map<String,Object> liftSalesDetail(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			List<GoodsChartObj3> salesDetails = reportService.getliftSalesDetail();
			resultMap.put("salesDetails", salesDetails);
		} catch (Exception e) {
			logger.error("获取电梯销售曲线失败",e);
		}
		return resultMap;
	}
}
