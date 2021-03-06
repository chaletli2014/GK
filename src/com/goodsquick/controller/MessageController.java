package com.goodsquick.controller;

import java.util.ArrayList;
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

import com.goodsquick.model.GoodsMessage;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.MessageService;
import com.goodsquick.service.RelationshipPropertyService;
import com.goodsquick.service.WebUserService;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class MessageController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("relationshipPropertyService")
	private RelationshipPropertyService relationshipPropertyService;
	
	@Autowired
	@Qualifier("webUserService")
	private WebUserService webUserService;
	
	@RequestMapping("/myMessage")
    public ModelAndView myMessage(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	List<GoodsRelatedRequest> messagelist = messageService.getMessageList();
			
			view.addObject("messagelist", messagelist);
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
	
	@RequestMapping("/getUnReadMessage")
	@ResponseBody
	public Map<String,Object> getUnReadMessage(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			
			resultMap.put("messageSize", "0");
		} catch (Exception e) {
			logger.error("fail to get the message,",e);
		}
		return resultMap;
	}

	@RequestMapping("/createNewMsg")
    public ModelAndView createNewMsg(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
			view.addObject("actived", ",createNewMsg,");
			view.addObject("opened", ",msgManagement,");
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
		} catch (Exception e) {
			logger.error(String.format("fail to get the inbox message list,%s",e.getMessage()),e);
		}
        
        view.setViewName("msg/createNewMsg");
        return view;
    }
	
	@RequestMapping("/getInBox")
    public ModelAndView getInBox(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
        	List<GoodsMessage> inBoxMessagelist = messageService.getInBoxMessageListByRepo(currentUser.getId());
			
			view.addObject("messagelist", inBoxMessagelist);
			view.addObject("actived", ",inBox,");
			view.addObject("opened", ",msgManagement,");
			
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		if( null != errorMessage ){
    			view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
    			request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
    		}
		} catch (Exception e) {
			logger.error(String.format("fail to get the inbox message list,%s",e.getMessage()),e);
		}
        
        view.setViewName("msg/inbox");
        return view;
    }
	
	@RequestMapping("/getOutBox")
	public ModelAndView getOutBox(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			List<GoodsMessage> messagelist = messageService.getOutBoxMessageListByRepo(currentUser.getId());
			
			view.addObject("messagelist", messagelist);
			view.addObject("actived", ",outBox,");
			view.addObject("opened", ",msgManagement,");
			
			Object errorMessage = request.getSession().getAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
			if( null != errorMessage ){
				view.addObject(GoodsQuickAttributes.WEB_ERROR_MESSAGE_HIDDEN, (String)errorMessage);
				request.getSession().removeAttribute(GoodsQuickAttributes.WEB_ERROR_MESSAGE);
			}
		} catch (Exception e) {
			logger.error(String.format("fail to get the outbox message list,%s",e.getMessage()),e);
		}
		
		view.setViewName("msg/outbox");
		return view;
	}
	

	@RequestMapping("/sendNewMsg")
	@ResponseBody
	public Map<String,Object> sendNewMsg(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String messageType = request.getParameter("msgType");
			String targetUserIds = request.getParameter("targetUserIds");
			String messageTitle = request.getParameter("msgTitle");
			String messageContent = request.getParameter("msgContent");
			
			GoodsMessage msg = new GoodsMessage();
			msg.setMessageType(messageType);
			msg.setMessageTitle(messageTitle);
			msg.setMessageContent(messageContent);
			
			if( StringUtils.isNotBlank(targetUserIds) ){
				String[] userIds = targetUserIds.split(",");
				if( null != userIds ){
					List<Long> targetUsers = new ArrayList<Long>();
					for( String userId : userIds ){
						if( StringUtils.isNotBlank(userId) ){
							targetUsers.add(Long.valueOf(userId));
						}
					}
					msg.setTargetUsers(targetUsers);
				}
			}
			
			msg.setSender(String.valueOf(currentUser.getId()));
			msg.setCreateUser(currentUser.getLoginName());
			msg.setUpdateUser(currentUser.getLoginName());
			messageService.createNewMessage(msg);
			
		} catch (Exception e) {
			logger.error("fail to get the message,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/getTargetUser")
	@ResponseBody
	public Map<String,Object> getTargetUser(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			List<WebUserInfo> targetUsers = new ArrayList<WebUserInfo>();
			targetUsers.addAll(relationshipPropertyService.getModuleSPByRepositoryCode(repositoryCode));
			
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			targetUsers.addAll(relationshipPropertyService.getCustomerBySpId(currentUser.getId()));
			
			resultMap.put("targetUsers", targetUsers);
		} catch (Exception e) {
			logger.error("fail to get the target users,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/getMsgInfo")
	@ResponseBody
	public Map<String,Object> getMsgInfo(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String msgId = request.getParameter("msgId");
			GoodsMessage msgObj = messageService.getMessageById(Long.parseLong(msgId));
			messageService.updateMessageStatus(Long.valueOf(msgId), Long.parseLong(String.valueOf(currentUser.getId())), "2", "in");
			resultMap.put("msgObj", msgObj);
		} catch (Exception e) {
			logger.error("fail to get the target users,",e);
		}
		return resultMap;
	}
	
	@RequestMapping("/updateMsg")
	@ResponseBody
	public Map<String,Object> updateMsg(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			String msgIds = request.getParameter("msgIds");
			String status = request.getParameter("status");
			String boxType = request.getParameter("boxType");
			
			for( String msgId : msgIds.split(",") ){
				messageService.updateMessageStatus(Long.valueOf(msgId), Long.parseLong(String.valueOf(currentUser.getId())), status, boxType);
			}
		} catch (Exception e) {
			logger.error("fail to get the target users,",e);
		}
		return resultMap;
	}
}
