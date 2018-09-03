package com.sxt.entity;

import java.io.Serializable;

public class SpaceRankInfo implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Integer spaceId;
	private Integer airLineId;
	private String airLineNumber;
	private String spaceNumber;
	private String rankName;
	private Integer rankId;
	private String isGift;
	private String isNewspaper;
	private String isDrink;
	private String isMovie;
	private String isChange;
	private String isReturnTicket;
	private String isDiscount;
	private float spacePrice;
	private String remark;
	

	public Integer getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
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
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	public String getIsNewspaper() {
		return isNewspaper;
	}
	public void setIsNewspaper(String isNewspaper) {
		this.isNewspaper = isNewspaper;
	}
	public String getIsDrink() {
		return isDrink;
	}
	public void setIsDrink(String isDrink) {
		this.isDrink = isDrink;
	}
	public String getIsMovie() {
		return isMovie;
	}
	public void setIsMovie(String isMovie) {
		this.isMovie = isMovie;
	}
	public String getIsChange() {
		return isChange;
	}
	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	public String getIsReturnTicket() {
		return isReturnTicket;
	}
	public void setIsReturnTicket(String isReturnTicket) {
		this.isReturnTicket = isReturnTicket;
	}
	public String getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}
	public float getSpacePrice() {
		return spacePrice;
	}
	public void setSpacePrice(float spacePrice) {
		this.spacePrice = spacePrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getSpaceNumber() {
		return spaceNumber;
	}
	public void setSpaceNumber(String spaceNumber) {
		this.spaceNumber = spaceNumber;
	}

}
