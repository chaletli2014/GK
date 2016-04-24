package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsCompanyInfoRowMapper;
import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.utils.GoodsQuickStringUtils;

@Repository("goodsRadarDAO")
public class GoodsRadarDAOImpl extends BaseDAOImpl implements GoodsRadarDAO {

	@Override
	public List<GoodsCompanyInfo> getCompanyInfoByLiftBrands(List<String> liftBrands)
			throws Exception {
		List<GoodsCompanyInfo> companyList = new ArrayList<GoodsCompanyInfo>();
		StringBuilder sql = new StringBuilder("");
		sql.append(" select company.* ");
		sql.append(" from tbl_goods_company company, tbl_goods_product_lift pl ");
		sql.append(" where pl.liftBrand in (");
		sql.append(GoodsQuickStringUtils.getInParameterByList(liftBrands));
		sql.append(") and pl.create_user = company.create_user");
        companyList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{}, new GoodsCompanyInfoRowMapper());
        return companyList;
	}

}
