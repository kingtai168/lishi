package com.lishi.recruit.dal.user.db.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lishi.recruit.dal.user.db.IUserDao;
import com.lishi.recruit.dal.user.model.Users;
import com.lishi.recruit.dal.user.model.VuserMenu;
import com.lishi.recruit.dbaccess.orm.mybatis.impl.BaseDaoImpl;

@Component
public class UserDaoImpl extends BaseDaoImpl<Users> implements IUserDao {

	@Override
	public Users login(Users user) throws SQLException {
		return sqlSessionTemplate.selectOne("User.login", user);
	}

	@Override
	public List<VuserMenu> queryMenuList(Users user) throws SQLException {
		return sqlSessionTemplate.selectList("User.queryMenuList", user);
	}

	@Override
	public List<Users> queryList(Map<String, Object> condition) throws SQLException {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("User.queryList", condition);
	}

	@Override
	public Integer queryCount(Map<String, Object> condition)
			throws SQLException {
		return sqlSessionTemplate.selectOne("User.queryTotalCount",condition);
	}


}
