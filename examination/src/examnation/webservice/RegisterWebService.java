package examnation.webservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import examination.bean.User;
import examination.dao.UserDAO;

@Component
@Consumes({ MediaType.APPLICATION_JSON })
@Produces("text/plain;charset=utf-8")
@Path("user")
public class RegisterWebService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	/**
	 * 注册用户,注册成功保存信息,否则返回失败原因
	 * 
	 * @return
	 */
	@POST
	@Path("/add")
	public String register(User user) {
		String str = null;
		String userName = "";
		// String userName = user.getUserName();
		// if (userName == null) {
		// str = "用户名为空";
		// return str;
		// }
		String loginName = user.getLoginName();
		if (loginName == null) {
			str = "登陆名不能为空";
			return str;
		} else {
			userName = loginName;
		}
		// 如果数据库中存在返回false
		boolean b = userDAO.queryUser(loginName);
		if (b == false) {
			str = "登录名已存在";
			return str;
		}
		String passWord = user.getPassWd();
		if (passWord == null) {
			str = "密码不能为空";
			return str;
		}
		// 使用md5對密码加盐
		String passwordSalt = getMD5(passWord);
		user.setPassWdSalt(passwordSalt);
		// 手机号码正则验证
		// String phone = user.getPhone();
		// if (phone == null) {
		// str = "手机号码不能为空";
		// return str;
		// }
		// String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
		// Pattern regex1 = Pattern.compile(regExp);
		// Matcher matcher1 = regex1.matcher(phone);
		// boolean b1 = matcher1.matches();
		// if (b1 == false) {
		// str = "手机号码格式不正确";
		// return str;
		// }
		// // 邮箱正则验证
		// String email = user.getEmail();
		// String regex =
		// "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		// Pattern regex2 = Pattern.compile(regex);
		// Matcher matcher2 = regex2.matcher(email);
		// boolean b2 = matcher2.matches();
		// if (b2 == false) {
		// str = "邮箱格式不正确";
		// return str;
		// }
		boolean i = userDAO.addUser(user);
		if (i == false) {
			str = "注册失败";
			return str;
		}
		str = "注册成功";
		return str;
	}

	private String getMD5(String passWord) {
		try { // 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(passWord.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用户登录,登录成功记录登陆信息,否则返回登陆失败原因
	 * 
	 * @return
	 */
	@POST
	@Path("/login")
	public String login(User user) {
		String string = null;
		if (user.getLoginName() == null) {
			string = "登录名不能为空";
			return string;
		}
		// 判段登陆名和密码是否正确
		String loginName = user.getLoginName();
		Map<String, Object> map = userDAO.queryLoginName(loginName);
		if (map == null) {
			string = "用户名不存在";
			return string;
		}
		String passWord = user.getPassWd();
		if (passWord == null) {
			string = "密码不能为空";
			return string;
		}
		String passwd = getMD5(passWord);
//		System.out.println(passwd);
		String passWdSalt = (String) map.get("PASSWD_SALT");
//		System.out.println(passWdSalt);
		if (passwd.equals(passWdSalt) == false) {
			string = "密码错误";
			return string;
		}
		// 验证成功，存入登陆信息
		boolean i = userDAO.login(loginName);
		if (i == true) {
			string = "登录成功:1";
			return string;
		} else {
			string = "登录失败";
			return string;
		}
	}

	/**
	 * 检查登陆用户名是否正确
	 * 
	 * @return
	 */
	@GET
	@Path("/check")
	public String check(@QueryParam("loginName") String loginName) {
		boolean b = userDAO.queryUser(loginName);
		String str = null;
		if (b == false) {
			str = "登录名已正确";
			return str;
		}
		str = "登陆名不正确";
		return str;
	}
}
