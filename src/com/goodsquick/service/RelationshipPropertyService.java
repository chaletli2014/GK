package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsHouseSP2nd;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

public interface RelationshipPropertyService {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId);
	
	public void saveOrUpdateRelationshipProperty(GoodsRelationshipProperty relationshipProperty, WebUserInfo userInfo) throws Exception;
	public void updateRelationshipProperty(String type, int relationShipId, String propertyColumn, String propertyValue, WebUserInfo userInfo) throws Exception ;

	public List<GoodsHouseSP> getSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule(GoodsHouseSP houseModule);
	public void removeSPModule(GoodsHouseSP houseModule);
	
	public List<GoodsHouseSP2nd> get2ndSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule2nd(GoodsHouseSP2nd houseModule);
	public void removeSPModule2nd(GoodsHouseSP2nd houseModule);

	public List<GoodsHouseModuleSP> getModuleSPByHouseCodeAndType(String houseCode,String moduleType);
	public void saveModuleSP(GoodsHouseModuleSP houseModuleSP);
	public void removeModuleSP(GoodsHouseModuleSP houseModuleSP);

}
