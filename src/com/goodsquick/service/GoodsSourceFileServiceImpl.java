package com.goodsquick.service;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsSourceFileDAO;
import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.WebUserInfo;

@Service("goodsSourceFileService")
public class GoodsSourceFileServiceImpl implements GoodsSourceFileService {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("goodsSourceFileDAO")
	GoodsSourceFileDAO goodsSourceFileDAO;
	
	@Override
	public void saveOrUpdateGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception {
		int fileId = houseFile.getId();
		if( fileId == 0 ){
			File targetFile = houseFile.getTargetFile();
			try {
				if (!targetFile.exists()) {
					houseFile.getSourceFile().transferTo(targetFile);
				}
			} catch (Exception e) {
				logger.error("file not found when upload house source file",e);
			}
			goodsSourceFileDAO.saveGoodsHouseFile(houseFile, currentUser);
		}else{
			goodsSourceFileDAO.updateGoodsHouseFile(houseFile, currentUser);
		}

	}

	@Override
	public void deleteGoodsHouseFile(int houseFileId) throws Exception {
		goodsSourceFileDAO.deleteGoodsHouseFile(houseFileId);
	}

	@Override
	public GoodsHouseFile getGoodsHouseFileById(int houseFileId)
			throws Exception {
		return goodsSourceFileDAO.getGoodsHouseFileById(houseFileId);
	}

	@Override
	public List<GoodsHouseFile> getGoodsHouseFileByRepositoryCode(
			String repositoryCode) throws Exception {
		try{
			return goodsSourceFileDAO.getGoodsHouseFileByRepositoryCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the house files,",e);
            return Collections.emptyList();
        }
	}
}
