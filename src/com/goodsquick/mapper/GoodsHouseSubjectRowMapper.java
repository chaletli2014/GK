package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.Category;

public class GoodsHouseSubjectRowMapper implements RowMapper<Category>{
    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
    	Category dbHouseSubject = new Category();
    	dbHouseSubject.setId(rs.getString("id"));
    	dbHouseSubject.setName(rs.getString("subject_name"));
    	dbHouseSubject.setCode(rs.getString("subject_code"));
    	dbHouseSubject.setDesc(rs.getString("subject_desc"));
    	dbHouseSubject.setParentId(rs.getString("parentId"));
    	dbHouseSubject.setHasChild(rs.getString("has_child"));
    	
        return dbHouseSubject;
    }
    
}