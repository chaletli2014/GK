package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsHouseSP2nd;
import com.goodsquick.model.GoodsRelationshipProperty;

public interface RelationshipPropertyDAO {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId);
	public int saveRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
	public void updateRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
	public void updateRelationshipProperty(String columnName, String columnValue, int relationShipId) throws Exception;
	
	public List<GoodsHouseSP> getSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule(GoodsHouseSP houseModule);
	public void updateSPModule(GoodsHouseSP houseModule);
	public void removeSPModule(GoodsHouseSP houseModule);
	
	public List<GoodsHouseSP2nd> get2ndSPModuleByHouseCodeAndType(String houseCode,String moduleType);
	public void saveSPModule2nd(GoodsHouseSP2nd houseModule);
	public void updateSPModule2nd(GoodsHouseSP2nd houseModule);
	public void removeSPModule2nd(GoodsHouseSP2nd houseModule);
	
	public List<GoodsHouseModuleSP> getModuleSPByRepositoryCodeAndType(String repositoryCode, String spTypeCode, String partCode);
	public List<GoodsHouseModuleSP> getModuleSPByModuleType(String repositoryCode, String spTypeCode, String partCode, String moduleType2);
	public GoodsHouseModuleSP getModuleSPById(String spId);
	public void saveModuleSP(GoodsHouseModuleSP houseModuleSP);
	public void updateModuleSP(GoodsHouseModuleSP houseModuleSP);
	public void removeModuleSP(GoodsHouseModuleSP houseModuleSP);
}
