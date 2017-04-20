package coml.jfinal.service.impl;

import java.util.List;

import com.jfinal.model.OcmUser;

import coml.jfinal.service.UserService;

/**
 * @author 李浩铭
 * @since 2017年4月20日 上午11:26:21
 */
public class UserServiceImpl implements UserService {
	/**饿汉式单例*/
	private static UserServiceImpl INSTANCE;

	private UserServiceImpl() {
	}

	public static synchronized UserServiceImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public List<OcmUser> queryAll() {
		List<OcmUser> list = OcmUser.dao.find("select * from ocm_user");
		return list;
	}

}
