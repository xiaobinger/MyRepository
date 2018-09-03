package com.sxt.entity;

import java.io.Serializable;

public class AirLineInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer airLineId;
	private float spacePrice;
	private String rankName;
	private Integer rankId;
	private Integer spaceId;
	private Integer planeId;
	private String planeSince;
	private String airLineNumber;
	private String deparCity;
	private String arrivalCity;
	private String airLineTime;
	private String outTime;
	private String inTime;
	private String remark;
	private String sendState;
	

	public Integer getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(Integer airLineId) {
		this.airLineId = airLineId;
	}
	public Integer getPlaneId() {
		return planeId;
	}
	public void setPlaneId(Integer planeId) {
		this.planeId = planeId;
	}
	public String getPlaneSince() {
		return planeSince;
	}
	public void setPlaneSince(String planeSince) {
		this.planeSince = planeSince;
	}
	public String getAirLineNumber() {
		return airLineNumber;
	}
	public void setAirLineNumber(String airLineNumber) {
		this.airLineNumber = airLineNumber;
	}
	public String getDeparCity() {
		return deparCity;
	}
	public void setDeparCity(String deparCity) {
		this.deparCity = deparCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public String getAirLineTime() {
		return airLineTime;
	}
	public void setAirLineTime(String airLineTime) {
		this.airLineTime = airLineTime;
	}
	public float getSpacePrice() {
		return spacePrice;
	}
	public void setSpacePrice(float spacePrice) {
		this.spacePrice = spacePrice;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public Integer getRankId() {
		return rankId;
	}
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}
	public Integer getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}
	

}
