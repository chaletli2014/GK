package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsHouseSP2nd;

public class GoodsHouseSP2ndRowMapper implements RowMapper<GoodsHouseSP2nd>{
    @Override
    public GoodsHouseSP2nd mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseSP2nd dbGoodsHouseModule = new GoodsHouseSP2nd();
    	dbGoodsHouseModule.setId(rs.getInt("id"));
    	dbGoodsHouseModule.setHouseCode(rs.getString("house_code"));
    	dbGoodsHouseModule.setSpType(rs.getString("sp_type"));
    	dbGoodsHouseModule.setModuleType(rs.getString("module_type"));
    	dbGoodsHouseModule.setSpName(rs.getString("sp_name"));
    	dbGoodsHouseModule.setCreateUser(rs.getString("create_user"));
    	dbGoodsHouseModule.setCreateDate(rs.getDate("create_date"));
    	dbGoodsHouseModule.setUpdateUser(rs.getString("update_user"));
    	dbGoodsHouseModule.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsHouseModule.setStatus(rs.getString("status"));
    	dbGoodsHouseModule.setRemark(rs.getString("remark"));
        return dbGoodsHouseModule;
    }
    
}