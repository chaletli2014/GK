package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsDataDAO;
import com.goodsquick.model.GoodsChartObj1;

@Service("goodsDataService")
public class GoodsDataServiceImpl implements GoodsDataService {

    Logger logger = Logger.getLogger(GoodsDataServiceImpl.class);

    @Autowired
    @Qualifier("goodsDataDAO")
    private GoodsDataDAO goodsDataDAO;
    
	@Override
	public List<GoodsChartObj1> getAssetYearData(String userCode) {
		try{
			return goodsDataDAO.getAssetYearData(userCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the asset year list by userCode,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsChartObj1> getDensityData(String userCode,
			String densityType) {
		try{
			return goodsDataDAO.getDensityData(userCode, densityType);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the asset year list by userCode,",e);
            return Collections.emptyList();
        }
	}

}
