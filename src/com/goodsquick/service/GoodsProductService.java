package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.WebUserInfo;

public interface GoodsProductService {
	public List<GoodsProduct> getGoodsProductByRepositoryCode(String repositoryCode) throws Exception;
	public GoodsProduct getGoodsProductById(int productId) throws Exception;
	public void saveOrUpdateGoodsProduct(GoodsProduct goodsProduct, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsProduct(GoodsProduct goodsProduct) throws Exception;
}
