package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsProductSource;

public class GoodsProductSourceRowMapper implements RowMapper<GoodsProductSource>{
    @Override
    public GoodsProductSource mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsProductSource dbGoodsProductSource = new GoodsProductSource();
    	dbGoodsProductSource.setId(rs.getLong("id"));
    	dbGoodsProductSource.setRepositoryCode(rs.getString("repository_code"));
    	dbGoodsProductSource.setGoodsProductObjId(rs.getLong("tbl_goods_product_obj_id"));
    	dbGoodsProductSource.setFileType(rs.getString("file_type"));
    	dbGoodsProductSource.setFileName(rs.getString("file_name"));
    	dbGoodsProductSource.setFilePath(rs.getString("file_path"));
    	dbGoodsProductSource.setIsMain("1".equalsIgnoreCase(rs.getString("is_main"))?"是":"否");
    	dbGoodsProductSource.setCreateUser(rs.getString("create_user"));
    	dbGoodsProductSource.setCreateDate(rs.getDate("create_date"));
    	dbGoodsProductSource.setUpdateUser(rs.getString("update_user"));
    	dbGoodsProductSource.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsProductSource.setStatus(rs.getString("status"));
        return dbGoodsProductSource;
    }
    
}