package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsRelatedRequestRowMapper;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.DataBean;

@Repository("messageDAO")
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;

	@Override
	public List<GoodsRelatedRequest> getMessageList(String loginName) throws Exception {
		List<GoodsRelatedRequest> messageList = new ArrayList<GoodsRelatedRequest>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select sr.id,sr.sp_customer_id,sr.customer_name,sr.sp_code,gs.name as sp_name");
		sql.append(" ,sr.status,sr.service_type as service_type_code, gd.dic_name as service_type_name  ");
		sql.append(" ,sr.createdate,sr.updatedate,sr.create_user,sr.update_user  ");
        sql.append(" from tbl_goods_ordinary_house oh, tbl_goods_sp_request sr, tbl_goods_service gs, tbl_goods_dictionary gd ");
        sql.append(" where oh.create_user = ? and oh.building_name = sr.customer_name and sr.sp_code = gs.code");
        sql.append(" and sr.service_type = gd.dic_code and gd.type_code='serviceTypes' order by sr.createdate desc");
        messageList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{loginName}, new GoodsRelatedRequestRowMapper());
        return messageList;
	}

	@Override
	public void handleMessage(int messageId, String status, WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_sp_request ");
        sql.append("set status = ?,updatedate = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(currentUser.getLoginName());
		params.add(messageId);
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public GoodsRelatedRequest getMessageById(int messageId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sr.id,sr.sp_customer_id,sr.customer_name,sr.sp_code,gs.name as sp_name");
		sql.append(" ,sr.status,sr.service_type as service_type_code, gd.dic_name as service_type_name  ");
		sql.append(" ,sr.createdate,sr.updatedate,sr.create_user,sr.update_user  ");
        sql.append(" from tbl_goods_ordinary_house oh, tbl_goods_sp_request sr, tbl_goods_service gs, tbl_goods_dictionary gd ");
        sql.append(" where sr.id = ? and oh.building_name = sr.customer_name and sr.sp_code = gs.code");
        sql.append(" and sr.service_type = gd.dic_code and gd.type_code='serviceTypes'");
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{messageId},new GoodsRelatedRequestRowMapper());
	}
}
