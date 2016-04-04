package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHousePaint;
import com.goodsquick.model.GoodsHouseOther;

public interface GoodsHouseOtherDAO {

	public int saveHouseOther(GoodsHouseOther houseOther) throws Exception;
	public void updateHouseOther(GoodsHouseOther houseOther) throws Exception;
	
	public List<GoodsHouseOther> getAllHouseOtherByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsHouseOther getOtherInfoById(int deviceId) throws Exception;
	
	public void deleteHouseOther(GoodsHouseOther obj) throws Exception;
	
	public List<GoodsHousePaint> getHousePaintByRepositoryCode(String repositoryCode) throws Exception;
	public void saveHousePaint(GoodsHousePaint housePaint ) throws Exception;
	public void saveHousePaintQuick(GoodsHousePaint housePaint ) throws Exception;
	public void updateHousePaint(GoodsHousePaint housePaint ) throws Exception;
	public void deleteHousePaint(GoodsHousePaint obj) throws Exception;
	public GoodsHousePaint getGoodsHousePaintById(int housePaintId) throws Exception;
	
	public List<GoodsHousePaint> getHousePaintBySubjectId(int subjectId) throws Exception; 
	public List<GoodsHousePaint> getHousePaintByModuleId(int moduleId) throws Exception; 
	
}
