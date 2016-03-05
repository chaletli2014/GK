package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsSPCustomer;
import com.goodsquick.model.GoodsServiceDetail;
import com.goodsquick.model.GoodsServiceProvider;
import com.goodsquick.model.WebUserInfo;

public interface GoodsSourceFileService {

	public void saveOrUpdateGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsHouseFile(int houseFileId) throws Exception;
	public GoodsHouseFile getGoodsHouseFileById(int houseFileId) throws Exception;
	public List<GoodsHouseFile> getGoodsHouseFileByRepositoryCode(String repositoryCode) throws Exception;
}
