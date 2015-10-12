package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsChart1RowMapper;
import com.goodsquick.mapper.GoodsChart2RowMapper;
import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsChartObj2;
import com.goodsquick.utils.DataBean;

@Repository("goodsChartDAO")
public class GoodsChartDAOImpl implements GoodsChartDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<GoodsChartObj1> getHouseVolumePerYear() throws Exception {
		List<GoodsChartObj1> ohList = new ArrayList<GoodsChartObj1>();
        String sql = "select start_year as xName,count(1) as yValue from tbl_goods_ordinary_house group by start_year";
        ohList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsChart1RowMapper());
        return ohList;
	}

	@Override
	public List<GoodsChartObj1> getliftArea() throws Exception {
		List<GoodsChartObj1> ohList = new ArrayList<GoodsChartObj1>();
        String sql = "select oh.city as xName,sum(hd.device_num) as yValue from tbl_goods_ordinary_house oh, tbl_goods_house_device hd where oh.house_code = hd.house_code and oh.status='1' and hd.device_type = 'lift_system' group by oh.city";
        ohList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsChart1RowMapper());
        return ohList;
	}

	@Override
	public List<GoodsChartObj1> getliftBrandDetail(String city) throws Exception {
		List<GoodsChartObj1> ohList = new ArrayList<GoodsChartObj1>();
        String sql = "select hd.brand_name as xName,sum(hd.device_num) as yValue from tbl_goods_ordinary_house oh, tbl_goods_house_device hd where oh.house_code = hd.house_code and oh.status='1' and oh.city=? and hd.device_type = 'lift_system' group by hd.brand_name order by sum(hd.device_num) desc limit 10";
        ohList = dataBean.getJdbcTemplate().query(sql, new Object[]{city}, new GoodsChart1RowMapper());
        return ohList;
	}

	@Override
	public List<GoodsChartObj2> getliftSalesDetail() throws Exception {
		List<GoodsChartObj2> ohList = new ArrayList<GoodsChartObj2>();
		StringBuilder sql = new StringBuilder();
		sql.append("select oh.start_year,hd.brand_name,sum(hd.device_num) as device_num ");
		sql.append("from tbl_goods_ordinary_house oh, tbl_goods_house_device hd ");
		sql.append("where oh.house_code = hd.house_code and oh.status='1' ");
		sql.append("and hd.brand_name is not null and hd.brand_name != '' ");
		sql.append("and hd.device_type = 'lift_system' ");
		sql.append("group by oh.start_year,hd.brand_name ");
        ohList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{}, new GoodsChart2RowMapper());
        return ohList;
	}

}
