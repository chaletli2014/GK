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
import com.goodsquick.utils.GoodsDateUtil;

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
	public void saveOrUpdateDeviceLift(GoodsDeviceLift lift,
			WebUserInfo currentUser) throws Exception {
		int liftId = lift.getId();
		
		if( 0 == liftId ){
			lift.setCreateUser(currentUser.getLoginName());
			lift.setUpdateUser(currentUser.getLoginName());
			String maxCode = liftDAO.getMaxCode();
			
			if( null == maxCode || "".equalsIgnoreCase(maxCode) ){
				maxCode = new StringBuilder("lift").append(GoodsDateUtil.getStringFormat(new Date())).append("1").toString();
			}else{
				int maxNum = Integer.valueOf(maxCode.substring(12)) + 1;
				maxCode = new StringBuilder("lift").append(GoodsDateUtil.getStringFormat(new Date())).append(maxNum).toString();
			}
			
			lift.setCode(maxCode);
			
			liftDAO.saveDeviceLift(lift);
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

}
