package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.WebUserInfo;

public interface GoodsSourceFileDAO {

	public void saveGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception;
	public void updateGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception;
	public void deleteGoodsHouseFile(int houseFileId) throws Exception;
	public GoodsHouseFile getGoodsHouseFileById(int houseFileId) throws Exception;
	
	public List<GoodsHouseFile> getGoodsHouseFileByRepositoryCode(String repositoryCode) throws Exception;
}
