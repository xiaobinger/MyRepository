package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CityInfo;
import com.sxt.entity.CustomerType;
import com.sxt.entity.Discount;

public interface CustomerTypeDaoMapper {

	List<Discount> findDiscountAllInfo();

	int saveCustomType(CustomerType cusType);

	List<CityInfo> findCustomTypeWithPage(Map<String, Object> map);

	int getCusTypeCount();

	CustomerType findCusTypeById(Integer id);

	int updateCusTypeInfo(CustomerType cusType);

	List<CustomerType> findCusTypeAllInfo();

}
