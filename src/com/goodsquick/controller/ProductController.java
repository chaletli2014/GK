package com.goodsquick.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsProductLiftService;
import com.goodsquick.service.GoodsProductService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class ProductController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("goodsProductService")
	private GoodsProductService goodsProductService;
	
	@Autowired
	@Qualifier("goodsProductLiftService")
	private GoodsProductLiftService goodsProductLiftService;
	
	@RequestMapping("/productlist")
    public ModelAndView productlist(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
        	List<GoodsProduct> products = goodsProductService.getGoodsProductByRepositoryCode(repositoryCode);
        	
        	List<GoodsDictionary> productTypes = dictionaryService.getDictionaryByType("productType1");
        	
        	List<GoodsProductLift> lifts = goodsProductLiftService.getGoodsProductLiftByRepositoryCode(repositoryCode);
        	
        	view.addObject("lifts", lifts);
        	view.addObject("products", products);
        	view.addObject("productTypes", productTypes);
			view.addObject("actived", ",productlist,");
		} catch (Exception e) {
			logger.error("fail to show product list,",e);
		}
        
        view.setViewName("productRepo/productlist");
        return view;
    }
	
	@ResponseBody
	@RequestMapping("/saveOrUpdateProduct")
	public Map<String, Object> saveOrUpdateProduct(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String productId = request.getParameter("productId");
			String productType = request.getParameter("productType");
			String productName = request.getParameter("productName");
			double productPrice = GoodsQuickUtils.parseDoubleFromString(request.getParameter("productPrice"));
			String remark = request.getParameter("remark");
			
			GoodsProduct productFromPage = new GoodsProduct();
			productFromPage.setId(GoodsQuickUtils.parseIntegerFromString(productId));
			productFromPage.setProductType(productType);
			productFromPage.setProductName(productName);
			productFromPage.setProductPrice(productPrice);
			productFromPage.setRemark(remark);
			productFromPage.setRepositoryCode(repositoryCode);
			
			goodsProductService.saveOrUpdateGoodsProduct(productFromPage, currentUser);
			
			if( StringUtils.isBlank(productId) ){
				result.put("result", "NEW");
			}else{
				result.put("result", "UPDATE");
			}
		} catch (Exception e) {
			logger.error("fail to check the name and code,",e);
		}
		
		return result;
	}
	
	@RequestMapping("/removeProduct")
	@ResponseBody
	public Map<String,Object> removeProduct(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsProduct goodsProductFromPage = new GoodsProduct();
			String productId = request.getParameter("productId");
			
			goodsProductFromPage.setId(GoodsQuickUtils.parseIntegerFromString(productId));
			goodsProductFromPage.setUpdateUser(currentUser.getLoginName());
			
			goodsProductService.deleteGoodsProduct(goodsProductFromPage);
			
		} catch (Exception e) {
			logger.error("fail to remove repository,",e);
		}
		return resultMap;
	}
}
