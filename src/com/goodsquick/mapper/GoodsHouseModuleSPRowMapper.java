package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsHouseModuleSP;

public class GoodsHouseModuleSPRowMapper implements RowMapper<GoodsHouseModuleSP>{
    @Override
    public GoodsHouseModuleSP mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseModuleSP dbGoodsHouseModule = new GoodsHouseModuleSP();
    	dbGoodsHouseModule.setId(rs.getInt("id"));
    	dbGoodsHouseModule.setHouseCode(rs.getString("house_code"));
    	dbGoodsHouseModule.setModuleSPType(rs.getString("module_SP_type"));
    	dbGoodsHouseModule.setModuleSPValue(rs.getString("module_SP_value"));
    	dbGoodsHouseModule.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseModule.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseModule.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseModule.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseModule.setStatus(rs.getString("status"));
        return dbGoodsHouseModule;
    }
    
}