package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsRadarDAO;
import com.goodsquick.dao.LiftDAO;
import com.goodsquick.model.GoodsCompanyInfo;

@Service("goodsRadarService")
public class GoodsRadarServiceImpl implements GoodsRadarService {

	private Logger logger = Logger.getLogger(GoodsRadarServiceImpl.class);
	
	@Autowired
	@Qualifier("goodsRadarDAO")
	private GoodsRadarDAO goodsRadarDAO;
	
	@Autowired
	@Qualifier("liftDAO")
	private LiftDAO liftDAO;
	
	@Override
	public List<GoodsCompanyInfo> getCompanyInfoByRepositoryCode(String repositoryCode) throws Exception {
		try{
			List<String> liftBrands = liftDAO.getLiftBrandsByRepositoryCode(repositoryCode);
			return goodsRadarDAO.getCompanyInfoByLiftBrands(liftBrands);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the company name by lift brands,",e);
			return Collections.emptyList();
		}
	}

}
