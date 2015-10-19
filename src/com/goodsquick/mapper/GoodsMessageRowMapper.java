package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsMessage;

public class GoodsMessageRowMapper implements RowMapper<GoodsMessage>{

	@Override
	public GoodsMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsMessage message = new GoodsMessage();
		message.setId(rs.getInt("id"));
		message.setSourceUser("source_user");
		message.setTargetUser("target_user");
		message.setMessageContent("message_content");
		message.setCreateDate(rs.getDate("createdate"));
		message.setCreateUser(rs.getString("create_user"));
		message.setUpdateDate(rs.getDate("updatedate"));
		message.setUpdateUser(rs.getString("update_user"));
		return message;
	}

}
