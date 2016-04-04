package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.goodsquick.mapper.GoodsDeviceLiftRowMapper;
import com.goodsquick.model.GoodsDeviceLift;

@Repository("liftDAO")
public class LiftDAOImpl extends BaseDAOImpl implements LiftDAO {
	
	private static final StringBuilder LIFT_QUERY_SELECTION_FROM
		= new StringBuilder("dl.*,hs.subject_name,sm.module_name,lt.dic_name as lift_type_name")
			.append(" ,br.dic_name as brand_name ")
			.append(" ,lp.dic_name as lift_purpose_name ")
			.append(" ,ls.dic_name as lift_style_name ")
			.append(" ,qa.dic_name as lift_QA_name ")
			.append(" ,ltime.dic_name as life_time_name ")
			.append(" from tbl_goods_devices_lift dl")
			.append(" left join tbl_goods_house_subject hs on dl.subject_id = hs.id ")
			.append(" left join tbl_goods_house_subject_module sm on dl.module_id = sm.id ")
			.append(" left join tbl_goods_dictionary lt on dl.lift_type = lt.dic_code ")
			.append(" left join tbl_goods_dictionary br on dl.brand_code = br.dic_code ")
			.append(" left join tbl_goods_dictionary lp on dl.lift_purpose = lp.dic_code ")
			.append(" left join tbl_goods_dictionary ls on dl.lift_style = ls.dic_code ")
			.append(" left join tbl_goods_dictionary qa on dl.lift_QA = qa.dic_code ")
			.append(" left join tbl_goods_dictionary ltime on dl.life_time = ltime.dic_code ");

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
		sql.append("select ");
		sql.append(LIFT_QUERY_SELECTION_FROM);
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
		sql.append("select ");
		sql.append(LIFT_QUERY_SELECTION_FROM);
		sql.append("where dl.id = ? ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{liftId}, new GoodsDeviceLiftRowMapper());
	}

	@Override
	public String getMaxCode() throws Exception {
		String sql = "select max(code) from tbl_goods_devices_lift";
		return dataBean.getJdbcTemplate().queryForObject(sql, String.class);
	}

	@Override
	public List<GoodsDeviceLift> getDeviceLiftBySubjectId(int subjectId)
			throws Exception {
		List<GoodsDeviceLift> ohList = new ArrayList<GoodsDeviceLift>();
		StringBuilder sql = new StringBuilder("select ");
		sql.append(LIFT_QUERY_SELECTION_FROM);
		sql.append(" where FIND_IN_SET(dl.subject_id, queryChildrenSubjectInfo(?)) and dl.status='1' ");
		ohList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{subjectId}, new GoodsDeviceLiftRowMapper());
        return ohList;
	}

	@Override
	public List<GoodsDeviceLift> getDeviceLiftByModuleId(int moduleId)
			throws Exception {
		List<GoodsDeviceLift> ohList = new ArrayList<GoodsDeviceLift>();
		StringBuilder sql = new StringBuilder("select ").append(LIFT_QUERY_SELECTION_FROM);
		sql.append(" where dl.module_id=? and dl.status='1' ");
		ohList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{moduleId}, new GoodsDeviceLiftRowMapper());
        return ohList;
	}

}
