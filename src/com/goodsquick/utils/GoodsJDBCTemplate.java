package com.goodsquick.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class GoodsJDBCTemplate {

	public static int executeSQL(DataBean dataBean, final StringBuilder sql, final List<String> params){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                for( String obj : params ){
                	ps.setString(i++, obj);
                }
                return ps;
            }
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * 判断查询结果集中是否存在某列
	 * @param rs 查询结果集
	 * @param columnName 列名
	 * @return true 存在; false 不存咋
	 */
	public static boolean isExistColumn(ResultSet rs, String columnName) {
	    try {
	        if (rs.findColumn(columnName) > 0 ) {
	            return true;
	        } 
	    }
	    catch (SQLException e) {
	        return false;
	    }
	     
	    return false;
	}
}
