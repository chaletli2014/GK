package com.goodsquick.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsRepositoryUser;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.service.WebUserService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class RepositoryController {

    Logger logger = Logger.getLogger(RepositoryController.class);
    
    @Autowired
    @Qualifier("repositoryService")
    private RepositoryService repositoryService;
    
    @Autowired
    @Qualifier("webUserService")
    private WebUserService webUserService;
    
	@RequestMapping("/saveOrUpdateRepository")
	@ResponseBody
	public Map<String,Object> saveOrUpdateRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsRepository goodsRepositoryFromPage = new GoodsRepository();
			String repositoryId = request.getParameter("repositoryId");
			String repositoryName = request.getParameter("repositoryName");
			String repositoryDesc = request.getParameter("repositoryDesc");
			String repositoryType = request.getParameter("repositoryType");
			
			goodsRepositoryFromPage.setId(GoodsQuickUtils.parseIntegerFromString(repositoryId));
			goodsRepositoryFromPage.setRepositoryName(repositoryName);
			goodsRepositoryFromPage.setRepositoryDesc(repositoryDesc);
			goodsRepositoryFromPage.setRepositoryType(repositoryType);
			goodsRepositoryFromPage.setCreateUser(currentUser.getLoginName());
			goodsRepositoryFromPage.setUpdateUser(currentUser.getLoginName());
			
			List<GoodsRepository> repositoryList = repositoryService.saveOrUpdateRepository(request, goodsRepositoryFromPage);
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_LIST, repositoryList);
			
			if( StringUtils.isBlank(repositoryId) ){
				resultMap.put("result", "NEW");
			}else{
				resultMap.put("result", "UPDATE");
			}
			resultMap.put("repositoryCode", request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE));
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/removeRepository")
	@ResponseBody
	public Map<String,Object> removeRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			GoodsRepository goodsRepositoryFromPage = new GoodsRepository();
			String repositoryId = request.getParameter("repositoryId");
			
			goodsRepositoryFromPage.setId(GoodsQuickUtils.parseIntegerFromString(repositoryId));
			goodsRepositoryFromPage.setUpdateUser(currentUser.getLoginName());
			
			repositoryService.removeRepository(goodsRepositoryFromPage);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to remove repository,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}
	
	@RequestMapping("/getRepositoryByName")
	@ResponseBody
	public Map<String,Object> getRepositoryByName(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String repositoryName = request.getParameter("repositoryName");
			List<GoodsRepository> repos = repositoryService.getRepositoryByName(repositoryName);
			if( CollectionUtils.isNotEmpty(repos) ){
				resultMap.put("result", "Y");
			}else{
				resultMap.put("result", "N");
			}
		} catch (Exception e) {
			logger.error("fail to remove repository,",e);
			resultMap.put("result", "E");
		}
		return resultMap;
	}
	
	@RequestMapping("/repositorylist")
	public ModelAndView repositorylist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("rep/repositorylist");
		
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			List<GoodsRepository> repositoryList = repositoryService.getRepositoryByLoginName(currentUser.getLoginName());
		
			view.addObject("repositoryList", repositoryList);
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		view.addObject("opened", ",system,");
        view.addObject("actived", ",repository,");
		return view;
	}
	
	@RequestMapping("/getRepository")
	@ResponseBody
	public Map<String,Object> getRepository(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			List<GoodsRepository> repositoryList1 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"1",false);
			List<GoodsRepository> repositoryList2 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"2",false);
			List<GoodsRepository> repositoryList3 = repositoryService.getRepositoryByLoginNameAndType(currentUser.getLoginName(),"3",false);
			
			resultMap.put("repositoryList1", repositoryList1);
			resultMap.put("repositoryList2", repositoryList2);
			resultMap.put("repositoryList3", repositoryList3);
			
		} catch (Exception e) {
			logger.error("fail to get repository by type,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/getRepositoryByCode")
	@ResponseBody
	public Map<String,Object> getRepositoryByCode(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String repositoryCode = request.getParameter("repositoryCode");
			GoodsRepository repository = repositoryService.getRepositoryByCode(repositoryCode);
			resultMap.put("repository", repository);
		} catch (Exception e) {
			logger.error("fail to get repository by code,",e);
		}
		return resultMap;
	}

	@RequestMapping("/repositoryUser")
	public ModelAndView repositoryUser(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("rep/repositoryUser");
		
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			List<GoodsRepositoryUser> repositoryUserList = repositoryService.getRepositoryUserByRepositoryCode(repositoryCode,currentUser.getLoginName());
			view.addObject("repositoryUserList", repositoryUserList);
		} catch (Exception e) {
			logger.error("fail to get the top level category,",e);
		}
		view.addObject("opened", ",userManagement,");
        view.addObject("actived", ",repositoryUser,");
		return view;
	}
	
	@RequestMapping("/saveRepositoryUser")
	@ResponseBody
	public Map<String,Object> saveRepositoryUser(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			
			String selectedId = request.getParameter("selectedId");
			WebUserInfo userInfo = webUserService.getUserProfileById(selectedId);
			
			List<GoodsRepositoryUser> dbRepoUser = repositoryService.getRepositoryUserByUserId(repositoryCode, selectedId);
			
			GoodsRepositoryUser repositoryUser = new GoodsRepositoryUser();
			repositoryUser.setRepositoryCode(repositoryCode);
			repositoryUser.setUserCode(userInfo.getLoginName());
			repositoryUser.setCreateUser(currentUser.getLoginName());
			repositoryUser.setUpdateUser(currentUser.getLoginName());
			repositoryUser.setPriv("r");
			
			if( CollectionUtils.isEmpty(dbRepoUser) ){
				repositoryService.saveRepositoryUser(repositoryUser);
				resultMap.put("result", "Y");
			}else if( dbRepoUser.size() == 1 && "0".equalsIgnoreCase(dbRepoUser.get(0).getStatus()) ){
				repositoryService.updateRepositoryUser(repositoryUser);
				resultMap.put("result", "Y");
			}else{
				resultMap.put("result", "E");
			}
			
		} catch (Exception e) {
			logger.error("fail to save user to repository,",e);
		}
		return resultMap;
	}
	

	@RequestMapping("/removeRepositoryUser")
	@ResponseBody
	public Map<String,Object> removeRepositoryUser(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String repoUserId = request.getParameter("repoUserId");
			GoodsRepositoryUser dbRepoUser = new GoodsRepositoryUser();
			dbRepoUser.setId(Integer.valueOf(repoUserId));
			dbRepoUser.setUpdateUser(currentUser.getLoginName());
			repositoryService.removeRepositoryUser(dbRepoUser);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to remove repository user,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}
	
	@RequestMapping("/getCommunityRepos")
	@ResponseBody
	public Map<String,Object> getCommunityRepos(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			List<GoodsOrdinaryHouse> communityAssetList = repositoryService.getRepositoryAssetByLoginNameAndType(currentUser.getLoginName(),"1",true);
			resultMap.put("communityAssetList", communityAssetList);
			resultMap.put("result", "Y");
		} catch (Exception e) {
			logger.error("fail to get the community repositorys,",e);
			resultMap.put("result", "N");
		}
		return resultMap;
	}
}