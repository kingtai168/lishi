package com.lishi.recruit.view.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lishi.recruit.common.Page;
import com.lishi.recruit.common.pubEnty.ResultJson;
import com.lishi.recruit.service.user.IUserService;
import com.lishi.recruit.service.user.model.UserDTO;
import com.lishi.recruit.system.Constants;
import com.lishi.recruit.tools.JsonUtils;
import com.lishi.recruit.view.BaseCotroller;

@Controller
public class UserController extends BaseCotroller  {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	/** 登录验证 */
	@RequestMapping("login")
	public String login(UserDTO user,ModelMap modelMap) {
		try{
			String code = user.getLogincode();
			String pass = user.getPassword();
			UserDTO userInfo = userService.login(code, pass);
			if (userInfo != null) {
				request.getSession().setAttribute(Constants.USERINFO, userInfo);
//				modelMap.put(Constants.USERINFO, userInfo);
				return "main/index";
			} else{
				request.setAttribute("error", "用户名或密码错误");
				return "input";
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "连接失败");
			return "login";
		}
	}
	
	
	/** 用户权限菜单 */
	@ResponseBody
	@RequestMapping("getMenuList")
	public String getMenuBuf(HttpServletRequest request,HttpServletResponse response) {
		
		UserDTO userInfo = (UserDTO)request.getSession().getAttribute(Constants.USERINFO);
			if(userInfo!=null){
				return userInfo.getUsermenu();
			}
		return null;
	}
	
	/** 
	 * 分页查询用户列表 
	 */
	@ResponseBody
	@RequestMapping("queryPageList")
	public String findPageUser() {
		ResultJson result = null;
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			userService.findPageUser(page);
			result = new ResultJson(true);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultJson(false);
			result.setErrors("get Page user failed!");
		}
		return JsonUtils.toJson(result);
	}	

	/**
	 * 保存/修改用户
	 */
	@ResponseBody
	@RequestMapping("save")
	public String saveOrUpdateUser() {
//		try {
//			UserDTO dto = new UserDTO(userid,logincode,password,username,roleid,null,state,bz);
//			boolean bool = userService.saveOrUpdateUser(dto);
//			if(bool){
//				if(userid==null){
//					this.outString("{success:true,message:'保存成功!'}");
//				}else{
//					this.outString("{success:true,message:'修改成功!'}");
//				}
//			}else{
//				this.outString("{success:false,errors:'用户账号已存在!'}");
//			}
//		} catch (Exception e) {
//			 e.printStackTrace();
//			 this.outError();
//		}
		return null;
	}
    
	/**
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping("delete")
	public String deleteUser() {
		try {
//			userService.deleteUser(userid);
//			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 进入用户管理页面
	 * @return
	 */
	@RequestMapping("toUserPage")
	public String toUserPage() {
		return "/power/user";
	}


}
