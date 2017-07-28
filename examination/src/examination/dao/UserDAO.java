package examination.dao;

import java.util.Map;

import examination.bean.User;

public interface UserDAO {
	boolean queryUser(String loginName);
	boolean addUser(User user);
	Map<String, Object> queryLoginName(String loginName);
	boolean login(String loginName);
}
