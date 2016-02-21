package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseOther;
import com.goodsquick.model.GoodsHousePaint;
import com.goodsquick.model.WebUserInfo;

public interface GoodsHouseOtherService {

	public List<GoodsHouseOther> getAllOther(String repositoryCode) throws Exception;
	
	public GoodsHouseOther getOtherInfoById(int deviceId) throws Exception;
	public GoodsHousePaint getPaintInfoById(int paintId) throws Exception;
	
	public void saveOrUpdateHouseOther(List<GoodsHouseOther> goodsSubjects, String currentUser, String repositoryCode) throws Exception;
	public void saveOrUpdateHousePaint(GoodsHousePaint housePaint, WebUserInfo currentUser, String repositoryCode) throws Exception;
	
	public void deleteHouseOther(GoodsHouseOther obj) throws Exception;
}
