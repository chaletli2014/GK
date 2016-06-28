package com.goodsquick.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsChartObj5;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsDataService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class DataController {
    
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("goodsDataService")
	private GoodsDataService goodsDataService;
	
	@RequestMapping("/locationDis")
	public ModelAndView locationDis(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("da/locationDis");
		view.addObject("opened", ",dataManagement,");
		view.addObject("actived", ",locationDis,");
		return view;
	}
	
	@RequestMapping("/assetYearDis")
	public ModelAndView assetYearDis(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("da/assetYearDis");
		view.addObject("opened", ",dataManagement,");
		view.addObject("actived", ",assetYearDis,");
		return view;
	}
	
	@RequestMapping("/densityDis")
	public ModelAndView densityDis(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("da/densityDis");
		view.addObject("opened", ",dataManagement,");
		view.addObject("actived", ",densityDis,");
		return view;
	}
	
	@RequestMapping("/getAssetYears")
    @ResponseBody
    public Map<String,Object> getAssetYears(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<GoodsChartObj1> chartObjList = new ArrayList<GoodsChartObj1>();
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		
		chartObjList = goodsDataService.getAssetYearData(currentUser.getLoginName());
		resultMap.put("assetYearList", chartObjList);
		return resultMap;
	}
	
	@RequestMapping("/getDensity")
	@ResponseBody
	public Map<String,Object> getDensity(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String densityType = request.getParameter("densityType");
		List<GoodsChartObj1> densityList = new ArrayList<GoodsChartObj1>();
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		
		if( StringUtils.isBlank(densityType) ){
			densityType = "lift_num";
		}
		densityList = goodsDataService.getDensityData(currentUser.getLoginName(), densityType);
		
		List<GoodsChartObj5> chartObjList = new ArrayList<GoodsChartObj5>();
		calculateDensity(chartObjList, densityList);
		resultMap.put("densityList", chartObjList);
		return resultMap;
	}
	
	private void calculateDensity(List<GoodsChartObj5> chartObjList, List<GoodsChartObj1> densityList){
		String url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=BVcqwEMHD74riuQUuyi6rvtdl3bu7aaM&address=";
		for( GoodsChartObj1 obj : densityList ){
			String getLocationURL = url+obj.getxName();
			String json = loadJSON(getLocationURL);
			JSONObject jsonObj = JSONObject.fromObject(json);
			if(jsonObj.get("status").toString().equals("0")){
				double lng=jsonObj.getJSONObject("result").getJSONObject("location").getDouble("lng");
				double lat=jsonObj.getJSONObject("result").getJSONObject("location").getDouble("lat");
				
				GoodsChartObj5 obj5 = new GoodsChartObj5();
				obj5.setC3(obj.getyValue());
				obj5.setC5(lng);
				obj5.setC6(lat);
				chartObjList.add(obj5);
			}
		}
	}
	
	public String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                        yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
}
