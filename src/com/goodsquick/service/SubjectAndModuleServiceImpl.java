package com.goodsquick.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.SubjectAndModuleDAO;
import com.goodsquick.model.Category;
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
	public List<Category> getChildSubjectByParentId(int parentId)
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
	public List<Category> getChildSubjectByParentCode(String parentCode)
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
	public Category getSubjectInfoById(int subjectId) throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectInfoById(subjectId);
		} catch(EmptyResultDataAccessException erd){
            return new Category();
        } catch(Exception e){
            logger.error("fail to get the subject info by id,",e);
            return new Category();
        }
	}

	@Override
	public List<Category> getAllSubject() throws Exception {
		try{
			return subjectAndModuleDAO.getAllSubject();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the subject,",e);
            return Collections.emptyList();
        }
	}

	@Override
	@Transactional
	public void saveOrupdateSubject(List<CategoryJsonObj> subjects) throws Exception {
		try{
			for( CategoryJsonObj obj : subjects ){
				if( GoodsQuickAttributes.STATUS_UPDATE.equalsIgnoreCase(obj.getStatus()) ){
					subjectAndModuleDAO.updateSubject(obj);
				}else if( GoodsQuickAttributes.STATUS_NEW.equalsIgnoreCase(obj.getStatus()) ){
					subjectAndModuleDAO.addSubject(obj);
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
			int subjectId) throws Exception {
		try{
			return subjectAndModuleDAO.getSubjectModulesBySubjectId(subjectId);
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

}
