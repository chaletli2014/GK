package com.goodsquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsRadarService;
import com.goodsquick.service.LiftService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class GoodsRadarController {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("liftService")
	private LiftService liftService;
	
	@Autowired
	@Qualifier("goodsRadarService")
	private GoodsRadarService goodsRadarService;

	@RequestMapping("/houseRadar")
	public ModelAndView houseRadar(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			List<GoodsDictionary> spTypes = dictionaryService.getDictionaryByType("houseRadar");
			view.addObject("spTypes", spTypes);
			
			String spTypeCode = request.getParameter("spTypeCode");
			if( StringUtils.isBlank(spTypeCode) ){
				spTypeCode = (String)request.getSession().getAttribute("spTypeCode");
			}else{
				request.getSession().setAttribute("spTypeCode", spTypeCode);
			}
			view.addObject("spTypeCode", spTypeCode);
			
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			List<GoodsCompanyInfo> companyInfos = goodsRadarService.getCompanyInfoByRepositoryCode(repositoryCode);
			view.addObject("companyInfos", companyInfos);
		} catch (Exception e) {
			logger.error("fail to show the house radar ",e);
		}
		
		view.setViewName("goodsRadar/houseRadar");
		view.addObject("opened", ",serviceCustomer,");
		view.addObject("actived", ",houseRadar,");
		return view;
	}
}
