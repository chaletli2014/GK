package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsHouseSubjectModule;

public class GoodsHouseSubjectModuleRowMapper implements RowMapper<GoodsHouseSubjectModule>{
    @Override
    public GoodsHouseSubjectModule mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsHouseSubjectModule dbHouseSubjectModule = new GoodsHouseSubjectModule();
    	dbHouseSubjectModule.setId(rs.getInt("id"));
    	dbHouseSubjectModule.setSubjectId(rs.getInt("subject_id"));
    	dbHouseSubjectModule.setModuleTypeCode(rs.getString("module_type_code"));
    	dbHouseSubjectModule.setModuleCode(rs.getString("module_code"));
    	dbHouseSubjectModule.setModuleName(rs.getString("module_name"));
    	dbHouseSubjectModule.setModuleDesc(rs.getString("module_desc"));
    	
        return dbHouseSubjectModule;
    }
    
}