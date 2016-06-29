package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.DictionaryService;
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

    @RequestMapping("/mainIndex")
    public ModelAndView mainIndex(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        String viewName = "mainIndex";
        WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String repositoryCode = request.getParameter(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
        if( null == repositoryCode ){
        	repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
        }
        if( null == repositoryCode ){
        	repositoryCode = currentUser.getLoginName()+"_0";
        }
        request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE, repositoryCode);
        
        try {
			GoodsRepository currentRepository = repositoryService.getRepositoryByCode(repositoryCode);
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ, currentRepository);
			
			String repositoryType = currentRepository.getRepositoryType();
			
			if( null != currentUser && "common".equalsIgnoreCase(currentUser.getUserType()) ){
	        	if( null == repositoryType 
	        			|| "1".equalsIgnoreCase(repositoryType) ){
	        		viewName = "goodsMainIndex";
	        		
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
	        	}else if( "2".equalsIgnoreCase(repositoryType) ){
	        		viewName = "productMainIndex";
	        	}else{
	        		viewName = "requirementMainIndex";
	        	}
	        	view.addObject("actived", ",index,");
	        }else if( null != currentUser && "group1".equalsIgnoreCase(currentUser.getUserType())){
	        	String contextPath = request.getSession().getServletContext().getContextPath();
	        	GoodsOrdinaryHouse orHouse = ordinaryHouseService.getOrdinaryHouseByRepositoryCode(repositoryCode);
	        	if( orHouse != null && StringUtils.isBlank(orHouse.getMainPic()) ){
	        		orHouse.setMainPic(contextPath+GoodsQuickAttributes.DEFAULT_HOUSE_PIC);
	        	}
	        	view.addObject("orHouse", orHouse);
	        	
	        	List<GoodsServiceDetail> serviceDetails = goodsServiceService.getGoodsServiceDetailsByUserCode(currentUser.getLoginName());
	    		view.addObject("serviceDetails", serviceDetails);
	    		
	    		List<GoodsDictionary> moduleTypes = dictionaryService.getDictionaryByType("subjectModule");
	    		view.addObject("moduleTypes", moduleTypes);
	    		
	    		List<GoodsHouseFile> houseFiles = goodsSourceFileService.getGoodsHouseFileByRepositoryCode(repositoryCode);
	    		view.addObject("houseFiles", houseFiles);
	    		
	        	view.addObject("opened", ",productManagement,");
				view.addObject("actived", ",ordinaryhouse,");
				
	        	viewName = "ep/ordinaryhouse";
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        view.setViewName(viewName);
        return view;
    }
    
    @RequestMapping("/index")
    public ModelAndView login(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	String viewName = "index";
    	WebUserInfo currentUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	try {
    		List<GoodsRepository> communityRepositoryList = new ArrayList<GoodsRepository>();
    		
    		if( "group1".equalsIgnoreCase(currentUser.getUserType()) ){
    			communityRepositoryList = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",true);
    		}
    		List<GoodsRepository> repositoryList1 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",false);
			List<GoodsRepository> repositoryList2 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"2",false);
			List<GoodsRepository> repositoryList3 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"3",false);
			
			view.addObject("communityRepositoryList", communityRepositoryList);
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
    		view.addObject("currentUser", currentUser);
    		view.addObject("actived", ",index,");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	view.setViewName(viewName);
    	return view;
    }
}
