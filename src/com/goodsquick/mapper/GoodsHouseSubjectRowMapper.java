package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsSubject;
import com.goodsquick.utils.GoodsJDBCTemplate;

public class GoodsHouseSubjectRowMapper implements RowMapper<GoodsSubject>{
    @Override
    public GoodsSubject mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsSubject dbHouseSubject = new GoodsSubject();
    	dbHouseSubject.setId(rs.getInt("id"));
    	dbHouseSubject.setName(rs.getString("subject_name"));
    	dbHouseSubject.setCode(rs.getString("subject_code"));
    	dbHouseSubject.setDesc(rs.getString("subject_desc"));
    	dbHouseSubject.setParentId(rs.getInt("parentId"));
    	dbHouseSubject.setLevel(rs.getString("subject_level"));
    	dbHouseSubject.setRepositoryCode(rs.getString("repository_code"));
    	if( GoodsJDBCTemplate.isExistColumn(rs, "subject1Name") ){
    		dbHouseSubject.setSubject1Name(rs.getString("subject1Name"));
    	}
    	if( GoodsJDBCTemplate.isExistColumn(rs, "subject2Name") ){
    		dbHouseSubject.setSubject2Name(rs.getString("subject2Name"));
    	}
    	
        return dbHouseSubject;
    }
    
}