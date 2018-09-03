package com.sxt.entity;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

public class RolerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer rolerId;
	private String rolerNumber;
	private String rolerDesc;
	private String rolerName;
	private String remark;
	private Set<PermissionInfo> setPermission=new HashSet<PermissionInfo>();
	public String getRolerName() {
		return rolerName;
	}
	public void setRolerName(String rolerName) {
		this.rolerName = rolerName;
	}
	public String getRolerNumber() {
		return rolerNumber;
	}
	public void setRolerNumber(String rolerNumber) {
		this.rolerNumber = rolerNumber;
	}
	public String getRolerDesc() {
		return rolerDesc;
	}
	public void setRolerDesc(String rolerDesc) {
		this.rolerDesc = rolerDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRolerId() {
		return rolerId;
	}
	public void setRolerId(Integer rolerId) {
		this.rolerId = rolerId;
	}
	public Set<PermissionInfo> getSetPermission() {
		return setPermission;
	}
	public void setSetPermission(Set<PermissionInfo> setPermission) {
		this.setPermission = setPermission;
	}
	
}
