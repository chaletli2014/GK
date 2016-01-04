package com.goodsquick.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsProductLiftDAO;
import com.goodsquick.model.GoodsProductLift;
import com.goodsquick.model.WebUserInfo;

@Service("goodsProductLiftLiftService")
public class GoodsProductLiftServiceImpl implements GoodsProductLiftService {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("goodsProductLiftDAO")
	private GoodsProductLiftDAO goodsProductLiftDAO;
	
	@Override
	public List<GoodsProductLift> getGoodsProductLiftByRepositoryCode(
			String repositoryCode) throws Exception {
		try{
			return goodsProductLiftDAO.getGoodsProductLiftByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the goods product,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsProductLift getGoodsProductLiftById(int productId) throws Exception {
		return goodsProductLiftDAO.getGoodsProductLiftById(productId);
	}

	@Override
	public void saveOrUpdateGoodsProductLift(GoodsProductLift goodsProductLift,
			WebUserInfo currentUser) throws Exception {
		int productId = goodsProductLift.getId();
		
		if( 0 == productId ){
			goodsProductLift.setCreateUser(currentUser.getLoginName());
			goodsProductLift.setUpdateUser(currentUser.getLoginName());
			goodsProductLiftDAO.saveGoodsProductLift(goodsProductLift, currentUser);
		}else{
			goodsProductLift.setUpdateUser(currentUser.getLoginName());
			goodsProductLift.setUpdateDate(new Date());
			
			goodsProductLiftDAO.updateGoodsProductLift(goodsProductLift, currentUser);
		}
	}

	@Override
	public void deleteGoodsProductLift(GoodsProductLift goodsProductLift) throws Exception {
		goodsProductLiftDAO.deleteGoodsProductLift(goodsProductLift);
	}

}
