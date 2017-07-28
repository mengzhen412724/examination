package examination.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import examination.bean.User;
import examination.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

	public void init() {
	}

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public boolean addUser(User user) {
		String insertSql = "insert into ss_user(LOGIN_NAME,PASSWD,PASSWD_SALT,ROLE_ID,CREATE_TIME) values(?,?,?,1,null)";

		// Object[] params = { user.getLoginName(), user.getPassWd(),
		// user.getPassWdSalt(), user.getRemark() };
		Object[] params = { user.getLoginName(), user.getPassWd(),
				user.getPassWdSalt() };
		try {
			int i = jdbcTemplate.update(insertSql, params);
//			System.out.println(i);
			if (i >= 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Object> queryLoginName(String loginName) {
		String sql = "select LOGIN_NAME,PASSWD_SALT from ss_user where LOGIN_NAME='"
				+ loginName + "'";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = jdbcTemplate.queryForMap(sql);
			return map;
		} catch (Exception e) {
			logger.info("error is {}", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean login(String loginName) {
		String sql = "insert into ss_login (LOGIN_NAME,LOGIN_TIME,LOGIN_RESULT) values(?,null,1)";
		Object[] params = { loginName };
		try {
			int i = jdbcTemplate.update(sql, params);
			if (i >= 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean queryUser(String loginName) {
		Map<String, Object> map = null;
		String sql = "select count(id) from ss_user where LOGIN_NAME='" + loginName
				+ "'";
		try {
			map = this.jdbcTemplate.queryForMap(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("0".equals(map.get("count(id)").toString())) {
			return true;
		} else {
			return false;
		}
	}
}
