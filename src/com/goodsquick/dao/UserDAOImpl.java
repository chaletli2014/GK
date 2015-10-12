package com.goodsquick.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.WebUserInfoRowMapper;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.DataBean;

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
        String sql = "select * from tbl_web_userinfo where id = ? ";
        userInfo = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{userId}, new WebUserInfoRowMapper());
        return userInfo;
	}

	@Override
	public void addUserInfo(WebUserInfo userInfo) throws Exception {
		String sql = "insert into tbl_web_userinfo(id,name,login_name,password,telephone,level,createdate,updatedate,last_login_time,status,has_house,has_service) values(null,?,?,?,?,?,now(),now(),now(),'1',?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] { userInfo.getName(), userInfo.getLoginName(), userInfo.getPassword(), userInfo.getTelephone(), userInfo.getLevel(), userInfo.getHasHouse(), userInfo.getHasService() });
	}
	
	@Override
	public void updateUserInfo(WebUserInfo userInfo) throws Exception {
		String sql = "update tbl_web_userinfo set name=?, login_name=?, password=?, level=?, has_house=?, has_service=? where id=?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { userInfo.getName(), userInfo.getLoginName(), userInfo.getPassword(), userInfo.getLevel(), userInfo.getHasHouse(), userInfo.getHasService(), userInfo.getId() });
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
}
