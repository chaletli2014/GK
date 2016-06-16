package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsRepositoryUser;

public class GoodsRepositoryUserRowMapper implements RowMapper<GoodsRepositoryUser>{

	@Override
	public GoodsRepositoryUser mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsRepositoryUser dbGoodsRepositoryUser = new GoodsRepositoryUser();
		dbGoodsRepositoryUser.setId(rs.getInt("id"));
		dbGoodsRepositoryUser.setRepositoryCode(rs.getString("repository_code"));
		dbGoodsRepositoryUser.setUserCode(rs.getString("user_code"));
		dbGoodsRepositoryUser.setUserName(rs.getString("user_name"));
		dbGoodsRepositoryUser.setTelephone(rs.getString("telephone"));
		dbGoodsRepositoryUser.setStatus(rs.getString("status"));
		dbGoodsRepositoryUser.setCreateDate(rs.getDate("create_date"));
		dbGoodsRepositoryUser.setUpdateDate(rs.getDate("update_date"));
		dbGoodsRepositoryUser.setCreateUser(rs.getString("create_user"));
		dbGoodsRepositoryUser.setUpdateUser(rs.getString("update_user"));
    	
        return dbGoodsRepositoryUser;
	}

}
