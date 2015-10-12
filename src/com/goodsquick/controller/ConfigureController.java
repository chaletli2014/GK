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

import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsConfigurationService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class ConfigureController {
	
	Logger logger = Logger.getLogger(ConfigureController.class);
	
	@Autowired
	@Qualifier("goodsConfigurationService")
	private GoodsConfigurationService goodsConfigurationService;

	@RequestMapping("/configure")
    public ModelAndView configure(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("sys/configuration");
        try{
        	List<GoodsConfiguration> goodsConfig = new ArrayList<GoodsConfiguration>();
        	goodsConfig = goodsConfigurationService.getAllConfiguration();
        	view.addObject("allConfigs", goodsConfig);
        }catch(Exception e){
        	logger.error("获取配置列表失败，",e);
        }
        view.addObject("opened", ",system,");
        view.addObject("actived", ",configure,");
        return view;
    }
	
	@RequestMapping("/addconfig")
	public String addconfig(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/addconfig");
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		try {
			GoodsConfiguration config = new GoodsConfiguration();
			config.setConfigCode(request.getParameter("configCode"));
			config.setConfigValue(request.getParameter("configValue"));
			goodsConfigurationService.saveConfiguration(config, currentUser);
		} catch (Exception e) {
			logger.error("fail to add the dictionary,",e);
		}
		
		return "redirect:configure";
	}
	
	@ResponseBody
	@RequestMapping("/checkIfConfigExists")
	public Map<String, Object> checkIfConfigExists(HttpServletRequest request){
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			String configCode = request.getParameter("configCode");
			boolean isExists = goodsConfigurationService.checkIfConfigExists(configCode);
			
			if( isExists ){
				message.put("status", "failure");
				message.put(GoodsQuickAttributes.WEB_INFO_MESSAGE, "配置项编码已经存在，不能重复添加");
			}else{
				message.put("status", "success");
			}
			
		} catch (Exception e) {
			logger.error("fail to check the config code,",e);
		}
		
		return message;
	}
}
