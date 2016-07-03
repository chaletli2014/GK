package com.goodsquick.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseDeviceRowMapper;
import com.goodsquick.model.GoodsHouseDevice;

@Repository("goodsHouseDeviceDAO")
public class GoodsHouseDeviceDAOImpl extends BaseDAOImpl implements GoodsHouseDeviceDAO {

	private static final StringBuilder SQL_SELECTION = new StringBuilder("select ")
		.append("hd.id,hd.eq_type as eq_type_code,gd.dic_name as eq_type_name, hd.name ")
		.append(",hd.brand,hd.style,hd.eq_desc,hd.subjectId,hd.moduleId ")
		.append(",hd.create_user,hd.create_date,hd.update_user,hd.update_date,hd.status ")
		.append(",hs.subject_name as subjectName,sm.module_name as moduleName ")
		.append(",hd.manufacturer, hd.velocity, hd.num, hd.enable_time, hd.maintenance_unit, hd.telephone, hd.remark ");
		
	private static final StringBuilder SQL_FROM = new StringBuilder("")
		.append(" from tbl_goods_house_device hd ")
		.append(" left join tbl_goods_house_subject hs on hd.subjectId = hs.id ")
		.append(" left join tbl_goods_house_subject_module sm on hd.moduleId = sm.id ")
		.append(" left join tbl_goods_dictionary gd on hd.eq_type = gd.dic_code ");
	
	@Override
	public int saveHouseDevice(final GoodsHouseDevice houseDevice) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final StringBuilder sql = new StringBuilder(300);
		sql.append("insert into tbl_goods_house_device");
		sql.append("(id,eq_type,name,brand,style,eq_desc,subjectId,moduleId ");
		sql.append(",create_date,create_user,update_date,update_user,status ) ");
		sql.append("values(null,?,?,?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, houseDevice.getEqTypeCode());
                ps.setString(i++, houseDevice.getName());
                ps.setString(i++, houseDevice.getBrand());
                ps.setString(i++, houseDevice.getStyle());
                ps.setString(i++, houseDevice.getEqDesc());
                ps.setInt(i++, houseDevice.getSubjectId());
                ps.setInt(i++, houseDevice.getModuleId());
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
		StringBuilder sql = new StringBuilder(200);
		sql.append("update tbl_goods_house_device ");
        sql.append("set name = ?, brand = ?, style = ?, eq_desc = ?, subjectId = ?, moduleId = ?");
        sql.append(",update_date = ?,update_user = ? where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(houseDevice.getName());
		params.add(houseDevice.getBrand());
		params.add(houseDevice.getStyle());
		params.add(houseDevice.getEqDesc());
		params.add(houseDevice.getSubjectId());
		params.add(houseDevice.getModuleId());
		params.add(houseDevice.getUpdateDate());
		params.add(houseDevice.getUpdateUser());
		params.add(houseDevice.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsHouseDevice> getAllHouseDeviceByRepositoryCode(String repositoryCode) throws Exception {
		List<GoodsHouseDevice> ohDeviceList = new ArrayList<GoodsHouseDevice>();
		StringBuilder sql = new StringBuilder(500);
		sql.append(SQL_SELECTION);
		sql.append(SQL_FROM);
		sql.append(" where hs.repository_code = ? and hd.status = '1'");
		ohDeviceList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsHouseDeviceRowMapper());
		return ohDeviceList;
	}

	@Override
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception {
		StringBuilder sql = new StringBuilder(500);
		sql.append(SQL_SELECTION);
		sql.append(SQL_FROM);
		sql.append(" where hd.id=?");
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{deviceId}, new GoodsHouseDeviceRowMapper());
	}

	@Override
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(obj.getUpdateUser());
		params.add(obj.getId());
		super.deleteObj("tbl_goods_house_device", params);
		
	}

	@Override
	public List<GoodsHouseDevice> getDeviceByEqTypeCode(String repositoryCode,
			String eqTypeCode) throws Exception {
		List<GoodsHouseDevice> ohDeviceList = new ArrayList<GoodsHouseDevice>();
		StringBuilder sql = new StringBuilder(500);
		sql.append(SQL_SELECTION);
		sql.append(SQL_FROM);
		sql.append(" where hs.repository_code = ? and hd.eq_type=? and hd.status = '1'");
		ohDeviceList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode, eqTypeCode}, new GoodsHouseDeviceRowMapper());
		return ohDeviceList;
	}

}
