package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsRepositoryRowMapper;
import com.goodsquick.mapper.GoodsRepositoryUserRowMapper;
import com.goodsquick.mapper.OrdinaryHouseRowMapper;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsRepository;
import com.goodsquick.model.GoodsRepositoryUser;
import com.goodsquick.utils.GoodsJDBCTemplate;

@Repository("repositoryDAO")
public class RepositoryDAOImpl extends BaseDAOImpl implements RepositoryDAO {

	@Override
	public void saveRepository(GoodsRepository goodsRepository)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" insert into tbl_goods_repository(");
		sql.append(" id,repository_name,repository_code,repository_desc,repository_type ");
		sql.append(" ,create_date,create_user,update_date,update_user,status ) ");
		sql.append(" values(null,?,?,?,?,now(),?,now(),?,'1')");
		
		params.add(goodsRepository.getRepositoryName());
		params.add(goodsRepository.getRepositoryCode());
		params.add(goodsRepository.getRepositoryDesc());
		params.add(goodsRepository.getRepositoryType());
		params.add(goodsRepository.getCreateUser());
		params.add(goodsRepository.getUpdateUser());
		
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}

	@Override
	public void updateRepository(GoodsRepository goodsRepository)
			throws Exception {
		StringBuilder sql = new StringBuilder(100);
		sql.append("update tbl_goods_repository ");
        sql.append("set repository_name = ?, repository_desc = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsRepository.getRepositoryName());
		params.add(goodsRepository.getRepositoryDesc());
		params.add(goodsRepository.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsRepository> getRepositoryByLoginName(String loginName, boolean hideDeleted) throws Exception {
		StringBuilder sql = new StringBuilder("select gr.* from tbl_goods_repository gr, tbl_goods_repository_user gru where gru.user_code = ? and gru.repository_code = gr.repository_code");
		if( hideDeleted ){
			sql.append(" and gr.status = '1' and gru.status = '1' ");
		}
		return dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{loginName}, new GoodsRepositoryRowMapper());
	}
	
	@Override
	public List<GoodsRepository> getRepositoryByLoginNameAndType(String loginName, String type, boolean hideDeleted, boolean excludeSelf) throws Exception {
		StringBuilder sql = new StringBuilder("select gr.* from tbl_goods_repository gr, tbl_goods_repository_user gru where gr.repository_type = ? and gru.user_code = ? and gru.repository_code = gr.repository_code");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		params.add(loginName);
		
		if( hideDeleted ){
			sql.append(" and gr.status = '1' and gru.status = '1' ");
		}
		if( excludeSelf ){
			sql.append(" and gru.create_user != ? ");
			params.add(loginName);
		}
		return dataBean.getJdbcTemplate().query(sql.toString(), params.toArray(), new GoodsRepositoryRowMapper());
	}

	@Override
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		StringBuilder sql = new StringBuilder(100);
		sql.append("update tbl_goods_repository_user set status = '1',update_user=?, update_date=now() where repository_code = ? and user_code = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryUser.getRepositoryCode());
		params.add(repositoryUser.getUserCode());
		params.add(repositoryUser.getUpdateUser());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}
	
	@Override
	public void saveRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" insert into tbl_goods_repository_user(");
		sql.append(" id,repository_code,user_code,priv ");
		sql.append(" ,create_date,create_user,update_date,update_user,status ) ");
		sql.append(" values(null,?,?,?,now(),?,now(),?,'1')");
		
		params.add(repositoryUser.getRepositoryCode());
		params.add(repositoryUser.getUserCode());
		params.add(repositoryUser.getPriv());
		params.add(repositoryUser.getCreateUser());
		params.add(repositoryUser.getUpdateUser());
		
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}

	@Override
	public GoodsRepository getRepositoryByCode(String repositoryCode)
			throws Exception {
		String sql = "select gr.* from tbl_goods_repository gr where gr.status='1' and gr.repository_code = ? ";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{repositoryCode}, new GoodsRepositoryRowMapper());
	}

	@Override
	public void removeRepository(GoodsRepository goodsRepositoryFromPage)
			throws Exception {
		StringBuilder sql = new StringBuilder(100);
		sql.append("update tbl_goods_repository set status = 0, update_date=now(), update_user=? where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsRepositoryFromPage.getUpdateUser());
		params.add(goodsRepositoryFromPage.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsRepositoryUser> getRepositoryUserByRepositoryCode(
			String repositoryCode, String userCode) throws Exception {
		StringBuilder sql = new StringBuilder("select gru.*, wu.name as user_name, wu.telephone  ");
		sql.append(" from tbl_goods_repository_user gru, tbl_web_userinfo wu ");
		sql.append(" where gru.repository_code = ? and gru.status='1' and gru.user_code = wu.login_name and gru.user_code != ?");
		return dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode, userCode}, new GoodsRepositoryUserRowMapper());
	}

	@Override
	public List<GoodsRepositoryUser> getRepositoryUserByUserId(String repositoryCode, String userId) throws Exception {
		StringBuilder sql = new StringBuilder("select gru.*, wu.name as user_name, wu.telephone ");
		sql.append(" from tbl_goods_repository_user gru, tbl_web_userinfo wu ");
		sql.append(" where wu.id = ? and gru.status='1' and gru.user_code = wu.login_name ");
		return dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{userId}, new GoodsRepositoryUserRowMapper());
	}

	@Override
	public void removeRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryUser.getUpdateUser());
		params.add(repositoryUser.getId());
		super.deleteObj("tbl_goods_repository_user", params);
	}

	@Override
	public List<GoodsOrdinaryHouse> getRepositoryAssetByRepositoryList(
			String repositoryCodes) throws Exception {
		List<GoodsOrdinaryHouse> ohList = new ArrayList<GoodsOrdinaryHouse>();
		StringBuilder sql = new StringBuilder(OrdinaryHouseDAOImpl.SQL_SELECTION);
		sql.append(OrdinaryHouseDAOImpl.SQL_FROM_OWN);
		sql.append(OrdinaryHouseDAOImpl.SQL_FROM_LEFT_JOIN);
		sql.append("where ho.status = '1' and ho.repository_code in (").append(repositoryCodes).append(")");
        ohList = dataBean.getJdbcTemplate().query(sql.toString(), new OrdinaryHouseRowMapper());
        return ohList;
	}

}
