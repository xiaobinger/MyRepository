package com.sxt.entity;

import java.io.Serializable;

public class OperationLogInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer operaId;
	private String operaUserName;
	private String operaKind;
	private String operaTime;
	private String remark;

	public OperationLogInfo(){}
	
	public OperationLogInfo(String operaUserName, String operaKind, String operaTime, String remark) {
		super();
		this.operaUserName = operaUserName;
		this.operaKind = operaKind;
		this.operaTime = operaTime;
		this.remark = remark;
	}
	public Integer getOperaId() {
		return operaId;
	}
	public void setOperaId(Integer operaId) {
		this.operaId = operaId;
	}
	public String getOperaUserName() {
		return operaUserName;
	}
	public void setOperaUserName(String operaUserName) {
		this.operaUserName = operaUserName;
	}
	public String getOperaKind() {
		return operaKind;
	}
	public void setOperaKind(String operaKind) {
		this.operaKind = operaKind;
	}
	public String getOperaTime() {
		return operaTime;
	}
	public void setOperaTime(String operaTime) {
		this.operaTime = operaTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
