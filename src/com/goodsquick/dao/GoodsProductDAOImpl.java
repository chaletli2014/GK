package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsProductRowMapper;
import com.goodsquick.mapper.GoodsServiceRowMapper;
import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.WebUserInfo;

@Repository("goodsProductDAO")
public class GoodsProductDAOImpl extends BaseDAOImpl implements GoodsProductDAO{

	@Override
	public List<GoodsProduct> getGoodsProductByRepositoryCode(
			String repositoryCode) throws Exception {
		List<GoodsProduct> dtList = new ArrayList<GoodsProduct>();
        String sql = "select * from tbl_goods_product where repository_code = ? and status = '1' ";
        dtList = dataBean.getJdbcTemplate().query(sql, new Object[]{repositoryCode}, new GoodsProductRowMapper());
        return dtList;
	}

	@Override
	public GoodsProduct getGoodsProductById(int productId) throws Exception {
		String sql = "select * from tbl_goods_product where id = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{productId}, new GoodsProductRowMapper());
	}

	@Override
	public void saveGoodsProduct(GoodsProduct goodsProduct,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_product(id");
        sql.append(",product_type,product_name,product_price,repository_code,remark ");
        sql.append(",create_date,create_user,update_date,update_user,status)");
		sql.append("values(null");
		sql.append(",?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getProductType());
		params.add(goodsProduct.getProductName());
		params.add(goodsProduct.getProductPrice());
		params.add(goodsProduct.getRepositoryCode());
		params.add(goodsProduct.getRemark());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void updateGoodsProduct(GoodsProduct goodsProduct,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_product ");
        sql.append("set product_name = ?, product_price = ?, remark = ? ");
        sql.append(",update_date = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getProductName());
		params.add(goodsProduct.getProductPrice());
		params.add(goodsProduct.getRemark());
		params.add(currentUser.getLoginName());
		params.add(goodsProduct.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsProduct(GoodsProduct goodsProduct) throws Exception {
		String sql = "update tbl_goods_product set status = '0',update_user=? where id = ? ";
		dataBean.getJdbcTemplate().update(sql,new Object[]{goodsProduct.getUpdateUser(),goodsProduct.getId()});
	}

}
