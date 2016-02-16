package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsDeviceLift;

public class GoodsDeviceLiftRowMapper implements RowMapper<GoodsDeviceLift>{

	@Override
	public GoodsDeviceLift mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsDeviceLift lift = new GoodsDeviceLift();
		lift.setId(rs.getInt("id"));
		lift.setLiftType(rs.getString("lift_type"));
		lift.setLiftTypeName(rs.getString("lift_type_name"));
		lift.setLiftCode(rs.getString("lift_code"));
		lift.setLiftName(rs.getString("lift_name"));
		lift.setLiftDesc(rs.getString("lift_desc"));
		lift.setSubjectId(rs.getInt("subject_id"));
		lift.setSubjectName(rs.getString("subject_name"));
		lift.setModuleId(rs.getInt("module_id"));
		lift.setModuleName(rs.getString("module_name"));
		lift.setDeliveryDate(rs.getDate("delivery_date"));
		lift.setPurchasePrice(rs.getDouble("purchase_price"));
		lift.setUserName(rs.getString("user_name"));
		lift.setBrandCode(rs.getString("brand_code"));
		lift.setBrandName(rs.getString("brand_name"));
		lift.setLiftPurpose(rs.getString("lift_purpose"));
		lift.setLiftPurposeName(rs.getString("lift_purpose_name"));
		lift.setLiftStyle(rs.getString("lift_style"));
		lift.setLiftStyleName(rs.getString("lift_style_name"));
		lift.setLiftCT(rs.getDouble("lift_CT"));
		lift.setLiftNS(rs.getDouble("lift_NS"));
		lift.setLiftQA(rs.getString("lift_QA"));
		lift.setLiftQAName(rs.getString("lift_QA_name"));
		lift.setCarSize(rs.getString("car_size"));
		lift.setCarHeight(rs.getInt("car_height"));
		lift.setDoorSize(rs.getString("door_size"));
		lift.setMainPower(rs.getInt("main_power"));
		lift.setMadeDate(rs.getDate("made_date"));
		lift.setLifeTime(rs.getString("life_time"));
		lift.setLifeTimeName(rs.getString("life_time_name"));
		lift.setRepositoryCode(rs.getString("repository_code"));
		lift.setCreateDate(rs.getDate("create_date"));
		lift.setCreateUser(rs.getString("create_user"));
		lift.setUpdateDate(rs.getDate("update_date"));
		lift.setUpdateUser(rs.getString("update_user"));
		lift.setStatus(rs.getString("status"));
		return lift;
	}

}
