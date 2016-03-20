package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.GoodsHouseDeviceDAO;
import com.goodsquick.dao.LiftDAO;
import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.model.GoodsHouseDevice;

@Service("goodsHouseDeviceService")
public class GoodsHouseDeviceServiceImpl implements GoodsHouseDeviceService {

    Logger logger = Logger.getLogger(GoodsHouseDeviceServiceImpl.class);
    
	@Autowired
	@Qualifier("goodsHouseDeviceDAO")
	private GoodsHouseDeviceDAO goodsHouseDeviceDAO;
	
	@Autowired
	@Qualifier("liftDAO")
	private LiftDAO liftDAO;
	
	@Override
	public List<GoodsHouseDevice> getAllDevice(String repositoryCode)
			throws Exception {
		List<GoodsHouseDevice> devices = new ArrayList<GoodsHouseDevice>();
		try{
			devices.addAll(goodsHouseDeviceDAO.getAllHouseDeviceByRepositoryCode(repositoryCode));
			List<GoodsDeviceLift> lifts = liftDAO.getDeviceLiftByRepositoryCode(repositoryCode);
			for( GoodsDeviceLift dbLift : lifts ){
				GoodsHouseDevice liftDevice = new GoodsHouseDevice();
				liftDevice.setEqTypeCode("dt");
				liftDevice.setEqTypeName("电梯");
				liftDevice.setName(dbLift.getLiftName());
				liftDevice.setEqDesc(dbLift.getLiftDesc());
				liftDevice.setSubjectId(dbLift.getSubjectId());
				liftDevice.setSubjectName(dbLift.getSubjectName());
				liftDevice.setModuleId(dbLift.getModuleId());
				liftDevice.setModuleName(dbLift.getModuleName());
				liftDevice.setId(dbLift.getId());
				
				devices.add(liftDevice);
			}
			return devices;
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
				
				int id = houseDevice.getId();
				if( id == 0 ){
					if( "dt".equalsIgnoreCase(houseDevice.getEqTypeCode()) ){
						GoodsDeviceLift lift = new GoodsDeviceLift();
						lift.setLiftName(houseDevice.getName());
						lift.setLiftDesc(houseDevice.getEqDesc());
						lift.setSubjectId(houseDevice.getSubjectId());
						lift.setModuleId(houseDevice.getModuleId());
						lift.setRepositoryCode(repositoryCode);
						lift.setCreateUser(currentUser);
						lift.setUpdateUser(currentUser);
						liftDAO.saveDeviceLiftQuick(lift);
					}
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
		if( "dt".equalsIgnoreCase(obj.getEqTypeCode()) ){
			GoodsDeviceLift liftObj = new GoodsDeviceLift();
			liftObj.setId(obj.getId());
			liftObj.setUpdateUser(obj.getUpdateUser());
			liftDAO.deleteDeviceLift(liftObj);
		}
	}

}
