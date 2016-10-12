package com.lishi.recruit.view.user.server;

import com.lishi.recruit.service.user.model.UserDTO;

public interface IUserViewService {

	/**
	 * 登录方法
	 * @param userName
	 * @param password
	 * @return UserDTO
	 */
	public UserDTO login(String userName,String password);
}
