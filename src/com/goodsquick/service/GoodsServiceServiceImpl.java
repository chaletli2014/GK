package com.goodsquick.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsServiceDAO;
import com.goodsquick.model.GoodsRelatedRequest;
import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.GoodsDateUtil;

@Service("goodsServiceService")
public class GoodsServiceServiceImpl implements GoodsServiceService {

	@Autowired
	@Qualifier("goodsServiceDAO")
	private GoodsServiceDAO goodsServiceDAO;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public List<GoodsServiceProvider> getGoodsServiceProviderByUserCode(String userCode)
			throws Exception {
		try{
			return goodsServiceDAO.getGoodsServiceProviderByUserCode(userCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the goods service,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public void saveOrUpdateGoodsServiceProvider(GoodsServiceProvider goodsService,
			WebUserInfo currentUser) throws Exception {
		int serviceId = goodsService.getId();
		
		if( 0 == serviceId ){
			goodsService.setCreateUser(currentUser.getLoginName());
			goodsService.setUpdateUser(currentUser.getLoginName());
			String maxCode = goodsServiceDAO.getMaxCode();
			
			if( null == maxCode || "".equalsIgnoreCase(maxCode) ){
				maxCode = new StringBuilder(30).append("service").append(GoodsDateUtil.getStringFormat(new Date())).append("1").toString();
			}else{
				int maxNum = Integer.valueOf(maxCode.substring(15)) + 1;
				maxCode = new StringBuilder(30).append("service").append(GoodsDateUtil.getStringFormat(new Date())).append(maxNum).toString();
			}
			
			goodsService.setCode(maxCode);
			
			goodsServiceDAO.saveGoodsServiceProvider(goodsService, currentUser);
		}else{
			goodsService.setUpdateUser(currentUser.getLoginName());
			goodsService.setUpdateDate(new Date());
			
			goodsServiceDAO.updateGoodsServiceProvider(goodsService, currentUser);
		}

	}

	@Override
	public void deleteGoodsServiceProvider(int serviceId) throws Exception {
		goodsServiceDAO.deleteGoodsServiceProvider(serviceId);
	}

	@Override
	public GoodsServiceProvider getGoodsServiceProviderById(int serviceId) throws Exception {
		return goodsServiceDAO.getGoodsServiceProviderById(serviceId);
	}

	@Override
	public List<GoodsServiceDetail> getGoodsServiceDetailsByProviderCode(
			String providerCode) throws Exception {
		try{
			return goodsServiceDAO.getGoodsServiceDetailsByProviderCode(providerCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the goods service,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsServiceDetail> getGoodsServiceDetailsByUserCode(
			String userCode) throws Exception {
		try{
			return goodsServiceDAO.getGoodsServiceDetailsByUserCode(userCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the goods service,",e);
			return Collections.emptyList();
		}
	}

	@Override
	public void saveOrUpdateGoodsServiceDetail(
			GoodsServiceDetail serviceDetail, WebUserInfo currentUser)
			throws Exception {
		int serviceId = serviceDetail.getId();
		
		if( 0 == serviceId ){
			goodsServiceDAO.saveGoodsServiceDetail(serviceDetail, currentUser);
		}else{
			goodsServiceDAO.updateGoodsServiceDetail(serviceDetail, currentUser);
		}
	}

	@Override
	public void deleteGoodsServiceDetail(int serviceDetailId) throws Exception {
		goodsServiceDAO.deleteGoodsServiceDetail(serviceDetailId);
	}

	@Override
	public GoodsServiceDetail getGoodsServiceDetailById(int serviceDetailId)
			throws Exception {
		return goodsServiceDAO.getGoodsServiceDetailById(serviceDetailId);
	}

	@Override
	public List<GoodsSPCustomer> getGoodsSPCustomerByProviderCode(
			String providerCode) throws Exception {
		try{
			return goodsServiceDAO.getGoodsSPCustomerByProviderCode(providerCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the service customer,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsServiceProvider getGoodsServiceProviderByCode(String spCode)
			throws Exception {
		return goodsServiceDAO.getGoodsServiceProviderByCode(spCode);
	}

	@Override
	public GoodsSPCustomer getGoodsSPCustomerBySPCustomerId(
			int serviceCustomerId) throws Exception {
		return goodsServiceDAO.getGoodsSPCustomerBySPCustomerId(serviceCustomerId);
	}

	@Override
	public void saveSPCustomer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		goodsServiceDAO.saveSPCustomer(spCustomer, currentUser);
	}
	
	@Override
	public void deleteSPCustomer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		goodsServiceDAO.deleteSPCustomer(spCustomer, currentUser);
	}

	@Override
	public void sendMessage2Customer(GoodsSPCustomer spCustomer,
			WebUserInfo currentUser) throws Exception {
		goodsServiceDAO.sendMessage2Customer(spCustomer, currentUser);
		goodsServiceDAO.updateSPCustomerStatus(spCustomer, currentUser);
	}

	@Override
	public void updateCustomer2SP(int messageId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
