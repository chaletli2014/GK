package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.OrdinaryHouseDAO;
import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRelationshipProperty;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.GoodsDateUtil;
import com.goodsquick.utils.GoodsQuickSearcherUtils;

@Service("ordinaryHouseService")
public class OrdinaryHouseServiceImpl implements OrdinaryHouseService {

	@Autowired
	@Qualifier("ordinaryHouseDAO")
	private OrdinaryHouseDAO ordinaryHouseDAO;
	
	@Autowired
	@Qualifier("relationshipPropertyService")
	private RelationshipPropertyService relationshipPropertyService;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public List<GoodsOrdinaryHouse> getOrdinaryHouseByUserCode(String userCode)
			throws Exception {
		try{
			return ordinaryHouseDAO.getOrdinaryHouseByUserCode(userCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the ordinary house by user code,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public GoodsOrdinaryHouse getOrdinaryHouseByRepositoryCode(String repositoryCode)
			throws Exception {
		try{
			return ordinaryHouseDAO.getOrdinaryHouseByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
			return null;
		} catch(Exception e){
			logger.error("fail to get the ordinary house by repository code,",e);
			return null;
		}
	}

	@Override
	@Transactional
	public void saveOrUpdateOrdinaryHouse(GoodsRelationshipProperty relationshipProperty, GoodsOrdinaryHouse ordinaryHouse, WebUserInfo userInfo)
			throws Exception {
		int orHouseId = ordinaryHouse.getId();
		
		if( 0 == orHouseId ){
			ordinaryHouse.setCreateUser(userInfo.getLoginName());
			ordinaryHouse.setUpdateUser(userInfo.getLoginName());
			String maxHouseCode = ordinaryHouseDAO.getMaxHouseCode();
			
			if( null == maxHouseCode || "".equalsIgnoreCase(maxHouseCode) ){
				maxHouseCode = new StringBuilder("oh").append(GoodsDateUtil.getStringFormat(new Date())).append("1").toString();
			}else{
				int maxNum = Integer.valueOf(maxHouseCode.substring(10)) + 1;
				maxHouseCode = new StringBuilder("oh").append(GoodsDateUtil.getStringFormat(new Date())).append(maxNum).toString();
			}
			
			ordinaryHouse.setHouseCode(maxHouseCode);
			
			int ordinaryHouseId = ordinaryHouseDAO.saveOrdinaryHouse(ordinaryHouse);
			relationshipProperty.setSourceId(ordinaryHouseId);
			
			String houseId = new StringBuilder("showOrdinaryHouse?ohId=").append(ordinaryHouseId).toString();
			String houseContent = ordinaryHouse.getBuildingName();
			String contentDesc = ordinaryHouse.getPropertyName();
			GoodsQuickSearcherUtils.createIndex(houseId, houseContent,contentDesc);
		}else{
			ordinaryHouse.setUpdateUser(userInfo.getLoginName());
			ordinaryHouse.setUpdateDate(new Date());
			
			ordinaryHouseDAO.updateOrdinaryHouse(ordinaryHouse);
			relationshipProperty.setSourceId(orHouseId);
		}
		
		relationshipPropertyService.saveOrUpdateRelationshipProperty(relationshipProperty, userInfo);
	}

	@Override
	public void deleteOrdinaryHouse(int ordinaryHouseId) throws Exception {
		ordinaryHouseDAO.deleteOrdinaryHouse(ordinaryHouseId);
	}

	@Override
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseById(int ordinaryHouseId)
			throws Exception {
		return ordinaryHouseDAO.getGoodsOrdinaryHouseById(ordinaryHouseId);
	}

	@Override
	@Transactional
	public void saveOrdinaryHousesFromFile(List<GoodsOrdinaryHouse> houses)
			throws Exception {
		List<String> existsHouseNames = ordinaryHouseDAO.getAllExistsHouses();
		
		int codeNum = 1;
		
		String maxHouseCode = ordinaryHouseDAO.getMaxHouseCode();
		if( null != maxHouseCode && !"".equalsIgnoreCase(maxHouseCode) ){
			codeNum = Integer.valueOf(maxHouseCode.substring(10)) + 1;
		}
		
		List<GoodsOrdinaryHouse> houseNeed2Save = new ArrayList<GoodsOrdinaryHouse>();
		for( GoodsOrdinaryHouse house1 : houses ){
			maxHouseCode = new StringBuilder(100).append("oh").append(GoodsDateUtil.getStringFormat(new Date())).append(codeNum++).toString();
			house1.setHouseCode(maxHouseCode);
			
			if( null == existsHouseNames || !existsHouseNames.contains(house1.getBuildingName()) ){
				houseNeed2Save.add(house1);
			}
		}
		ordinaryHouseDAO.saveOrdinaryHousesFromFile(houseNeed2Save);
		
		for( GoodsOrdinaryHouse house2 : houses ){
			if( null != existsHouseNames && existsHouseNames.contains(house2.getBuildingName()) ){
				continue;
			}
			
			final String houseCode = house2.getHouseCode();
			List<GoodsHouseDevice> houseDevices = house2.getHouseDevices();
			if( !CollectionUtils.isEmpty(houseDevices) ){
				for( GoodsHouseDevice device : houseDevices ){
					device.setOrHouseCode(houseCode);
				}
				ordinaryHouseDAO.saveOrdinaryHouseDeviceFromFile(houseDevices);
			}
		}
	}

	@Override
	public void saveOrUpdateHouseDevice(GoodsHouseDevice houseDevice,
			GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser)
			throws Exception {
		int deviceId = houseDevice.getId();
		
		if( 0 == deviceId ){
			houseDevice.setCreateUser(currentUser.getLoginName());
			houseDevice.setUpdateUser(currentUser.getLoginName());
			ordinaryHouseDAO.saveHouseDevice(houseDevice);
		}else{
			ordinaryHouse.setUpdateUser(currentUser.getLoginName());
			ordinaryHouse.setUpdateDate(new Date());
			ordinaryHouseDAO.updateHouseDevice(houseDevice);
		}
	}

	@Override
	public List<GoodsHouseDevice> getAllHouseDeviceByUser(GoodsOrdinaryHouse ordinaryHouse, WebUserInfo currentUser)
			throws Exception {
		try{
			return ordinaryHouseDAO.getAllHouseDeviceByUser(ordinaryHouse, currentUser);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error(String.format("fail to get the house device by user code %s,", currentUser.getLoginName()),e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsRelationshipProperty> getAllHouseRelationshipByUserCode(
			String userCode) throws Exception {
		try{
			return ordinaryHouseDAO.getAllHouseRelationshipByUserCode(userCode);
		} catch(IncorrectResultSizeDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error(String.format("fail to get all the house relationship by user code %s,", userCode),e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsOrdinaryHouse getGoodsOrdinaryHouseByName(String houseName)
			throws Exception {
		try{
			return ordinaryHouseDAO.getGoodsOrdinaryHouseByName(houseName);
		}catch( EmptyResultDataAccessException e){
			return null;
		}catch(IncorrectResultSizeDataAccessException erd){
			throw new Exception("数据冲突，存在多个相同的名称");
		}
	}
}
