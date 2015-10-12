package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsRole;

public class GoodsRoleRowMapper implements RowMapper<GoodsRole>{

	@Override
	public GoodsRole mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsRole dbGoodsRole = new GoodsRole();
		dbGoodsRole.setId(rs.getInt("id"));
		dbGoodsRole.setRoleCode(rs.getString("role_code"));
		dbGoodsRole.setRoleName(rs.getString("role_name"));
		dbGoodsRole.setRoleDesc(rs.getString("role_desc"));
    	
        return dbGoodsRole;
	}

}
