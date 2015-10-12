package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsRelationshipProperty;

/**
 * 该Mapper用于展示当前用户的所有不动产和服务商的列表
 * @author chalet
 *
 */
public class GoodsHouseRalationshipRowMapper implements RowMapper<GoodsRelationshipProperty>{
	@Override
	public GoodsRelationshipProperty mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsRelationshipProperty relationshipProperty = new GoodsRelationshipProperty();
		relationshipProperty.setHouseName(rs.getString("house_name"));
		relationshipProperty.setBrandName(rs.getString("brand_name"));
		relationshipProperty.setBrandCode(rs.getString("brand_code"));
		relationshipProperty.setDesignName(rs.getString("design_name"));
		relationshipProperty.setDesignCode(rs.getString("design_code"));
		relationshipProperty.setManufacturerName(rs.getString("manufacturer_name"));
		relationshipProperty.setManufacturerCode(rs.getString("manufacturer_code"));
		relationshipProperty.setCertificationName(rs.getString("certification_name"));
		relationshipProperty.setCertificationCode(rs.getString("certification_code"));
		relationshipProperty.setChannelName(rs.getString("channel_name"));
		relationshipProperty.setChannelCode(rs.getString("channel_code"));
		relationshipProperty.setLogisticsName(rs.getString("logistics_name"));
		relationshipProperty.setLogisticsCode(rs.getString("logistics_code"));
		relationshipProperty.setOwnerName(rs.getString("owner_name"));
		relationshipProperty.setOwnerCode(rs.getString("owner_code"));
		relationshipProperty.setTrusteeshipName(rs.getString("trusteeship_name"));
		relationshipProperty.setTrusteeshipCode(rs.getString("trusteeship_code"));
		relationshipProperty.setSupervisionName(rs.getString("supervision_name"));
		relationshipProperty.setSupervisionCode(rs.getString("supervision_code"));
		relationshipProperty.setRecyclingName(rs.getString("recycling_name"));
		relationshipProperty.setRecyclingCode(rs.getString("recycling_code"));
		return relationshipProperty;
	}
    
}