package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsCompanyInfo;


public class GoodsCompanyInfoRowMapper implements RowMapper<GoodsCompanyInfo>{
    @Override
    public GoodsCompanyInfo mapRow(ResultSet rs, int i) throws SQLException {
        GoodsCompanyInfo company = new GoodsCompanyInfo();
        company.setIndustry(rs.getString("industry"));
        company.setCompanyName(rs.getString("company_name"));
        company.setCompanyEmail(rs.getString("company_email"));
        company.setCompanyProvince(rs.getString("company_province"));
        company.setCompanyCity(rs.getString("company_city"));
        company.setId(rs.getInt("company_id"));
        
        return company;
    }
    
}