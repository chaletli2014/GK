package com.goodsquick.controller;

import java.util.Date;
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
import com.goodsquick.model.GoodsProductObj;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsProductLiftService;
import com.goodsquick.service.GoodsProductService;
import com.goodsquick.utils.GoodsDateUtil;
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
	
	@RequestMapping("/newGoodsProductPre")
	public ModelAndView newGoodsProductPre(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			List<GoodsDictionary> productTypes = dictionaryService.getDictionaryByType("productType1");
			
			view.addObject("productTypes", productTypes);
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",newProduct,");
		} catch (Exception e) {
			logger.error("fail to show add product pre,",e);
		}
		
		view.setViewName("productRepo/addProductPre");
		return view;
	}
	
	@RequestMapping("/newGoodsProductObj")
	public ModelAndView newGoodsProduct(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			String productType = request.getParameter("productType");
			view.addObject("productType", productType);
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",newProduct,");
		} catch (Exception e) {
			logger.error("fail to show add product obj,",e);
		}
		
		view.setViewName("productRepo/addProductObj");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/saveOrUpdateProductObj")
	public Map<String, Object> saveOrUpdateProductObj(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String productId = request.getParameter("productId");
			String productType = request.getParameter("productType");
			String productName = request.getParameter("productName");
			String productBrand = request.getParameter("productBrand");
			String productModel = request.getParameter("productModel");
			String itemCode = request.getParameter("itemCode");
			Date productDom = GoodsDateUtil.getDateFormat(request.getParameter("productDom"));
			int productQA = GoodsQuickUtils.parseIntegerFromString(request.getParameter("productQA"));
			double productPrice = GoodsQuickUtils.parseDoubleFromString(request.getParameter("productPrice"));
			String remark = request.getParameter("remark");
			
			GoodsProductObj productObjFromPage = new GoodsProductObj();
			productObjFromPage.setId(GoodsQuickUtils.parseIntegerFromString(productId));
			productObjFromPage.setProductType(productType);
			productObjFromPage.setProductName(productName);
			productObjFromPage.setRemark(remark);
			productObjFromPage.setRepositoryCode(repositoryCode);
			productObjFromPage.setProductBrand(productBrand);
			productObjFromPage.setProductModel(productModel);
			productObjFromPage.setItemCode(itemCode);
			productObjFromPage.setProductDom(productDom);
			productObjFromPage.setProductQA(productQA);
			productObjFromPage.setProductPrice(productPrice);
			
			goodsProductService.saveOrUpdateProductObj(productObjFromPage, currentUser);
			
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
	
	@RequestMapping("/productlist")
    public ModelAndView productlist(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
    		
        	List<GoodsProduct> products = goodsProductService.getGoodsProductByRepositoryCode(repositoryCode);
        	List<GoodsProductObj> productObjs = goodsProductService.getProductObjByRepositoryCode(repositoryCode);
        	
        	List<GoodsDictionary> productTypes = dictionaryService.getDictionaryByType("productType1");
        	
        	view.addObject("products", products);
        	view.addObject("productObjs", productObjs);
        	view.addObject("productTypes", productTypes);
        	view.addObject("opened", ",productManagement,");
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
	
	@RequestMapping("/removeProductObj")
	@ResponseBody
	public Map<String,Object> removeProductObj(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsProductObj goodsProductFromPage = new GoodsProductObj();
			String productId = request.getParameter("productId");
			
			goodsProductFromPage.setId(GoodsQuickUtils.parseIntegerFromString(productId));
			goodsProductFromPage.setUpdateUser(currentUser.getLoginName());
			
			goodsProductService.deleteProductObj(goodsProductFromPage);
			
		} catch (Exception e) {
			logger.error("fail to remove repository,",e);
		}
		return resultMap;
	}
	

	@RequestMapping("/getProductInfoById")
	@ResponseBody
	public Map<String,Object> getProductInfoById(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			int productId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("productId"));
			GoodsProductObj productObj = goodsProductService.getProductObjById(productId);
			
			result.put("productObj", productObj);
			result.put("result", "Y");
		} catch (Exception e) {
			logger.error("getLiftInfoById: 根据ID获取实物类产品信息失败",e);
			result.put("result", "N");
			result.put("message", e.getMessage());
		}
		
		return result;
	}
}
