package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.CustomerTypeDaoMapper;
import com.sxt.entity.CityInfo;
import com.sxt.entity.CustomerType;
import com.sxt.entity.Discount;

@Transactional
@Component("customerTypeService")
public class CustomerTypeServiceImpI implements CustomerTypeService {
	
	@Resource(name="customerTypeDaoMapper")
	private CustomerTypeDaoMapper customerTypeDaoMapper;

	@Override
	public int getCusTypeCount() {
		int count=customerTypeDaoMapper.getCusTypeCount();
		return count;
	}

	@Override
	public List<CityInfo> findCustomTypeWithPage(Map<String, Object> map) {
		List<CityInfo> list=customerTypeDaoMapper.findCustomTypeWithPage(map);
		return list;
	}

	@Override
	public boolean saveCustomType(CustomerType cusType) {
		int flag=customerTypeDaoMapper.saveCustomType(cusType);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<Discount> findDiscountAllInfo() {
		List<Discount> list=customerTypeDaoMapper.findDiscountAllInfo();
		return list;
	}

	@Override
	public CustomerType findCusTypeById(Integer id) {
		CustomerType cusType=customerTypeDaoMapper.findCusTypeById(id);
		return cusType;
	}

	@Override
	public boolean updateCusTypeInfo(CustomerType cusType) {
		int flag=customerTypeDaoMapper.updateCusTypeInfo(cusType);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<CustomerType> findCusTypeAllInfo() {
		List<CustomerType> list=customerTypeDaoMapper.findCusTypeAllInfo();
		return list;
	}
}
