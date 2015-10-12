package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsChartObj2;

public class GoodsChart2RowMapper implements RowMapper<GoodsChartObj2>{
    @Override
    public GoodsChartObj2 mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsChartObj2 chart = new GoodsChartObj2();
    	chart.setxName(rs.getString("start_year"));
    	chart.setyCategory(rs.getString("brand_name"));
    	chart.setyValue(rs.getInt("device_num"));
        return chart;
    }
    
}