package com.lishi.recruit.view.user.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lishi.recruit.service.user.IUserService;
import com.lishi.recruit.service.user.model.UserDTO;
import com.lishi.recruit.view.user.server.IUserViewService;

@Component
public class UserViewServiceImpl implements IUserViewService {

	@Autowired
	private IUserService userService;
	
	@Override
	public UserDTO login(String userName, String password) {
		
		//加密
//		String pwd = EncryptUtil.getEncryptedPwd(password);
		return null;
	}

}
