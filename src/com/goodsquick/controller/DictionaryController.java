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

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsDictionaryType;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class DictionaryController {
    
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@RequestMapping("/dictionary")
	public ModelAndView dictionary(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/dictionary");
		
		List<GoodsDictionary> dList = new ArrayList<GoodsDictionary>();
		
		List<GoodsDictionaryType> dtList = new ArrayList<GoodsDictionaryType>();
		
		try {
			dtList = dictionaryService.getAllDictionaryType();
			dList = dictionaryService.getAllDictionary();
		} catch (Exception e) {
			logger.error("fail to get the dList,",e);
		}
		
		view.addObject("opened", ",system,");
		view.addObject("actived", ",dictionary,");
		view.addObject("dList", dList);
		view.addObject("dtList", dtList);
		return view;
	}

	@RequestMapping("/dictionarytype")
    public ModelAndView dictionarytype(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("sys/dictionarytype");
        
        List<GoodsDictionaryType> dtList = new ArrayList<GoodsDictionaryType>();
        
        try {
        	dtList = dictionaryService.getAllDictionaryType();
		} catch (Exception e) {
			logger.error("fail to get the dtList,",e);
		}
        
        view.addObject("opened", ",system,");
        view.addObject("actived", ",dictionarytype,");
        view.addObject("dtList", dtList);
        return view;
    }
	
	@RequestMapping("/adddictionarytype")
	public String adddictionarytype(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/adddictionarytype");
		
		try {
			GoodsDictionaryType dt = new GoodsDictionaryType();
			dt.setDtName(request.getParameter("dtName"));
			dt.setDtCode(request.getParameter("dtCode"));
			dt.setDtDesc(request.getParameter("dtDesc"));
			dictionaryService.saveDictionaryType(dt);
		} catch (Exception e) {
			logger.error("fail to add the dt,",e);
		}
		
		return "redirect:dictionarytype";
	}
	
	@RequestMapping("/adddictionary")
	public String adddictionary(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/adddictionary");
		
		try {
			GoodsDictionary gd = new GoodsDictionary();
			gd.setDicName(request.getParameter("dicName"));
			gd.setDicCode(request.getParameter("dicCode"));
			gd.setTypeCode(request.getParameter("dictionaryType"));
			gd.setDicDesc(request.getParameter("dicDesc"));
			dictionaryService.saveDictionary(gd);
		} catch (Exception e) {
			logger.error("fail to add the dictionary,",e);
		}
		
		return "redirect:dictionary";
	}
	
	@ResponseBody
	@RequestMapping("/checknameandcode")
	public Map<String, Object> checknameandcode(HttpServletRequest request){
		Map<String, Object> message = new HashMap<String, Object>();
		String returnMessage = null;
		try {
			String dtName = request.getParameter("dtName");
			String dtCode = request.getParameter("dtCode");
			boolean isExists = dictionaryService.checkIfDictionaryTypeCodeOrNameExists(dtCode, dtName);
			
			if( isExists ){
				returnMessage = "数据字典类型编码或者名称已经存在，不能重复添加";
				message.put("status", "failure");
				message.put(GoodsQuickAttributes.WEB_INFO_MESSAGE, returnMessage);
			}else{
				message.put("status", "success");
			}
			
		} catch (Exception e) {
			logger.error("fail to check the name and code,",e);
		}
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/checkIfDictionaryExists")
	public Map<String, Object> checkIfDictionaryExists(HttpServletRequest request){
		Map<String, Object> message = new HashMap<String, Object>();
		try {
			String dicName = request.getParameter("dicName");
			String dtCode = request.getParameter("dtCode");
			boolean isExists = dictionaryService.checkIfDictionaryExists(dtCode, dicName);
			
			if( isExists ){
				message.put("status", "failure");
				message.put(GoodsQuickAttributes.WEB_INFO_MESSAGE, "该分类下的数据项名称已经存在，不能重复添加");
			}else{
				message.put("status", "success");
			}
			
		} catch (Exception e) {
			logger.error("fail to check the dictionary name and type code,",e);
		}
		
		return message;
	}
}
