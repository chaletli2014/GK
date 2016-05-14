package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.GoodsProductObj;
import com.goodsquick.model.WebUserInfo;

public interface GoodsProductDAO {
	public List<GoodsProduct> getGoodsProductByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsProduct getGoodsProductById(int productId) throws Exception;
	public void saveGoodsProduct(GoodsProduct goodsProduct, WebUserInfo currentUser) throws Exception;
	public void updateGoodsProduct(GoodsProduct goodsProduct, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsProduct(GoodsProduct goodsProduct) throws Exception;
	
	public List<GoodsProductObj> getProductObjByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsProductObj getProductObjById(int productId) throws Exception;
	public void saveProductObj(GoodsProductObj goodsProduct, WebUserInfo currentUser) throws Exception;
	public void updateProductObj(GoodsProductObj goodsProduct, WebUserInfo currentUser) throws Exception;
	public void deleteProductObj(GoodsProductObj goodsProduct) throws Exception;
}
