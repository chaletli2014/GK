package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsRelationshipProperty;

public interface RelationshipPropertyDAO {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId);
	public int saveRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
	public void updateRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
	public void updateRelationshipProperty(String columnName, String columnValue, int relationShipId) throws Exception;
	
	public List<GoodsHouseModuleSP> getSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule(GoodsHouseModuleSP houseModule);
	public void removeSPModule(GoodsHouseModuleSP houseModule);
}
