package com.sxt.entity;

import java.io.Serializable;

public class PlaneInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer planeId;
	private String planeNumber;
	private String planeSince;
	private String buyTime;
	private String serviceTime;
	private Integer tSeats;
	private Integer bSeats;
	private Integer fSeats;
	private String remark;

	public Integer getPlaneId() {
		return planeId;
	}
	public void setPlaneId(Integer planeId) {
		this.planeId = planeId;
	}
	public String getPlaneNumber() {
		return planeNumber;
	}
	public void setPlaneNumber(String planeNumber) {
		this.planeNumber = planeNumber;
	}
	public String getPlaneSince() {
		return planeSince;
	}
	public void setPlaneSince(String planeSince) {
		this.planeSince = planeSince;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public Integer gettSeats() {
		return tSeats;
	}
	public void settSeats(Integer tSeats) {
		this.tSeats = tSeats;
	}
	public Integer getbSeats() {
		return bSeats;
	}
	public void setbSeats(Integer bSeats) {
		this.bSeats = bSeats;
	}
	public Integer getfSeats() {
		return fSeats;
	}
	public void setfSeats(Integer fSeats) {
		this.fSeats = fSeats;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
