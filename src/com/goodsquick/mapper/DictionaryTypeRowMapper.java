package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsDictionaryType;

public class DictionaryTypeRowMapper implements RowMapper<GoodsDictionaryType>{
    @Override
    public GoodsDictionaryType mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsDictionaryType dbGoodsDictionaryType = new GoodsDictionaryType();
    	dbGoodsDictionaryType.setId(rs.getString("id"));
    	dbGoodsDictionaryType.setDtName(rs.getString("type_name"));
    	dbGoodsDictionaryType.setDtCode(rs.getString("type_code"));
    	dbGoodsDictionaryType.setDtDesc(rs.getString("type_desc"));
    	
        return dbGoodsDictionaryType;
    }
    
}