package com.goodsquick.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.goodsquick.mapper.GoodsPrivilegeRowMapper;
import com.goodsquick.mapper.GoodsRoleRowMapper;
import com.goodsquick.model.GoodsPrivilege;
import com.goodsquick.model.GoodsRole;
import com.goodsquick.utils.DataBean;

@Repository("systemManagerDAO")
public class SystemManagerDAOImpl implements SystemManagerDAO {

	@Autowired
	@Qualifier("dataBean")
	private DataBean dataBean;
	
	@Override
	public List<GoodsRole> getAllRoles() throws Exception {
		List<GoodsRole> dtList = new ArrayList<GoodsRole>();
        String sql = "select gr.* from tbl_goods_role gr";
        dtList = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsRoleRowMapper());
        return dtList;
	}

	@Override
	public List<GoodsRole> getRolesByUserId(String userId) throws Exception {
		List<GoodsRole> dtList = new ArrayList<GoodsRole>();
        String sql = "select gr.* from tbl_goods_role gr, tbl_goods_user_role ur where gr.role_code = ur.role_code and ur.user_id = ?";
        dtList = dataBean.getJdbcTemplate().query(sql, new Object[]{userId}, new GoodsRoleRowMapper());
        return dtList;
	}

	@Override
	public List<GoodsPrivilege> getAllPrivileges() throws Exception {
		List<GoodsPrivilege> list = new ArrayList<GoodsPrivilege>();
        String sql = "select * from tbl_goods_privilege";
        list = dataBean.getJdbcTemplate().query(sql, new Object[]{}, new GoodsPrivilegeRowMapper());
        return list;
	}

	@Override
	public List<GoodsPrivilege> getPrivsByRoleCode(String roleCode)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void insertGoodsRole(GoodsRole goodsRole) throws Exception {
		String sql = "insert into tbl_goods_role(id,role_code,role_name,role_desc) values(null,?,?,?)";
        dataBean.getJdbcTemplate().update(sql, new Object[] { goodsRole.getRoleCode(),goodsRole.getRoleName(),goodsRole.getRoleDesc() });
	}

	@Override
	public void updateGoodsRole(GoodsRole goodsRole) throws Exception {
		String sql = "update tbl_goods_role set role_desc = ? where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { goodsRole.getRoleDesc(),goodsRole.getId() });
	}

	@Override
	public void deleteRole(int roleId) throws Exception {
		String sql = "delete from tbl_goods_role where id = ?";
        dataBean.getJdbcTemplate().update(sql, new Object[] { roleId });
	}

	@Override
	public GoodsRole getRoleById(int roleId) throws Exception {
		String sql = "select * from tbl_goods_role where id = ?";
        return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{roleId}, new GoodsRoleRowMapper());
	}
	
	@Override
	public void insertGoodsPrivilege(GoodsPrivilege goodsPriv) throws Exception {
		String sql = "insert into tbl_goods_privilege(id,privilege_code,privilege_name,privilege_desc,create_time,create_user,update_time,update_user) values(null,?,?,?,now(),'sys',now(),'sys')";
		dataBean.getJdbcTemplate().update(sql, new Object[] { goodsPriv.getPrivilegeCode(),goodsPriv.getPrivilegeName(),goodsPriv.getPrivilegeDesc() });
	}
	
	@Override
	public void updateGoodsPrivilege(GoodsPrivilege goodsPriv) throws Exception {
		String sql = "update tbl_goods_privilege set privilege_desc = ? where id = ?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { goodsPriv.getPrivilegeDesc(),goodsPriv.getId() });
	}
	
	@Override
	public void deletePrivilege(int privId) throws Exception {
		String sql = "delete from tbl_goods_privilege where id = ?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { privId });
	}
	
	@Override
	public GoodsPrivilege getPrivilegeById(int privId) throws Exception {
		String sql = "select * from tbl_goods_privilege where id = ?";
		return dataBean.getJdbcTemplate().queryForObject(sql, new Object[]{privId}, new GoodsPrivilegeRowMapper());
	}

	@Override
	public void deleteRoleOfUser(String userId) throws Exception {
		String sql = "delete from tbl_goods_user_role where user_id = ?";
		dataBean.getJdbcTemplate().update(sql, new Object[] { userId });
	}

	@Override
	public void insertRoleOfUser(String userId, List<String> roleCodes)
			throws Exception {
		StringBuilder sb = new StringBuilder("");
		sb.append(" insert into tbl_goods_user_role(id,role_code,user_id,create_time,create_user) values ");
		
		for( String roleCode : roleCodes ){
			sb.append(" (null,'").append(roleCode).append("',").append(userId).append(",now(),'sys' ),");
		}
		
		dataBean.getJdbcTemplate().update(sb.substring(0, sb.length()-1));
	}

}
