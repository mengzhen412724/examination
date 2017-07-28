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
	 * ע���û�,ע��ɹ�������Ϣ,���򷵻�ʧ��ԭ��
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
		// str = "�û���Ϊ��";
		// return str;
		// }
		String loginName = user.getLoginName();
		if (loginName == null) {
			str = "��½������Ϊ��";
			return str;
		} else {
			userName = loginName;
		}
		// ������ݿ��д��ڷ���false
		boolean b = userDAO.queryUser(loginName);
		if (b == false) {
			str = "��¼���Ѵ���";
			return str;
		}
		String passWord = user.getPassWd();
		if (passWord == null) {
			str = "���벻��Ϊ��";
			return str;
		}
		// ʹ��md5���������
		String passwordSalt = getMD5(passWord);
		user.setPassWdSalt(passwordSalt);
		// �ֻ�����������֤
		// String phone = user.getPhone();
		// if (phone == null) {
		// str = "�ֻ����벻��Ϊ��";
		// return str;
		// }
		// String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
		// Pattern regex1 = Pattern.compile(regExp);
		// Matcher matcher1 = regex1.matcher(phone);
		// boolean b1 = matcher1.matches();
		// if (b1 == false) {
		// str = "�ֻ������ʽ����ȷ";
		// return str;
		// }
		// // ����������֤
		// String email = user.getEmail();
		// String regex =
		// "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		// Pattern regex2 = Pattern.compile(regex);
		// Matcher matcher2 = regex2.matcher(email);
		// boolean b2 = matcher2.matches();
		// if (b2 == false) {
		// str = "�����ʽ����ȷ";
		// return str;
		// }
		boolean i = userDAO.addUser(user);
		if (i == false) {
			str = "ע��ʧ��";
			return str;
		}
		str = "ע��ɹ�";
		return str;
	}

	private String getMD5(String passWord) {
		try { // ����һ��MD5���ܼ���ժҪ
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ����md5����
			md.update(passWord.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �û���¼,��¼�ɹ���¼��½��Ϣ,���򷵻ص�½ʧ��ԭ��
	 * 
	 * @return
	 */
	@POST
	@Path("/login")
	public String login(User user) {
		String string = null;
		if (user.getLoginName() == null) {
			string = "��¼������Ϊ��";
			return string;
		}
		// �жε�½���������Ƿ���ȷ
		String loginName = user.getLoginName();
		Map<String, Object> map = userDAO.queryLoginName(loginName);
		if (map == null) {
			string = "�û���������";
			return string;
		}
		String passWord = user.getPassWd();
		if (passWord == null) {
			string = "���벻��Ϊ��";
			return string;
		}
		String passwd = getMD5(passWord);
//		System.out.println(passwd);
		String passWdSalt = (String) map.get("PASSWD_SALT");
//		System.out.println(passWdSalt);
		if (passwd.equals(passWdSalt) == false) {
			string = "�������";
			return string;
		}
		// ��֤�ɹ��������½��Ϣ
		boolean i = userDAO.login(loginName);
		if (i == true) {
			string = "��¼�ɹ�:1";
			return string;
		} else {
			string = "��¼ʧ��";
			return string;
		}
	}

	/**
	 * ����½�û����Ƿ���ȷ
	 * 
	 * @return
	 */
	@GET
	@Path("/check")
	public String check(@QueryParam("loginName") String loginName) {
		boolean b = userDAO.queryUser(loginName);
		String str = null;
		if (b == false) {
			str = "��¼������ȷ";
			return str;
		}
		str = "��½������ȷ";
		return str;
	}
}
