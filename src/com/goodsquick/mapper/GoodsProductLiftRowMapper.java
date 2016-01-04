package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsProductLift;

public class GoodsProductLiftRowMapper implements RowMapper<GoodsProductLift>{
    @Override
    public GoodsProductLift mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsProductLift dbLift = new GoodsProductLift();
    	dbLift.setId(rs.getInt("id"));
    	dbLift.setCode(rs.getString("code"));
    	dbLift.setLiftBrand(rs.getString("liftBrand"));
    	dbLift.setLiftPurpose(rs.getString("liftPurpose"));
    	dbLift.setLiftStyle(rs.getString("liftStyle"));
    	dbLift.setLiftCT(rs.getDouble("liftCT"));
    	dbLift.setLiftNS(rs.getDouble("liftNS"));
    	dbLift.setLiftQA(rs.getInt("liftQA"));
    	dbLift.setPrice(rs.getDouble("price"));
    	dbLift.setHoleSize(rs.getDouble("holeSize"));
    	dbLift.setPitDepth(rs.getInt("pitDepth"));
    	dbLift.setOverheadHeight(rs.getInt("overheadHeight"));
    	dbLift.setReservation(rs.getString("reservation"));
    	dbLift.setRoomSize(rs.getString("roomSize"));
    	dbLift.setRoomHeight(rs.getInt("roomHeight"));
    	dbLift.setCarSize(rs.getString("carSize"));
    	dbLift.setCarHeight(rs.getInt("carHeight"));
    	dbLift.setDoorSize(rs.getString("doorSize"));
    	dbLift.setMainPower(rs.getInt("mainPower"));
    	dbLift.setManufacturer(rs.getString("manufacturer"));
    	dbLift.setMadeDate(rs.getDate("madeDate"));
    	dbLift.setCreateDate(rs.getDate("create_date"));
    	dbLift.setUpdateDate(rs.getDate("update_date"));
    	dbLift.setCreateUser(rs.getString("create_user"));
    	dbLift.setUpdateUser(rs.getString("update_user"));
    	dbLift.setRemark(rs.getString("remark"));
    	dbLift.setStatus(rs.getString("status"));
    	
        return dbLift;
    }
    
}