package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsHouseFileRowMapper;
import com.goodsquick.mapper.GoodsProductSourceRowMapper;
import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsProductSource;
import com.goodsquick.model.WebUserInfo;

@Repository("goodsSourceFileDAO")
public class GoodsSourceFileDAOImpl extends BaseDAOImpl implements GoodsSourceFileDAO {

	@Override
	public void saveGoodsHouseFile(GoodsHouseFile houseFile, WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_house_file(id,repository_code");
        sql.append(",file_type,file_name,file_path,is_main");
        sql.append(",create_date,create_user,update_date,update_user,status)");
		sql.append("values(null,?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(houseFile.getRepositoryCode());
		params.add(houseFile.getFileType());
		params.add(houseFile.getFileName());
		params.add(houseFile.getFilePath());
		params.add(houseFile.getIsMain());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateGoodsHouseFile(GoodsHouseFile houseFile,WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_house_file ");
        sql.append("set file_name = ?, is_main = ? ");
        sql.append(",updatedate = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(houseFile.getFileName());
		params.add(houseFile.getIsMain());
		params.add(currentUser.getLoginName());
		params.add(houseFile.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsHouseFile(GoodsHouseFile houseFile) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(houseFile.getUpdateUser());
		params.add(houseFile.getId());
		super.deleteObj("tbl_goods_house_file", params);
	}

	@Override
	public GoodsHouseFile getGoodsHouseFileById(int houseFileId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsHouseFile> getGoodsHouseFileByRepositoryCode(
			String repositoryCode) throws Exception {
		List<GoodsHouseFile> fileList = new ArrayList<GoodsHouseFile>();
		StringBuilder sql = new StringBuilder(90);
		sql.append(" select * from tbl_goods_house_file where repository_code = ? and status = '1' ");
		fileList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode}, new GoodsHouseFileRowMapper());
        return fileList;
	}

	@Override
	public void setMainPicOfGoodsHouse(int houseFileId, String repositoryCode, WebUserInfo currentUser)
			throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_house_file ");
		sql.append("set is_main = 0, update_date = now(),update_user = ? ");
		sql.append("where repository_code = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(repositoryCode);
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
		
		sql = new StringBuilder("update tbl_goods_house_file ");
        sql.append("set is_main = 1, update_date = now(),update_user = ? ");
		sql.append("where id = ? ");
		params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(houseFileId);
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
		
	}

	@Override
	public void saveGoodsProductSource(GoodsProductSource productSource,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("insert into tbl_goods_product_source(id,repository_code,tbl_goods_product_obj_id");
        sql.append(",file_type,file_name,file_path,is_main");
        sql.append(",create_date,create_user,update_date,update_user,status)");
		sql.append("values(null,?,?,?,?,?,?");
		sql.append(",now(),?,now(),?,'1')");

		List<Object> params = new ArrayList<Object>();
		params.add(productSource.getRepositoryCode());
		params.add(productSource.getGoodsProductObjId());
		params.add(productSource.getFileType());
		params.add(productSource.getFileName());
		params.add(productSource.getFilePath());
		params.add(productSource.getIsMain());
		params.add(currentUser.getLoginName());
		params.add(currentUser.getLoginName());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void updateGoodsProductSource(GoodsProductSource productSource,
			WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_product_source ");
        sql.append("set file_name = ?, is_main = ? ");
        sql.append(",updatedate = now(),update_user = ? ");
		sql.append("where id = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(productSource.getFileName());
		params.add(productSource.getIsMain());
		params.add(currentUser.getLoginName());
		params.add(productSource.getId());
		
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

	@Override
	public void deleteGoodsProductSource(GoodsProductSource productSource) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(productSource.getUpdateUser());
		params.add(productSource.getId());
		super.deleteObj("tbl_goods_product_source", params);
	}

	@Override
	public GoodsProductSource getGoodsProductSourceById(int productSourceId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsProductSource> getGoodsProductSourceByRCAndProductId(
			String repositoryCode, long productId) throws Exception {
		List<GoodsProductSource> fileList = new ArrayList<GoodsProductSource>();
		StringBuilder sql = new StringBuilder(90);
		sql.append(" select * from tbl_goods_product_source where repository_code = ? and tbl_goods_product_obj_id = ? and status = '1' ");
		fileList = dataBean.getJdbcTemplate().query(sql.toString(), new Object[]{repositoryCode,productId}, new GoodsProductSourceRowMapper());
        return fileList;
	}

	@Override
	public void setMainPicOfGoodsProduct(long productSourceId, long productId,
			String repositoryCode, WebUserInfo currentUser) throws Exception {
		StringBuilder sql = new StringBuilder("update tbl_goods_product_source ");
		sql.append("set is_main = 0, update_date = now(),update_user = ? ");
		sql.append("where repository_code = ? and tbl_goods_product_obj_id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(repositoryCode);
		params.add(productId);
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
		
		sql = new StringBuilder("update tbl_goods_product_source ");
        sql.append("set is_main = 1, update_date = now(),update_user = ? ");
		sql.append("where id = ? ");
		params = new ArrayList<Object>();
		params.add(currentUser.getLoginName());
		params.add(productSourceId);
		dataBean.getJdbcTemplate().update(sql.toString(), params.toArray());
	}

}
