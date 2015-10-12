package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsConfigurationDAO;
import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.WebUserInfo;

@Service("goodsConfigurationService")
public class GoodsConfigurationServiceImpl implements GoodsConfigurationService {

    Logger logger = Logger.getLogger(GoodsConfigurationServiceImpl.class);
    
	@Autowired
	@Qualifier("goodsConfigurationDAO")
	private GoodsConfigurationDAO goodsConfigurationDAO;
	
	@Override
	public List<GoodsConfiguration> getAllConfiguration() throws Exception {
		try{
			return goodsConfigurationDAO.getAllConfiguration();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the config,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public boolean checkIfConfigExists(String configCode) throws Exception {
		return goodsConfigurationDAO.checkIfConfigExists(configCode);
	}

	@Override
	public void saveConfiguration(GoodsConfiguration config, WebUserInfo userInfo)
			throws Exception {
		goodsConfigurationDAO.saveConfiguration(config, userInfo);
	}

	@Override
	public void updateConfiguration(GoodsConfiguration config, WebUserInfo userInfo)
			throws Exception {
		goodsConfigurationDAO.updateConfiguration(config, userInfo);
	}

	@Override
	public void deleteConfiguration(GoodsConfiguration config) throws Exception {
		goodsConfigurationDAO.deleteConfiguration(config);
	}

	@Override
	public GoodsConfiguration getConfigByCode(String configCode)
			throws Exception {
		return goodsConfigurationDAO.getConfigByCode(configCode);
	}

}
