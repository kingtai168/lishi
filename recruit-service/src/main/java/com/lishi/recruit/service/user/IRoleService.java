package com.lishi.recruit.service.user;

import java.util.List;

import com.lishi.recruit.common.ComboData;
import com.lishi.recruit.common.Page;
import com.lishi.recruit.service.user.model.RoleDTO;

public interface IRoleService {

	/**
	 * 分页查询角色列表
	 */
	public void findPageRole(Page page);
	
	/**
	 * 
	 * 保存角色
	 * @param dto
	 */
	public Boolean saveRole(RoleDTO dto);
	
	/**
	 * 修改角色
	 * @param dto
	 */
	public Boolean updateRole(RoleDTO dto);
	
	
	
	/**
	 * 删除角色
	 */
	public Boolean deleteRole(String roleid);
	
	/**
	 * 角色下拉数据
	 */
	
	public List<ComboData> findRoleType();
	
	/**
	 * 查询角色菜单
	 */
	public List findRoleMenu(String roleid);
	
	/**
	 * 保存角色权限
	 */
	public void saveRoleMenu(String roleid, String menuids);
	
}
