package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsDeviceLiftRowMapper;
import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.utils.DataBean;

@Repository("liftDAO")
public class LiftDAOImpl implements LiftDAO {
	
	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;

	@Override
	public List<GoodsDeviceLift> getDeviceLiftByUserCode(String userCode, String liftType)
			throws Exception {
		List<GoodsDeviceLift> ohList = new ArrayList<GoodsDeviceLift>();
        String sql = "select * from tbl_goods_devices_lift where create_user = ? and lift_type = ? and status = '1' ";
        ohList = dataBean.getJdbcTemplate().query(sql, new Object[]{userCode,liftType}, new GoodsDeviceLiftRowMapper());
        return ohList;
	}

	@Override
	public void saveDeviceLift(GoodsDeviceLift lift) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_devices_lift(id,code,name,lift_type ");
        sql.append(",company_code,model,main_arguments,manufacturer,made_date");
        sql.append(",createdate,create_user,updatedate,update_user,status)");
		sql.append("values(null,?,?,?");
		sql.append(",?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(lift.getCode());
		params.add(lift.getName());
		params.add(lift.getLiftType());
		params.add(lift.getCompanyCode());
		params.add(lift.getModel());
		params.add(lift.getMainArguments());
		params.add(lift.getManufacturer());
		params.add(lift.getMadeDate());
		params.add(lift.getCreateUser());
		params.add(lift.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void updateDeviceLift(GoodsDeviceLift lift) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_devices_lift ");
        sql.append("set name = ?, company_code = ?, model = ?, manufacturer = ?, made_date = ?, main_arguments = ? ");
        sql.append(",updatedate = ?,update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(lift.getName());
		params.add(lift.getCompanyCode());
		params.add(lift.getModel());
		params.add(lift.getManufacturer());
		params.add(lift.getMadeDate());
		params.add(lift.getMainArguments());
		params.add(lift.getUpdateDate());
		params.add(lift.getUpdateUser());
		params.add(lift.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteDeviceLift(int liftId) throws Exception {
		String sql = "update tbl_goods_devices_lift set status = '0' where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{liftId});
	}

	@Override
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception {
		String sql = "select * from tbl_goods_devices_lift where id = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{liftId}, new GoodsDeviceLiftRowMapper());
	}

	@Override
	public String getMaxCode() throws Exception {
		String sql = "select max(code) from tbl_goods_devices_lift";
		return dataBean.getJdbcTemplate().queryForObject(sql, String.class);
	}

}
