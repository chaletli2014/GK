package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsPrivilege;

public class GoodsPrivilegeRowMapper implements RowMapper<GoodsPrivilege>{

	@Override
	public GoodsPrivilege mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsPrivilege dbGoodsPrivilege = new GoodsPrivilege();
		dbGoodsPrivilege.setId(rs.getInt("id"));
		dbGoodsPrivilege.setPrivilegeCode(rs.getString("privilege_code"));
		dbGoodsPrivilege.setPrivilegeName(rs.getString("privilege_name"));
		dbGoodsPrivilege.setPrivilegeDesc(rs.getString("privilege_desc"));
		dbGoodsPrivilege.setCreateTime(rs.getDate("create_time"));
		dbGoodsPrivilege.setUpdateTime(rs.getDate("update_time"));
		dbGoodsPrivilege.setCreateUser(rs.getString("create_user"));
		dbGoodsPrivilege.setUpdateUser(rs.getString("update_user"));
    	
        return dbGoodsPrivilege;
	}

}
