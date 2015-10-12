package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;

public interface MessageService {

	public List<GoodsRelatedRequest> getMessageList() throws Exception;
	
	public void handleMessage(int messageId, String status) throws Exception;
	
	public void updateCustomer2SP(int messageId, WebUserInfo currentUser) throws Exception;
}
