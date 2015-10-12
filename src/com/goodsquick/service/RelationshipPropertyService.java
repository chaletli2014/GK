package com.goodsquick.service;

import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;

public interface RelationshipPropertyService {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	
	public void saveOrUpdateRelationshipProperty(GoodsRelationshipProperty relationshipProperty, WebUserInfo userInfo) throws Exception ;
}
