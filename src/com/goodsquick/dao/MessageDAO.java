package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsMessage;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;

public interface MessageDAO {
	public List<GoodsRelatedRequest> getMessageList(String loginName) throws Exception;
	public void handleMessage(int messageId, String status, WebUserInfo currentUser) throws Exception;
	
	public GoodsRelatedRequest getMessageById(int messageId) throws Exception;
	
	public void createNewMessage(String sourceUser, String targetUser, String content) throws Exception;
	public GoodsMessage getMessageByHouseName(String sourceUser, String content) throws Exception;
	
	public List<GoodsMessage> getMessageListByRepo(String repositoryCode, int userId, String boxType) throws Exception;
	public void createNewMessage(GoodsMessage msg) throws Exception;
	public GoodsMessage getMessageById(long messageId) throws Exception;
}
