package com.goodsquick.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.SubjectAndModuleDAO;
import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;
import com.goodsquick.utils.GoodsQuickAttributes;

@Service("subjectAndModuleService")
public class SubjectAndModuleServiceImpl implements SubjectAndModuleService {

    Logger logger = Logger.getLogger(SubjectAndModuleServiceImpl.class);
    
	@Autowired
	@Qualifier("subjectAndModuleDAO")
	private SubjectAndModuleDAO subjectAndModuleDAO;
	
	@Override
	public List<GoodsSubject> getChildSubjectByParentId(int parentId)
			throws Exception {
		try{
			return subjectAndModuleDAO.getChildSubjectByParentId(parentId);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the subject by pid,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsSubject> getChildSubjectByParentCode(String parentCode)
			throws Exception {
		try{
			return subjectAndModuleDAO.getChildSubjectByParentCode(parentCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get the subject by pcode,",e);
			return Collections.emptyList();
		}
	}

	@Override
	public GoodsSubject getSubjectInfoById(int subjectId) throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectInfoById(subjectId);
		} catch(EmptyResultDataAccessException erd){
            return new GoodsSubject();
        } catch(Exception e){
            logger.error("fail to get the subject info by id,",e);
            return new GoodsSubject();
        }
	}

	@Override
	public List<GoodsSubject> getSubjectByLevel(String level,String repositoryCode) throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectByLevel(level, repositoryCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the subject,",e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsSubject> getAllSubject(String repositoryCode) throws Exception {
		try{
			return subjectAndModuleDAO.getAllSubject(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get all the subject,",e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<GoodsSubject> getAllSubjectWithRoot(String repositoryCode) throws Exception {
		try{
			return subjectAndModuleDAO.getAllSubjectWithRoot(repositoryCode);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error("fail to get all the subject,",e);
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public void saveOrupdateSubject(List<CategoryJsonObj> subjects, String repositoryCode) throws Exception {
		try{
			for( CategoryJsonObj obj : subjects ){
				if( GoodsQuickAttributes.STATUS_UPDATE.equalsIgnoreCase(obj.getStatus()) ){
					subjectAndModuleDAO.updateSubject(obj);
				}else if( GoodsQuickAttributes.STATUS_NEW.equalsIgnoreCase(obj.getStatus()) ){
					subjectAndModuleDAO.addSubject(obj, repositoryCode);
				}else if( GoodsQuickAttributes.STATUS_DELETE.equalsIgnoreCase(obj.getStatus()) ){
					subjectAndModuleDAO.deleteSubject(obj);
				}
			}
		}catch(Exception e){
			logger.error("fail to save Or update subject,",e);
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public void saveOrUpdateSubject(GoodsSubject goodsSubject, String repositoryCode) throws Exception {
		try{
			int subjectId = goodsSubject.getId();
			if( subjectId == 0 ){
				subjectAndModuleDAO.addSubject(goodsSubject, repositoryCode);
			}else{
				subjectAndModuleDAO.updateSubject(goodsSubject);
			}
		}catch(Exception e){
			logger.error("fail to save Or update subject,",e);
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public void saveOrUpdateSubject(List<GoodsSubject> goodsSubjects, String currentUser, String repositoryCode) throws Exception {
		try{
			for( GoodsSubject goodsSubject : goodsSubjects ){
				
				int subjectId = goodsSubject.getId();
				if( subjectId == 0 ){
					goodsSubject.setRepositoryCode(repositoryCode);
					goodsSubject.setCreateUser(currentUser);
					goodsSubject.setUpdateUser(currentUser);
					subjectAndModuleDAO.addSubject(goodsSubject, repositoryCode);
				}else{
					goodsSubject.setUpdateUser(currentUser);
					subjectAndModuleDAO.updateSubject(goodsSubject);
				}
			}
		}catch(Exception e){
			logger.error("fail to save Or update subject,",e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getSubjectCodeByName(String subjectName) throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectCodeByName(subjectName);
		} catch(EmptyResultDataAccessException erd){
            return "";
        } catch(Exception e){
            logger.error("fail to get the subject code by name,",e);
            return "";
        }
	}

	@Override
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectId(
			int subjectId,String repositoryCode) throws Exception {
		try{
			if( subjectId == 0 ){
				return subjectAndModuleDAO.getAllSubjectModulesByRepositoryCode(repositoryCode);
			}else{
				return subjectAndModuleDAO.getSubjectModulesBySubjectId(subjectId);
			}
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the subject module by subjectId,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId)
			throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectModuleByModuleId(moduleId);
		} catch(EmptyResultDataAccessException erd){
            return new GoodsHouseSubjectModule();
        } catch(Exception e){
            logger.error("fail to get the subject module by id,",e);
            return new GoodsHouseSubjectModule();
        }
	}

	@Override
	public void saveOrUpdateSubjectModule(GoodsHouseSubjectModule obj)
			throws Exception {
		int sourceId = obj.getId();
		if( 0 == sourceId ){
			obj.setCreateUser(obj.getCreateUser());
			obj.setUpdateUser(obj.getUpdateUser());
			subjectAndModuleDAO.addSubjectModule(obj);
		}else{
			obj.setUpdateUser(obj.getUpdateUser());
			obj.setUpdateDate(new Date());
			subjectAndModuleDAO.updateSubjectModule(obj);
		}
		
	}

	@Override
	public void deleteSubjectModule(GoodsHouseSubjectModule obj)
			throws Exception {
		subjectAndModuleDAO.deleteSubjectModule(obj);
	}
	
	@Override
	public void deleteSubject(GoodsSubject obj)
			throws Exception {
		subjectAndModuleDAO.deleteSubject(obj);
	}

	@Override
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectIdAndModuleType(
			int subjectId, String moduleType) throws Exception {
		List<GoodsHouseSubjectModule> modules = new ArrayList<GoodsHouseSubjectModule>();
		try{
			if( StringUtils.isBlank(moduleType) ){
				modules = subjectAndModuleDAO.getSubjectModulesBySubjectId(subjectId);
			}else{
				modules = subjectAndModuleDAO.getSubjectModulesBySubjectIdAndModuleType(subjectId, moduleType);
			}
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the subject module by subjectId and moduleType,",e);
            return Collections.emptyList();
        }
		return modules;
	}

}
