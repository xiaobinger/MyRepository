package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.AirLineInfo;

public interface AirLineInfoService {

	AirLineInfo findAirLineInfoById(Map<String, Object> map);

	List<AirLineInfo> findAirLineAllInfo();

	List<AirLineInfo> findAirLineWithPage(Map<String, Object> map);

	int getAirLineCount();

	boolean updateAirLineInfo(AirLineInfo airLine);

	boolean saveAirLine(AirLineInfo airLine);

	boolean delAirLineInfo(int airLineId);

	List<AirLineInfo> findAirLineWithRequest(Map<String, Object> map);

}
