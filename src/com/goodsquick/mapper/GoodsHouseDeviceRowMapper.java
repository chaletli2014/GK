package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsHouseDevice;

public class GoodsHouseDeviceRowMapper implements RowMapper<GoodsHouseDevice>{
    @Override
    public GoodsHouseDevice mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseDevice dbGoodsHouseDevice = new GoodsHouseDevice();
    	dbGoodsHouseDevice.setId(rs.getInt("id"));
    	dbGoodsHouseDevice.setOrHouseCode(rs.getString("house_code"));
    	dbGoodsHouseDevice.setDeviceType(rs.getString("device_type"));
    	dbGoodsHouseDevice.setDeviceName(rs.getString("device_name"));
    	dbGoodsHouseDevice.setManufacturerName(rs.getString("manufacturer_name"));
    	dbGoodsHouseDevice.setBrandName(rs.getString("brand_name"));
    	dbGoodsHouseDevice.setModel(rs.getString("model"));
    	dbGoodsHouseDevice.setDeviceNum(rs.getInt("device_num"));
    	dbGoodsHouseDevice.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseDevice.setCreateDate(rs.getDate("createdate"));
    	dbGoodsHouseDevice.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseDevice.setUpdateDate(rs.getDate("updatedate"));
    	dbGoodsHouseDevice.setStatus(rs.getString("status"));
        return dbGoodsHouseDevice;
    }
    
}