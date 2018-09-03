package com.sxt.entity;

import java.io.Serializable;

public class CustomerType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cusTypeId;
	private String cusTypeNumber;
	private String cusTypeName;
	private String discountName;
	private Integer discountId;
	private String remark;

	public Integer getCusTypeId() {
		return cusTypeId;
	}
	public void setCusTypeId(Integer cusTypeId) {
		this.cusTypeId = cusTypeId;
	}
	public String getCusTypeNumber() {
		return cusTypeNumber;
	}
	public void setCusTypeNumber(String cusTypeNumber) {
		this.cusTypeNumber = cusTypeNumber;
	}
	public String getCusTypeName() {
		return cusTypeName;
	}
	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public Integer getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
