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
    	dbGoodsHouseModule.setRepositoryCode(rs.getString("repository_code"));
    	dbGoodsHouseModule.setFromSource(rs.getString("from_source"));
    	dbGoodsHouseModule.setSpTypeCode(rs.getString("sp_type_code"));
    	dbGoodsHouseModule.setSpTypeName(rs.getString("sp_type_name"));
    	dbGoodsHouseModule.setModuleType1(rs.getString("module_type1"));
    	dbGoodsHouseModule.setModuleType2(rs.getString("module_type2"));
    	dbGoodsHouseModule.setProServiceName(rs.getString("proServiceName"));
    	dbGoodsHouseModule.setSpName(rs.getString("sp_name"));
    	dbGoodsHouseModule.setSpId(rs.getInt("sp_id"));
    	dbGoodsHouseModule.setSpTel(rs.getString("sp_tel"));
    	dbGoodsHouseModule.setSpPhone(rs.getString("sp_phone"));
    	dbGoodsHouseModule.setRelationStatus("1".equalsIgnoreCase(rs.getString("relation_status"))?"未关联":"已关联");
    	dbGoodsHouseModule.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseModule.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseModule.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseModule.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseModule.setStatus(rs.getString("status"));
    	dbGoodsHouseModule.setRemark(rs.getString("remark"));
        return dbGoodsHouseModule;
    }
    
}