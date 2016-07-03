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
    	dbGoodsHouseDevice.setEqTypeCode(rs.getString("eq_type_code"));
    	dbGoodsHouseDevice.setEqTypeName(rs.getString("eq_type_name"));
    	dbGoodsHouseDevice.setName(rs.getString("name"));
    	dbGoodsHouseDevice.setBrand(rs.getString("brand"));
    	dbGoodsHouseDevice.setStyle(rs.getString("style"));
    	dbGoodsHouseDevice.setEqDesc(rs.getString("eq_desc"));
    	dbGoodsHouseDevice.setSubjectId(rs.getInt("subjectId"));
    	dbGoodsHouseDevice.setSubjectName(rs.getString("subjectName"));
    	dbGoodsHouseDevice.setModuleId(rs.getInt("moduleId"));
    	dbGoodsHouseDevice.setModuleName(rs.getString("moduleName"));
    	dbGoodsHouseDevice.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseDevice.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseDevice.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseDevice.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseDevice.setStatus(rs.getString("status"));
    	dbGoodsHouseDevice.setManufacturer(rs.getString("manufacturer"));
    	dbGoodsHouseDevice.setVelocity(rs.getDouble("velocity"));
    	dbGoodsHouseDevice.setNum(rs.getInt("num"));
    	dbGoodsHouseDevice.setEnableTime(rs.getDate("enable_time"));
    	dbGoodsHouseDevice.setMaintenanceUnit(rs.getString("maintenance_unit"));
    	dbGoodsHouseDevice.setTelephone(rs.getString("telephone"));
    	dbGoodsHouseDevice.setRemark(rs.getString("remark"));
        return dbGoodsHouseDevice;
    }
    
}