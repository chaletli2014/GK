package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsRepository;

public class GoodsRepositoryRowMapper implements RowMapper<GoodsRepository>{

	@Override
	public GoodsRepository mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsRepository dbGoodsRepository = new GoodsRepository();
		dbGoodsRepository.setId(rs.getInt("id"));
		dbGoodsRepository.setRepositoryCode(rs.getString("repository_code"));
		dbGoodsRepository.setRepositoryName(rs.getString("repository_name"));
		dbGoodsRepository.setRepositoryDesc(rs.getString("repository_desc"));
		dbGoodsRepository.setRepositoryType(rs.getString("repository_type"));
		dbGoodsRepository.setStatus(rs.getString("status"));
		dbGoodsRepository.setCreateDate(rs.getDate("create_date"));
		dbGoodsRepository.setUpdateDate(rs.getDate("update_date"));
		dbGoodsRepository.setCreateUser(rs.getString("create_user"));
		dbGoodsRepository.setUpdateUser(rs.getString("update_user"));
    	
        return dbGoodsRepository;
	}

}
