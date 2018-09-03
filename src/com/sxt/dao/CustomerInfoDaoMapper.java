package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CustomerInfo;

public interface CustomerInfoDaoMapper {

	List<CustomerInfo> findCustomerWithPage(Map<String, Object> map);

	int getCustomerCount();

	CustomerInfo findCustomerById(Integer id);

	int updateCustomerInfo(CustomerInfo customer);

	int delCustomerInfo(int custId);

	List<CustomerInfo> findCustomerAllInfo();

	CustomerInfo loginOfCust(Map<String, Object> map);

	int registOfCust(CustomerInfo customer);

	CustomerInfo findCustomerByCustName(String custName);

}
