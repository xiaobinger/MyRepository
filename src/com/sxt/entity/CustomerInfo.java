package com.sxt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerInfo implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Integer custId;
	private Integer cusTypeId;
	private String cusTypeName;
	private String custNumber;
	private String custName;
	private String custPass;
	private String custAge;
	private String custSex;
	private String custAdress;
	private String custHirbeat;
	private String remark;
	private String custIdCard;
	private String custEmail;
	private String custTel;
	private List<TicketInfo> list=new ArrayList<TicketInfo>();
	

	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getCusTypeId() {
		return cusTypeId;
	}
	public void setCusTypeId(Integer cusTypeId) {
		this.cusTypeId = cusTypeId;
	}
	public String getCusTypeName() {
		return cusTypeName;
	}
	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
	}
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAge() {
		return custAge;
	}
	public void setCustAge(String custAge) {
		this.custAge = custAge;
	}
	public String getCustSex() {
		return custSex;
	}
	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}
	public String getCustAdress() {
		return custAdress;
	}
	public void setCustAdress(String custAdress) {
		this.custAdress = custAdress;
	}
	public String getCustHirbeat() {
		return custHirbeat;
	}
	public void setCustHirbeat(String custHirbeat) {
		this.custHirbeat = custHirbeat;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	public List<TicketInfo> getList() {
		return list;
	}
	public void setList(List<TicketInfo> list) {
		this.list = list;
	}
	public String getCustIdCard() {
		return custIdCard;
	}
	public void setCustIdCard(String custIdCard) {
		this.custIdCard = custIdCard;
	}
	public String getCustPass() {
		return custPass;
	}
	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}
	

}
