package com.sxt.entity;

import java.io.Serializable;

public class TalkInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String talkInfo;
	private String talkType;
	private Integer talkId;
	private Integer userId;
	private String userName;
	public String getTalkInfo() {
		return talkInfo;
	}
	public void setTalkInfo(String talkInfo) {
		this.talkInfo = talkInfo;
	}
	public String getTalkType() {
		return talkType;
	}
	public void setTalkType(String talkType) {
		this.talkType = talkType;
	}
	public Integer getTalkId() {
		return talkId;
	}
	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
