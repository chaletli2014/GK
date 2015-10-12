package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsServiceProvider;

public class GoodsServiceRowMapper implements RowMapper<GoodsServiceProvider>{

	@Override
	public GoodsServiceProvider mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsServiceProvider goodsService = new GoodsServiceProvider();
		goodsService.setId(rs.getInt("id"));
		goodsService.setCode(rs.getString("code"));
		goodsService.setName(rs.getString("name"));
		goodsService.setAddress(rs.getString("address"));
		goodsService.setTelephone(rs.getString("telephone"));
		goodsService.setContractNumber(rs.getString("contract_number"));
		goodsService.setServiceContent(rs.getString("service_content"));
		goodsService.setCreateUser(rs.getString("create_user"));
		goodsService.setCreateDate(rs.getDate("createdate"));
		goodsService.setUpdateUser(rs.getString("update_user"));
		goodsService.setUpdateDate(rs.getDate("updatedate"));
		goodsService.setStatus(rs.getString("status"));
		return goodsService;
	}

}
