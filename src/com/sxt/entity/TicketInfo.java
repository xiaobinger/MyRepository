package com.sxt.entity;

import java.io.Serializable;

public class TicketInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer bookId;
	private Integer custId;
	private String custName;
	private String rankName;
	private Integer airLineId;
	private String airLineNumber;
	private String deparCity;
	private String arrivalCity;
	private Integer spaceId;
	private String spaceName;
	private String bookNumber;
	private float bookPrice;
	private Integer bookSum;
	private String bookTime;
	private String remark;


	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
	public float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Integer getBookSum() {
		return bookSum;
	}
	public void setBookSum(Integer bookSum) {
		this.bookSum = bookSum;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(Integer airLineId) {
		this.airLineId = airLineId;
	}
	public String getAirLineNumber() {
		return airLineNumber;
	}
	public void setAirLineNumber(String airLineNumber) {
		this.airLineNumber = airLineNumber;
	}
	public Integer getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
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
	
}
