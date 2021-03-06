package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseSubjectModuleRowMapper;
import com.goodsquick.mapper.GoodsHouseSubjectRowMapper;
import com.goodsquick.model.GoodsSubject;
import com.goodsquick.model.CategoryJsonObj;
import com.goodsquick.model.GoodsHouseSubjectModule;

@Repository("subjectAndModuleDAO")
public class SubjectAndModuleDAOImpl extends BaseDAOImpl implements SubjectAndModuleDAO{

	@Override
	public List<GoodsSubject> getChildSubjectByParentId(int parentId)
			throws Exception {
		List<GoodsSubject> topSubjectList = new ArrayList<GoodsSubject>();
        String sql = "select * from tbl_goods_house_subject hs where parentId = ? and status = '1' order by hs.update_date desc";
        topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentId}, new GoodsHouseSubjectRowMapper());
        return topSubjectList;
	}
	
	@Override
	public List<GoodsSubject> getChildSubjectByParentCode(String parentCode)
			throws Exception {
		List<GoodsSubject> topSubjectList = new ArrayList<GoodsSubject>();
		String sql = "select child.* from tbl_goods_house_subject child, tbl_goods_house_subject parent where child.parentId = parent.id and parent.subject_code = ? and child.status='1' order by child.update_date desc";
		topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{parentCode}, new GoodsHouseSubjectRowMapper());
		return topSubjectList;
	}

	@Override
	public GoodsSubject getSubjectInfoById(int subjectId) throws Exception {
		StringBuilder sql = new StringBuilder("select hs.*");
		sql.append(",'' as subject1Name, '' as subject2Name ");
		sql.append("from tbl_goods_house_subject hs ");
		sql.append("where hs.id= ? ");
		return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{subjectId}, new GoodsHouseSubjectRowMapper());
	}
	
	@Override
	public List<GoodsSubject> getSubjectByLevel(String level, String repositoryCode) throws Exception {
		List<GoodsSubject> topSubjectList = new ArrayList<GoodsSubject>();
		StringBuilder sql = new StringBuilder("");
		if( "1".equalsIgnoreCase(level) ){
			sql.append("select hs.* from tbl_goods_house_subject hs ");
			sql.append("where hs.subject_level= ? and hs.repository_code = ? and hs.status = '1' order by hs.update_date desc");
		}else if( "2".equalsIgnoreCase(level) ){
			sql.append("select hs.*,subject1.subject_name as subject1Name ");
			sql.append("from tbl_goods_house_subject hs, tbl_goods_house_subject subject1 ");
			sql.append("where hs.subject_level= ? and hs.repository_code = ? and hs.parentId = subject1.id  and hs.status = '1' order by hs.update_date desc");
		}else if( "3".equalsIgnoreCase(level) ){
			sql.append("select hs.*,subject1.subject_name as subject1Name, subject2.subject_name as subject2Name ");
			sql.append("from tbl_goods_house_subject hs, tbl_goods_house_subject subject1, tbl_goods_house_subject subject2 ");
			sql.append("where hs.subject_level= ? and hs.repository_code = ? and hs.status = '1' ");
			sql.append("and hs.parentId = subject2.id and subject2.parentId = subject1.id  order by hs.update_date desc");
		}
		topSubjectList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{level,repositoryCode}, new GoodsHouseSubjectRowMapper());
		return topSubjectList;
	}

	@Override
	public List<GoodsSubject> getAllSubject(String repositoryCode) throws Exception {
		List<GoodsSubject> topSubjectList = new ArrayList<GoodsSubject>();
        String sql = "select * from tbl_goods_house_subject hs where repository_code= ? and status = '1' order by hs.subject_name ";
        topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{repositoryCode}, new GoodsHouseSubjectRowMapper());
        return topSubjectList;
	}
	
	@Override
	public List<GoodsSubject> getAllSubjectWithRoot(String repositoryCode) throws Exception {
		List<GoodsSubject> topSubjectList = new ArrayList<GoodsSubject>();
		String sql = "select * from tbl_goods_house_subject hs where ( repository_code= ? or parentId = 0 ) and status = '1' order by hs.update_date desc";
		topSubjectList = dataBean.getJdbcTemplate().query(sql, new Object[]{repositoryCode}, new GoodsHouseSubjectRowMapper());
		return topSubjectList;
	}
	
	@Override
	public void addSubject(CategoryJsonObj obj, String repositoryCode) throws Exception {
		String sql = "insert into tbl_goods_house_subject(id,subject_name,parentId,repository_code,status) values(null,?,?,?,'1')";
		dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId(),repositoryCode });
	}

	@Override
	public void updateSubject(CategoryJsonObj obj) throws Exception {
		String sql = "update tbl_goods_house_subject set subject_name = ?, parentId = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getpId(),obj.getId() });
	}

	@Override
	public void deleteSubject(CategoryJsonObj obj) throws Exception {
		String sql = "delete from tbl_goods_house_subject where id=?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getId() });
	}

	@Override
	public void addSubject(GoodsSubject obj, String repositoryCode) throws Exception {
		String sql = "insert into tbl_goods_house_subject(id,subject_name,subject_desc,parentId,subject_level,repository_code,status) values(null,?,?,?,?,?,'1')";
		dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getDesc(),obj.getParentId(),obj.getLevel(),repositoryCode });
	}

	@Override
	public void updateSubject(GoodsSubject obj) throws Exception {
		String sql = "update tbl_goods_house_subject set subject_name = ?, subject_desc = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { obj.getName(),obj.getDesc(),obj.getId() });
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
        String sql = "select hs.*,gd.dic_name as module_type_name from tbl_goods_house_subject_module hs, tbl_goods_dictionary gd where subject_id = ? and hs.module_type_code = gd.dic_code and gd.type_code = 'subjectModule' and hs.status='1' order by hs.update_date desc ";
        moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{subjectId}, new GoodsHouseSubjectModuleRowMapper());
        return moduleList;
	}
	
	@Override
	public List<GoodsHouseSubjectModule> getAllModuleNodesBySubjectId(int subjectId) throws Exception {
		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
		String sql = "select sm.* from tbl_goods_house_subject_module sm, tbl_goods_house_subject hs where FIND_IN_SET(hs.id, queryChildrenSubjectInfo(?)) and sm.subject_id = hs.id and sm.status = '1' and hs.status = '1' ";
		moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{subjectId}, new GoodsHouseSubjectModuleRowMapper());
		return moduleList;
	}
	
	@Override
	public List<GoodsHouseSubjectModule> getAllSubjectModulesByRepositoryCode(String repositoryCode) throws Exception {
		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
		String sql = "select sm.* from tbl_goods_house_subject_module sm, tbl_goods_house_subject hs where sm.subject_id = hs.id and hs.repository_code = ?  and hs.status = '1' order by hs.update_date desc";
		moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{repositoryCode}, new GoodsHouseSubjectModuleRowMapper());
		return moduleList;
	}

	@Override
	public GoodsHouseSubjectModule getSubjectModuleByModuleId(int moduleId)
			throws Exception {
		String sql = "select hs.*,gd.dic_name as module_type_name from tbl_goods_house_subject_module hs, tbl_goods_dictionary gd where hs.id = ? and hs.module_type_code = gd.dic_code and gd.type_code = 'subjectModule' ";
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
		params.add(obj.getModuleName());
		params.add(obj.getModuleDesc());
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
	public void deleteSubject(GoodsSubject obj)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(obj.getUpdateUser());
		params.add(obj.getId());
		super.deleteObj("tbl_goods_house_subject", params);
	}

	@Override
	public List<GoodsHouseSubjectModule> getSubjectModulesBySubjectIdAndModuleType(
			int subjectId, String moduleType) throws Exception {
		List<GoodsHouseSubjectModule> moduleList = new ArrayList<GoodsHouseSubjectModule>();
        String sql = "select hs.*,gd.dic_name as module_type_name from tbl_goods_house_subject_module hs, tbl_goods_dictionary gd where subject_id = ? and module_type_code = ? and hs.module_type_code = gd.dic_code and gd.type_code = 'subjectModule' and hs.status='1' order by hs.update_date desc";
        moduleList = dataBean.getJdbcTemplate().query(sql, new Object[]{subjectId,moduleType}, new GoodsHouseSubjectModuleRowMapper());
        return moduleList;
	}
}
