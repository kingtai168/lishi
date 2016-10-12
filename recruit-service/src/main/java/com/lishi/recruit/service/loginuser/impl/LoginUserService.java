package com.lishi.recruit.service.loginuser.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lishi.recruit.common.Page;
import com.lishi.recruit.dal.loginuser.ILoginUserDao;
import com.lishi.recruit.service.loginuser.ILoginUserService;
import com.lishi.recruit.service.loginuser.model.LoginUserDTO;

@Service
public class LoginUserService implements ILoginUserService {

	@Autowired
	private ILoginUserDao loginUserDao;
	
	@Override
	public LoginUserDTO login() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addLoginUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean registerUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public void findPageLoginUser(Page page) {
		
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("start", page.getStart());
		condition.put("limit", page.getLimit());
//		try {
//			List<Users>	userList =userDao.queryList(condition);
//			List dtoList = UserDTO.createDtos(userList);
//			int totalCount = userDao.queryCount(condition);
//			page.setRoot(dtoList);
//			page.setTotal(totalCount);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}
