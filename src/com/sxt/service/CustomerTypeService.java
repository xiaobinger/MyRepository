package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CityInfo;
import com.sxt.entity.CustomerType;
import com.sxt.entity.Discount;

public interface CustomerTypeService {

	int getCusTypeCount();

	List<CityInfo> findCustomTypeWithPage(Map<String, Object> map);

	boolean saveCustomType(CustomerType cusType);

	List<Discount> findDiscountAllInfo();

	CustomerType findCusTypeById(Integer id);

	boolean updateCusTypeInfo(CustomerType cusType);

	List<CustomerType> findCusTypeAllInfo();

}
