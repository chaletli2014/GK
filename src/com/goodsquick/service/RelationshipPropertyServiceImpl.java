package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsConfigurationDAO;
import com.goodsquick.dao.RelationshipPropertyDAO;
import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsHouseSP2nd;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

@Service("relationshipPropertyService")
public class RelationshipPropertyServiceImpl implements RelationshipPropertyService {
	
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("relationshipPropertyDAO")
	private RelationshipPropertyDAO relationshipPropertyDAO;
	
	@Autowired
	@Qualifier("goodsConfigurationDAO")
	private GoodsConfigurationDAO goodsConfigurationDAO;
	
	@Override
	public GoodsRelationshipProperty getRelationshipProperty(
			String sourceTable, int sourceId) {
		try{
			return relationshipPropertyDAO.getRelationshipProperty(sourceTable, sourceId);
		} catch(EmptyResultDataAccessException erd){
            return null;
        } catch(Exception e){
            logger.error(String.format("fail to get the relationship property by sourceTable %s and sourceId %s,",sourceTable,sourceId),e);
            return null;
        }
	}
	
	@Override
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId) {
		try{
			return relationshipPropertyDAO.getRelationshipProperty(relationShipId);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error(String.format("fail to get the relationship property by Id %s,",relationShipId),e);
			return null;
		}
	}

	@Override
	public void saveOrUpdateRelationshipProperty(GoodsRelationshipProperty relationshipProperty, WebUserInfo userInfo) 
			throws Exception {
		
		int sourceId = relationshipProperty.getId();
		if( 0 == sourceId ){
			relationshipProperty.setCreateUser(userInfo.getLoginName());
			relationshipProperty.setUpdateUser(userInfo.getLoginName());
			relationshipPropertyDAO.saveRelationshipProperty(relationshipProperty);
		}else{
			relationshipProperty.setUpdateUser(userInfo.getLoginName());
			relationshipProperty.setUpdateDate(new Date());
			relationshipPropertyDAO.updateRelationshipProperty(relationshipProperty);
		}
	}

	@Override
	public void updateRelationshipProperty(String type, int relationShipId,
			String propertyColumn, String propertyValue, WebUserInfo userInfo) throws Exception {
		
		GoodsRelationshipProperty relationShip = relationshipPropertyDAO.getRelationshipProperty(relationShipId);
		if( null != relationShip ){
			GoodsConfiguration config = goodsConfigurationDAO.getConfigByCode(propertyColumn);
			String spCodeColumn = config.getConfigValue();
			String spNameColumn = spCodeColumn.substring(0,spCodeColumn.indexOf('_'))+"_name";
			String updateCodeValue = "";
			String updateNameValue = "";
			if( "2".equalsIgnoreCase(type) ){
				updateNameValue = propertyValue;
			}
			relationshipPropertyDAO.updateRelationshipProperty(spCodeColumn, updateCodeValue, relationShipId);
			relationshipPropertyDAO.updateRelationshipProperty(spNameColumn, updateNameValue, relationShipId);
		}
		
	}

	@Override
	public void saveSPModule(GoodsHouseSP houseModule) {
		if( houseModule.getId() != 0 ){
			relationshipPropertyDAO.updateSPModule(houseModule);
		}else{
			relationshipPropertyDAO.saveSPModule(houseModule);
		}
	}

	@Override
	public void removeSPModule(GoodsHouseSP houseModule) {
		relationshipPropertyDAO.removeSPModule(houseModule);
	}

	@Override
	public List<GoodsHouseSP> getSPModuleByHouseCodeAndType(String houseCode,
			String moduleType) {
		try{
			return relationshipPropertyDAO.getSPModuleByHouseCodeAndType(houseCode,moduleType);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error(String.format("fail to get the house module,"),e);
			return null;
		}
	}
	
	@Override
	public List<GoodsHouseSP2nd> get2ndSPModuleByHouseCodeAndType(String houseCode,
			String moduleType) {
		try{
			return relationshipPropertyDAO.get2ndSPModuleByHouseCodeAndType(houseCode,moduleType);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error(String.format("fail to get the house module,"),e);
			return null;
		}
	}

	@Override
	public void saveSPModule2nd(GoodsHouseSP2nd houseModule) {
		relationshipPropertyDAO.saveSPModule2nd(houseModule);
	}

	@Override
	public void removeSPModule2nd(GoodsHouseSP2nd houseModule) {
		relationshipPropertyDAO.removeSPModule2nd(houseModule);
	}
	

	@Override
	public void saveModuleSP(GoodsHouseModuleSP houseModule) {
		if( houseModule.getId() != 0 ){
			relationshipPropertyDAO.updateModuleSP(houseModule);
		}else{
			relationshipPropertyDAO.saveModuleSP(houseModule);
		}
	}

	@Override
	public void removeModuleSP(GoodsHouseModuleSP houseModule) {
		relationshipPropertyDAO.removeModuleSP(houseModule);
	}

	@Override
	public List<GoodsHouseModuleSP> getModuleSPByHouseCodeAndType(String repositoryCode, String spTypeCode) {
		try{
			return relationshipPropertyDAO.getModuleSPByRepositoryCodeAndType(repositoryCode, spTypeCode);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error(String.format("fail to get the house module,"),e);
			return null;
		}
	}
	
	@Override
	public List<GoodsHouseModuleSP> getModuleSPByModuleType(String repositoryCode, String spTypeCode, String moduleType2) {
		try{
			return relationshipPropertyDAO.getModuleSPByModuleType(repositoryCode, spTypeCode, moduleType2);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error(String.format("fail to get the house module by module type %s,",moduleType2),e);
			return null;
		}
	}

	@Override
	public GoodsHouseModuleSP getModuleSPById(String spId) {
		return relationshipPropertyDAO.getModuleSPById(spId);
	}

	@Override
	public void relateModuleSP(int userId, int spRelationId, String currentUser) {
		relationshipPropertyDAO.relateModuleSP(userId, spRelationId, currentUser);
	}

	@Override
	public List<WebUserInfo> getModuleSPByRepositoryCode(String repositoryCode) {
		List<WebUserInfo> targetUsers = new ArrayList<WebUserInfo>();
		try{
			targetUsers.addAll(relationshipPropertyDAO.getModuleSPByRepositoryCode(repositoryCode));
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error("fail to get the module sp,",e);
			return null;
		}
		return targetUsers;
	}
	
	@Override
	public List<WebUserInfo> getCustomerBySpId(int userId) {
		try{
			return relationshipPropertyDAO.getCustomerBySpId(userId);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error("fail to get the customer by sp id,",e);
			return null;
		}
	}

}
