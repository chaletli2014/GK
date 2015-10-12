package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;

public interface GoodsServiceDAO {

	public List<GoodsServiceProvider> getGoodsServiceProviderByUserCode(String userCode) throws Exception;
	public void saveGoodsServiceProvider(GoodsServiceProvider goodsService, WebUserInfo currentUser) throws Exception;
	public void updateGoodsServiceProvider(GoodsServiceProvider goodsService, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsServiceProvider(int serviceId) throws Exception;
	public GoodsServiceProvider getGoodsServiceProviderById(int serviceId) throws Exception;
	public GoodsServiceProvider getGoodsServiceProviderByCode(String spCode) throws Exception;
	
	public String getMaxCode() throws Exception;
	
	public List<GoodsServiceDetail> getGoodsServiceDetailsByProviderCode(String providerCode) throws Exception;
	public void saveGoodsServiceDetail(GoodsServiceDetail serviceDetail, WebUserInfo currentUser) throws Exception;
	public void updateGoodsServiceDetail(GoodsServiceDetail serviceDetail, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsServiceDetail(int serviceId) throws Exception;
	public GoodsServiceDetail getGoodsServiceDetailById(int serviceId) throws Exception;
	
	public List<GoodsSPCustomer> getGoodsSPCustomerByProviderCode(String providerCode) throws Exception;
	public GoodsSPCustomer getGoodsSPCustomerBySPCustomerId(int serviceCustomerId) throws Exception;
	public void saveSPCustomer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	public void deleteSPCustomer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	public void sendMessage2Customer(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	public void updateSPCustomerStatus(GoodsSPCustomer spCustomer, WebUserInfo currentUser) throws Exception;
	
	public void updateCustomer2SP(int messageId, WebUserInfo currentUser) throws Exception;
	public void rejectCustomer2SP(int messageId, WebUserInfo currentUser) throws Exception;
}
