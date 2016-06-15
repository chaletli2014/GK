package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.RepositoryDAO;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsRepositoryUser;
import com.goodsquick.utils.GoodsQuickAttributes;

@Service("repositoryService")
public class RepositoryServiceImpl implements RepositoryService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("repositoryDAO")
	private RepositoryDAO repositoryDAO;
	
	@Override
	public List<GoodsRepository> saveOrUpdateRepository(HttpServletRequest request, GoodsRepository goodsRepository)
			throws Exception {
		if( 0 == goodsRepository.getId() ){
			int repositoryNum = 0;
			try{
				List<GoodsRepository> repositoryList = repositoryDAO.getRepositoryByLoginName(goodsRepository.getCreateUser(),false);
				if( !CollectionUtils.isEmpty(repositoryList) ){
					repositoryNum = repositoryList.size();
				}
			}catch(Exception e){}
			
			repositoryNum++;
			String newRepositoryCode = goodsRepository.getCreateUser()+"_"+repositoryNum;
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE, newRepositoryCode);
			goodsRepository.setRepositoryCode(newRepositoryCode);
			repositoryDAO.saveRepository(goodsRepository);
		}else{
			repositoryDAO.updateRepository(goodsRepository);
		}
		return repositoryDAO.getRepositoryByLoginName(goodsRepository.getCreateUser(),true);
	}

	@Override
	public List<GoodsRepository> getRepositoryByLoginName(String loginName) throws Exception {
		try{
			return repositoryDAO.getRepositoryByLoginName(loginName,true);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsRepository> getRepositoryByLoginNameAndType(String loginName, String type) throws Exception {
		try{
			return repositoryDAO.getRepositoryByLoginNameAndType(loginName,type,true);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			return Collections.emptyList();
		}
	}

	@Override
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		repositoryDAO.updateRepositoryUser(repositoryUser);
	}
	
	@Override
	public void removeRepository(GoodsRepository goodsRepositoryFromPage)
			throws Exception {
		repositoryDAO.removeRepository(goodsRepositoryFromPage);
	}

	@Override
	public GoodsRepository getRepositoryByCode(String repositoryCode)
			throws Exception {
		try{
			return repositoryDAO.getRepositoryByCode(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return new GoodsRepository();
        } catch(Exception e){
            return new GoodsRepository();
        }
	}

	@Override
	public List<GoodsRepositoryUser> getRepositoryUserByRepositoryCode(
			String repositoryCode, String currentUser) throws Exception {
		try{
			return repositoryDAO.getRepositoryUserByRepositoryCode(repositoryCode, currentUser);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<GoodsRepositoryUser> getRepositoryUserByUserId(String repositoryCode, String userId) throws Exception {
		try{
			return repositoryDAO.getRepositoryUserByUserId(repositoryCode, userId);
		} catch(EmptyResultDataAccessException erd){
			logger.warn("empty data");
			return null;
		} catch(Exception e){
			logger.error("fail to get repository user,",e);
			return null;
		}
	}

	@Override
	public void saveRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		repositoryDAO.saveRepositoryUser(repositoryUser);
	}

	@Override
	public void removeRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		repositoryDAO.removeRepositoryUser(repositoryUser);
	}

}
