package com.goodsquick.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.LiftDAO;
import com.goodsquick.model.GoodsDeviceLift;
import com.goodsquick.model.WebUserInfo;

@Service("liftService")
public class LiftServiceImpl implements LiftService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("liftDAO")
	private LiftDAO liftDAO;
	
	@Override
	public List<GoodsDeviceLift> getDeviceLiftByUserCode(String userCode, String liftType) throws Exception {
		try{
			return liftDAO.getDeviceLiftByUserCode(userCode, liftType);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the lift by user code,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsDeviceLift> getDeviceLiftByRepositoryCode(String repositoryCode) throws Exception {
		try{
			return liftDAO.getDeviceLiftByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the lift by repositoryCode,",e);
			return Collections.emptyList();
		}
	}

	@Override
	public void saveOrUpdateDeviceLift(GoodsDeviceLift lift,
			WebUserInfo currentUser) throws Exception {
		int liftId = lift.getId();
		
		if( 0 == liftId ){
			lift.setCreateUser(currentUser.getLoginName());
			lift.setUpdateUser(currentUser.getLoginName());
			
			liftDAO.saveDeviceLiftQuick(lift);
		}else{
			lift.setUpdateUser(currentUser.getLoginName());
			lift.setUpdateDate(new Date());
			
			liftDAO.updateDeviceLift(lift);
		}
	}

	@Override
	public void deleteDeviceLift(int liftId) throws Exception {
		liftDAO.deleteDeviceLift(liftId);
	}

	@Override
	public GoodsDeviceLift getGoodsDeviceLiftById(int liftId) throws Exception {
		return liftDAO.getGoodsDeviceLiftById(liftId);
	}

	@Override
	public List<String> getLiftBrandsByRepositoryCode(String repositoryCode)
			throws Exception {
		try{
			return liftDAO.getLiftBrandsByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the lift brand by repositoryCode,",e);
			return Collections.emptyList();
		}
	}

}
