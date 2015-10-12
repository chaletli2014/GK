package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsRelatedRequest;

public class GoodsRelatedRequestRowMapper implements RowMapper<GoodsRelatedRequest>{

	@Override
	public GoodsRelatedRequest mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsRelatedRequest dbGoodsRelatedRequest = new GoodsRelatedRequest();
		dbGoodsRelatedRequest.setId(rs.getInt("id"));
		dbGoodsRelatedRequest.setSpCustomerId(rs.getInt("sp_customer_id"));
		dbGoodsRelatedRequest.setOrHouseName(rs.getString("customer_name"));
		dbGoodsRelatedRequest.setSpCode(rs.getString("sp_code"));
		dbGoodsRelatedRequest.setSpName(rs.getString("sp_name"));
		dbGoodsRelatedRequest.setStatus(rs.getString("status"));
		dbGoodsRelatedRequest.setServiceTypeCode(rs.getString("service_type_code"));
		dbGoodsRelatedRequest.setServiceTypeName(rs.getString("service_type_name"));
		dbGoodsRelatedRequest.setCreateDate(rs.getDate("createdate"));
		dbGoodsRelatedRequest.setUpdateDate(rs.getDate("updatedate"));
		dbGoodsRelatedRequest.setCreateUser(rs.getString("create_user"));
		dbGoodsRelatedRequest.setUpdateUser(rs.getString("update_user"));
    	
        return dbGoodsRelatedRequest;
	}

}
