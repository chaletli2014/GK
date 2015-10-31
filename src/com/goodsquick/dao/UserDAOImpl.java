package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsCompanyInfoRowMapper;
import com.goodsquick.mapper.WebUserInfoRowMapper;
import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.DataBean;
import com.goodsquick.utils.GoodsJDBCTemplate;

/**
 * @author Chalet
 * 类说明
 */

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	private Logger logger = Logger.getLogger(UserDAOImpl.class);
	
    public WebUserInfo getWebUser(String username, String password) throws Exception {
        WebUserInfo userInfo = new WebUserInfo();
        String sql = "select * from tbl_web_userinfo where login_name = ? and password = ? and status = '1' ";
        userInfo = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{username, password}, new WebUserInfoRowMapper());
        return userInfo;
    }
    
    @Override
	public List<WebUserInfo> getAllUserByQueryInfo(String queryCondition, Object[] queryParam) throws Exception{
        String sql = new StringBuilder(" select * from tbl_web_userinfo ").append(queryCondition).append(" and status = '1' ").toString();
        return dataBean.getJdbcTemplate().query(sql, queryParam, new WebUserInfoRowMapper());
    }

	@Override
	public WebUserInfo getUserProfileById(String userId) throws Exception {
		WebUserInfo userInfo = new WebUserInfo();
        String userSql = "select * from tbl_web_userinfo where id = ? ";
        userInfo = dataBean.getJdbcTemplate().queryForObject(userSql, new Object[]{userId}, new WebUserInfoRowMapper());
        return userInfo;
	}

	@Override
	public int addUserInfo(WebUserInfo userInfo) throws Exception {
		final StringBuilder sql = new StringBuilder(200);
		sql.append(" insert into tbl_web_userinfo( ");
		sql.append(" id,name,login_name,password,telephone,level,createdate,updatedate,last_login_time,status,has_house,has_service) ");
		sql.append(" values(null,?,?,?,?,?,now(),now(),now(),'1','on','on') ");
		List<String> params = new ArrayList<String>();
		params.add(userInfo.getName());
		params.add(userInfo.getLoginName());
        params.add(userInfo.getPassword());
        params.add(userInfo.getTelephone());
        params.add(userInfo.getLevel());
		return GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
	
	@Override
	public void updateUserInfo(WebUserInfo userInfo) throws Exception {
		String sql = "update tbl_web_userinfo set name=?, login_name=?, password=?, level=?, has_house=?, has_service=? where id=?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { userInfo.getName(), userInfo.getLoginName(), userInfo.getPassword(), userInfo.getLevel(), userInfo.getHasHouse(), userInfo.getHasService(), userInfo.getId() });
	}
	
	@Override
	public int addCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception {
		final StringBuilder sql = new StringBuilder(200);
		sql.append(" insert into tbl_goods_company( ");
		sql.append(" id,industry,company_name,company_province,company_city,company_email,createdate,updatedate,create_user,update_user) ");
		sql.append(" values(null,?,?,?,?,?,now(),now(),?,?) ");
		List<String> params = new ArrayList<String>();
		params.add(companyInfo.getIndustry());
		params.add(companyInfo.getCompanyName());
		params.add(companyInfo.getCompanyProvince());
		params.add(companyInfo.getCompanyCity());
		params.add(companyInfo.getCompanyEmail());
		params.add(companyInfo.getCreateUser());
		params.add(companyInfo.getUpdateUser());
		return GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
	
	@Override
	public void updateCompanyInfo(GoodsCompanyInfo companyInfo) throws Exception {
		StringBuilder sql = new StringBuilder(210);
		sql.append(" update tbl_goods_company ");
		sql.append(" set company_name=?, ");
		sql.append(" company_email=?, ");
		sql.append(" updatedate=now(), ");
		sql.append(" update_user=? ");
		sql.append(" where id=? ");
		dataBean.getJdbcTemplate().update(sql.toString(), new Object[] { companyInfo.getCompanyName()
			, companyInfo.getCompanyEmail()
			, companyInfo.getUpdateUser()
			, companyInfo.getId()});
	}
	
	@Override
	public void disableUser(String userId) throws Exception {
		String sql = "update tbl_web_userinfo set status='0' where id=?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { userId });
	}

	@Override
	public WebUserInfo getUserProfileByLoginName(String loginName)
			throws Exception {
		WebUserInfo userInfo = new WebUserInfo();
        String sql = "select * from tbl_web_userinfo where login_name = ? ";
        userInfo = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{loginName}, new WebUserInfoRowMapper());
        return userInfo;
	}

	@Override
	public int getMessageNumByLoginName(String loginName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from tbl_goods_sp_request sr, tbl_goods_ordinary_house oh ");
		sql.append(" where sr.customer_name = oh.building_name ");
		sql.append(" and oh.create_user = ? ");
		sql.append(" and sr.status = '1' ");
        return dataBean.getJdbcTemplate().queryForObject(sql.toString(), new Object[]{loginName}, Integer.class);
	}

	@Override
	public GoodsCompanyInfo getCompanyInfoByUserID(int userId) throws Exception {
		String companySql = "select c.* from tbl_goods_company c, tbl_goods_company_user cu where cu.user_id=? and cu.company_id = c.id";
		return dataBean.getJdbcTemplate().queryForObject(companySql, new Object[]{userId}, new GoodsCompanyInfoRowMapper());
	}

	@Override
	public void insertCompanyUserRelationship(int companyId, int userId, String loginName)
			throws Exception {
		final StringBuilder sql = new StringBuilder(200);
		sql.append(" insert into tbl_goods_company_user( ");
		sql.append(" id,company_id,user_id,createdate,updatedate,create_user,update_user) ");
		sql.append(" values(null,?,?,now(),now(),?,?) ");
		List<String> params = new ArrayList<String>();
		params.add(String.valueOf(companyId));
		params.add(String.valueOf(userId));
		params.add(loginName);
		params.add(loginName);
		GoodsJDBCTemplate.executeSQL(dataBean, sql, params);
	}
}
