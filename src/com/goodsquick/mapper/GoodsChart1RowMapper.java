package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsChartObj1;

public class GoodsChart1RowMapper implements RowMapper<GoodsChartObj1>{
    @Override
    public GoodsChartObj1 mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsChartObj1 chart1 = new GoodsChartObj1();
    	chart1.setxName(rs.getString("xName"));
    	chart1.setyValue(rs.getInt("yValue"));
        return chart1;
    }
    
}