package examination.bean;

import java.io.Serializable;
import java.security.Timestamp;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String loginName;
	private String userName;
	private Timestamp accountValid;
	private int accountEnable;
	private String passWd;
	private String passWdSalt;
	private Timestamp passWdValid;
	private int roleId;
	private String phone;
	private String email;
	private Timestamp updateTime;
	private String updateUser;
	private String remark;
	private Timestamp createTime;
	private String proprtya;
	private String proprtyb;
	private String proprtyc;
	private String proprtyd;
	private String proprtye;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getAccountValid() {
		return accountValid;
	}

	public void setAccountValid(Timestamp accountValid) {
		this.accountValid = accountValid;
	}

	public int getAccountEnable() {
		return accountEnable;
	}

	public void setAccountEnable(int accountEnable) {
		this.accountEnable = accountEnable;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getPassWdSalt() {
		return passWdSalt;
	}

	public void setPassWdSalt(String passWdSalt) {
		this.passWdSalt = passWdSalt;
	}

	public Timestamp getPassWdValid() {
		return passWdValid;
	}

	public void setPassWdValid(Timestamp passWdValid) {
		this.passWdValid = passWdValid;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getProprtya() {
		return proprtya;
	}

	public void setProprtya(String proprtya) {
		this.proprtya = proprtya;
	}

	public String getProprtyb() {
		return proprtyb;
	}

	public void setProprtyb(String proprtyb) {
		this.proprtyb = proprtyb;
	}

	public String getProprtyc() {
		return proprtyc;
	}

	public void setProprtyc(String proprtyc) {
		this.proprtyc = proprtyc;
	}

	public String getProprtyd() {
		return proprtyd;
	}

	public void setProprtyd(String proprtyd) {
		this.proprtyd = proprtyd;
	}

	public String getProprtye() {
		return proprtye;
	}

	public void setProprtye(String proprtye) {
		this.proprtye = proprtye;
	}

}
