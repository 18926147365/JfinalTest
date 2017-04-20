package com.jfinal.common;


import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.controller.IndexController;
import com.jfinal.controller.UserController;
import com.jfinal.core.JFinal;
import com.jfinal.handler.MyHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.model._MappingKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

import comjfinal.intercept.MyInterceptor;

/**
 * @author 李浩铭
 * @since 2017年4月20日 上午9:42:09
 */
public class JfinalConfig extends JFinalConfig {

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为： -XX:PermSize=64M
	 * -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("src/main/webapp", 8666, "/", 5);

		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("WebRoot", 80, "/");
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("dbconfig.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		
		me.add("/user", UserController.class,"/user");
		me.add("/index", IndexController.class,"/myIndex");//后面的index是文件路径即webapp下面的文件夹
	}

	public void configEngine(Engine me) {

	}

	/**
	 * 创建DruidPlugin对象
	 */
	public static DruidPlugin createDruidPlugin() {
		String password = PropKit.get("password");
		try {
			password = ConfigTools.decrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("url"), PropKit.get("username"), password,
				PropKit.get("driver"));
		druidPlugin.addFilter(new StatFilter());
		druidPlugin.setValidationQuery("SELECT 1");
		druidPlugin.setTestWhileIdle(true);
		druidPlugin.setTestOnBorrow(true);
		druidPlugin.setTestOnReturn(true);
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		druidPlugin.addFilter(wall);

		return druidPlugin;
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		
		// 配置C3p0数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

		me.add(new MyInterceptor());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

		me.add(new MyHandler());//这个是全局处理器
	}
}
