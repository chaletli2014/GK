package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
import com.goodsquick.service.GoodsDataService;
import com.goodsquick.service.GoodsProductLiftService;
import com.goodsquick.service.GoodsRadarService;
import com.goodsquick.service.GoodsServiceService;
import com.goodsquick.service.GoodsSourceFileService;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class IndexController {
	
    @Autowired
    @Qualifier("repositoryService")
    private RepositoryService repositoryService;
    
    @Autowired
    @Qualifier("ordinaryHouseService")
    private OrdinaryHouseService ordinaryHouseService;
    
    @Autowired
    @Qualifier("goodsProductLiftService")
    private GoodsProductLiftService goodsProductLiftService;
    
    @Autowired
    @Qualifier("goodsRadarService")
    private GoodsRadarService goodsRadarService;
	
	@Autowired
	@Qualifier("goodsSourceFileService")
	private GoodsSourceFileService goodsSourceFileService;
	
	@Autowired
	@Qualifier("dictionaryService")
	private DictionaryService dictionaryService;
	
	@Autowired
	@Qualifier("goodsServiceService")
	private GoodsServiceService goodsServiceService;
	
	@Autowired
	@Qualifier("goodsDataService")
	private GoodsDataService goodsDataService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/goodsMainIndex")
    public ModelAndView goodsMainIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			view.setViewName("goodsMainIndex");
			
			GoodsOrdinaryHouse house = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
			List<GoodsProductLift> lifts = goodsProductLiftService.getGoodsProductLiftByRepositoryCode(repositoryCode);
			List<GoodsCompanyInfo> companyInfos = goodsRadarService.getCompanyInfoByRepositoryCode(repositoryCode);
			int radarCompanySize = 0;
			if( !CollectionUtils.isEmpty(companyInfos) ){
				radarCompanySize = companyInfos.size();
			}
			view.addObject("house", house);
			view.addObject("lifts", lifts);
			if( !CollectionUtils.isEmpty(companyInfos) ){
				view.addObject("radarCompany", companyInfos.subList(0, 2>radarCompanySize?radarCompanySize:2));
			}
			view.addObject("actived", ",index,");
		}catch(Exception e){
			logger.error("fail to get info in goodsMainIndex",e);
		}
		return view;
	}
	
	@RequestMapping("/productMainIndex")
	public ModelAndView productMainIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("productMainIndex");
		view.addObject("actived", ",index,");
		return view;
	}
	
	@RequestMapping("/requirementMainIndex")
	public ModelAndView requirementMainIndex(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("requirementMainIndex");
		view.addObject("actived", ",index,");
		return view;
	}

    @RequestMapping("/mainIndex")
    public String mainIndex(HttpServletRequest request){
        WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String repositoryCode = request.getParameter(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
        if( null == repositoryCode ){
        	repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
        }else{
        	request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE, repositoryCode);
        }
        
        try {
        	GoodsRepository currentRepository = repositoryService.getRepositoryByCode(repositoryCode);
	        if( null == currentRepository || currentRepository.getId() == 0 ){
	        	return "redirect:pageNotFound";
	        }else{
	        	request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ, currentRepository);
        	
    			String repositoryType = currentRepository.getRepositoryType();
    			
    			if( null != currentUser && "common".equalsIgnoreCase(currentUser.getUserType()) ){
    	        	if( null == repositoryType 
    	        			|| "1".equalsIgnoreCase(repositoryType) ){
    	        		return "redirect:ordinaryhouse";
    	        	}else if( "2".equalsIgnoreCase(repositoryType) ){
    	        		return "redirect:productMainIndex";
    	        	}else{
    	        		return "redirect:requirementMainIndex";
    	        	}
    	        }else if( null != currentUser && "group1".equalsIgnoreCase(currentUser.getUserType())){
    	        	return "redirect:ordinaryhouse";
    	        }
	        }
		} catch (Exception e) {
			logger.error("fail to redirect in mainIndex,",e);
		}
        return "redirect:index";
    }
    
    @RequestMapping("/index")
    public ModelAndView login(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	String viewName = "index";
    	WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	try {
    		List<GoodsRepository> repositoryList1 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",false);
			List<GoodsRepository> repositoryList2 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"2",false);
			List<GoodsRepository> repositoryList3 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"3",false);
			
			view.addObject("repositoryList1", repositoryList1);
			view.addObject("repositoryList2", repositoryList2);
			view.addObject("repositoryList3", repositoryList3);
    		view.addObject("currentUser", currentUser);
    		view.addObject("actived", ",index,");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	view.setViewName(viewName);
    	return view;
    }
    
    @RequestMapping("/communityIndex")
    public ModelAndView communityIndex(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	String viewName = "communityIndex";
    	WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	try {
    		List<GoodsRepository> communityRepoList = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",true);
    		view.addObject("communityRepoList", communityRepoList);
    		
    		List<GoodsChartObj1> densityList = goodsDataService.getDensityData(currentUser.getLoginName(), "lift_num");
    		int liftTotalNum = 0;
    		
    		for( GoodsChartObj1 obj : densityList ){
    			liftTotalNum = liftTotalNum + obj.getyValue();
    		}
    		view.addObject("liftTotalNum", liftTotalNum);
    		
    		view.addObject("currentUser", currentUser);
    		view.addObject("actived", ",index,");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	view.setViewName(viewName);
    	return view;
    }
    

	@RequestMapping("/pageNotFound")
	public ModelAndView pageNotFound(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("404");
		return view;
	}
}
