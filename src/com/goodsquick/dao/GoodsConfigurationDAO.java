package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.WebUserInfo;

public interface GoodsConfigurationDAO {
	
	public List<GoodsConfiguration> getAllConfiguration() throws Exception;
	
	public boolean checkIfConfigExists(String configCode) throws Exception;
	
	public void saveConfiguration(GoodsConfiguration config, WebUserInfo userInfo) throws Exception;
	
	public void updateConfiguration(GoodsConfiguration config, WebUserInfo userInfo) throws Exception;
	
	public void deleteConfiguration(GoodsConfiguration config) throws Exception;
	
	public GoodsConfiguration getConfigByCode( String configCode ) throws Exception;
	
}
