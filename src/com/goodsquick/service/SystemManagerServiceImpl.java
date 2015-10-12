package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodsquick.dao.SystemManagerDAO;
import com.goodsquick.model.GoodsPrivilege;
import com.goodsquick.model.GoodsRole;

@Service("systemManagerService")
public class SystemManagerServiceImpl implements SystemManagerService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("systemManagerDAO")
	private SystemManagerDAO systemManagerDAO;
	
	@Override
	public List<GoodsRole> getAllRoles() throws Exception {
		try{
			return systemManagerDAO.getAllRoles();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the roles,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsRole> getRolesByUserId(String userId) throws Exception {
		try{
			return systemManagerDAO.getRolesByUserId(userId);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error(String.format("fail to get the roles by user id %s,", userId),e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsPrivilege> getAllPrivileges() throws Exception {
		try{
			return systemManagerDAO.getAllPrivileges();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get all the privilege,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsPrivilege> getPrivsByRoleCode(String roleCode)	throws Exception {
		try{
			return systemManagerDAO.getPrivsByRoleCode(roleCode);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error(String.format("fail to get the privilege by roleCode %s,",roleCode),e);
            return Collections.emptyList();
        }
	}

	@Override
	public void saveOrUpdateRole(GoodsRole goodsRole) throws Exception {
		if( null == goodsRole ){
			logger.error("goods role is null!");
		}else{
			int roleId = goodsRole.getId();
			if( roleId != -1 ){
				systemManagerDAO.updateGoodsRole(goodsRole);
			}else{
				systemManagerDAO.insertGoodsRole(goodsRole);
			}
		}
		
	}

	@Override
	public void deleteRole(int roleId) throws Exception {
		systemManagerDAO.deleteRole(roleId);
	}

	@Override
	public GoodsRole getRoleById(int roleId) throws Exception {
		return systemManagerDAO.getRoleById(roleId);
	}
	
	@Override
	public void saveOrUpdatePrivilege(GoodsPrivilege goodsPrivilege) throws Exception {
		if( null == goodsPrivilege ){
			logger.error("goods Privilege is null!");
		}else{
			int roleId = goodsPrivilege.getId();
			if( roleId != -1 ){
				systemManagerDAO.updateGoodsPrivilege(goodsPrivilege);
			}else{
				systemManagerDAO.insertGoodsPrivilege(goodsPrivilege);
			}
		}
		
	}
	
	@Override
	public void deletePrivilege(int privId) throws Exception {
		systemManagerDAO.deletePrivilege(privId);
	}
	
	@Override
	public GoodsPrivilege getPrivilegeById(int privId) throws Exception {
		return systemManagerDAO.getPrivilegeById(privId);
	}

	@Override
	@Transactional
	public void saveUserRole(String userId, List<String> roleCodes) throws Exception {
		systemManagerDAO.deleteRoleOfUser(userId);
		
		if( null != roleCodes && roleCodes.size() > 0 ){
			systemManagerDAO.insertRoleOfUser(userId, roleCodes);
		}
	}

}
