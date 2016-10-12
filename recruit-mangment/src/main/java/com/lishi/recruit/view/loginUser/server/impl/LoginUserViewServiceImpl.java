package com.lishi.recruit.view.loginUser.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lishi.recruit.service.user.IUserService;
import com.lishi.recruit.view.loginUser.server.ILoginUserViewService;
import com.lishi.recruit.view.loginuser.model.LoginUserVO;

@Component
public class LoginUserViewServiceImpl implements ILoginUserViewService {

	@Autowired
	private IUserService userService;

	@Override
	public LoginUserVO addLoginUser(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
