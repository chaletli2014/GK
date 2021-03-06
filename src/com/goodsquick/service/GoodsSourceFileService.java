package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsProductSource;
import com.goodsquick.model.WebUserInfo;

public interface GoodsSourceFileService {

	public void saveOrUpdateGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsHouseFile(GoodsHouseFile houseFile) throws Exception;
	public GoodsHouseFile getGoodsHouseFileById(int houseFileId) throws Exception;
	public List<GoodsHouseFile> getGoodsHouseFileByRepositoryCode(String repositoryCode) throws Exception;
	public void setMainPicOfGoodsHouse( int houseFileId, String repositoryCode, WebUserInfo currentUser ) throws Exception;
	
	public void saveOrUpdateGoodsProductSource(GoodsProductSource productSource, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsProductSource(GoodsProductSource productSource) throws Exception;
	public GoodsProductSource getGoodsProductSourceById(int productSourceId) throws Exception;
	public List<GoodsProductSource> getGoodsProductSourceByRCAndProductId(String repositoryCode, long productId) throws Exception;
	public void setMainPicOfGoodsProduct( int productSourceId, long productId, String repositoryCode, WebUserInfo currentUser ) throws Exception;
}
