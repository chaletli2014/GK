package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsOwnerDevice;

public class GoodsOwnerDeviceRowMapper implements RowMapper<GoodsOwnerDevice>{

	@Override
	public GoodsOwnerDevice mapRow(ResultSet rs, int arg1)
			throws SQLException {
		GoodsOwnerDevice ownerDevice = new GoodsOwnerDevice();
		ownerDevice.setId(rs.getInt("id"));
		ownerDevice.setOwnerCode(rs.getString("owner_code"));
		ownerDevice.setDeviceCode(rs.getString("device_code"));
		ownerDevice.setOwnerDeviceCode(rs.getString("owner_device_code"));
		ownerDevice.setInstallationSite(rs.getString("installation_site"));
		ownerDevice.setUseDate(rs.getDate("use_date"));
		ownerDevice.setLoginDate(rs.getDate("login_date"));
		ownerDevice.setCreateDate(rs.getDate("create_date"));
		ownerDevice.setCreateUser(rs.getString("create_user"));
		return ownerDevice;
	}

}
