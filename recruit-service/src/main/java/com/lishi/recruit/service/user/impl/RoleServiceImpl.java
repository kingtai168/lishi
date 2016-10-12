package com.lishi.recruit.service.user.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lishi.recruit.common.ComboData;
import com.lishi.recruit.common.Page;
import com.lishi.recruit.common.TreeNodeChecked;
import com.lishi.recruit.dal.user.db.IMenuDao;
import com.lishi.recruit.dal.user.db.IRoleDao;
import com.lishi.recruit.dal.user.model.Menu;
import com.lishi.recruit.dal.user.model.Role;
import com.lishi.recruit.dal.user.model.RoleMenu_temp;
import com.lishi.recruit.service.user.IRoleService;
import com.lishi.recruit.service.user.model.RoleDTO;
import com.lishi.recruit.tools.UuidGenUtils;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IMenuDao menuDao;
	
	/*
	 * 分页查询角色列表
	 * @see com.cxstock.biz.power.RoleBiz#findPageRole(com.cxstock.utils.system.Page)
	 */
	public void findPageRole(Page page) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("start", page.getStart());
		condition.put("limit", page.getLimit());
		try {
			List<Role>	roleList = roleDao.queryPageList(condition);
			List dtoList = RoleDTO.createDtos(roleList);
			int total = roleDao.queryTotalCount(condition);
			page.setRoot(dtoList);
			page.setTotal(total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 保存/修改角色
	 * @see com.cxstock.biz.power.RoleBiz#saveOrUpdateRole(com.cxstock.biz.power.dto.RoleDTO)
	 */
	public Boolean saveRole(RoleDTO dto) {
		Boolean flag = false;
		String key =UuidGenUtils.genUUID();
		Role role = new Role();
		role.setRoleid(key);
		role.setRolename(dto.getRolename());
		role.setBz(dto.getBz());
		try {
			flag = roleDao.saveRole(role);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	/*
	 * 保存/修改角色
	 * @see com.cxstock.biz.power.RoleBiz#saveOrUpdateRole(com.cxstock.biz.power.dto.RoleDTO)
	 */
	public Boolean updateRole(RoleDTO dto) {
		Boolean flag = false;
		Role role = new Role();
		role.setRoleid(dto.getRoleid());
		role.setRolename(dto.getRolename());
		role.setBz(dto.getBz());
		try {
			flag =roleDao.updateRole(role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * 删除角色
	 * @see com.cxstock.biz.power.RoleBiz#deleteRole(java.lang.Integer)
	 */
	public Boolean deleteRole(String roleid) {
		Boolean isSuccess = false;
		//查询角色下有没有用户
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleid", roleid);
		int count = 0;
		try {
			count = roleDao.queryUserCount(param);
			if(count<1){
			    Boolean isDelt=roleDao.deleteByKey(param);
				isSuccess = isDelt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	/*
	 * 角色下拉数据
	 * @see com.cxstock.biz.power.RoleBiz#deleteRole(java.lang.Integer)
	 */
	public List<ComboData> findRoleType() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Role> roleList = roleDao.queryAll();
		for (Role role : roleList) {
			ComboData dto = new ComboData();
			dto.setValue(role.getRoleid().toString());
			dto.setText(role.getRolename());
			list.add(dto);
		}
		return list;
	}
	
	/*
	 * 查询角色菜单
	 * @see com.cxstock.biz.power.RoleBiz#findRoleMenu()
	 */
	public List findRoleMenu(String roleid) {
		List<Menu> menuList = menuDao.queryMenuList();
		List<RoleMenu_temp> roleMenuList = menuDao.queryRoleMenuList(roleid);
		List treeNodeList = this.getTreeNode("0",menuList,roleMenuList);
		return treeNodeList;
	}
	
	//功能树
	private List getTreeNode(String menuid,List listFunc,List listRoleFunc){
		List resultList = new ArrayList();
		//当前级菜单集合
		List list = this.getChildrens(listFunc, menuid);
		for (Object obj : list) {
			Menu menu = (Menu) obj;
			TreeNodeChecked treeNodeChecked = new TreeNodeChecked();
			treeNodeChecked.setText(menu.getMenuname());
			treeNodeChecked.setId(menu.getMenuid().toString());
			treeNodeChecked.setIconCls(menu.getIcon()==null?"":menu.getIcon());
			if(listRoleFunc.contains(menu.getMenuid())){
				treeNodeChecked.setChecked(true);
			}else{
				treeNodeChecked.setChecked(false);
			}
			treeNodeChecked.setChildren(getTreeNode(menu.getMenuid(),listFunc,listRoleFunc));//递归
			resultList.add(treeNodeChecked);
		}
		return resultList;
	}
	//子集合
	private List getChildrens(List funcs, String menuid) {
		List resultList = new ArrayList();
		Menu func = null;
		for (Object obj : funcs) {
			func = (Menu) obj;
			if (func.getPid().equals(menuid)) {//父节点id
				resultList.add(func);
			}
		}
		return resultList;
	}
	
	/*
	 * 保存角色权限
	 * @see com.cxstock.biz.power.RoleBiz#saveRoleMenu(java.lang.Integer, java.lang.String)
	 */
	public void saveRoleMenu(String roleid, String menuids) {
		String hql = "select t.menu from Rolemenu as t where t.role.roleid="+roleid;
		//数据库角色权限
		List<Menu> rmList= null; //roleDao.findByHql(hql);
		
		//页面勾选权限id
		String [] mids = menuids.split(",");
		
		//页面存在数据库不存在就添加
		if(mids.length>0){				
			for (String menuid : mids) {
				if(this.isContain(rmList,menuid)){
					RoleMenu_temp rolemenu = new RoleMenu_temp();
//					rolemenu.setId(new RoleMenuId(roleid,menuid));
//					roleDao.saveOrUpdate(rolemenu);
				}
			} 
		}
		
		//数据库存在而页面不存在就删除
		for (Menu menu : rmList) {
			String menuid = menu.getMenuid().toString();				
			if(this.isContain(mids,menuid)){
//				roleDao.deleteById(Rolemenu.class, new RolemenuId(roleid,menu.getMenuid()));
			}
		}
	}
	
	private boolean isContain(String [] mids,String menuid){
		for (String mid : mids) {
			if(menuid.equals(mid))
				return false;
		}
		return true;
	}
	
	private boolean isContain(List<Menu> menuList,String menuid){
		for (Menu menu : menuList) {
			if(menuid.equals(menu.getMenuid().toString()))
				return false;
		}
		return true;
	}

}
