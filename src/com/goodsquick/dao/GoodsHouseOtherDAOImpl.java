package com.goodsquick.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseOtherRowMapper;
import com.goodsquick.model.GoodsHouseOther;

@Repository("goodsHouseOtherDAO")
public class GoodsHouseOtherDAOImpl extends BaseDAOImpl implements GoodsHouseOtherDAO {

	@Override
	public int saveHouseOther(final GoodsHouseOther houseOther) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		final StringBuilder sql = new StringBuilder(300);
		sql.append("insert into tbl_goods_house_other");
		sql.append("(id,type_code,other_name,other_desc,subjectId,moduleId ");
		sql.append(",create_date,create_user,update_date,update_user,status ) ");
		sql.append("values(null,?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");
		
		dataBean.getJdbcTemplate().update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, houseOther.getTypeCode());
                ps.setString(i++, houseOther.getName());
                ps.setString(i++, houseOther.getDesc());
                ps.setInt(i++, houseOther.getSubjectId());
                ps.setInt(i++, houseOther.getModuleId());
                ps.setString(i++, houseOther.getCreateUser());
                ps.setString(i++, houseOther.getUpdateUser());
                return ps;
            }
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateHouseOther(GoodsHouseOther houseOther)
			throws Exception {
		StringBuilder sql = new StringBuilder(200);
		sql.append("update tbl_goods_house_other ");
        sql.append("set name = ?, desc = ?, subjectId = ?, moduleId = ?");
        sql.append(",update_date = ?,update_user = ? where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(houseOther.getName());
		params.add(houseOther.getDesc());
		params.add(houseOther.getSubjectId());
		params.add(houseOther.getModuleId());
		params.add(houseOther.getUpdateDate());
		params.add(houseOther.getUpdateUser());
		params.add(houseOther.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public List<GoodsHouseOther> getAllHouseOtherByRepositoryCode(String repositoryCode) throws Exception {
		List<GoodsHouseOther> ohOtherList = new ArrayList<GoodsHouseOther>();
		StringBuilder sql = new StringBuilder(500);
		sql.append(" select ho.id,ho.type_code,gd.dic_name as type_name, ho.other_name");
		sql.append(" ,ho.other_desc,ho.subjectId,ho.moduleId");
		sql.append(" ,ho.create_user,ho.create_date,ho.update_user,ho.update_date,ho.status");
		sql.append(" ,hs.subject_name as subjectName,sm.module_name as moduleName ");
		sql.append(" from tbl_goods_house_other ho ");
		sql.append(" left join tbl_goods_house_subject hs on ho.subjectId = hs.id ");
		sql.append(" left join tbl_goods_house_subject_module sm on ho.moduleId = sm.id ");
		sql.append(" left join tbl_goods_dictionary gd on ho.type_code = gd.dic_code ");
		sql.append(" where hs.repository_code = ? and ho.status = '1'");
		ohOtherList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsHouseOtherRowMapper());
		return ohOtherList;
	}

	@Override
	public GoodsHouseOther getOtherInfoById(int deviceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHouseOther(GoodsHouseOther obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}