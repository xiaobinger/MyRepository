package com.sxt.entity;

import java.io.Serializable;

public class Discount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer discountId;
	private String discountName;
	public Integer getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
}
