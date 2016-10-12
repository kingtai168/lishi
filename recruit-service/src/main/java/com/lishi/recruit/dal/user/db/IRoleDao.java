package com.lishi.recruit.dal.user.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lishi.recruit.dal.user.model.Role;

/**
 * 
 * @author admin
 *
 */
public interface IRoleDao {
	
	/**
	 * 查询分页的Role列表
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Role> queryPageList(Map<String,Object> condition)throws SQLException;
	
	/**
	 * 根据条件查询所有记录条数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Integer queryTotalCount(Map<String,Object> condition)throws SQLException;
	
	/**
	 * 添加role
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public Boolean saveRole(Role role)throws SQLException;
	
	/**
	 * 修改Role
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public Boolean updateRole(Role role)throws SQLException;
	
	/**
	 * 查询角色下有多少用户
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public Integer queryUserCount(Map<String,Object> param)throws SQLException;
	
	/**
	 * 根据roleid删除Role
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public Boolean deleteByKey(Map<String,Object> param)throws SQLException;
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryAll();
	

}
