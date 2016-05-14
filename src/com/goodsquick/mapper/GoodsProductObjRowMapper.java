package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsProductObj;

/**
 * 实物类产品Map
 * @author chalet
 *
 */
public class GoodsProductObjRowMapper implements RowMapper<GoodsProductObj>{
    @Override
    public GoodsProductObj mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsProductObj dbProduct = new GoodsProductObj();
    	dbProduct.setId(rs.getInt("id"));
    	dbProduct.setProductType(rs.getString("product_type"));
    	dbProduct.setProductTypeDesc(rs.getString("product_type_desc"));
    	dbProduct.setProductName(rs.getString("product_name"));
    	dbProduct.setProductPrice(rs.getDouble("product_price"));
    	dbProduct.setRepositoryCode(rs.getString("repository_code"));
    	dbProduct.setCreateDate(rs.getDate("create_date"));
    	dbProduct.setUpdateDate(rs.getDate("update_date"));
    	dbProduct.setCreateUser(rs.getString("create_user"));
    	dbProduct.setUpdateUser(rs.getString("update_user"));
    	dbProduct.setRemark(rs.getString("remark"));
    	dbProduct.setStatus(rs.getString("status"));
    	
    	dbProduct.setProductBrand(rs.getString("product_brand"));
    	dbProduct.setProductModel(rs.getString("product_model"));
    	dbProduct.setItemCode(rs.getString("item_code"));
    	dbProduct.setProductDom(rs.getDate("product_dom"));
    	dbProduct.setProductQA(rs.getInt("product_QA"));
    	
        return dbProduct;
    }
    
}