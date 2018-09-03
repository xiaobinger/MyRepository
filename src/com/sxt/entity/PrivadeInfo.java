package com.sxt.entity;

import java.io.Serializable;

public class PrivadeInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer privadeId;
	private String privadeName;
	public Integer getPrivadeId() {
		return privadeId;
	}
	public void setPrivadeId(Integer privadeId) {
		this.privadeId = privadeId;
	}
	public String getPrivadeName() {
		return privadeName;
	}
	public void setPrivadeName(String privadeName) {
		this.privadeName = privadeName;
	}

}
