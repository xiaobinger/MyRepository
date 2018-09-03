package com.sxt.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginLogInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer loginId;
	private String loginName;
	private String loginIp;
	private String loginTime;
	private String logoutTime;
	private Date inTime;
	private Date outTime;
	public LoginLogInfo(){}
	public LoginLogInfo(String loginName, String loginIp, String loginTime,
			String logoutTime) {
		super();
		this.loginName = loginName;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	

}
