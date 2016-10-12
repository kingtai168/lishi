package com.lishi.recruit.dal.user.db.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lishi.recruit.dal.user.db.IMenuDao;
import com.lishi.recruit.dal.user.model.Menu;
import com.lishi.recruit.dal.user.model.RoleMenu_temp;
import com.lishi.recruit.dbaccess.orm.mybatis.impl.BaseDaoImpl;

@Repository
public class MenuDaoImpl extends BaseDaoImpl<RoleMenu_temp> implements IMenuDao {

	@Override
	public List<Menu> queryMenuList(){
		
		return sqlSessionTemplate.selectList("Menu.queryAllMenuList");
	}

	@Override
	public List<RoleMenu_temp> queryRoleMenuList(String roleid){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleid", roleid);
		return sqlSessionTemplate.selectList("Menu.queryListByRoleId",param);
	}

}
