package com.jfinal.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.model.OcmUser;
import coml.jfinal.service.UserService;
import coml.jfinal.service.impl.UserServiceImpl;

/**
 * @author 李浩铭
 * @since 2017年4月20日 上午11:17:52
 */
public class UserController extends Controller {
	private static UserService userService = UserServiceImpl.getInstance();

	public void queryAllUser() {
		List<OcmUser> list = userService.queryAll();
		for (OcmUser ocmUser : list) {
			System.out.println(ocmUser.getAccount());
		}
		setAttr("userList", list);
		
//		forwardAction("/userPage/userList.jsp");
		renderJsp("/userPage/userList.jsp");
		
	}
}
