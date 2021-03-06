package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsProductObjRowMapper;
import com.goodsquick.mapper.GoodsProductRowMapper;
import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.GoodsProductObj;
import com.goodsquick.model.WebUserInfo;

@Repository("goodsProductDAO")
public class GoodsProductDAOImpl extends BaseDAOImpl implements GoodsProductDAO{

	@Override
	public List<GoodsProduct> getGoodsProductByRepositoryCode(
			String repositoryCode) throws Exception {
		List<GoodsProduct> dtList = new ArrayList<GoodsProduct>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,td.dic_name as product_type_desc,nd.dic_name as product_name_desc ");
		sql.append(" from tbl_goods_product gp ");
		sql.append(" left join tbl_goods_dictionary td on gp.product_type = td.dic_code ");
		sql.append(" left join tbl_goods_dictionary nd on gp.product_name = nd.dic_code ");
		sql.append(" where gp.repository_code = ? and gp.status = '1' ");
		
        dtList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsProductRowMapper());
        return dtList;
	}

	@Override
	public GoodsProduct getGoodsProductById(int productId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,td.dic_name as product_type_desc,nd.dic_name as product_name_desc ");
		sql.append(" from tbl_goods_product gp ");
		sql.append(" left join tbl_goods_dictionary td on gp.product_type = td.dic_code ");
		sql.append(" left join tbl_goods_dictionary nd on gp.product_name = nd.dic_code ");
		sql.append(" where gp.id = ? ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{productId}, new GoodsProductRowMapper());
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
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getUpdateUser());
		params.add(goodsProduct.getId());
		super.deleteObj("tbl_goods_product", params);
	}

	@Override
	public List<GoodsProductObj> getProductObjByRepositoryCode(
			String repositoryCode) throws Exception {
		List<GoodsProductObj> dtList = new ArrayList<GoodsProductObj>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,coalesce(td.dic_name,gp.product_type) as product_type_desc ");
		sql.append(" from tbl_goods_product_obj gp ");
		sql.append(" left join tbl_goods_dictionary td on gp.product_type = td.dic_code ");
		sql.append(" where gp.repository_code = ? and gp.status = '1'");
		
		dtList = queryByCondition(sql.toString(), new Object[]{repositoryCode}, new GoodsProductObjRowMapper());
        return dtList;
	}

	@Override
	public GoodsProductObj getProductObjById(int productId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select gp.*,coalesce(td.dic_name,gp.product_type) as product_type_desc");
		sql.append(" from tbl_goods_product_obj gp ");
		sql.append(" left join tbl_goods_dictionary td on gp.product_type = td.dic_code ");
		sql.append(" where gp.id = ?");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{productId}, new GoodsProductObjRowMapper());
	}

	@Override
	public void saveProductObj(GoodsProductObj goodsProduct,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_product_obj(id");
        sql.append(",product_type,product_name,product_brand,product_model,item_code,product_dom,product_QA,product_price");
        sql.append(",repository_code,remark,create_date,create_user,update_date,update_user,status)");
		sql.append("values(null");
		sql.append(",?,?,?,?,?,?,?,?");
		sql.append(",?,?,now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getProductType());
		params.add(goodsProduct.getProductName());
		params.add(goodsProduct.getProductBrand());
		params.add(goodsProduct.getProductModel());
		params.add(goodsProduct.getItemCode());
		params.add(goodsProduct.getProductDom());
		params.add(goodsProduct.getProductQA());
		params.add(goodsProduct.getProductPrice());
		params.add(goodsProduct.getRepositoryCode());
		params.add(goodsProduct.getRemark());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateProductObj(GoodsProductObj goodsProduct,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_product_obj ");
        sql.append("set product_name = ?, product_brand = ?, product_model = ?, item_code = ?, product_dom = ?, product_QA = ?, product_price = ? ");
        sql.append(",update_date = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getProductName());
		params.add(goodsProduct.getProductBrand());
		params.add(goodsProduct.getProductModel());
		params.add(goodsProduct.getItemCode());
		params.add(goodsProduct.getProductDom());
		params.add(goodsProduct.getProductQA());
		params.add(goodsProduct.getProductPrice());
		params.add(currentUser.getLoginName());
		params.add(goodsProduct.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteProductObj(GoodsProductObj goodsProduct) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(goodsProduct.getUpdateUser());
		params.add(goodsProduct.getId());
		super.deleteObj("tbl_goods_product_obj", params);
	}

}
