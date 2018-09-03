package com.sxt.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String userNumber;
	private String userPass;
	private Set<RolerInfo> setRolers=new HashSet<RolerInfo>();
	private Set<PermissionInfo> setPermission=new HashSet<PermissionInfo>();
	private String userAge;
	private String userSex;
	private String userEmail;
	private String userTel;
	private String remark;
	private String rolerName;
	private String permiName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRolerName() {
		return rolerName;
	}
	public void setRolerName(String rolerName) {
		this.rolerName = rolerName;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Set<RolerInfo> getSetRolers() {
		return setRolers;
	}
	public void setSetRolers(Set<RolerInfo> setRolers) {
		this.setRolers = setRolers;
	}
	public Set<PermissionInfo> getSetPermission() {
		return setPermission;
	}
	public void setSetPermission(Set<PermissionInfo> setPermission) {
		this.setPermission = setPermission;
	}
	public String getPermiName() {
		return permiName;
	}
	public void setPermiName(String permiName) {
		this.permiName = permiName;
	}
	
}
