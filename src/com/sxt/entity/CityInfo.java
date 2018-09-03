package com.sxt.entity;

import java.io.Serializable;

public class CityInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private String cityNumber;
	private String cityName;
	private Integer privadeId;
	private String privadeName;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
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
