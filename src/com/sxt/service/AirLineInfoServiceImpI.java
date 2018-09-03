package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.AirLineInfoDaoMapper;
import com.sxt.entity.AirLineInfo;

@Transactional
@Component("airLineInfoService")
public class AirLineInfoServiceImpI implements AirLineInfoService {
	@Resource(name="airLineInfoDaoMapper")
	private AirLineInfoDaoMapper airLineInfoDaoMapper;

	@Override
	public AirLineInfo findAirLineInfoById(Map<String, Object> map) {
		List<AirLineInfo> listAirLine=airLineInfoDaoMapper.findAirLineInfoById(map);
		return listAirLine.get(0);
	}

	@Override
	public List<AirLineInfo> findAirLineAllInfo() {
		return airLineInfoDaoMapper.findAirLineAllInfo();
	}

	@Override
	public List<AirLineInfo> findAirLineWithPage(Map<String, Object> map) {
		return airLineInfoDaoMapper.findAirLineWithPage(map);
	}

	@Override
	public int getAirLineCount() {
		return airLineInfoDaoMapper.getAirLineCount();
	}

	@Override
	public boolean updateAirLineInfo(AirLineInfo airLine) {
		int flag=airLineInfoDaoMapper.updateAirLineInfo(airLine);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean saveAirLine(AirLineInfo airLine) {
		int flag=airLineInfoDaoMapper.saveAirLine(airLine);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delAirLineInfo(int airLineId) {
		int flag=airLineInfoDaoMapper.delAirLineInfo(airLineId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<AirLineInfo> findAirLineWithRequest(Map<String, Object> map) {
		return airLineInfoDaoMapper.findAirLineWithRequest(map);
	}

}
