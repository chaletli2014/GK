package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;

public interface MessageDAO {
	public List<GoodsRelatedRequest> getMessageList(String loginName) throws Exception;
	public void handleMessage(int messageId, String status, WebUserInfo currentUser) throws Exception;
	
	public GoodsRelatedRequest getMessageById(int messageId) throws Exception;
}
