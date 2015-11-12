package com.goodsquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.RepositoryService;
import com.goodsquick.service.WebUserService;
import com.goodsquick.utils.GoodsQuickAttributes;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);
    
    @Autowired
    @Qualifier("webUserService")
    private WebUserService webUserService;
    
    @Autowired
    @Qualifier("repositoryService")
    private RepositoryService repositoryService;
    
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        
        if( null != request.getSession(true).getAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE)){
            view.addObject(GoodsQuickAttributes.WEB_LOGIN_MESSAGE,(String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE));
        }
        request.getSession().removeAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE);
        
        view.setViewName("login");
        return view;
    }
    
    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request){
        try{
            String username = request.getParameter("web_username");
            String password = request.getParameter("web_password");
            WebUserInfo webUser = webUserService.getWebUser(username, password);
            if( webUser == null ){
                request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE,GoodsQuickAttributes.WEB_LOGIN_MESSAGE_NO_USER);
                return "redirect:login";
            }else{
            	List<GoodsRepository> repositoryList = repositoryService.getRepositoryByLoginName(webUser.getLoginName());
            	request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_LIST, repositoryList);
            	
                request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_LOGIN_USER, webUser);
                return "redirect:index";
            }
        }catch(Exception e){
            logger.error("fail to login",e);
            request.getSession(true).setAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE,e.getMessage());
            return "redirect:login";
        }
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute(GoodsQuickAttributes.WEB_LOGIN_MESSAGE);
        request.getSession().removeAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        return "redirect:login";
    }
}
