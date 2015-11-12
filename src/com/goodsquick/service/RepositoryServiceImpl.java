package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.CollectionUtils;
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

	@Autowired
	@Qualifier("repositoryDAO")
	private RepositoryDAO repositoryDAO;
	
	@Override
	public List<GoodsRepository> saveOrUpdateRepository(HttpServletRequest request, GoodsRepository goodsRepository)
			throws Exception {
		if( 0 == goodsRepository.getId() ){
			int repositoryNum = 0;
			try{
				List<GoodsRepository> repositoryList = repositoryDAO.getRepositoryByLoginName(goodsRepository.getCreateUser());
				if( !CollectionUtils.isEmpty(repositoryList) ){
					repositoryNum = repositoryList.size();
				}
			}catch(Exception e){}
			
			repositoryNum++;
			String newRepositoryCode = goodsRepository.getCreateUser()+"_"+repositoryNum;
			request.getSession().setAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE, newRepositoryCode);
			goodsRepository.setRepositoryCode(newRepositoryCode);
			repositoryDAO.saveRepository(goodsRepository);
			
			GoodsRepositoryUser repositoryUser = new GoodsRepositoryUser();
			repositoryUser.setRepositoryCode(newRepositoryCode);
			repositoryUser.setUserCode(goodsRepository.getCreateUser());
			repositoryUser.setCreateUser(goodsRepository.getCreateUser());
			repositoryUser.setUpdateUser(goodsRepository.getCreateUser());
			repositoryDAO.saveRepositoryUser(repositoryUser);
		}else{
			repositoryDAO.updateRepository(goodsRepository);
		}
		return repositoryDAO.getRepositoryByLoginName(goodsRepository.getCreateUser());
	}

	@Override
	public List<GoodsRepository> getRepositoryByLoginName(String loginName) throws Exception {
		try{
			return repositoryDAO.getRepositoryByLoginName(loginName);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}

	@Override
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
