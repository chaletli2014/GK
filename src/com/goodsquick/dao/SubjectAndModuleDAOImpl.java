package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseSubjectModuleRowMapper;
import com.goodsquick.mapper.GoodsHouseSubjectRowMapper;
import com.goodsquick.model.Category;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;

@Repository("subjectAndModuleDAO")
public class SubjectAndModuleDAOImpl extends BaseDAOImpl implements SubjectAndModuleDAO{

	@Override
	public List<Category> getChildSubjectByParentId(int parentId)
			throws Exception {
		List<Category> topSubjectList = new ArrayList<Category>();
        String sql = "select * from tbl_goods_house_subject where parentId = ? ";
        topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentId}, new GoodsHouseSubjectRowMapper());
        return topSubjectList;
	}
	
	@Override
	public List<Category> getChildSubjectByParentCode(String parentCode)
			throws Exception {
		List<Category> topSubjectList = new ArrayList<Category>();
		String sql = "select child.* from tbl_goods_house_subject child, tbl_goods_house_subject parent where child.parentId = parent.id and parent.subject_code = ? ";
		topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentCode}, new GoodsHouseSubjectRowMapper());
		return topSubjectList;
	}

	@Override
	public Category getSubjectInfoById(int subjectId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllSubject() throws Exception {
		List<Category> topSubjectList = new ArrayList<Category>();
        String sql = "select * from tbl_goods_house_subject";
        topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsHouseSubjectRowMapper());
        return topSubjectList;
	}

	@Override
	public void updateSubject(CategoryJsonObj obj) throws Exception {
		String sql = "update tbl_goods_house_subject set subject_name = ?, parentId = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId(),obj.getId() });
	}

	@Override
	public void addSubject(CategoryJsonObj obj) throws Exception {
		String sql = "insert into tbl_goods_house_subject(id,subject_name,parentId) values(null,?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId() });
	}

	@Override
	public void deleteSubject(CategoryJsonObj obj) throws Exception {
		String sql = "delete from tbl_goods_house_subject where id=?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getId() });
	}

	@Override
	public String getSubjectCodeByName(String subjectName) throws Exception {
		String sql = "select subject_code from tbl_goods_house_subject where subject_name = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{subjectName}, String.class);
	}

	@Override
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectId(
			int subjectId) throws Exception {
		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
        String sql = "select * from tbl_goods_house_subject_module where subject_id = ?";
        moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{subjectId}, new GoodsHouseSubjectModuleRowMapper());
        return moduleList;
	}

	@Override
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId)
			throws Exception {
		String sql = "select * from tbl_goods_house_subject_module where id = ? ";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{moduleId}, new GoodsHouseSubjectModuleRowMapper());
	}

	@Override
	public void updateSubjectModule(GoodsHouseSubjectModule obj)
			throws Exception {
		StringBuilder sql = new StringBuilder(100);
		sql.append("update tbl_goods_house_subject_module ");
        sql.append("set module_name = ?, module_desc = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(obj.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void addSubjectModule(GoodsHouseSubjectModule obj) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(" insert into tbl_goods_house_subject_module(");
		sql.append(" id,subject_id,module_type_code,module_code,module_name,module_desc ");
		sql.append(" ,create_date,create_user,update_date,update_user,status,remark ) ");
		sql.append(" values(null,?,?,?,?,?,now(),?,now(),?,'1','*')");
		
		params.add(obj.getSubjectId());
		params.add(obj.getModuleTypeCode());
		params.add(obj.getModuleCode());
		params.add(obj.getModuleName());
		params.add(obj.getModuleDesc());
		params.add(obj.getCreateUser());
		params.add(obj.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteSubjectModule(GoodsHouseSubjectModule obj)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(obj.getUpdateUser());
		params.add(obj.getId());
		super.deleteObj("tbl_goods_house_subject_module", params);
	}

	@Override
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectIdAndModuleType(
			int subjectId, String moduleType) throws Exception {
		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
        String sql = "select * from tbl_goods_house_subject_module where subject_id = ? and module_type_code = ?";
        moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{subjectId,moduleType}, new GoodsHouseSubjectModuleRowMapper());
        return moduleList;
	}
}
