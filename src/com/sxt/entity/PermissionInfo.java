package com.sxt.entity;

import java.io.Serializable;

public class PermissionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer permiId;
	private String permiNumber;
	private String permiName;
	private String permiDesc;
	private String remark;

	public Integer getPermiId() {
		return permiId;
	}
	public void setPermiId(Integer permiId) {
		this.permiId = permiId;
	}
	public String getPermiNumber() {
		return permiNumber;
	}
	public void setPermiNumber(String permiNumber) {
		this.permiNumber = permiNumber;
	}
	public String getPermiName() {
		return permiName;
	}
	public void setPermiName(String permiName) {
		this.permiName = permiName;
	}
	public String getPermiDesc() {
		return permiDesc;
	}
	public void setPermiDesc(String permiDesc) {
		this.permiDesc = permiDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
