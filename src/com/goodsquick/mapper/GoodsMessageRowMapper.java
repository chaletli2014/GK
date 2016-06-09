package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsMessage;
import com.goodsquick.utils.GoodsDateUtil;

public class GoodsMessageRowMapper implements RowMapper<GoodsMessage>{

	@Override
	public GoodsMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsMessage message = new GoodsMessage();
		message.setId(rs.getLong("id"));
		message.setSourceUser(rs.getString("source_user"));
		message.setTargetUser(rs.getString("target_user"));
		message.setMessageType(rs.getString("message_type"));
		message.setMessageTypeName(rs.getString("message_type_name"));
		message.setMessageTitle(rs.getString("message_title"));
		message.setMessageContent(rs.getString("message_content"));
		message.setRepositoryCode(rs.getString("repository_code"));
		message.setStatus(rs.getString("status"));
		Date createDate = rs.getDate("create_date");
		if( GoodsDateUtil.isSameDay(createDate) ){
			message.setCreateDate(rs.getTime("create_date"));
			message.setUpdateDate(rs.getTime("update_date"));
		}else{
			message.setCreateDate(rs.getDate("create_date"));
			message.setUpdateDate(rs.getDate("update_date"));
		}
		message.setCreateUser(rs.getString("create_user"));
		message.setUpdateUser(rs.getString("update_user"));
		return message;
	}

}
