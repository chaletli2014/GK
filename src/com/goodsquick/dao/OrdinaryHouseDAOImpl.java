package com.goodsquick.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseDeviceRowMapper;
import com.goodsquick.mapper.GoodsHouseRalationshipRowMapper;
import com.goodsquick.mapper.OrdinaryHouseRowMapper;
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.DataBean;

@Repository("ordinaryHouseDAO")
public class OrdinaryHouseDAOImpl implements OrdinaryHouseDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<GoodsOrdinaryHouse> getOrdinaryHouseByUserCode(String userCode)
			throws Exception {
		List<GoodsOrdinaryHouse> ohList = new ArrayList<GoodsOrdinaryHouse>();
        String sql = "select * from tbl_goods_ordinary_house where create_user = ? and status = '1' ";
        ohList = dataBean.getJdbcTemplate().query(sql, new Object[]{userCode}, new OrdinaryHouseRowMapper());
        return ohList;
	}
	
	@Override
	public String getMaxHouseCode()	throws Exception {
		String sql = "select max(house_code) from tbl_goods_ordinary_house";
		return dataBean.getJdbcTemplate().queryForObject(sql, String.class);
	}

	@Override
	public int saveOrdinaryHouse(final GoodsOrdinaryHouse ordinaryHouse) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into tbl_goods_ordinary_house(id,house_code,building_name,building_status,company ");
        sql.append(",contacter_name,contacter_position,contacter_telephone,property_name,location");
        sql.append(",project_position_e,project_position_w,project_position_s,project_position_n");
        sql.append(",property_type,property_type_o");
        sql.append(",start_year,start_month,start_date,checkin_year,checkin_month,checkin_date");
        sql.append(",floor_space,building_number,has_lift_number,non_lift_number,lobby_number,lift_lobby_number,non_lift_lobby_number");
        sql.append(",owner_households,tenant_households,delivery_households,non_delivery_households");
        sql.append(",covered_area,period,west_east_length,south_north_length");
        sql.append(",plan_sideway_num,plan_carway_num,actual_sideway_num,actual_carway_num");
        sql.append(",create_date,create_user,update_date,update_user,last_login_time,status )");
		sql.append("values(null,?,?,?,?");
		sql.append(",?,?,?,?,?");
		sql.append(",?,?,?,?");
		sql.append(",?,?");
		sql.append(",?,?,?,?,?,?");
		sql.append(",?,?,?,?,?,?,?");
		sql.append(",?,?,?,?");
		sql.append(",?,?,?,?");
		sql.append(",?,?,?,?");
		sql.append(",now(),?,now(),?,now(),'1')");
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, ordinaryHouse.getHouseCode());
                ps.setString(i++, ordinaryHouse.getBuildingName());
                ps.setString(i++, ordinaryHouse.getBuildingStatus());
                ps.setString(i++, ordinaryHouse.getCompany());
                ps.setString(i++, ordinaryHouse.getContacterName());
                ps.setString(i++, ordinaryHouse.getContacterPosition());
                ps.setString(i++, ordinaryHouse.getContacterTelephone());
                ps.setString(i++, ordinaryHouse.getPropertyName());
                ps.setString(i++, ordinaryHouse.getLocation());
                ps.setString(i++, ordinaryHouse.getProjectPositionE());
                ps.setString(i++, ordinaryHouse.getProjectPositionW());
                ps.setString(i++, ordinaryHouse.getProjectPositionS());
                ps.setString(i++, ordinaryHouse.getProjectPositionN());
                ps.setString(i++, ordinaryHouse.getPropertyType());
                ps.setString(i++, ordinaryHouse.getPropertytypeO());
                ps.setInt(i++, ordinaryHouse.getStartYear());
                ps.setInt(i++, ordinaryHouse.getStartMonth());
                ps.setInt(i++, ordinaryHouse.getStartDate());
                ps.setInt(i++, ordinaryHouse.getCheckinYear());
                ps.setInt(i++, ordinaryHouse.getCheckinMonth());
                ps.setInt(i++, ordinaryHouse.getCheckinDate());
                ps.setDouble(i++, ordinaryHouse.getFloorSpace());
                ps.setInt(i++, ordinaryHouse.getBuildingNumber());
                ps.setInt(i++, ordinaryHouse.getHasLiftNumber());
                ps.setInt(i++, ordinaryHouse.getNonLiftNumber());
                ps.setInt(i++, ordinaryHouse.getLobbyNumber());
                ps.setInt(i++, ordinaryHouse.getLiftLobbyNumber());
                ps.setInt(i++, ordinaryHouse.getNonLiftLobbyNumber());
                ps.setInt(i++, ordinaryHouse.getOwnerHouseholds());
                ps.setInt(i++, ordinaryHouse.getTenantHouseholds());
                ps.setInt(i++, ordinaryHouse.getDeliveryHouseholds());
                ps.setInt(i++, ordinaryHouse.getNonDeliveryHouseholds());
                ps.setInt(i++, ordinaryHouse.getCoveredArea());
                ps.setInt(i++, ordinaryHouse.getPeriod());
                ps.setInt(i++, ordinaryHouse.getWestEastLength());
                ps.setInt(i++, ordinaryHouse.getSouthNorthLength());
                ps.setInt(i++, ordinaryHouse.getPlanSidewayNum());
                ps.setInt(i++, ordinaryHouse.getPlanCarwayNum());
                ps.setInt(i++, ordinaryHouse.getActualSidewayNum());
                ps.setInt(i++, ordinaryHouse.getActualCarwayNum());
                ps.setString(i++, ordinaryHouse.getCreateUser());
                ps.setString(i++, ordinaryHouse.getUpdateUser());
                return ps;
            }
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateOrdinaryHouse(GoodsOrdinaryHouse ordinaryHouse)
			throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_ordinary_house ");
        sql.append("set building_name = ?, building_status = ?, company = ?");
		sql.append(",contacter_name = ?,contacter_position = ?,contacter_telephone = ?,property_name = ?,location = ?");
        sql.append(",project_position_e = ?,project_position_w = ?,project_position_s = ?,project_position_n = ?");
        sql.append(",property_type = ?,property_type_o = ?");
        sql.append(",start_year = ?,start_month = ?,start_date = ?,checkin_year = ?,checkin_month = ?,checkin_date = ?");
        sql.append(",floor_space = ?,building_number = ?,has_lift_number = ?,non_lift_number = ?,lobby_number = ?,lift_lobby_number = ?,non_lift_lobby_number = ?");
        sql.append(",owner_households = ?,tenant_households = ?,delivery_households = ?,non_delivery_households = ?");
        sql.append(",covered_area = ?,period = ?,west_east_length = ?,south_north_length = ?");
        sql.append(",plan_sideway_num = ?,plan_carway_num = ?,actual_sideway_num = ?,actual_carway_num = ?");
        sql.append(",update_date = ?,update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(ordinaryHouse.getBuildingName());
		params.add(ordinaryHouse.getBuildingStatus());
		params.add(ordinaryHouse.getCompany());
		params.add(ordinaryHouse.getContacterName());
		params.add(ordinaryHouse.getContacterPosition());
		params.add(ordinaryHouse.getContacterTelephone());
		params.add(ordinaryHouse.getPropertyName());
		params.add(ordinaryHouse.getLocation());
		params.add(ordinaryHouse.getProjectPositionE());
		params.add(ordinaryHouse.getProjectPositionW());
		params.add(ordinaryHouse.getProjectPositionS());
		params.add(ordinaryHouse.getProjectPositionN());
		params.add(ordinaryHouse.getPropertyType());
		params.add(ordinaryHouse.getPropertytypeO());
		params.add(ordinaryHouse.getStartYear());
		params.add(ordinaryHouse.getStartMonth());
		params.add(ordinaryHouse.getStartDate());
		params.add(ordinaryHouse.getCheckinYear());
		params.add(ordinaryHouse.getCheckinMonth());
		params.add(ordinaryHouse.getCheckinDate());
		params.add(ordinaryHouse.getFloorSpace());
		params.add(ordinaryHouse.getBuildingNumber());
		params.add(ordinaryHouse.getHasLiftNumber());
		params.add(ordinaryHouse.getNonLiftNumber());
		params.add(ordinaryHouse.getLobbyNumber());
		params.add(ordinaryHouse.getLiftLobbyNumber());
		params.add(ordinaryHouse.getNonLiftLobbyNumber());
		params.add(ordinaryHouse.getOwnerHouseholds());
		params.add(ordinaryHouse.getTenantHouseholds());
		params.add(ordinaryHouse.getDeliveryHouseholds());
		params.add(ordinaryHouse.getNonDeliveryHouseholds());
		params.add(ordinaryHouse.getCoveredArea());
		params.add(ordinaryHouse.getPeriod());
		params.add(ordinaryHouse.getWestEastLength());
		params.add(ordinaryHouse.getSouthNorthLength());
		params.add(ordinaryHouse.getPlanSidewayNum());
		params.add(ordinaryHouse.getPlanCarwayNum());
		params.add(ordinaryHouse.getActualSidewayNum());
		params.add(ordinaryHouse.getActualCarwayNum());
		params.add(ordinaryHouse.getUpdateDate());
		params.add(ordinaryHouse.getUpdateUser());
		params.add(ordinaryHouse.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteOrdinaryHouse(int ordinaryHouseId) throws Exception {
		String sql = "update tbl_goods_ordinary_house set status = '0' where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{ordinaryHouseId});
	}

	@Override
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseById(int ordinaryHouseId)
			throws Exception {
        String sql = "select * from tbl_goods_ordinary_house where id = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{ordinaryHouseId}, new OrdinaryHouseRowMapper());
	}

	@Override
	public void saveOrdinaryHousesFromFile(List<GoodsOrdinaryHouse> houses) throws Exception {
		for( GoodsOrdinaryHouse ordinaryHouse : houses ){
			StringBuilder sql = new StringBuilder("insert into tbl_goods_ordinary_house(id,house_code,building_name,building_status,company ");
			sql.append(",contacter_name,contacter_position,contacter_telephone,property_name,location");
			sql.append(",project_position_e,project_position_w,project_position_s,project_position_n");
			sql.append(",property_type,property_type_o");
			sql.append(",start_year,start_month,start_date,checkin_year,checkin_month,checkin_date");
			sql.append(",floor_space,building_number,has_lift_number,non_lift_number,lobby_number,lift_lobby_number,non_lift_lobby_number");
			sql.append(",owner_households,tenant_households,delivery_households,non_delivery_households");
			sql.append(",covered_area,period,west_east_length,south_north_length");
			sql.append(",plan_sideway_num,plan_carway_num,actual_sideway_num,actual_carway_num");
			sql.append(",create_date,create_user,update_date,update_user,last_login_time,status ) values ");
			sql.append("(null,?,?,?,?");
			sql.append(",?,?,?,?,?");
			sql.append(",?,?,?,?");
			sql.append(",?,?");
			sql.append(",?,?,?,?,?,?");
			sql.append(",?,?,?,?,?,?,?");
			sql.append(",?,?,?,?");
			sql.append(",?,?,?,?");
			sql.append(",?,?,?,?");
			sql.append(",now(),'admin',now(),'admin',now(),'1')");
			
			List<Object> params = new ArrayList<Object>();
			params.add(ordinaryHouse.getHouseCode());
			params.add(ordinaryHouse.getBuildingName());
			params.add(ordinaryHouse.getBuildingStatus());
			params.add(ordinaryHouse.getCompany());
			params.add(ordinaryHouse.getContacterName());
			params.add(ordinaryHouse.getContacterPosition());
			params.add(ordinaryHouse.getContacterTelephone());
			params.add(ordinaryHouse.getPropertyName());
			params.add(ordinaryHouse.getLocation());
			params.add(ordinaryHouse.getProjectPositionE());
			params.add(ordinaryHouse.getProjectPositionW());
			params.add(ordinaryHouse.getProjectPositionS());
			params.add(ordinaryHouse.getProjectPositionN());
			params.add(ordinaryHouse.getPropertyType());
			params.add(ordinaryHouse.getPropertytypeO());
			params.add(ordinaryHouse.getStartYear());
			params.add(ordinaryHouse.getStartMonth());
			params.add(ordinaryHouse.getStartDate());
			params.add(ordinaryHouse.getCheckinYear());
			params.add(ordinaryHouse.getCheckinMonth());
			params.add(ordinaryHouse.getCheckinDate());
			params.add(ordinaryHouse.getFloorSpace());
			params.add(ordinaryHouse.getBuildingNumber());
			params.add(ordinaryHouse.getHasLiftNumber());
			params.add(ordinaryHouse.getNonLiftNumber());
			params.add(ordinaryHouse.getLobbyNumber());
			params.add(ordinaryHouse.getLiftLobbyNumber());
			params.add(ordinaryHouse.getNonLiftLobbyNumber());
			params.add(ordinaryHouse.getOwnerHouseholds());
			params.add(ordinaryHouse.getTenantHouseholds());
			params.add(ordinaryHouse.getDeliveryHouseholds());
			params.add(ordinaryHouse.getNonDeliveryHouseholds());
			params.add(ordinaryHouse.getCoveredArea());
			params.add(ordinaryHouse.getPeriod());
			params.add(ordinaryHouse.getWestEastLength());
			params.add(ordinaryHouse.getSouthNorthLength());
			params.add(ordinaryHouse.getPlanSidewayNum());
			params.add(ordinaryHouse.getPlanCarwayNum());
			params.add(ordinaryHouse.getActualSidewayNum());
			params.add(ordinaryHouse.getActualCarwayNum());
			
			dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
		}
	}
	
	@Override
	public void saveOrdinaryHouseDeviceFromFile(List<GoodsHouseDevice> houseDevices) throws Exception {
		for( GoodsHouseDevice houseDevice : houseDevices ){
			StringBuilder sql = new StringBuilder("insert into tbl_goods_house_device(id,house_code,device_type,device_name,manufacturer_name ");
			sql.append(",brand_name,model,device_num ");
			sql.append(",createdate,create_user,updatedate,update_user,status ) values ");
			sql.append("(null,?,?,?,?");
			sql.append(",?,?,?");
			sql.append(",now(),'admin',now(),'admin','1')");
			
			List<Object> params = new ArrayList<Object>();
			params.add(houseDevice.getOrHouseCode());
			params.add(houseDevice.getDeviceType());
			params.add(houseDevice.getDeviceName());
			params.add(houseDevice.getManufacturerName());
			params.add(houseDevice.getBrandName());
			params.add(houseDevice.getModel());
			params.add(houseDevice.getDeviceNum());
			
			dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
		}
	}

	@Override
	public int saveHouseDevice(final GoodsHouseDevice houseDevice) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final StringBuilder sql = new StringBuilder(300);
		sql.append("insert into tbl_goods_house_device(id,house_code,device_type,device_name,manufacturer_name ");
        sql.append(",brand_name,model,device_num");
        sql.append(",createdate,create_user,updatedate,update_user,status )");
		sql.append("values(null,?,?,?,?");
		sql.append(",?,?,?");
		sql.append(",now(),?,now(),?,'1')");
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, houseDevice.getOrHouseCode());
                ps.setString(i++, houseDevice.getDeviceType());
                ps.setString(i++, houseDevice.getDeviceName());
                ps.setString(i++, houseDevice.getManufacturerName());
                ps.setString(i++, houseDevice.getBrandName());
                ps.setString(i++, houseDevice.getModel());
                ps.setInt(i++, houseDevice.getDeviceNum());
                ps.setString(i++, houseDevice.getCreateUser());
                ps.setString(i++, houseDevice.getUpdateUser());
                return ps;
            }
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateHouseDevice(GoodsHouseDevice houseDevice)
			throws Exception {
		StringBuilder sql = new StringBuilder(150);
		sql.append("update tbl_goods_house_device ");
        sql.append("set device_name = ?, manufacturer_name = ?, brand_name = ?");
		sql.append(",model = ?,device_num = ?");
        sql.append(",update_date = ?,update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(houseDevice.getDeviceName());
		params.add(houseDevice.getManufacturerName());
		params.add(houseDevice.getBrandName());
		params.add(houseDevice.getModel());
		params.add(houseDevice.getDeviceNum());
		params.add(houseDevice.getUpdateDate());
		params.add(houseDevice.getUpdateUser());
		params.add(houseDevice.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsHouseDevice> getAllHouseDeviceByUser(
			GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser)
			throws Exception {
		List<GoodsHouseDevice> ohDeviceList = new ArrayList<GoodsHouseDevice>();
		StringBuilder sql = new StringBuilder(500);
		sql.append(" select hd.id,hd.house_code,d.dic_name as device_type");
		sql.append(" ,hd.device_name,hd.manufacturer_name,hd.brand_name,hd.model,hd.device_num");
		sql.append(" ,hd.create_user,hd.createdate,hd.update_user,hd.updatedate,hd.status");
		sql.append(" from tbl_goods_house_device hd, tbl_goods_dictionary d ");
		sql.append(" where hd.house_code = ? and hd.device_type = d.dic_code ");
		sql.append(" and d.type_code = 'device_type' ");
		sql.append(" and hd.create_user = ? and hd.status = '1'");
        ohDeviceList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{ordinaryHouse.getHouseCode(),currentUser.getLoginName()}, new GoodsHouseDeviceRowMapper());
        return ohDeviceList;
	}

	@Override
	public void updateSP2House(String updateSQL,List<Object> params) throws Exception {
		dataBean.getJdbcTemplate().update(updateSQL,params.toArray());
	}

	@Override
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseByName(String houseName)
			throws Exception {
		String sql = "select * from tbl_goods_ordinary_house where building_name = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{houseName}, new OrdinaryHouseRowMapper());
	}

	@Override
	public List<GoodsRelationshipProperty> getAllHouseRelationshipByUserCode(
			String userCode) throws Exception {
		StringBuilder sql = new StringBuilder(249);
		sql.append(" select rp.*,oh.building_name as house_name ");
		sql.append(" from tbl_goods_relationship_property rp, tbl_goods_ordinary_house oh ");
		sql.append(" where rp.source_table = 'tbl_goods_ordinary_house' and rp.source_id = oh.id ");
		sql.append(" and oh.create_user = ? ");
        
		return dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{userCode}, new GoodsHouseRalationshipRowMapper());
	}

	@Override
	public List<String> getAllExistsHouses() throws Exception {
		return dataBean.getJdbcTemplate().queryForList("select distinct building_name from tbl_goods_ordinary_house", String.class);
	}

}
