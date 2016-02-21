package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.GoodsHouseOtherDAO;
import com.goodsquick.model.GoodsHouseOther;
import com.goodsquick.model.GoodsHousePaint;
import com.goodsquick.model.WebUserInfo;

@Service("goodsHouseOtherService")
public class GoodsHouseOtherServiceImpl implements GoodsHouseOtherService {

    Logger logger = Logger.getLogger(GoodsHouseOtherServiceImpl.class);
    
	@Autowired
	@Qualifier("goodsHouseOtherDAO")
	private GoodsHouseOtherDAO goodsHouseOtherDAO;
	
	@Override
	public List<GoodsHouseOther> getAllOther(String repositoryCode)
			throws Exception {
		List<GoodsHouseOther> houseOthers = new ArrayList<GoodsHouseOther>();
		try{
			houseOthers.addAll(goodsHouseOtherDAO.getAllHouseOtherByRepositoryCode(repositoryCode));
			List<GoodsHousePaint> paints = goodsHouseOtherDAO.getHousePaintByRepositoryCode(repositoryCode);
			for( GoodsHousePaint dbPaint : paints ){
				GoodsHouseOther houseOther = new GoodsHouseOther();
				houseOther.setTypeCode(dbPaint.getType2Code());
				houseOther.setTypeName("防水涂料");
				houseOther.setName(dbPaint.getPaintName());
				houseOther.setDesc(dbPaint.getPaintDesc());
				houseOther.setSubjectId(dbPaint.getSubjectId());
				houseOther.setSubjectName(dbPaint.getSubjectName());
				houseOther.setModuleId(dbPaint.getModuleId());
				houseOther.setModuleName(dbPaint.getModuleName());
				houseOther.setId(dbPaint.getId());
				
				houseOthers.add(houseOther);
			}
			return houseOthers;
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the house other by repositoryCode,",e);
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
				int id = houseOther.getId();
				if( id == 0 ){
					if( "wpp".equalsIgnoreCase(houseOther.getTypeCode()) ){
						GoodsHousePaint housePaint = new GoodsHousePaint();
						housePaint.setType1Code("jjp");
						housePaint.setType2Code(houseOther.getTypeCode());
						housePaint.setPaintName(houseOther.getName());
						housePaint.setPaintDesc(houseOther.getDesc());
						housePaint.setSubjectId(houseOther.getSubjectId());
						housePaint.setModuleId(houseOther.getModuleId());
						housePaint.setRepositoryCode(repositoryCode);
						housePaint.setCreateUser(currentUser);
						housePaint.setUpdateUser(currentUser);
						goodsHouseOtherDAO.saveHousePaintQuick(housePaint);
					}
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

	@Override
	public GoodsHousePaint getPaintInfoById(int housePaintId) throws Exception {
		return goodsHouseOtherDAO.getGoodsHousePaintById(housePaintId);
	}

	@Override
	public void saveOrUpdateHousePaint(GoodsHousePaint housePaint,
			WebUserInfo currentUser, String repositoryCode) throws Exception {
		int paintId = housePaint.getId();
		
		if( 0 == paintId ){
			housePaint.setCreateUser(currentUser.getLoginName());
			housePaint.setUpdateUser(currentUser.getLoginName());

			goodsHouseOtherDAO.saveHousePaintQuick(housePaint);
		}else{
			housePaint.setUpdateUser(currentUser.getLoginName());
			housePaint.setUpdateDate(new Date());
			
			goodsHouseOtherDAO.updateHousePaint(housePaint);
		}
	}

}
