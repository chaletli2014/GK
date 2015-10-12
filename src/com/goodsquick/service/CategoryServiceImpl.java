package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.CategoryDAO;
import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.utils.GoodsQuickAttributes;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    Logger logger = Logger.getLogger(CategoryServiceImpl.class);
    
	@Autowired
	@Qualifier("categoryDAO")
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> getChildCategoryByParentId(int parentId)
			throws Exception {
		try{
			return categoryDAO.getChildCategoryByParentId(parentId);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the top category,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public Category getCategoryInfoById(int categoryId) throws Exception {
		try{
			return categoryDAO.getCategoryInfoById(categoryId);
		} catch(EmptyResultDataAccessException erd){
            return new Category();
        } catch(Exception e){
            logger.error("fail to get the top category,",e);
            return new Category();
        }
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		try{
			return categoryDAO.getAllCategory();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the category,",e);
            return Collections.emptyList();
        }
	}

	@Override
	@Transactional
	public void saveOrupdateCategory(List<CategoryJsonObj> categorys) throws Exception {
		try{
			for( CategoryJsonObj obj : categorys ){
				if( GoodsQuickAttributes.STATUS_UPDATE.equalsIgnoreCase(obj.getStatus()) ){
					categoryDAO.updateCategory(obj);
				}else if( GoodsQuickAttributes.STATUS_NEW.equalsIgnoreCase(obj.getStatus()) ){
					categoryDAO.addCategory(obj);
				}else if( GoodsQuickAttributes.STATUS_DELETE.equalsIgnoreCase(obj.getStatus()) ){
					categoryDAO.deleteCategory(obj);
				}
			}
		}catch(Exception e){
			logger.error("fail to save Or update category,",e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String getCategoryCodeByName(String categoryName) throws Exception {
		try{
			return categoryDAO.getCategoryCodeByName(categoryName);
		} catch(EmptyResultDataAccessException erd){
            return "";
        } catch(Exception e){
            logger.error("fail to get the top category,",e);
            return "";
        }
	}

}
