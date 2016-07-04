package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsChart1RowMapper;
import com.goodsquick.model.GoodsChartObj1;

@Repository("goodsDataDAO")
public class GoodsDataDAOImpl extends BaseDAOImpl implements GoodsDataDAO {

	@Override
	public List<GoodsChartObj1> getAssetYearData(String userCode) {
		List<GoodsChartObj1> chargtObjList = new ArrayList<GoodsChartObj1>();
        StringBuilder sql = new StringBuilder("select ho.yearDuration as xName,count(1) as yValue ");
        sql.append("from ( ");
        sql.append("	select case ");
        sql.append("	when year(curdate()) - finish_Year <= 5 then '0-5' ");
        sql.append("	when year(curdate()) - finish_Year > 5 and year(curdate()) - finish_Year <= 10 then '05-10' ");
        sql.append("	when year(curdate()) - finish_Year > 10 and year(curdate()) - finish_Year <= 15 then '10-15' ");
        sql.append("	when year(curdate()) - finish_Year > 15 and year(curdate()) - finish_Year <= 20 then '15-20' ");
        sql.append("	else '20+' ");
        sql.append("	end yearDuration ");
        sql.append("from  tbl_goods_ordinary_house_owned oh,tbl_goods_repository gr, tbl_goods_repository_user gru ");
        sql.append("where oh.repository_code = gr.repository_code ");
        sql.append("and gr.repository_code = gru.repository_code ");
        sql.append("and gru.user_code=?");
        sql.append(") ho group by ho.yearDuration ");
        
        chargtObjList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{userCode}, new GoodsChart1RowMapper());
        return chargtObjList;
	}
	
	@Override
	public List<GoodsChartObj1> getLiftYearData(String userCode) {
		List<GoodsChartObj1> chargtObjList = new ArrayList<GoodsChartObj1>();
		StringBuilder sql = new StringBuilder("select ho.yearDuration as xName,sum(lift_num) as yValue ");
		sql.append("from ( ");
		sql.append("	select case ");
		sql.append("	when year(curdate()) - finish_Year <= 5 then '0-5' ");
		sql.append("	when year(curdate()) - finish_Year > 5 and year(curdate()) - finish_Year <= 10 then '05-10' ");
		sql.append("	when year(curdate()) - finish_Year > 10 and year(curdate()) - finish_Year <= 15 then '10-15' ");
		sql.append("	when year(curdate()) - finish_Year > 15 and year(curdate()) - finish_Year <= 20 then '15-20' ");
		sql.append("	else '20+' ");
		sql.append("	end yearDuration, lift_num ");
		sql.append("from  tbl_goods_ordinary_house_owned oh,tbl_goods_repository gr, tbl_goods_repository_user gru ");
		sql.append("where oh.repository_code = gr.repository_code ");
		sql.append("and gr.repository_code = gru.repository_code ");
		sql.append("and gru.user_code=?");
		sql.append(") ho group by ho.yearDuration ");
		
		chargtObjList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{userCode}, new GoodsChart1RowMapper());
		return chargtObjList;
	}

	@Override
	public List<GoodsChartObj1> getDensityData(String userCode, String densityType) {
		List<GoodsChartObj1> chargtObjList = new ArrayList<GoodsChartObj1>();
        StringBuilder sql = new StringBuilder("select oh.location as xName, oh.").append(densityType).append(" as yValue ");
        sql.append("from  tbl_goods_ordinary_house_owned oh,tbl_goods_repository gr, tbl_goods_repository_user gru ");
        sql.append("where oh.repository_code = gr.repository_code ");
        sql.append("and gr.repository_code = gru.repository_code ");
        sql.append("and gru.user_code=?");
        
        chargtObjList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{userCode}, new GoodsChart1RowMapper());
        return chargtObjList;
	}


}
