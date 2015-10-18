package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsServiceDetail;

public class GoodsServiceDetailRowMapper implements RowMapper<GoodsServiceDetail>{

	@Override
	public GoodsServiceDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsServiceDetail goodsService = new GoodsServiceDetail();
		goodsService.setId(rs.getInt("id"));
		goodsService.setServiceName(rs.getString("service_name"));
		goodsService.setServiceRangeCode(rs.getString("service_range_code"));
		goodsService.setServiceRangeName(rs.getString("service_range_name"));
		goodsService.setPrice(rs.getDouble("price"));
		goodsService.setServiceContent(rs.getString("service_content"));
		goodsService.setCreateUser(rs.getString("create_user"));
		goodsService.setCreateDate(rs.getDate("createdate"));
		goodsService.setUpdateUser(rs.getString("update_user"));
		goodsService.setUpdateDate(rs.getDate("updatedate"));
		goodsService.setStatus(rs.getString("status"));
		return goodsService;
	}

}
