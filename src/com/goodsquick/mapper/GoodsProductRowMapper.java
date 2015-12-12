package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.goodsquick.model.GoodsProduct;

public class GoodsProductRowMapper implements RowMapper<GoodsProduct>{
    @Override
    public GoodsProduct mapRow(ResultSet rs, int i) throws SQLException {
    	GoodsProduct dbProduct = new GoodsProduct();
    	dbProduct.setId(rs.getInt("id"));
    	dbProduct.setProductType(rs.getString("product_type"));
    	dbProduct.setProductName(rs.getString("product_name"));
    	dbProduct.setProductPrice(rs.getDouble("product_price"));
    	dbProduct.setRepositoryCode(rs.getString("repository_code"));
    	dbProduct.setCreateDate(rs.getDate("create_date"));
    	dbProduct.setUpdateDate(rs.getDate("update_date"));
    	dbProduct.setCreateUser(rs.getString("create_user"));
    	dbProduct.setUpdateUser(rs.getString("update_user"));
    	dbProduct.setRemark(rs.getString("remark"));
    	dbProduct.setStatus(rs.getString("status"));
    	
        return dbProduct;
    }
    
}