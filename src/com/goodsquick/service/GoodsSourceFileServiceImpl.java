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
import com.goodsquick.model.GoodsProductSource;
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
	public void deleteGoodsHouseFile(GoodsHouseFile houseFile) throws Exception {
		goodsSourceFileDAO.deleteGoodsHouseFile(houseFile);
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

	@Override
	public void setMainPicOfGoodsHouse(int houseFileId, String repositoryCode, WebUserInfo currentUser)
			throws Exception {
		goodsSourceFileDAO.setMainPicOfGoodsHouse(houseFileId, repositoryCode, currentUser);
	}

	@Override
	public void saveOrUpdateGoodsProductSource(
			GoodsProductSource productSource, WebUserInfo currentUser)
			throws Exception {
		long fileId = productSource.getId();
		if( fileId == 0 ){
			File targetFile = productSource.getTargetFile();
			try {
				if (!targetFile.exists()) {
					productSource.getSourceFile().transferTo(targetFile);
				}
			} catch (Exception e) {
				logger.error("file not found when upload product source file",e);
			}
			goodsSourceFileDAO.saveGoodsProductSource(productSource, currentUser);
		}else{
			goodsSourceFileDAO.updateGoodsProductSource(productSource, currentUser);
		}
	}

	@Override
	public void deleteGoodsProductSource(GoodsProductSource productSource) throws Exception {
		goodsSourceFileDAO.deleteGoodsProductSource(productSource);
	}

	@Override
	public GoodsProductSource getGoodsProductSourceById(int productSourceId)
			throws Exception {
		return goodsSourceFileDAO.getGoodsProductSourceById(productSourceId);
	}

	@Override
	public List<GoodsProductSource> getGoodsProductSourceByRCAndProductId(
			String repositoryCode, long productId) throws Exception {
		try{
			return goodsSourceFileDAO.getGoodsProductSourceByRCAndProductId(repositoryCode, productId);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the product files,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public void setMainPicOfGoodsProduct(int productSourceId, long productId,
			String repositoryCode, WebUserInfo currentUser) throws Exception {
		goodsSourceFileDAO.setMainPicOfGoodsProduct(productSourceId, productId, repositoryCode, currentUser);
	}
}
