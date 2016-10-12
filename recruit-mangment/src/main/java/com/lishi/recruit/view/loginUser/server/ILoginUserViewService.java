package com.lishi.recruit.view.loginUser.server;

import com.lishi.recruit.view.loginuser.model.LoginUserVO;

public interface ILoginUserViewService {

	/**
	 * 登录方法
	 * @param userName
	 * @param password
	 * @return UserDTO
	 */
	public LoginUserVO addLoginUser(String userName,String password);
}
