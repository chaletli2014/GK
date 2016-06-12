package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsMessage;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;

public interface MessageService {

	public List<GoodsRelatedRequest> getMessageList() throws Exception;
	
	public void handleMessage(int messageId, String status) throws Exception;
	
	public void updateCustomer2SP(int messageId, WebUserInfo currentUser) throws Exception;
	
	public void createNewMessage(String sourceUser, String targetUser, String content) throws Exception;
	public GoodsMessage getMessageByHouseName(String sourceUser, String content) throws Exception;
	
	public List<GoodsMessage> getInBoxMessageListByRepo(int userId) throws Exception;
	public List<GoodsMessage> getOutBoxMessageListByRepo(int userId) throws Exception;
	public void createNewMessage(GoodsMessage msg) throws Exception;
	public GoodsMessage getMessageById(long messageId) throws Exception;
	
	public void updateMessageStatus(long messageId, long userId, String status, String boxType);
}
