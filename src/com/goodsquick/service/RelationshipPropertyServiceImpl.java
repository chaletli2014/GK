package com.goodsquick.service;

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
	public void saveSPModule(GoodsHouseModuleSP houseModule) {
		relationshipPropertyDAO.saveSPModule(houseModule);
	}

	@Override
	public void removeSPModule(GoodsHouseModuleSP houseModule) {
		relationshipPropertyDAO.removeSPModule(houseModule);
	}

	@Override
	public List<GoodsHouseModuleSP> getSPModuleByHouseCodeAndType(String houseCode,
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

}
