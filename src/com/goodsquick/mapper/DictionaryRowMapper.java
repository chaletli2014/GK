package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsDictionary;

public class DictionaryRowMapper implements RowMapper<GoodsDictionary>{
    @Override
    public GoodsDictionary mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsDictionary dbGoodsDictionary = new GoodsDictionary();
    	dbGoodsDictionary.setId(rs.getString("id"));
    	dbGoodsDictionary.setTypeName(rs.getString("type_name"));
    	dbGoodsDictionary.setDicCode(rs.getString("dic_code"));
    	dbGoodsDictionary.setDicName(rs.getString("dic_name"));
    	dbGoodsDictionary.setDicDesc(rs.getString("dic_desc"));
    	
        return dbGoodsDictionary;
    }
    
}