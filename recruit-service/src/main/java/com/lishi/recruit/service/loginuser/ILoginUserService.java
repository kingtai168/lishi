package com.lishi.recruit.service.loginuser;

import java.util.List;

import com.lishi.recruit.common.Page;
import com.lishi.recruit.service.loginuser.model.LoginUserDTO;

/**
 * 
 * @author zt
 *
 */
public interface ILoginUserService {
	
	/**
	 * 登录
	 * @return
	 */
	public LoginUserDTO login();

	/**
	 * 后台添加用户
	 * @return
	 */
	public Boolean addLoginUser();
	
	/**
	 * 前台注册用户
	 * @return
	 */
	public Boolean registerUser();
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public Boolean updateUser();
	
	/**
	 * 删除用户信息
	 * @return
	 */
	public Boolean deleteUser();
	
	/**
	 * 查询用户列表
	 * @param loginUser
	 * @return
	 */
	public void findPageLoginUser(Page page);
	

}
