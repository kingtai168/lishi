package com.lishi.recruit.dal.user.db.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lishi.recruit.dal.user.db.IRoleDao;
import com.lishi.recruit.dal.user.model.Role;
import com.lishi.recruit.dbaccess.orm.mybatis.impl.BaseDaoImpl;

@Component
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

	@Override
	public List<Role> queryPageList(Map<String, Object> condition)
			throws SQLException {
		return sqlSessionTemplate.selectList("Role.queryRoleList", condition);
	}

	@Override
	public Integer queryTotalCount(Map<String, Object> condition)
			throws SQLException {
		return sqlSessionTemplate.selectOne("Role.queryTotalCont",condition);
	}

	@Override
	public Boolean saveRole(Role role) throws SQLException {
		
		return sqlSessionTemplate.insert("Role.insert",role)>0?true:false;
	}

	@Override
	public Boolean updateRole(Role role) throws SQLException {
		return sqlSessionTemplate.insert("Role.update",role)>0?true:false;
	}

	@Override
	public Integer queryUserCount(Map<String, Object> param)
			throws SQLException {
		return sqlSessionTemplate.selectOne("Role.queryUserCount",param);
	}

	@Override
	public Boolean deleteByKey(Map<String, Object> param) throws SQLException {
		return sqlSessionTemplate.delete("Role.delete",param)>0?true:false;
	}

	@Override
	public List<Role> queryAll() {
		return sqlSessionTemplate.selectList("Role.queryList");
	}

}
