package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.CategoryRowMapper;
import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.utils.DataBean;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<Category> getChildCategoryByParentId(int parentId)
			throws Exception {
		List<Category> topCategoryList = new ArrayList<Category>();
        String sql = "select * from tbl_category where parentId = ? ";
        topCategoryList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentId}, new CategoryRowMapper());
        return topCategoryList;
	}
	
	@Override
	public List<Category> getChildCategoryByParentCode(String parentCode)
			throws Exception {
		List<Category> topCategoryList = new ArrayList<Category>();
		String sql = "select child.* from tbl_category child, tbl_category parent where child.parentId = parent.id and parent.category_code = ? ";
		topCategoryList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentCode}, new CategoryRowMapper());
		return topCategoryList;
	}

	@Override
	public Category getCategoryInfoById(int categoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		List<Category> topCategoryList = new ArrayList<Category>();
        String sql = "select * from tbl_category";
        topCategoryList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new CategoryRowMapper());
        return topCategoryList;
	}

	@Override
	public void updateCategory(CategoryJsonObj obj) throws Exception {
		String sql = "update tbl_category set category_name = ?, parentId = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId(),obj.getId() });
	}

	@Override
	public void addCategory(CategoryJsonObj obj) throws Exception {
		String sql = "insert into tbl_category(id,category_name,parentId) values(null,?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId() });
	}

	@Override
	public void deleteCategory(CategoryJsonObj obj) throws Exception {
		String sql = "delete from tbl_category where id=?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getId() });
	}

	@Override
	public String getCategoryCodeByName(String categoryName) throws Exception {
		String sql = "select category_code from tbl_category where category_name = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{categoryName}, String.class);
	}

}
