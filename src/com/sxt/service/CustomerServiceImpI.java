package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.CustomerInfoDaoMapper;
import com.sxt.entity.CustomerInfo;

@Transactional
@Component("customerInfoService")
public class CustomerServiceImpI implements CustomerInfoService {
	@Resource(name="customerInfoDaoMapper")
	private CustomerInfoDaoMapper customerInfoDaoMapper;

	@Override
	public List<CustomerInfo> findCustomerWithPage(Map<String, Object> map) {
		List<CustomerInfo> list=customerInfoDaoMapper.findCustomerWithPage(map);
		return list;
	}

	@Override
	public int getCustomerCount() {
		int count=customerInfoDaoMapper.getCustomerCount();
		return count;
	}

	@Override
	public CustomerInfo findCustomerById(Integer id) {
		CustomerInfo customer=customerInfoDaoMapper.findCustomerById(id);
		return customer;
	}

	@Override
	public boolean updateCustomerInfo(CustomerInfo customer) {
		int flag=customerInfoDaoMapper.updateCustomerInfo(customer);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delCustomerInfo(int custId) {
		int flag=customerInfoDaoMapper.delCustomerInfo(custId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<CustomerInfo> findCustomerAllInfo() {
		return customerInfoDaoMapper.findCustomerAllInfo();
	}

	@Override
	public CustomerInfo loginOfCust(Map<String, Object> map) {
		return customerInfoDaoMapper.loginOfCust(map);
	}

	@Override
	public boolean registOfCust(CustomerInfo customer) {
		int flag=customerInfoDaoMapper.registOfCust(customer);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean findCustomerByCustName(String custName) {
		CustomerInfo customer=customerInfoDaoMapper.findCustomerByCustName(custName);
		if(customer!=null){
			return true;
		}
		return false;
	}
}
