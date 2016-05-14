package com.goodsquick.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.utils.DataBean;

public class BaseDAOImpl {
	@Autowired
	@Qualifier("dataBean")
	protected DataBean dataBean;
	
	public void deleteObj(String tableName, List<Object> params){
		StringBuilder sql = new StringBuilder(100);
		sql.append("update ").append(tableName).append(" set status = 0, update_date=now(), update_user=? where id = ? ");
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	public List queryByCondition(String sql, Object[] params, RowMapper rowMapper){
		return dataBean.getJdbcTemplate().query(sql, params, rowMapper);
	}
}
