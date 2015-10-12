package com.goodsquick.dao;

import com.goodsquick.model.GoodsRelationshipProperty;

public interface RelationshipPropertyDAO {

	public GoodsRelationshipProperty getRelationshipProperty(String sourceTable, int sourceId);
	public int saveRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
	public void updateRelationshipProperty(GoodsRelationshipProperty relationshipProperty) throws Exception;
}
