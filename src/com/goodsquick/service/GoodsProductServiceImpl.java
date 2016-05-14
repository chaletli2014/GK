package com.goodsquick.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsProductDAO;
import com.goodsquick.model.GoodsProduct;
import com.goodsquick.model.GoodsProductObj;
import com.goodsquick.model.WebUserInfo;

@Service("goodsProductService")
public class GoodsProductServiceImpl implements GoodsProductService {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("goodsProductDAO")
	private GoodsProductDAO goodsProductDAO;
	
	@Override
	public List<GoodsProduct> getGoodsProductByRepositoryCode(
			String repositoryCode) throws Exception {
		try{
			return goodsProductDAO.getGoodsProductByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the goods product,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsProduct getGoodsProductById(int productId) throws Exception {
		return goodsProductDAO.getGoodsProductById(productId);
	}

	@Override
	public void saveOrUpdateGoodsProduct(GoodsProduct goodsProduct,
			WebUserInfo currentUser) throws Exception {
		int productId = goodsProduct.getId();
		
		if( 0 == productId ){
			goodsProduct.setCreateUser(currentUser.getLoginName());
			goodsProduct.setUpdateUser(currentUser.getLoginName());
			goodsProductDAO.saveGoodsProduct(goodsProduct, currentUser);
		}else{
			goodsProduct.setUpdateUser(currentUser.getLoginName());
			goodsProduct.setUpdateDate(new Date());
			
			goodsProductDAO.updateGoodsProduct(goodsProduct, currentUser);
		}
	}

	@Override
	public void deleteGoodsProduct(GoodsProduct goodsProduct) throws Exception {
		goodsProductDAO.deleteGoodsProduct(goodsProduct);
	}

	@Override
	public List<GoodsProductObj> getProductObjByRepositoryCode(
			String repositoryCode) throws Exception {
		try{
			return goodsProductDAO.getProductObjByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the goods product obj,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsProductObj getProductObjById(int productId) throws Exception {
		return goodsProductDAO.getProductObjById(productId);
	}

	@Override
	public void saveOrUpdateProductObj(GoodsProductObj goodsProduct,
			WebUserInfo currentUser) throws Exception {
		int productId = goodsProduct.getId();
		
		if( 0 == productId ){
			goodsProduct.setCreateUser(currentUser.getLoginName());
			goodsProduct.setUpdateUser(currentUser.getLoginName());
			goodsProductDAO.saveProductObj(goodsProduct, currentUser);
		}else{
			goodsProduct.setUpdateUser(currentUser.getLoginName());
			goodsProduct.setUpdateDate(new Date());
			
			goodsProductDAO.updateProductObj(goodsProduct, currentUser);
		}
	}

	@Override
	public void deleteProductObj(GoodsProductObj goodsProduct) throws Exception {
		goodsProductDAO.deleteProductObj(goodsProduct);
	}

}
