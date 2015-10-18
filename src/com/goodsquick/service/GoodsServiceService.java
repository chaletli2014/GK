package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;

public interface GoodsServiceService {

	public List<GoodsServiceProvider> getGoodsServiceProviderByUserCode(String userCode) throws Exception;
	public void saveOrUpdateGoodsServiceProvider(GoodsServiceProvider goodsService, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsServiceProvider(int serviceId) throws Exception;
	public GoodsServiceProvider getGoodsServiceProviderById(int serviceId) throws Exception;
	public GoodsServiceProvider getGoodsServiceProviderByCode(String spCode) throws Exception;
	
	/**
	 * 根据服务商编码获取该服务商的所有服务列表
	 * @param providerCode 服务商编码
	 * @return 服务列表
	 * @throws Exception
	 */
	public List<GoodsServiceDetail> getGoodsServiceDetailsByProviderCode(String providerCode) throws Exception;
	public List<GoodsServiceDetail> getGoodsServiceDetailsByUserCode(String userCode) throws Exception;
	public void saveOrUpdateGoodsServiceDetail(GoodsServiceDetail serviceDetail, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsServiceDetail(int serviceDetailId) throws Exception;
	public GoodsServiceDetail getGoodsServiceDetailById(int serviceDetailId) throws Exception;
	
	public List<GoodsSPCustomer> getGoodsSPCustomerByProviderCode(String providerCode) throws Exception;
	public GoodsSPCustomer getGoodsSPCustomerBySPCustomerId(int serviceCustomerId) throws Exception;
	public void saveSPCustomer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	public void deleteSPCustomer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	public void sendMessage2Customer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	
	public void updateCustomer2SP(int messageId) throws Exception;
	
}
