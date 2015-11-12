package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsRepositoryRowMapper;
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
        sql.append("set repository_name = ? ");
		sql.append("where repository_code = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(goodsRepository.getRepositoryName());
		params.add(goodsRepository.getRepositoryCode());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsRepository> getRepositoryByLoginName(String loginName) throws Exception {
		String sql = "select gr.* from tbl_goods_repository gr, tbl_goods_repository_user gru where gru.user_code = ? and gru.repository_code = gr.repository_code and gru.status = '1' and gr.status='1' ";
		return dataBean.getJdbcTemplate().query(sql, new Object[]{loginName}, new GoodsRepositoryRowMapper());
	}

	@Override
	public void updateRepositoryUser(GoodsRepositoryUser repositoryUser)
			throws Exception {
		// TODO Auto-generated method stub

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

}
