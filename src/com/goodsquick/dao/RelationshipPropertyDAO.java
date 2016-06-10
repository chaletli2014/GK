package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsHouseSP2nd;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

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
	
	public List<GoodsHouseModuleSP> getModuleSPByRepositoryCodeAndType(String repositoryCode, String spTypeCode);
	public List<GoodsHouseModuleSP> getModuleSPByModuleType(String repositoryCode, String spTypeCode, String moduleType2);
	public GoodsHouseModuleSP getModuleSPById(String spId);
	public void saveModuleSP(GoodsHouseModuleSP houseModuleSP);
	public void updateModuleSP(GoodsHouseModuleSP houseModuleSP);
	public void removeModuleSP(GoodsHouseModuleSP houseModuleSP);
	
	public void relateModuleSP(int userId, int spRelationId, String currentUser);
	/**
	 * 根据物库编码获取已经关联的供应商列表
	 * @param repositoryCode
	 * @return
	 */
	public List<WebUserInfo> getModuleSPByRepositoryCode(String repositoryCode);
	
	/**
	 * 查找供应商是当前用户的下游用户信息
	 * @param userId 当前用户
	 * @return List<WebUserInfo>
	 */
	public List<WebUserInfo> getCustomerBySpId(int userId);
}
