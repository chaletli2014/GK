package com.goodsquick.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsHouseOther;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsHouseDeviceService;
import com.goodsquick.service.GoodsHouseOtherService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.service.SubjectAndModuleService;
import com.goodsquick.utils.GoodsQuickAttributes;

/**
 * 应用管理
 * @author chalet
 *
 */
@Controller
public class ApplicationManagementController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/applicationManagement")
	public ModelAndView applicationManagement(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			view.addObject("actived", ",applicationManagement,");
		} catch (Exception e) {
			logger.error("fail to show application m,",e);
		}
		
		view.setViewName("application/applicationManagement");
		return view;
	}
}
