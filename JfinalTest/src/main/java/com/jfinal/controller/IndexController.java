package com.jfinal.controller;

import com.jfinal.core.Controller;

/** 
* @author 李浩铭 
* @since 2017年4月20日 下午2:41:15
*/
public class IndexController extends Controller{
	
	public void index(){
		redirect("/myIndex/index.html");
	}
}
