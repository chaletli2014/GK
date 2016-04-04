package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseDevice;
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
	
	/**
	 * 根据主体ID查出该主体以及子主体下的所有材料装饰
	 * @param subjectId
	 * @return List<GoodsHouseOther> 材料装饰列表
	 * @throws Exception
	 */
	public List<GoodsHouseOther> getHouseOtherBySubjectId(int subjectId) throws Exception;
	
	/**
	 * 根据构件ID查出该构件下的所有材料装饰
	 * @param moduleId
	 * @return List<GoodsHouseOther> 材料装饰列表
	 * @throws Exception
	 */
	public List<GoodsHouseOther> getHouseOtherByModuleId(int moduleId) throws Exception;
}
