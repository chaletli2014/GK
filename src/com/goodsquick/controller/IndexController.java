package com.goodsquick.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class IndexController {
	
    @Autowired
    @Qualifier("repositoryService")
    private RepositoryService repositoryService;

    @RequestMapping("/index")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        String viewName = "index";
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
			
			if( null != currentUser ){
	        	if( "3".equalsIgnoreCase(currentRepository.getRepositoryType()) ){
	        		viewName = "goodsIndex";
	        	}else{
	        		viewName = "goodsServiceIndex";
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        view.setViewName(viewName);
        return view;
    }
}
