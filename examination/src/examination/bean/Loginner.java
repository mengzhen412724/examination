package examination.bean;

import java.io.Serializable;
import java.security.Timestamp;

public class Loginner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String loginName;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String hostIp;
	private String loginResult;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
}
