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
import com.goodsquick.utils.GoodsJDBCTemplate;

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
	public GoodsOrdinaryHouse getOrdinaryHouseByRepositoryCode(String repositoryCode)
			throws Exception{
		String sql = "select * from tbl_goods_ordinary_house_owned where repository_code = ? and status = '1' ";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{repositoryCode}, new OrdinaryHouseRowMapper());
	}
	
	@Override
	public String getMaxHouseCode()	throws Exception {
		String sql = "select max(house_code) from tbl_goods_ordinary_house";
		return dataBean.getJdbcTemplate().queryForObject(sql, String.class);
	}

	@Override
	public int saveOrdinaryHouse(final GoodsOrdinaryHouse ordinaryHouse) throws Exception {
		final StringBuilder sourceSQL = new StringBuilder();
		final StringBuilder ownedSQL = new StringBuilder();
		final StringBuilder commonSQL = new StringBuilder();
		sourceSQL.append("insert into tbl_goods_ordinary_house( ");
		ownedSQL.append("insert into tbl_goods_ordinary_house_owned( ");
		
		commonSQL.append("id,house_code,building_name,building_status,company,contacter_name,contacter_position,contacter_telephone,property_name,location");
		commonSQL.append(",project_position_e,project_position_w,project_position_s,project_position_n");
		commonSQL.append(",property_type,property_type_o");
		commonSQL.append(",start_year,start_month,start_date,checkin_year,checkin_month,checkin_date");
		commonSQL.append(",floor_space,building_number,has_lift_number,non_lift_number,lobby_number,lift_lobby_number,non_lift_lobby_number");
        commonSQL.append(",owner_households,tenant_households,delivery_households,non_delivery_households");
        commonSQL.append(",covered_area,period,west_east_length,south_north_length");
        commonSQL.append(",plan_sideway_num,plan_carway_num,actual_sideway_num,actual_carway_num");
        commonSQL.append(",create_date,create_user,update_date,update_user,last_login_time,status");
        
        sourceSQL.append(commonSQL).append(") ");
		ownedSQL.append(commonSQL).append(",repository_code) ");
        
		final StringBuilder valueSQL = new StringBuilder();
		valueSQL.append("values(null,?,?,?,?");
		valueSQL.append(",?,?,?,?,?");
		valueSQL.append(",?,?,?,?");
		valueSQL.append(",?,?");
		valueSQL.append(",?,?,?,?,?,?");
		valueSQL.append(",?,?,?,?,?,?,?");
		valueSQL.append(",?,?,?,?");
		valueSQL.append(",?,?,?,?");
		valueSQL.append(",?,?,?,?");
		valueSQL.append(",now(),?,now(),?,now(),'1'");
		
		sourceSQL.append(valueSQL).append(")");
		ownedSQL.append(valueSQL).append(",?)");
		
		List<String> params = new ArrayList<String>();
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
		params.add(String.valueOf(ordinaryHouse.getStartYear()));
		params.add(String.valueOf(ordinaryHouse.getStartMonth()));
		params.add(String.valueOf(ordinaryHouse.getStartDate()));
		params.add(String.valueOf(ordinaryHouse.getCheckinYear()));
		params.add(String.valueOf(ordinaryHouse.getCheckinMonth()));
		params.add(String.valueOf(ordinaryHouse.getCheckinDate()));
		params.add(String.valueOf(ordinaryHouse.getFloorSpace()));
		params.add(String.valueOf(ordinaryHouse.getBuildingNumber()));
		params.add(String.valueOf(ordinaryHouse.getHasLiftNumber()));
		params.add(String.valueOf(ordinaryHouse.getNonLiftNumber()));
		params.add(String.valueOf(ordinaryHouse.getLobbyNumber()));
		params.add(String.valueOf(ordinaryHouse.getLiftLobbyNumber()));
		params.add(String.valueOf(ordinaryHouse.getNonLiftLobbyNumber()));
		params.add(String.valueOf(ordinaryHouse.getOwnerHouseholds()));
		params.add(String.valueOf(ordinaryHouse.getTenantHouseholds()));
		params.add(String.valueOf(ordinaryHouse.getDeliveryHouseholds()));
		params.add(String.valueOf(ordinaryHouse.getNonDeliveryHouseholds()));
		params.add(String.valueOf(ordinaryHouse.getCoveredArea()));
		params.add(String.valueOf(ordinaryHouse.getPeriod()));
		params.add(String.valueOf(ordinaryHouse.getWestEastLength()));
		params.add(String.valueOf(ordinaryHouse.getSouthNorthLength()));
		params.add(String.valueOf(ordinaryHouse.getPlanSidewayNum()));
		params.add(String.valueOf(ordinaryHouse.getPlanCarwayNum()));
		params.add(String.valueOf(ordinaryHouse.getActualSidewayNum()));
		params.add(String.valueOf(ordinaryHouse.getActualCarwayNum()));
		params.add(ordinaryHouse.getCreateUser());
		params.add(ordinaryHouse.getUpdateUser());
		
		int sourceId = GoodsJDBCTemplate.executeSQL(dataBean, sourceSQL, params);
		
		params.add(ordinaryHouse.getRepositoryCode());
		GoodsJDBCTemplate.executeSQL(dataBean, ownedSQL, params);
		
		return sourceId;
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
	public GoodsOrdinaryHouse getOwnedGoodsOrdinaryHouseById(int ordinaryHouseId)
			throws Exception {
		String sql = "select * from tbl_goods_ordinary_house_owned where id = ?";
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
	public GoodsOrdinaryHouse getOwnedGoodsOrdinaryHouseByName(String houseName)
			throws Exception {
		String sql = "select * from tbl_goods_ordinary_house_owned where building_name = ?";
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
