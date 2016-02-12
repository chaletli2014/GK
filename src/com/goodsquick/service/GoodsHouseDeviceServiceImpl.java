package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.GoodsHouseDeviceDAO;
import com.goodsquick.model.GoodsHouseDevice;

@Service("goodsHouseDeviceService")
public class GoodsHouseDeviceServiceImpl implements GoodsHouseDeviceService {

    Logger logger = Logger.getLogger(GoodsHouseDeviceServiceImpl.class);
    
	@Autowired
	@Qualifier("goodsHouseDeviceDAO")
	private GoodsHouseDeviceDAO goodsHouseDeviceDAO;
	
	@Override
	public List<GoodsHouseDevice> getAllDevice(String repositoryCode)
			throws Exception {
		try{
			return goodsHouseDeviceDAO.getAllHouseDeviceByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the device by repositoryCode,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsHouseDevice getDeviceInfoById(int deviceId) throws Exception {
		return goodsHouseDeviceDAO.getDeviceInfoById(deviceId);
	}

	@Override
	@Transactional
	public void saveOrUpdateHouseDevice(List<GoodsHouseDevice> houseDevices, String currentUser, String repositoryCode)
			throws Exception {
		try{
			for( GoodsHouseDevice houseDevice : houseDevices ){
				
				int subjectId = houseDevice.getId();
				if( subjectId == 0 ){
					houseDevice.setCreateUser(currentUser);
					houseDevice.setUpdateUser(currentUser);
					goodsHouseDeviceDAO.saveHouseDevice(houseDevice);
				}else{
					houseDevice.setUpdateUser(currentUser);
					goodsHouseDeviceDAO.updateHouseDevice(houseDevice);
				}
			}
		}catch(Exception e){
			logger.error("fail to save Or update device,",e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteHouseDevice(GoodsHouseDevice obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
