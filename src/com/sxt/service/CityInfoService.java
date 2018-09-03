package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.CityInfo;
import com.sxt.entity.PrivadeInfo;

public interface CityInfoService {

	List<CityInfo> findAllCityInfo();

	List<PrivadeInfo> findPrivadeAllInfo();

	boolean saveCity(CityInfo city);

	List<CityInfo> findCityWithPage(Map<String, Object> map);

	int getCityCount();

}
