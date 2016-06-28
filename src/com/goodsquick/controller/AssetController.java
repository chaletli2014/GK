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
	
	@Autowired
	@Qualifier("subjectAndModuleService")
	private SubjectAndModuleService subjectAndModuleService;
	
	@Autowired
	@Qualifier("goodsHouseDeviceService")
	private GoodsHouseDeviceService goodsHouseDeviceService;
	
	@Autowired
	@Qualifier("goodsHouseOtherService")
	private GoodsHouseOtherService goodsHouseOtherService;
	
	@Autowired
	@Qualifier("repositoryService")
	private RepositoryService repositoryService;
	
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
	
	@RequestMapping("/assetlist")
	public ModelAndView assetlist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.addObject("opened", ",productManagement,");
		view.addObject("actived", ",assetlist,");
		view.setViewName("asset/assetlist");
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String contextPath = request.getSession().getServletContext().getContextPath();
			GoodsOrdinaryHouse orHouse = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
			if( orHouse != null && StringUtils.isBlank(orHouse.getMainPic()) ){
				orHouse.setMainPic(contextPath+GoodsQuickAttributes.DEFAULT_HOUSE_PIC);
			}
			view.addObject("orHouse", orHouse);
			
			List<GoodsSubject> subjectList1 = subjectAndModuleService.getSubjectByLevel("1", repositoryCode);
			List<GoodsSubject> subjectList2 = subjectAndModuleService.getSubjectByLevel("2", repositoryCode);
			List<GoodsSubject> subjectList3 = subjectAndModuleService.getSubjectByLevel("3", repositoryCode);
			List<GoodsDictionary> moduleTypes = dictionaryService.getDictionaryByType("subjectModule");
			
			view.addObject("moduleTypes", moduleTypes);
			view.addObject("subjectList1", subjectList1);
			view.addObject("subjectList2", subjectList2);
			view.addObject("subjectList3", subjectList3);
			
			List<GoodsHouseDevice> houseDevices = goodsHouseDeviceService.getAllDevice(repositoryCode);
			view.addObject("houseDevices", houseDevices);
			
			List<GoodsHouseOther> houseOthers = goodsHouseOtherService.getAllOther(repositoryCode);
			view.addObject("houseOthers", houseOthers);
		}catch(Exception e){
			logger.error("fail to get ordinaryhouse,",e);
		}
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
	
	@RequestMapping("/assetGroupOverview")
	public ModelAndView assetGroupOverview(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			List<GoodsRepository> communityRepoList = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",true);
    		view.addObject("communityRepoList", communityRepoList);
    		
			view.addObject("opened", ",productManagement,");
			view.addObject("actived", ",assetGroupOverview,");
		} catch (Exception e) {
			logger.error("fail to show asset overview,",e);
		}
		
		view.setViewName("asset/assetGroupOverview");
		return view;
	}
}
