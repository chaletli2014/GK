package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsConfiguration;

public class GoodsConfigurationRowMapper implements RowMapper<GoodsConfiguration>{
    @Override
    public GoodsConfiguration mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsConfiguration dbGoodsConfig = new GoodsConfiguration();
    	dbGoodsConfig.setId(rs.getString("id"));
    	dbGoodsConfig.setConfigCode(rs.getString("config_code"));
    	dbGoodsConfig.setConfigValue(rs.getString("config_value"));
    	
        return dbGoodsConfig;
    }
    
}