package com.lishi.recruit.view.user.controller;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lishi.recruit.common.Page;
import com.lishi.recruit.common.pubEnty.ResultJson;
import com.lishi.recruit.service.user.IRoleService;
import com.lishi.recruit.service.user.model.RoleDTO;
import com.lishi.recruit.tools.JsonUtils;
import com.lishi.recruit.view.BaseCotroller;



@Controller
@RequestMapping("role")
public class RoleController extends BaseCotroller  {
	
	@Autowired
	private IRoleService roleService;
	

	/**
	 * 分页查询角色
	 */
	@ResponseBody
	@RequestMapping("queryRoleList")
	public String findPageRole(Integer start,Integer limit){
		ResultJson result = null;
		Page page = null;
		try {
			page = new Page();
			page.setStart(start);
			page.setLimit(limit);
			roleService.findPageRole(page);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultJson(false);
			result.setErrors("get Page Role failed!");
			return JsonUtils.toJson(result);
		}
		return JsonUtils.toJson(page);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("saveOrUpdateRole")
	public String saveOrUpdateRole(RoleDTO role) {
		
		
		ResultJson result = null;
		Boolean isSuccess = false;
		try {
			if(null==role.getRoleid()||role.getRoleid().isEmpty())
			{
				isSuccess = roleService.saveRole(role);
			}else
			{
				isSuccess = roleService.updateRole(role);
			}
			if(isSuccess){
				result = new ResultJson(isSuccess);
				result.setMessage("操作成功!");
			}else{
				result = new ResultJson(isSuccess);
				result.setMessage("操作失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultJson(false);
			result.setMessage("操作失败!");
		}
		return JsonUtils.toJson(result);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("saveRole")
	public String saveRole(RoleDTO role) {
		ResultJson result = null;
		try {
			Boolean isSuccess = roleService.saveRole(role);
			if(isSuccess){
				result = new ResultJson(isSuccess);
				result.setMessage("添加成功!");
			}else{
				result = new ResultJson(isSuccess);
				result.setMessage("添加失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultJson(false);
			result.setMessage("添加失败!");
		}
		return JsonUtils.toJson(result);
	}
	
	/**
	 * 修改角色
	 */
	@ResponseBody
	@RequestMapping("updateRole")
	public String updateRole(RoleDTO role) {
		ResultJson result = null;
		try {
			Boolean isSuccess = roleService.updateRole(role);
			if(isSuccess){
				result = new ResultJson(isSuccess);
				result.setMessage("添加成功!");
			}else{
				result = new ResultJson(isSuccess);
				result.setMessage("添加失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultJson(false);
			result.setMessage("添加失败!");
		}
		return JsonUtils.toJson(result);
	}
    
	/**
	 * 删除角色
	 */
	@ResponseBody
	@RequestMapping("deleteRole")
	public String deleteRole(String roleid) {
		ResultJson result = null;
		try {
			
			Boolean isSuccess = roleService.deleteRole(roleid);
			if(isSuccess){
				result = new ResultJson(isSuccess);
			}else{
				result = new ResultJson(isSuccess);
				result.setMessage("删除失败!");
				result.setError("该角色已被使用，不能删除");
			}
		} catch (Exception e) {
			result = new ResultJson(false);
			result.setMessage("删除失败!");
			result.setError("该角色已被使用，不能删除");
		}
		return JsonUtils.toJson(result);
	}
	
	/**
	 * 角色下拉数据
	 */
	@ResponseBody
	@RequestMapping("queryRoleTypeList")
	public String findRoleType() {
		String result=null;
		try {
			List roleList=roleService.findRoleType();
			if(null!=roleList&&!roleList.isEmpty())
			{
				Page page = new Page();
				page.setTotal(roleList.size());
				page.setRoot(roleList);
				result =JsonUtils.toJson(page);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 result= "{success:false,errors:'操作失败！'}";
		}
		return result;
	}
	
	/**
	 * 角色权限菜单
	 */
	@ResponseBody
	@RequestMapping("queryRoleMenu")
	public String findRoleMenu(String roleid) {
		JSONArray jsonArray = new JSONArray();
		try {
			List list = roleService.findRoleMenu(roleid);
			if (null!=list&&list.size() > 0) {
				jsonArray = JSONArray.fromObject(list);
			}
		} catch (Exception e) {
			 e.printStackTrace();
//			 this.outError();
		}
		return jsonArray.toString();
	}
	
	/**
	 * 保存角色权限
	 */
	public String saveRoleMenu() {
//		try {
//			roleBiz.saveRoleMenu(roleid,menuids);
//			this.outString("保存成功!");
//		} catch (Exception e) {
//			 e.printStackTrace();
//			 this.outError();
//		}
		return null;
	}
	
	@RequestMapping("toQuery")
	public String toQueryRole()
	{
		return "/power/role";
	}
	
}
