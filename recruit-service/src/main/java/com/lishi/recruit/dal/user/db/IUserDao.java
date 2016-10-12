package com.lishi.recruit.dal.user.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lishi.recruit.dal.user.model.Users;
import com.lishi.recruit.dal.user.model.VuserMenu;


/**
 * UserDao
 * @author zt
 *
 */
public interface IUserDao
{      
	/**
	 * 登录
	 * @param user
	 * @return
	 * @throws SQLException
	 */
    public Users login(Users user)throws SQLException;
    
    /**
     * 查询菜单列表
     * @param user
     * @return
     * @throws SQLException
     */
    public List<VuserMenu> queryMenuList(Users user)throws SQLException;
    
    /**
     * 分页查询用户
     * @param condition
     * @return
     * @throws SQLException
     */
    public List<Users> queryList(Map<String, Object> condition)throws SQLException;
    
    /**
     * 根据条件查询出总记录条数
     * @param condition
     * @return
     * @throws SQLException
     */
    public Integer queryCount(Map<String, Object> condition)throws SQLException;
    
}
