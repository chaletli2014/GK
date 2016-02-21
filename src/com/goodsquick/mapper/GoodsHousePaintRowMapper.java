package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsHousePaint;

public class GoodsHousePaintRowMapper implements RowMapper<GoodsHousePaint>{

	@Override
	public GoodsHousePaint mapRow(ResultSet rs, int arg1) throws SQLException {
		GoodsHousePaint housePaint = new GoodsHousePaint();
		housePaint.setId(rs.getInt("id"));
		housePaint.setType1Code(rs.getString("type1_code"));
		housePaint.setType2Code(rs.getString("type2_code"));
		housePaint.setPaintName(rs.getString("paint_name"));
		housePaint.setPaintDesc(rs.getString("paint_desc"));
		housePaint.setSubjectId(rs.getInt("subject_id"));
		housePaint.setSubjectName(rs.getString("subject_name"));
		housePaint.setModuleId(rs.getInt("module_id"));
		housePaint.setModuleName(rs.getString("module_name"));
		housePaint.setPaintBrand(rs.getString("paint_brand"));
		housePaint.setPaintModel(rs.getString("paint_model"));
		housePaint.setPaintStyle(rs.getString("paint_style"));
		housePaint.setLiquidType(rs.getString("liquid_type"));
		housePaint.setFilmFormer(rs.getString("film_former"));
		housePaint.setPaintStorage(rs.getString("paint_storage"));
		housePaint.setSolidProportion(rs.getDouble("solid_proportion"));
		housePaint.setBrushArea(rs.getDouble("brush_area"));
		housePaint.setTensileStrength(rs.getDouble("tensile_strength"));
		housePaint.setBreakElongation(rs.getDouble("break_elongation"));
		housePaint.setSurfaceDryTime(rs.getString("surface_dryTime"));
		housePaint.setDryingTime(rs.getString("drying_time"));
		housePaint.setCoatingFilmColor(rs.getString("coating_filmColor"));
		housePaint.setOriginPlace(rs.getString("origin_place"));
		housePaint.setRepositoryCode(rs.getString("repository_code"));
		housePaint.setCreateDate(rs.getDate("create_date"));
		housePaint.setCreateUser(rs.getString("create_user"));
		housePaint.setUpdateDate(rs.getDate("update_date"));
		housePaint.setUpdateUser(rs.getString("update_user"));
		housePaint.setStatus(rs.getString("status"));
		return housePaint;
	}
}
