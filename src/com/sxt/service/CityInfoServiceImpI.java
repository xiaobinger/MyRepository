package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.CityInfoDaoMapper;
import com.sxt.entity.CityInfo;
import com.sxt.entity.PrivadeInfo;

@Transactional
@Component("cityInfoService")
public class CityInfoServiceImpI implements CityInfoService {
	@Resource(name="cityInfoDaoMapper")
	private CityInfoDaoMapper cityInfoDaoMapper;

	@Override
	public List<CityInfo> findAllCityInfo() {
		List<CityInfo> list=cityInfoDaoMapper.findAllCityInfo();
		return list;
	}

	@Override
	public List<PrivadeInfo> findPrivadeAllInfo() {
		List<PrivadeInfo> list=cityInfoDaoMapper.findPrivadeAllInfo();
		return list;
	}

	@Override
	public boolean saveCity(CityInfo city) {
		int flag=cityInfoDaoMapper.saveCity(city);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<CityInfo> findCityWithPage(Map<String, Object> map) {
		List<CityInfo> list=cityInfoDaoMapper.findCityWithPage(map);
		return list;
	}

	@Override
	public int getCityCount() {
		int count=cityInfoDaoMapper.getCityCount();
		return count;
	}
}
