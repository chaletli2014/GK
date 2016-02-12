package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.goodsquick.model.GoodsHouseOther;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsHouseOtherService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class HouseOtherController {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@Autowired
	@Qualifier("goodsHouseOtherService")
	private GoodsHouseOtherService goodsHouseOtherService;

	@RequestMapping("/houseOtherList")
	public ModelAndView houseOtherList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("houseOther/houseOtherList");
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		try {
			List<GoodsHouseOther> houseOthers = goodsHouseOtherService.getAllOther(repositoryCode);
			view.addObject("houseOthers", houseOthers);
			
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",houseOther,");
		} catch (Exception e) {
			logger.error("fail to get the house subject,",e);
		}
		
		return view;
	}
	
	@RequestMapping("/saveOrUpdateOther")
    @ResponseBody
    public Map<String,String> saveOrUpdateOther(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
    		String otherListFromPage = request.getParameter("otherList");
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement el = parser.parse(otherListFromPage);
			
			JsonArray otherArray = new JsonArray();
			if(el.isJsonArray()){
				otherArray = el.getAsJsonArray();
			}
			
			Iterator it = otherArray.iterator();
			List<GoodsHouseOther> otherList = new ArrayList<GoodsHouseOther>();
			while(it.hasNext()){
				JsonElement e = (JsonElement)it.next();
				//JsonElement转换为JavaBean对象
				otherList.add(gson.fromJson(e, GoodsHouseOther.class));
			}
    		
			goodsHouseOtherService.saveOrUpdateHouseOther(otherList, currentUser.getLoginName(), repositoryCode);
    		result.put("result", "Y");
    	} catch (Exception e) {
    		logger.error("saveHouseOther: 保存设施设备失败",e);
    		result.put("result", "N");
    		result.put("message", e.getMessage());
    	}
    	
    	return result;
    }
}
