package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.Category;

public class CategoryRowMapper implements RowMapper<Category>{
    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
    	Category dbCategory = new Category();
    	dbCategory.setId(rs.getString("id"));
    	dbCategory.setName(rs.getString("category_name"));
    	dbCategory.setCode(rs.getString("category_code"));
    	dbCategory.setDesc(rs.getString("category_desc"));
    	dbCategory.setParentId(rs.getString("parentId"));
    	dbCategory.setHasChild(rs.getString("has_child"));
    	
        return dbCategory;
    }
    
}