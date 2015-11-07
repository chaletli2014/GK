package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

public interface RelationshipPropertyService {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId);
	
	public void saveOrUpdateRelationshipProperty(GoodsRelationshipProperty relationshipProperty, WebUserInfo userInfo) throws Exception;
	public void updateRelationshipProperty(String type, int relationShipId, String propertyColumn, String propertyValue, WebUserInfo userInfo) throws Exception ;

	public List<GoodsHouseModuleSP> getSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule(GoodsHouseModuleSP houseModule);
	public void removeSPModule(GoodsHouseModuleSP houseModule);

}
