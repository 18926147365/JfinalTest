package coml.jfinal.service;

import java.util.List;

import com.jfinal.model.OcmUser;

/** 
* @author 李浩铭 
* @since 2017年4月20日 上午11:25:46
*/
public interface UserService {
	
	/**查询所有用户*/
	public List<OcmUser> queryAll();
}
