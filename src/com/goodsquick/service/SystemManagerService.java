package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsPrivilege;
import com.goodsquick.model.GoodsRole;

public interface SystemManagerService {

	public List<GoodsRole> getAllRoles() throws Exception;
	
	public List<GoodsRole> getRolesByUserId(String userId) throws Exception;
	
	public List<GoodsPrivilege> getAllPrivileges() throws Exception;
	
	public List<GoodsPrivilege> getPrivsByRoleCode(String roleCode) throws Exception;
	
	
	public void saveOrUpdateRole(GoodsRole goodsRole) throws Exception;
	public void deleteRole(int roleId) throws Exception;
	public GoodsRole getRoleById(int roleId) throws Exception;
	
	public void saveOrUpdatePrivilege(GoodsPrivilege goodsPriv) throws Exception;
	public void deletePrivilege(int PrivId) throws Exception;
	public GoodsPrivilege getPrivilegeById(int PrivId) throws Exception;
	
	public void saveUserRole(String userId, List<String> roleCodes) throws Exception;
}
