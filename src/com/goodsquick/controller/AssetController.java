package com.goodsquick.controller;

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
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsProductLiftService;
import com.goodsquick.service.GoodsProductService;
import com.goodsquick.service.GoodsSourceFileService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.GoodsQuickAttributes;

/**
 * 资品库
 * @author chalet
 *
 */
@Controller
public class AssetController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;
	
	@RequestMapping("/newAssetPre")
	public ModelAndView newAssetPre(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			List<GoodsDictionary> productTypes = dictionaryService.getDictionaryByType("template_estate");
			
			view.addObject("productTypes", productTypes);
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",newProduct,");
		} catch (Exception e) {
			logger.error("fail to show add asset pre,",e);
		}
		
		view.setViewName("asset/addAssetPre");
		return view;
	}
	
	@RequestMapping("/checkOwnedHouse")
    @ResponseBody
    public Map<String,String> checkOwnedHouse(HttpServletRequest request){
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		GoodsOrdinaryHouse oh = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
    		if( null == oh ){
    			result.put("result", "N");
    		}else{
    			result.put("result", "Y");
    		}
    	} catch (Exception e) {
    		logger.error("ordinaryHousedevice: 获取不动产服务商信息失败",e);
    	}
    	return result;
    }
}
