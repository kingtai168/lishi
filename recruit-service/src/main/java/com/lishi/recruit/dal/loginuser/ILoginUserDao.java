package com.lishi.recruit.dal.loginuser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lishi.recruit.dal.loginuser.model.LoginUser;


/**
 * ILoginUserDao
 * @author zt
 *
 */
public interface ILoginUserDao
{      
	/**
	 * 登录
	 * @param user
	 * @return
	 * @throws SQLException
	 */
    public LoginUser login(LoginUser user)throws SQLException;
    
    
    /**
     * 分页查询用户
     * @param condition
     * @return
     * @throws SQLException
     */
    public List<LoginUser> queryList(Map<String, Object> condition)throws SQLException;
    
    /**
     * 根据条件查询出总记录条数
     * @param condition
     * @return
     * @throws SQLException
     */
    public Integer queryCount(Map<String, Object> condition)throws SQLException;
    
}
