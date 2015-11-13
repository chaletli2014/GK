package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.DictionaryRowMapper;
import com.goodsquick.mapper.DictionaryTypeRowMapper;
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsDictionaryType;
import com.goodsquick.utils.DataBean;

@Repository("dictionaryDAO")
public class DictionaryDAOImpl implements DictionaryDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<GoodsDictionaryType> getAllDictionaryType() throws Exception {
		List<GoodsDictionaryType> dtList = new ArrayList<GoodsDictionaryType>();
        String sql = "select * from tbl_goods_dictionary_type";
        dtList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new DictionaryTypeRowMapper());
        return dtList;
	}

	@Override
	public List<GoodsDictionary> getAllDictionary() throws Exception {
		List<GoodsDictionary> dList = new ArrayList<GoodsDictionary>();
        String sql = "select gd.*,gdt.type_name from tbl_goods_dictionary gd, tbl_goods_dictionary_type gdt where gd.type_code = gdt.type_code";
        dList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new DictionaryRowMapper());
        return dList;
	}

	@Override
	public List<GoodsDictionary> getDictionaryByType(String type)
			throws Exception {
		List<GoodsDictionary> dList = new ArrayList<GoodsDictionary>();
        String sql = "select gd.*,gdt.type_name from tbl_goods_dictionary gd, tbl_goods_dictionary_type gdt where gd.type_code = gdt.type_code and gdt.type_code = ?";
        dList = dataBean.getJdbcTemplate().query(sql, new Object[]{type}, new DictionaryRowMapper());
        return dList;
	}

	@Override
	public boolean checkIfDictionaryTypeCodeOrNameExists(String typeCode, String name)
			throws Exception {
		String sql = "select count(1) from tbl_goods_dictionary_type where type_code = ? or type_name = ?";
		int count = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{typeCode, name}, Integer.class);
		return count>0;
	}

	@Override
	public boolean checkIfDictionaryExists(String typeCode, String name)
			throws Exception {
		String sql = "select count(1) from tbl_goods_dictionary where type_code = ? and dic_name = ? ";
		int count = dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{typeCode, name}, Integer.class);
		return count>0;
	}

	@Override
	public void saveDictionary(GoodsDictionary dictionary) throws Exception {
		String sql = "insert into tbl_goods_dictionary(id,type_code,dic_name,dic_code,dic_desc) values(null,?,?,?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] {dictionary.getTypeCode(),dictionary.getDicName(),dictionary.getDicCode(),dictionary.getDicDesc()});
	}

	@Override
	public void updateDictionary(GoodsDictionary dictionary) throws Exception {
		String sql = "update tbl_goods_dictionary set dic_name = ?, dic_desc = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { dictionary.getDicName(),dictionary.getDicDesc(),dictionary.getId() });
	}

	@Override
	public void deleteDictionary(GoodsDictionary dictionary) throws Exception {
		String sql = "delete from tbl_goods_dictionary where id=?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { dictionary.getId() });
	}

	@Override
	public void saveDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		String sql = "insert into tbl_goods_dictionary_type(id,type_name,type_code,type_desc) values (null,?,?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] {dictionaryType.getDtName(),dictionaryType.getDtCode(),dictionaryType.getDtDesc()});
	}

	@Override
	public void updateDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		String sql = "update tbl_goods_dictionary_type set type_name = ?, type_desc = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { dictionaryType.getDtName(),dictionaryType.getDtDesc(),dictionaryType.getId() });
	}

	@Override
	public void deleteDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		String sql = "delete from tbl_goods_dictionary_type where id=?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { dictionaryType.getId() });
	}

	@Override
	public GoodsDictionary getDictionaryByCode(String code) throws Exception {
        String sql = "select gd.*,gdt.type_name from tbl_goods_dictionary gd, tbl_goods_dictionary_type gdt where gd.type_code = gdt.type_code and gd.dic_code = ? and gd.type_code='serviceTypes' ";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{code}, new DictionaryRowMapper());
	}

}
