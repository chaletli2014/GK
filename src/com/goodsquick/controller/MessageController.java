package com.goodsquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.MessageService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class MessageController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;
	
	@RequestMapping("/myMessage")
    public ModelAndView myMessage(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
			List<GoodsRelatedRequest> messagelist = messageService.getMessageList();
			
			view.addObject("messagelist", messagelist);
			view.addObject("opened", ",system,");
			view.addObject("actived", ",myMessage,");
			
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
    		
		} catch (Exception e) {
			logger.error(String.format("fail to get the message list,%s",e.getMessage()),e);
		}
        
        view.setViewName("sys/messagelist");
        return view;
    }
	
	@RequestMapping("/handleMessage")
	public String handleMessage(HttpServletRequest request){
		try {
			int messageId = GoodsQuickUtils.parseIntegerFromString(request.getParameter("messageId"));
			String status = request.getParameter("actionType");
			messageService.handleMessage(messageId, status);
		} catch (Exception e) {
			logger.error(String.format("fail to handle the message ,%s",e.getMessage()),e);
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE,GoodsQuickAttributes.MESSAGE_HANDLE_ERROR+e.getMessage());
		}
		
		return "redirect:myMessage";
	}
}
