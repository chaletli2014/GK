package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsPrivilege;
import com.goodsquick.model.GoodsRole;

public interface SystemManagerDAO {

	public List<GoodsRole> getAllRoles() throws Exception;
	
	public List<GoodsRole> getRolesByUserId(String userId) throws Exception;
	
	public List<GoodsPrivilege> getAllPrivileges() throws Exception;
	
	public List<GoodsPrivilege> getPrivsByRoleCode(String roleCode) throws Exception;
	
	
	public void insertGoodsRole(GoodsRole goodsRole) throws Exception;
	public void updateGoodsRole(GoodsRole goodsRole) throws Exception;
	public void deleteRole(int roleId) throws Exception;
	public GoodsRole getRoleById(int roleId) throws Exception;
	
	public void insertGoodsPrivilege(GoodsPrivilege goodsPriv) throws Exception;
	public void updateGoodsPrivilege(GoodsPrivilege goodsPriv) throws Exception;
	public void deletePrivilege(int privId) throws Exception;
	public GoodsPrivilege getPrivilegeById(int privId) throws Exception;
	
	public void deleteRoleOfUser(String userId) throws Exception;
	public void insertRoleOfUser(String userId, List<String> roleCodes) throws Exception;
}
