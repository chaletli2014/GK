package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.GoodsHouseOtherDAO;
import com.goodsquick.model.GoodsHouseOther;

@Service("goodsHouseOtherService")
public class GoodsHouseOtherServiceImpl implements GoodsHouseOtherService {

    Logger logger = Logger.getLogger(GoodsHouseOtherServiceImpl.class);
    
	@Autowired
	@Qualifier("goodsHouseOtherDAO")
	private GoodsHouseOtherDAO goodsHouseOtherDAO;
	
	@Override
	public List<GoodsHouseOther> getAllOther(String repositoryCode)
			throws Exception {
		try{
			return goodsHouseOtherDAO.getAllHouseOtherByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the device by repositoryCode,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsHouseOther getOtherInfoById(int deviceId) throws Exception {
		return goodsHouseOtherDAO.getOtherInfoById(deviceId);
	}

	@Override
	@Transactional
	public void saveOrUpdateHouseOther(List<GoodsHouseOther> houseOthers, String currentUser, String repositoryCode)
			throws Exception {
		try{
			for( GoodsHouseOther houseOther : houseOthers ){
				
				int subjectId = houseOther.getId();
				if( subjectId == 0 ){
					houseOther.setCreateUser(currentUser);
					houseOther.setUpdateUser(currentUser);
					goodsHouseOtherDAO.saveHouseOther(houseOther);
				}else{
					houseOther.setUpdateUser(currentUser);
					goodsHouseOtherDAO.updateHouseOther(houseOther);
				}
			}
		}catch(Exception e){
			logger.error("fail to save Or update device,",e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteHouseOther(GoodsHouseOther obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
