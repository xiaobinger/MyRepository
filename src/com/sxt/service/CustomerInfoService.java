package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CustomerInfo;

public interface CustomerInfoService {

	List<CustomerInfo> findCustomerWithPage(Map<String, Object> map);

	int getCustomerCount();

	CustomerInfo findCustomerById(Integer id);

	boolean updateCustomerInfo(CustomerInfo customer);

	boolean delCustomerInfo(int custId);

	List<CustomerInfo> findCustomerAllInfo();

	CustomerInfo loginOfCust(Map<String, Object> map);

	boolean registOfCust(CustomerInfo customer);

	boolean findCustomerByCustName(String custName);

}
