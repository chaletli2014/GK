package com.goodsquick.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseModuleSPRowMapper;
import com.goodsquick.mapper.GoodsHouseSP2ndRowMapper;
import com.goodsquick.mapper.GoodsHouseSPRowMapper;
import com.goodsquick.mapper.GoodsRelationshipPropertyRowMapper;
import com.goodsquick.model.GoodsHouseModuleSP;
import com.goodsquick.model.GoodsHouseSP;
import com.goodsquick.model.GoodsHouseSP2nd;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.utils.GoodsJDBCTemplate;

@Repository("relationshipPropertyDAO")
public class RelationshipPropertyDAOImpl extends BaseDAOImpl implements RelationshipPropertyDAO {

	@Override
	public GoodsRelationshipProperty getRelationshipProperty(
			String sourceTable, int sourceId) {
		String sql = "select * from tbl_goods_relationship_property where source_table = ? and source_id = ? ";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{sourceTable,sourceId}, new GoodsRelationshipPropertyRowMapper());
	}
	
	@Override
	public GoodsRelationshipProperty getRelationshipProperty(int relationShipId) {
		String sql = "select * from tbl_goods_relationship_property where id = ?";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{relationShipId}, new GoodsRelationshipPropertyRowMapper());
	}

	@Override
	public int saveRelationshipProperty(final GoodsRelationshipProperty relationshipProperty)
			throws Exception {
		final StringBuilder sql = new StringBuilder(800);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		sql.append("insert into tbl_goods_relationship_property(id,source_table,source_id,location");
        sql.append(",brand_name,brand_code ");
        sql.append(",design_name,design_code ");
        sql.append(",manufacturer_name,manufacturer_code ");
        sql.append(",certification_name,certification_code ");
        sql.append(",channel_name,channel_code ");
        sql.append(",logistics_name,logistics_code ");
        sql.append(",owner_name,owner_code ");
        sql.append(",trusteeship_name,trusteeship_code ");
        sql.append(",supervision_name,supervision_code ");
        sql.append(",recycling_name,recycling_code ");
        sql.append(",createdate,create_user,updatedate,update_user )");
		sql.append("values(null,?,?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",?,?");
		sql.append(",now(),?,now(),?)");
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, relationshipProperty.getSourceTable());
                ps.setInt(2, relationshipProperty.getSourceId());
                ps.setString(3, relationshipProperty.getLocation());
                ps.setString(4, relationshipProperty.getBrandName());
                ps.setString(5, relationshipProperty.getBrandCode());
                ps.setString(6, relationshipProperty.getDesignName());
                ps.setString(7, relationshipProperty.getDesignCode());
                ps.setString(8, relationshipProperty.getManufacturerName());
                ps.setString(9, relationshipProperty.getManufacturerCode());
                ps.setString(10, relationshipProperty.getCertificationName());
                ps.setString(11, relationshipProperty.getCertificationCode());
                ps.setString(12, relationshipProperty.getChannelName());
                ps.setString(13, relationshipProperty.getChannelCode());
                ps.setString(14, relationshipProperty.getLogisticsName());
                ps.setString(15, relationshipProperty.getLogisticsCode());
                ps.setString(16, relationshipProperty.getOwnerName());
                ps.setString(17, relationshipProperty.getOwnerCode());
                ps.setString(18, relationshipProperty.getTrusteeshipName());
                ps.setString(19, relationshipProperty.getTrusteeshipCode());
                ps.setString(20, relationshipProperty.getSupervisionName());
                ps.setString(21, relationshipProperty.getSupervisionCode());
                ps.setString(22, relationshipProperty.getRecyclingName());
                ps.setString(23, relationshipProperty.getRecyclingCode());
                ps.setString(24, relationshipProperty.getCreateUser());
                ps.setString(25, relationshipProperty.getUpdateUser());
                return ps;
            }
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateRelationshipProperty(
			GoodsRelationshipProperty relationshipProperty) throws Exception {
		StringBuilder sql = new StringBuilder(800);
		sql.append("update tbl_goods_relationship_property ");
        sql.append("set location = ?, brand_name = ?, brand_code = ?");
		sql.append(",design_name = ?,design_code = ? ");
        sql.append(",manufacturer_name = ?,manufacturer_code = ? ");
        sql.append(",certification_name = ?,certification_code = ? ");
        sql.append(",channel_name = ?,channel_code = ? ");
        sql.append(",logistics_name = ?,logistics_code = ? ");
        sql.append(",owner_name = ?,owner_code = ? ");
        sql.append(",trusteeship_name = ?,trusteeship_code = ? ");
        sql.append(",supervision_name = ?,supervision_code = ? ");
        sql.append(",recycling_name = ?,recycling_code = ? ");
        sql.append(",updatedate = now(),update_user = ? ");
		sql.append("where source_table = ? and source_id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(relationshipProperty.getLocation());
		params.add(relationshipProperty.getBrandName());
		params.add(relationshipProperty.getBrandCode());
		params.add(relationshipProperty.getDesignName());
		params.add(relationshipProperty.getDesignCode());
		params.add(relationshipProperty.getManufacturerName());
		params.add(relationshipProperty.getManufacturerCode());
		params.add(relationshipProperty.getCertificationName());
		params.add(relationshipProperty.getCertificationCode());
		params.add(relationshipProperty.getChannelName());
		params.add(relationshipProperty.getChannelCode());
		params.add(relationshipProperty.getLogisticsName());
		params.add(relationshipProperty.getLogisticsCode());
		params.add(relationshipProperty.getOwnerName());
		params.add(relationshipProperty.getOwnerCode());
		params.add(relationshipProperty.getTrusteeshipName());
		params.add(relationshipProperty.getTrusteeshipCode());
		params.add(relationshipProperty.getSupervisionName());
		params.add(relationshipProperty.getSupervisionCode());
		params.add(relationshipProperty.getRecyclingName());
		params.add(relationshipProperty.getRecyclingCode());
		params.add(relationshipProperty.getUpdateUser());
		params.add(relationshipProperty.getSourceTable());
		params.add(relationshipProperty.getSourceId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateRelationshipProperty(String columnName, String columnValue, int relationShipId)
			throws Exception {
		StringBuilder sql = new StringBuilder(150);
		sql.append("update tbl_goods_relationship_property ");
        sql.append("set ").append(columnName).append(" = ?");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(columnValue);
		params.add(relationShipId);
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void saveSPModule(GoodsHouseSP houseModule) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbl_goods_house_sp(id,house_code,module_sp_type,module_sp_value");
        sql.append(",create_date,create_user,update_date,update_user,status,remark) ");
		sql.append("values(null,?,?,?,now(),?,now(),?,'1',?)");
		
		List<String> params = new ArrayList<String>();
		params.add(houseModule.getHouseCode());
		params.add(houseModule.getModuleSPType());
		params.add(houseModule.getModuleSPValue());
		params.add(houseModule.getCreateUser());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getRemark());
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
	
	@Override
	public void updateSPModule(GoodsHouseSP houseModule) {
		StringBuilder sql = new StringBuilder(150);
		sql.append("update tbl_goods_house_sp set module_sp_value = ?, remark = ?, update_date = now(), update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(houseModule.getModuleSPValue());
		params.add(houseModule.getRemark());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getId());
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void removeSPModule(GoodsHouseSP houseModule) {
		dataBean.getJdbcTemplate().update("update tbl_goods_house_sp set status = '0' where id = ? ",houseModule.getId());
	}

	@Override
	public List<GoodsHouseSP> getSPModuleByHouseCodeAndType(
			String houseCode, String moduleType) {
		String sql = "select * from tbl_goods_house_sp where house_code = ? and module_sp_type = ? ";
		return dataBean.getJdbcTemplate().query(sql, new Object[]{houseCode,moduleType}, new GoodsHouseSPRowMapper());
	}
	
	@Override
	public List<GoodsHouseSP2nd> get2ndSPModuleByHouseCodeAndType(
			String houseCode, String moduleType) {
		String sql = "select * from tbl_goods_house_sp_2nd where house_code = ? and sp_type = ? ";
		return dataBean.getJdbcTemplate().query(sql, new Object[]{houseCode,moduleType}, new GoodsHouseSP2ndRowMapper());
	}
	

	@Override
	public void saveSPModule2nd(GoodsHouseSP2nd houseModule) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbl_goods_house_sp_2nd(id,house_code,sp_type,module_type,sp_name");
        sql.append(",create_date,create_user,update_date,update_user,status,remark) ");
		sql.append("values(null,?,?,?,?,now(),?,now(),?,'1',?)");
		
		List<String> params = new ArrayList<String>();
		params.add(houseModule.getHouseCode());
		params.add(houseModule.getSpType());
		params.add(houseModule.getModuleType());
		params.add(houseModule.getSpName());
		params.add(houseModule.getCreateUser());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getRemark());
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
	

	@Override
	public void updateSPModule2nd(GoodsHouseSP2nd houseModule) {
		StringBuilder sql = new StringBuilder(150);
		sql.append("update tbl_goods_house_sp_2nd set module_sp_value = ?, remark = ?, update_date = now(), update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(houseModule.getRemark());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getId());
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void removeSPModule2nd(GoodsHouseSP2nd houseModule) {
		dataBean.getJdbcTemplate().update("update tbl_goods_house_sp_2nd set status = '0' where id = ? ",houseModule.getId());
	}
	
	@Override
	public List<GoodsHouseModuleSP> getModuleSPByHouseCodeAndType(
			String houseCode, String moduleType) {
		StringBuilder sql = new StringBuilder(100);
		List<String> params = new ArrayList<String>();
		
		sql.append(" select * from tbl_goods_house_module_sp ");
		sql.append(" where house_code = ? ");
		
		params.add(houseCode);
		
		if( !StringUtils.isBlank(moduleType) ){
			sql.append("and module_type = ? ");
			params.add(moduleType);
		}
		return dataBean.getJdbcTemplate().query(sql.toString(), params.toArray(), new GoodsHouseModuleSPRowMapper());
	}
	

	@Override
	public void saveModuleSP(GoodsHouseModuleSP houseModule) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbl_goods_house_module_sp(id,house_code,module_type,sp_name");
        sql.append(",create_date,create_user,update_date,update_user,status,remark) ");
		sql.append("values(null,?,?,?,now(),?,now(),?,'1',?)");
		
		List<String> params = new ArrayList<String>();
		params.add(houseModule.getHouseCode());
		params.add(houseModule.getModuleType());
		params.add(houseModule.getModuleSPName());
		params.add(houseModule.getCreateUser());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getRemark());
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
	

	@Override
	public void updateModuleSP(GoodsHouseModuleSP houseModule) {
		StringBuilder sql = new StringBuilder(150);
		sql.append("update tbl_goods_house_module_sp set sp_name = ?, remark = ?, update_date = now(), update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(houseModule.getModuleSPName());
		params.add(houseModule.getRemark());
		params.add(houseModule.getUpdateUser());
		params.add(houseModule.getId());
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void removeModuleSP(GoodsHouseModuleSP houseModule) {
		dataBean.getJdbcTemplate().update("update tbl_goods_house_module_sp set status = '0' where id = ? ",houseModule.getId());
	}

}
