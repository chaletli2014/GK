package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsHouseFile;

public class GoodsHouseFileRowMapper implements RowMapper<GoodsHouseFile>{
    @Override
    public GoodsHouseFile mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseFile dbGoodsHouseFile = new GoodsHouseFile();
    	dbGoodsHouseFile.setId(rs.getInt("id"));
    	dbGoodsHouseFile.setRepositoryCode(rs.getString("repository_code"));
    	dbGoodsHouseFile.setFileType(rs.getString("file_type"));
    	dbGoodsHouseFile.setFileName(rs.getString("file_name"));
    	dbGoodsHouseFile.setFilePath(rs.getString("file_path"));
    	dbGoodsHouseFile.setIsMain("1".equalsIgnoreCase(rs.getString("is_main"))?"是":"否");
    	dbGoodsHouseFile.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseFile.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseFile.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseFile.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseFile.setStatus(rs.getString("status"));
        return dbGoodsHouseFile;
    }
    
}