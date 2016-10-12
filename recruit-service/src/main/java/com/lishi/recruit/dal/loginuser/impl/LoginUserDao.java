package com.lishi.recruit.dal.loginuser.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lishi.recruit.dal.loginuser.ILoginUserDao;
import com.lishi.recruit.dal.loginuser.model.LoginUser;

@Repository
public class LoginUserDao implements ILoginUserDao {

	@Override
	public LoginUser login(LoginUser user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LoginUser> queryList(Map<String, Object> condition)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryCount(Map<String, Object> condition)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
