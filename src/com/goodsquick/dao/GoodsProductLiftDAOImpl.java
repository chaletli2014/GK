package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsProductLiftRowMapper;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;

@Repository("goodsProductLiftDAO")
public class GoodsProductLiftDAOImpl extends BaseDAOImpl implements GoodsProductLiftDAO{

	@Override
	public List<GoodsProductLift> getGoodsProductLiftByRepositoryCode(
			String repositoryCode) throws Exception {
		List<GoodsProductLift> dtList = new ArrayList<GoodsProductLift>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,gd1.dic_name as liftPurposeDesc,gd2.dic_name as liftStyleDesc ");
		sql.append(" from tbl_goods_product_lift gp, tbl_goods_dictionary gd1, tbl_goods_dictionary gd2 ");
		sql.append(" where gp.repository_code = ? and gp.status = '1' and gp.liftPurpose = gd1.dic_code and gp.liftStyle = gd2.dic_code ");
        dtList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsProductLiftRowMapper());
        return dtList;
	}

	@Override
	public GoodsProductLift getGoodsProductLiftById(int productLiftId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,gd1.dic_name as liftPurposeDesc,gd2.dic_name as liftStyleDesc ");
		sql.append(" from tbl_goods_product_lift gp, tbl_goods_dictionary gd1, tbl_goods_dictionary gd2 ");
		sql.append(" where gp.id = ? and gp.liftPurpose = gd1.dic_code and gp.liftStyle = gd2.dic_code ");
		
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{productLiftId}, new GoodsProductLiftRowMapper());
	}

	@Override
	public void saveGoodsProductLift(GoodsProductLift goodsProductLift,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_product_lift(id");
        sql.append(",code,liftBrand,liftPurpose,liftStyle,liftCT,liftNS,liftQA,price ");
        sql.append(",holeSize,pitDepth,overheadHeight,reservation,roomSize,roomHeight,carSize,carHeight,doorSize,mainPower,manufacturer,madeDate ");
        sql.append(",repository_code,create_date,create_user,update_date,update_user,status,remark)");
		sql.append("values(null");
		sql.append(",?,?,?,?,?,?,?,?");
		sql.append(",?,?,?,?,?,?,?,?,?,?,?,?");
		sql.append(",?,now(),?,now(),?,'1',?)");

		List<Object> params = new ArrayList<Object>();
		params.add(goodsProductLift.getCode());
		params.add(goodsProductLift.getLiftBrand());
		params.add(goodsProductLift.getLiftPurpose());
		params.add(goodsProductLift.getLiftStyle());
		params.add(goodsProductLift.getLiftCT());
		params.add(goodsProductLift.getLiftNS());
		params.add(goodsProductLift.getLiftQA());
		params.add(goodsProductLift.getPrice());
		params.add(goodsProductLift.getHoleSize());
		params.add(goodsProductLift.getPitDepth());
		params.add(goodsProductLift.getOverheadHeight());
		params.add(goodsProductLift.getReservation());
		params.add(goodsProductLift.getRoomSize());
		params.add(goodsProductLift.getRoomHeight());
		params.add(goodsProductLift.getCarSize());
		params.add(goodsProductLift.getCarHeight());
		params.add(goodsProductLift.getDoorSize());
		params.add(goodsProductLift.getMainPower());
		params.add(goodsProductLift.getManufacturer());
		params.add(goodsProductLift.getMadeDate());
		params.add(goodsProductLift.getRepositoryCode());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		params.add(goodsProductLift.getRemark());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void updateGoodsProductLift(GoodsProductLift goodsProductLift,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_product_lift ");
        sql.append("set liftBrand = ?, liftPurpose = ?, liftStyle = ?, liftCT = ?, liftNS = ?, liftQA = ?, price = ? ");
        sql.append(", holeSize = ?, pitDepth = ?, overheadHeight = ?, reservation = ?, roomSize = ?, roomHeight = ?, carSize = ?, carHeight = ?, doorSize = ?, mainPower = ? ");
        sql.append(", remark = ?, update_date = now(), update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProductLift.getLiftBrand());
		params.add(goodsProductLift.getLiftPurpose());
		params.add(goodsProductLift.getLiftStyle());
		params.add(goodsProductLift.getLiftCT());
		params.add(goodsProductLift.getLiftNS());
		params.add(goodsProductLift.getLiftQA());
		params.add(goodsProductLift.getPrice());
		params.add(goodsProductLift.getHoleSize());
		params.add(goodsProductLift.getPitDepth());
		params.add(goodsProductLift.getOverheadHeight());
		params.add(goodsProductLift.getReservation());
		params.add(goodsProductLift.getRoomSize());
		params.add(goodsProductLift.getRoomHeight());
		params.add(goodsProductLift.getCarSize());
		params.add(goodsProductLift.getCarHeight());
		params.add(goodsProductLift.getDoorSize());
		params.add(goodsProductLift.getMainPower());
		params.add(goodsProductLift.getRemark());
		params.add(currentUser.getLoginName());
		params.add(goodsProductLift.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsProductLift(GoodsProductLift goodsProductLift) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProductLift.getUpdateUser());
		params.add(goodsProductLift.getId());
		super.deleteObj("tbl_goods_product_lift", params);
	}

}
