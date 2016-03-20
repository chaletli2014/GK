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
public class LiftDAOImpl extends BaseDAOImpl implements LiftDAO {
	
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
	public List<GoodsDeviceLift> getDeviceLiftByRepositoryCode(String repositoryCode)
			throws Exception {
		List<GoodsDeviceLift> ohList = new ArrayList<GoodsDeviceLift>();
		StringBuilder sql = new StringBuilder();
		sql.append("select dl.*,hs.subject_name,sm.module_name,lt.dic_name as lift_type_name ");
		sql.append(" ,br.dic_name as brand_name ");
		sql.append(" ,lp.dic_name as lift_purpose_name ");
		sql.append(" ,ls.dic_name as lift_style_name ");
		sql.append(" ,qa.dic_name as lift_QA_name ");
		sql.append(" ,ltime.dic_name as life_time_name ");
		sql.append(" from tbl_goods_devices_lift dl");
		sql.append(" left join tbl_goods_house_subject hs on dl.subject_id = hs.id ");
		sql.append(" left join tbl_goods_house_subject_module sm on dl.module_id = sm.id ");
		sql.append(" left join tbl_goods_dictionary lt on dl.lift_type = lt.dic_code ");
		sql.append(" left join tbl_goods_dictionary br on dl.brand_code = br.dic_code ");
		sql.append(" left join tbl_goods_dictionary lp on dl.lift_purpose = lp.dic_code ");
		sql.append(" left join tbl_goods_dictionary ls on dl.lift_style = ls.dic_code ");
		sql.append(" left join tbl_goods_dictionary qa on dl.lift_QA = qa.dic_code ");
		sql.append(" left join tbl_goods_dictionary ltime on dl.life_time = ltime.dic_code ");
		sql.append("where dl.repository_code = ? and dl.status = '1' ");
		ohList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsDeviceLiftRowMapper());
		return ohList;
	}

	@Override
	public void saveDeviceLift(GoodsDeviceLift lift) throws Exception {
		StringBuilder sql = new StringBuilder(500);
		sql.append("insert into tbl_goods_devices_lift(id,lift_type,lift_code,lift_name,lift_desc,subject_id,module_id ");
        sql.append(",delivery_date,purchase_price,user_name,brand_code,lift_purpose,lift_style,lift_CT,lift_NS,lift_QA");
        sql.append(",car_size,car_height,door_size,main_power,made_date,life_time,repository_code");
        sql.append(",createdate,create_user,updatedate,update_user,status)");
		sql.append("values(null,?,?,?,?,?,?");
		sql.append(",?,?,?,?,?,?,?,?,?");
		sql.append(",?,?,?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		
		params.add(lift.getLiftType());
		params.add(lift.getLiftCode());
		params.add(lift.getLiftName());
		params.add(lift.getLiftDesc());
		params.add(lift.getSubjectId());
		params.add(lift.getModuleId());
		params.add(lift.getDeliveryDate());
		params.add(lift.getPurchasePrice());
		params.add(lift.getUserName());
		params.add(lift.getBrandCode());
		params.add(lift.getLiftPurpose());
		params.add(lift.getLiftStyle());
		params.add(lift.getLiftCT());
		params.add(lift.getLiftNS());
		params.add(lift.getLiftQA());
		params.add(lift.getCarSize());
		params.add(lift.getCarHeight());
		params.add(lift.getDoorSize());
		params.add(lift.getMainPower());
		params.add(lift.getMadeDate());
		params.add(lift.getLifeTime());
		params.add(lift.getRepositoryCode());
		params.add(lift.getCreateUser());
		params.add(lift.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void saveDeviceLiftQuick(GoodsDeviceLift lift) throws Exception {
		StringBuilder sql = new StringBuilder(250);
		sql.append(" insert into tbl_goods_devices_lift(id,lift_name,lift_desc,subject_id,module_id,repository_code");
		sql.append(" ,create_date,create_user,update_date,update_user,status)");
		sql.append(" values(null,?,?,?,?,?");
		sql.append(" ,now(),?,now(),?,'1')");
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(lift.getLiftName());
		params.add(lift.getLiftDesc());
		params.add(lift.getSubjectId());
		params.add(lift.getModuleId());
		params.add(lift.getRepositoryCode());
		params.add(lift.getCreateUser());
		params.add(lift.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void updateDeviceLift(GoodsDeviceLift lift) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_devices_lift ");
        sql.append("set lift_type = ?,lift_code = ?,lift_name = ?,lift_desc = ?,subject_id = ?,module_id = ? ");
        sql.append(",delivery_date = ?,purchase_price = ?,user_name = ?,brand_code = ?");
        sql.append(",lift_purpose = ?,lift_style = ?,lift_CT = ?,lift_NS = ?,lift_QA = ?");
        sql.append(",car_size = ?,car_height = ?,door_size = ?,main_power = ?,made_date = ?,life_time = ? ");
        sql.append(",update_date = ?,update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(lift.getLiftType());
		params.add(lift.getLiftCode());
		params.add(lift.getLiftName());
		params.add(lift.getLiftDesc());
		params.add(lift.getSubjectId());
		params.add(lift.getModuleId());
		params.add(lift.getDeliveryDate());
		params.add(lift.getPurchasePrice());
		params.add(lift.getUserName());
		params.add(lift.getBrandCode());
		params.add(lift.getLiftPurpose());
		params.add(lift.getLiftStyle());
		params.add(lift.getLiftCT());
		params.add(lift.getLiftNS());
		params.add(lift.getLiftQA());
		params.add(lift.getCarSize());
		params.add(lift.getCarHeight());
		params.add(lift.getDoorSize());
		params.add(lift.getMainPower());
		params.add(lift.getMadeDate());
		params.add(lift.getLifeTime());
		params.add(lift.getUpdateDate());
		params.add(lift.getUpdateUser());
		params.add(lift.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteDeviceLift(int liftId) throws Exception {
		String sql = "update tbl_goods_devices_lift set status = '0',update_date=now() where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{liftId});
	}
	
	@Override
	public void deleteDeviceLift(GoodsDeviceLift liftObj) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(liftObj.getUpdateUser());
		params.add(liftObj.getId());
		super.deleteObj("tbl_goods_devices_lift", params);
	}

	@Override
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select dl.*,hs.subject_name,sm.module_name,lt.dic_name as lift_type_name ");
		sql.append(" ,br.dic_name as brand_name ");
		sql.append(" ,lp.dic_name as lift_purpose_name ");
		sql.append(" ,ls.dic_name as lift_style_name ");
		sql.append(" ,qa.dic_name as lift_QA_name ");
		sql.append(" ,ltime.dic_name as life_time_name ");
		sql.append(" from tbl_goods_devices_lift dl");
		sql.append(" left join tbl_goods_house_subject hs on dl.subject_id = hs.id ");
		sql.append(" left join tbl_goods_house_subject_module sm on dl.module_id = sm.id ");
		sql.append(" left join tbl_goods_dictionary lt on dl.lift_type = lt.dic_code ");
		sql.append(" left join tbl_goods_dictionary br on dl.brand_code = br.dic_code ");
		sql.append(" left join tbl_goods_dictionary lp on dl.lift_purpose = lp.dic_code ");
		sql.append(" left join tbl_goods_dictionary ls on dl.lift_style = ls.dic_code ");
		sql.append(" left join tbl_goods_dictionary qa on dl.lift_QA = qa.dic_code ");
		sql.append(" left join tbl_goods_dictionary ltime on dl.life_time = ltime.dic_code ");
		sql.append("where dl.id = ? ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{liftId}, new GoodsDeviceLiftRowMapper());
	}

	@Override
	public String getMaxCode() throws Exception {
		String sql = "select max(code) from tbl_goods_devices_lift";
		return dataBean.getJdbcTemplate().queryForObject(sql, String.class);
	}

}
