package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CityInfo;
import com.sxt.entity.PrivadeInfo;

public interface CityInfoDaoMapper {

	List<CityInfo> findAllCityInfo();

	List<PrivadeInfo> findPrivadeAllInfo();

	int saveCity(CityInfo city);

	List<CityInfo> findCityWithPage(Map<String, Object> map);

	int getCityCount();

}
