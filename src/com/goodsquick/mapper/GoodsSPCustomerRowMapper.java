package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsSPCustomer;

public class GoodsSPCustomerRowMapper implements RowMapper<GoodsSPCustomer>{

	@Override
	public GoodsSPCustomer mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsSPCustomer goodsService = new GoodsSPCustomer();
		goodsService.setId(rs.getInt("id"));
//		goodsService.setSpCode(rs.getString("sp_code"));
//		goodsService.setCategoryCode(rs.getString("category_code"));
//		goodsService.setCategoryName(rs.getString("category_name"));
		goodsService.setServiceTypeCode(rs.getString("service_type"));
		goodsService.setServiceTypeName(rs.getString("service_type_name"));
		goodsService.setCustomerCode(rs.getString("customer_code"));
		goodsService.setCustomerName(rs.getString("customer_name"));
		goodsService.setStatus(rs.getString("status"));
		goodsService.setCreateUser(rs.getString("create_user"));
		goodsService.setCreateDate(rs.getDate("createdate"));
		goodsService.setUpdateUser(rs.getString("update_user"));
		goodsService.setUpdateDate(rs.getDate("updatedate"));
		return goodsService;
	}

}
