package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.utils.GoodsJDBCTemplate;

public class OrdinaryHouseRowMapper implements RowMapper<GoodsOrdinaryHouse>{
    @Override
    public GoodsOrdinaryHouse mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsOrdinaryHouse dbGoodsOrHouse = new GoodsOrdinaryHouse();
    	dbGoodsOrHouse.setId(rs.getInt("id"));
    	dbGoodsOrHouse.setHouseCode(rs.getString("house_code"));
    	dbGoodsOrHouse.setBuildingName(rs.getString("building_name"));
    	dbGoodsOrHouse.setProvince(rs.getString("province"));
    	dbGoodsOrHouse.setCity(rs.getString("city"));
    	dbGoodsOrHouse.setBuildingStatus(rs.getString("building_status"));
    	dbGoodsOrHouse.setCompany(rs.getString("company"));
    	dbGoodsOrHouse.setContacterName(rs.getString("contacter_name"));
    	dbGoodsOrHouse.setContacterPosition(rs.getString("contacter_position"));
    	dbGoodsOrHouse.setContacterTelephone(rs.getString("contacter_telephone"));
    	dbGoodsOrHouse.setPropertyName(rs.getString("property_name"));
    	dbGoodsOrHouse.setLocation(rs.getString("location"));
    	dbGoodsOrHouse.setProjectPositionE(rs.getString("project_position_e"));
    	dbGoodsOrHouse.setProjectPositionW(rs.getString("project_position_w"));
    	dbGoodsOrHouse.setProjectPositionS(rs.getString("project_position_s"));
    	dbGoodsOrHouse.setProjectPositionN(rs.getString("project_position_n"));
    	dbGoodsOrHouse.setPropertyType(rs.getString("property_type"));
    	dbGoodsOrHouse.setPropertyTypeDesc(rs.getString("property_type_desc"));
    	dbGoodsOrHouse.setPropertytypeO(rs.getString("property_type_o"));
    	dbGoodsOrHouse.setFinishYear(rs.getInt("finish_year"));
    	dbGoodsOrHouse.setFinishMonth(rs.getInt("finish_month"));
    	dbGoodsOrHouse.setFinishDate(rs.getInt("finish_date"));
    	dbGoodsOrHouse.setStartYear(rs.getInt("start_year"));
    	dbGoodsOrHouse.setStartMonth(rs.getInt("start_month"));
    	dbGoodsOrHouse.setStartDate(rs.getInt("start_date"));
    	dbGoodsOrHouse.setCheckinYear(rs.getInt("checkin_year"));
    	dbGoodsOrHouse.setCheckinMonth(rs.getInt("checkin_month"));
    	dbGoodsOrHouse.setCheckinDate(rs.getInt("checkin_date"));
    	dbGoodsOrHouse.setFloorSpace(rs.getInt("floor_space"));
    	dbGoodsOrHouse.setBuildingNumber(rs.getInt("building_number"));
    	dbGoodsOrHouse.setHasLiftNumber(rs.getInt("has_lift_number"));
    	dbGoodsOrHouse.setNonLiftNumber(rs.getInt("non_lift_number"));
    	dbGoodsOrHouse.setLobbyNumber(rs.getInt("lobby_number"));
    	dbGoodsOrHouse.setLiftLobbyNumber(rs.getInt("lift_lobby_number"));
    	dbGoodsOrHouse.setNonLiftLobbyNumber(rs.getInt("non_lift_lobby_number"));
    	dbGoodsOrHouse.setOwnerHouseholds(rs.getInt("owner_households"));
    	dbGoodsOrHouse.setTenantHouseholds(rs.getInt("tenant_households"));
    	dbGoodsOrHouse.setDeliveryHouseholds(rs.getInt("delivery_households"));
    	dbGoodsOrHouse.setNonDeliveryHouseholds(rs.getInt("non_delivery_households"));
    	dbGoodsOrHouse.setCoveredArea(rs.getInt("covered_area"));
    	dbGoodsOrHouse.setPeriod(rs.getInt("period"));
    	dbGoodsOrHouse.setWestEastLength(rs.getInt("west_east_length"));
    	dbGoodsOrHouse.setSouthNorthLength(rs.getInt("south_north_length"));
    	dbGoodsOrHouse.setPlanSidewayNum(rs.getInt("plan_sideway_num"));
    	dbGoodsOrHouse.setPlanCarwayNum(rs.getInt("plan_carway_num"));
    	dbGoodsOrHouse.setActualSidewayNum(rs.getInt("actual_sideway_num"));
    	dbGoodsOrHouse.setActualCarwayNum(rs.getInt("actual_carway_num"));
    	dbGoodsOrHouse.setCreateDate(rs.getDate("create_date"));
    	dbGoodsOrHouse.setCreateUser(rs.getString("create_user"));
    	dbGoodsOrHouse.setUpdateDate(rs.getDate("update_date"));
    	dbGoodsOrHouse.setUpdateUser(rs.getString("update_user"));
    	dbGoodsOrHouse.setLastLoginTime(rs.getDate("last_login_time"));
    	if( GoodsJDBCTemplate.isExistColumn(rs, "lift_num") ){
    		dbGoodsOrHouse.setLiftNum(rs.getInt("lift_num"));
    	}
    	if( GoodsJDBCTemplate.isExistColumn(rs, "parking_vg_num") ){
    		dbGoodsOrHouse.setParkingVgNum(rs.getInt("parking_vg_num"));
    	}
    	if( GoodsJDBCTemplate.isExistColumn(rs, "parking_ug_num") ){
    		dbGoodsOrHouse.setParkingUgNum(rs.getInt("parking_ug_num"));
    	}
    	if( GoodsJDBCTemplate.isExistColumn(rs, "fire_pump_num") ){
    		dbGoodsOrHouse.setFirePumpNum(rs.getInt("fire_pump_num"));
    	}
    	
        return dbGoodsOrHouse;
    }
    
}