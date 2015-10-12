package com.goodsquick.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.RelationshipPropertyDAO;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

@Service("relationshipPropertyService")
public class RelationshipPropertyServiceImpl implements RelationshipPropertyService {
	
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("relationshipPropertyDAO")
	private RelationshipPropertyDAO relationshipPropertyDAO;
	
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

}
