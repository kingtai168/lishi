package com.lishi.recruit.view.loginUser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lishi.recruit.view.loginuser.model.LoginUserVO;

/**
 * 
 * @author zt
 *
 */
@RequestMapping("loginUser")
@Controller
public class LoginUserController {
	
	/**
	 * 添加用户
	 * @param userVo
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("add")
	public Boolean addLoginUser(LoginUserVO userVo,ModelMap modelMap)
	{
		System.out.println(userVo.getUserName());
		return null;
	}

}
