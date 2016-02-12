package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsHouseOther;

public class GoodsHouseOtherRowMapper implements RowMapper<GoodsHouseOther>{
    @Override
    public GoodsHouseOther mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseOther dbGoodsHouseOther = new GoodsHouseOther();
    	dbGoodsHouseOther.setId(rs.getInt("id"));
    	dbGoodsHouseOther.setTypeCode(rs.getString("type_code"));
    	dbGoodsHouseOther.setTypeName(rs.getString("type_name"));
    	dbGoodsHouseOther.setName(rs.getString("other_name"));
    	dbGoodsHouseOther.setDesc(rs.getString("other_desc"));
    	dbGoodsHouseOther.setSubjectId(rs.getInt("subjectId"));
    	dbGoodsHouseOther.setSubjectName(rs.getString("subjectName"));
    	dbGoodsHouseOther.setModuleId(rs.getInt("moduleId"));
    	dbGoodsHouseOther.setModuleName(rs.getString("moduleName"));
    	dbGoodsHouseOther.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseOther.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseOther.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseOther.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseOther.setStatus(rs.getString("status"));
        return dbGoodsHouseOther;
    }
    
}