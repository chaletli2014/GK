package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsDeviceLift;

public class GoodsDeviceLiftRowMapper implements RowMapper<GoodsDeviceLift>{

	@Override
	public GoodsDeviceLift mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsDeviceLift lift = new GoodsDeviceLift();
		lift.setId(rs.getInt("id"));
		lift.setCode(rs.getString("code"));
		lift.setName(rs.getString("name"));
		lift.setLiftType(rs.getString("lift_type"));
		lift.setBrandName(rs.getString("brand_name"));
		lift.setCompanyCode(rs.getString("company_code"));
		lift.setModel(rs.getString("model"));
		lift.setMainArguments(rs.getString("main_arguments"));
		lift.setManufacturer(rs.getString("manufacturer"));
		lift.setMadeDate(rs.getDate("made_date"));
		lift.setCreateDate(rs.getDate("createdate"));
		lift.setCreateUser(rs.getString("create_user"));
		lift.setUpdateDate(rs.getDate("updatedate"));
		lift.setUpdateUser(rs.getString("update_user"));
		return lift;
	}

}
