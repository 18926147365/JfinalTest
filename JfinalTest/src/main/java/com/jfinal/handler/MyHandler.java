package com.jfinal.handler;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 李浩铭 
* @since 2017年4月20日 下午3:25:32
* 拦截器
*/
public class MyHandler extends Handler{

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		//target就是跳转地址，如果用户没有登录则修改该target地址
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		next.handle(target, request, response, isHandled);
	}

}
