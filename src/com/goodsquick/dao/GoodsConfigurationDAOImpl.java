package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsConfigurationRowMapper;
import com.goodsquick.model.GoodsConfiguration;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.utils.DataBean;

@Repository("goodsConfigurationDAO")
public class GoodsConfigurationDAOImpl implements GoodsConfigurationDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<GoodsConfiguration> getAllConfiguration() throws Exception {
		List<GoodsConfiguration> dtList = new ArrayList<GoodsConfiguration>();
        String sql = "select * from tbl_goods_config";
        dtList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsConfigurationRowMapper());
        return dtList;
	}

	@Override
	public boolean checkIfConfigExists(String configCode) throws Exception {
		String sql = "select count(1) from tbl_goods_config where config_code = ?";
		int count = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{configCode}, Integer.class);
		return count>0;
	}

	@Override
	public void saveConfiguration(GoodsConfiguration config, WebUserInfo userInfo) throws Exception {
		String sql = "insert into tbl_goods_config(id,config_code,config_value,createdate,create_user,updatedate,update_user) values(null,?,?,now(),?,now(),?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] {config.getConfigCode(),config.getConfigValue(),userInfo.getLoginName(),userInfo.getLoginName()});
	}

	@Override
	public void updateConfiguration(GoodsConfiguration config, WebUserInfo userInfo) throws Exception {
		String sql = "update tbl_goods_config set config_value = ?, updatedate=now(),update_user=? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { config.getConfigValue(), userInfo.getLoginName(),config.getId() });
	}

	@Override
	public void deleteConfiguration(GoodsConfiguration config) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public GoodsConfiguration getConfigByCode(String configCode)
			throws Exception {
        String sql = "select * from tbl_goods_config where config_code=?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{configCode}, new GoodsConfigurationRowMapper());
	}

}
