package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsSPCustomerRowMapper;
import com.goodsquick.mapper.GoodsServiceDetailRowMapper;
import com.goodsquick.mapper.GoodsServiceRowMapper;
import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.GoodsDateUtil;

@Repository("goodsServiceDAO")
public class GoodsServiceDAOImpl extends BaseDAOImpl implements GoodsServiceDAO {

	@Override
	public List<GoodsServiceProvider> getGoodsServiceProviderByUserCode(String userCode)
			throws Exception {
		List<GoodsServiceProvider> serviceList = new ArrayList<GoodsServiceProvider>();
        String sql = "select * from tbl_goods_service where create_user = ? and status = '1' ";
        serviceList = dataBean.getJdbcTemplate().query(sql, new Object[]{userCode}, new GoodsServiceRowMapper());
        return serviceList;
	}

	@Override
	public void saveGoodsServiceProvider(GoodsServiceProvider goodsService,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_service(id,code,name,address ");
        sql.append(",telephone,contract_number,service_content");
        sql.append(",createdate,create_user,updatedate,update_user,status)");
		sql.append("values(null,?,?,?");
		sql.append(",?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(goodsService.getCode());
		params.add(goodsService.getName());
		params.add(goodsService.getAddress());
		params.add(goodsService.getTelephone());
		params.add(goodsService.getContractNumber());
		params.add(goodsService.getServiceContent());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateGoodsServiceProvider(GoodsServiceProvider goodsService,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_service ");
        sql.append("set name = ?, address = ?, telephone = ?, contract_number = ?, service_content = ? ");
        sql.append(",updatedate = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsService.getName());
		params.add(goodsService.getAddress());
		params.add(goodsService.getTelephone());
		params.add(goodsService.getContractNumber());
		params.add(goodsService.getServiceContent());
		params.add(currentUser.getLoginName());
		params.add(goodsService.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsServiceProvider(int serviceId) throws Exception {
		String sql = "update tbl_goods_service set status = '0' where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{serviceId});
	}

	@Override
	public GoodsServiceProvider getGoodsServiceProviderById(int serviceId) throws Exception {
		String sql = "select * from tbl_goods_service where id = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{serviceId}, new GoodsServiceRowMapper());
	}

	@Override
	public String getMaxCode() throws Exception {
		String prefix = "service"+GoodsDateUtil.getStringFormat(new Date())+"%";
		String sql = "select max(code) from tbl_goods_service where code like ? ";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{prefix},String.class);
	}

	@Override
	public List<GoodsServiceDetail> getGoodsServiceDetailsByProviderCode(
			String providerCode) throws Exception {
		List<GoodsServiceDetail> serviceDetailList = new ArrayList<GoodsServiceDetail>();
        StringBuffer sql = new StringBuffer("select gsd.*,tc.category_name,gd.dic_name as service_name ");
        sql.append(" from tbl_goods_service_detail gsd,  tbl_category tc, tbl_goods_dictionary gd ");
        sql.append(" where gsd.provider_code = ? and gsd.status = '1' ");
        sql.append(" and gsd.product_category = tc.category_code ");
        sql.append(" and gsd.service_code = gd.dic_code ");
        serviceDetailList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{providerCode}, new GoodsServiceDetailRowMapper());
        return serviceDetailList;
	}

	@Override
	public void saveGoodsServiceDetail(GoodsServiceDetail serviceDetail,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_service_detail(id");
        sql.append(",provider_code,product_category,service_code,price,service_content ");
        sql.append(",createdate,create_user,updatedate,update_user,status)");
		sql.append("values(null");
		sql.append(",?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(serviceDetail.getProviderCode());
		params.add(serviceDetail.getProductCategory());
		params.add(serviceDetail.getServiceCode());
		params.add(serviceDetail.getPrice());
		params.add(serviceDetail.getServiceContent());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateGoodsServiceDetail(GoodsServiceDetail serviceDetail,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_service_detail ");
        sql.append("set price = ?, service_content = ? ");
        sql.append(",updatedate = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(serviceDetail.getPrice());
		params.add(serviceDetail.getServiceContent());
		params.add(currentUser.getLoginName());
		params.add(serviceDetail.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsServiceDetail(int serviceId) throws Exception {
		String sql = "update tbl_goods_service_detail set status = '0' where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{serviceId});
	}

	@Override
	public GoodsServiceDetail getGoodsServiceDetailById(int serviceId)
			throws Exception {
		StringBuffer sql = new StringBuffer("select gsd.*,tc.category_name,gd.dic_name as service_name ");
        sql.append(" from tbl_goods_service_detail gsd,  tbl_category tc, tbl_goods_dictionary gd ");
        sql.append(" where gsd.id = ? and gsd.status = '1' ");
        sql.append(" and gsd.product_category = tc.category_code ");
        sql.append(" and gsd.service_code = gd.dic_code ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{serviceId}, new GoodsServiceDetailRowMapper());
	}

	@Override
	public List<GoodsSPCustomer> getGoodsSPCustomerByProviderCode(
			String providerCode) throws Exception {
		List<GoodsSPCustomer> serviceDetailList = new ArrayList<GoodsSPCustomer>();
        StringBuffer sql = new StringBuffer(600);
        sql.append("select gsc.id, gsc.sp_code, gsc.category_code, gsc.service_type");
        sql.append(" ,gsc.customer_code, gsc.customer_name, gd2.dic_name as status");
        sql.append(" ,gsc.create_user,gsc.createdate,gsc.update_user,gsc.updatedate ");
        sql.append(" ,tc.category_name,gd1.dic_name as service_type_name");
        sql.append(" from tbl_goods_sp_customer gsc ");
        sql.append(" left join tbl_goods_dictionary gd1 on gsc.service_type = gd1.dic_code and gd1.type_code='serviceTypes' ");
        sql.append(" left join tbl_goods_dictionary gd2 on gsc.status = gd2.dic_code and gd2.type_code='sp_customer_status' ");
        sql.append(" left join tbl_category tc on gsc.category_code = tc.category_code ");
        sql.append(" where gsc.sp_code = ? and gsc.status != '0' ");
        serviceDetailList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{providerCode}, new GoodsSPCustomerRowMapper());
        return serviceDetailList;
	}

	@Override
	public GoodsServiceProvider getGoodsServiceProviderByCode(String spCode)
			throws Exception {
		String sql = "select * from tbl_goods_service where code = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{spCode}, new GoodsServiceRowMapper());
	}

	@Override
	public GoodsSPCustomer getGoodsSPCustomerBySPCustomerId(
			int serviceCustomerId) throws Exception {
		StringBuffer sql = new StringBuffer(600);
        sql.append("select gsc.id, gsc.sp_code, gsc.category_code, gsc.service_type");
        sql.append(" ,gsc.customer_code, gsc.customer_name, gd2.dic_name as status");
        sql.append(" ,gsc.create_user,gsc.createdate,gsc.update_user,gsc.updatedate ");
        sql.append(" ,tc.category_name,gd1.dic_name as service_type_name");
        sql.append(" from tbl_goods_sp_customer gsc,  tbl_category tc, tbl_goods_dictionary gd1, tbl_goods_dictionary gd2 ");
        sql.append(" where gsc.id = ? and gsc.status != '0' ");
        sql.append(" and gsc.category_code = tc.category_code ");
        sql.append(" and gsc.service_type = gd1.dic_code and gd1.type_code='serviceTypes' ");
        sql.append(" and gsc.status = gd2.dic_code and gd2.type_code='sp_customer_status' ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{serviceCustomerId}, new GoodsSPCustomerRowMapper());
	}

	@Override
	public void saveSPCustomer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_sp_customer(id,sp_code,category_code,service_type ");
        sql.append(",customer_name,customer_code,status");
        sql.append(",createdate,create_user,updatedate,update_user)");
		sql.append("values(null,?,?,?");
		sql.append(",?,'',?");
		sql.append(",now(),?,now(),?)");

		List<Object> params = new ArrayList<Object>();
		params.add(spCustomer.getSpCode());
		params.add(spCustomer.getCategoryCode());
		params.add(spCustomer.getServiceTypeCode());
		params.add(spCustomer.getCustomerName());
		params.add(spCustomer.getStatus());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteSPCustomer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder(120);
        sql.append("update tbl_goods_sp_customer set status = 1,updatedate = now(),update_user = ? where id = ?");
		
		List<Object> params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(spCustomer.getId());
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void sendMessage2Customer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("");
        sql.append(" insert into tbl_goods_sp_request(id,sp_customer_id,sp_code,service_type,customer_name,status");
        sql.append(",createdate,create_user,updatedate,update_user)");
		sql.append("values(null,?,?,?,?,'1'");
		sql.append(",now(),?,now(),?)");

		List<Object> params = new ArrayList<Object>();
		params.add(spCustomer.getId());
		params.add(spCustomer.getSpCode());
		params.add(spCustomer.getServiceTypeCode());
		params.add(spCustomer.getCustomerName());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateSPCustomerStatus(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		String sql = "update tbl_goods_sp_customer set status = '2' where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{spCustomer.getId()});
	}

	@Override
	public void updateCustomer2SP(int messageId, WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_ordinary_house oh, tbl_goods_sp_request sr, tbl_goods_sp_customer gsc ");
        sql.append("set gsc.customer_code = oh.house_code, gsc.updatedate = now(), gsc.update_user = ?, gsc.status='3' ");
		sql.append("where sr.id = ? and sr.sp_customer_id = gsc.id and sr.customer_name = oh.building_name");
		
		List<Object> params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(messageId);
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void rejectCustomer2SP(int messageId, WebUserInfo currentUser)
			throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_sp_request sr, tbl_goods_sp_customer gsc ");
        sql.append(" set gsc.customer_code = '', gsc.status = '4', gsc.updatedate = now(), gsc.update_user = ? ");
		sql.append(" where sr.id = ? and gsc.customer_name = sr.customer_name ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(messageId);
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

}
