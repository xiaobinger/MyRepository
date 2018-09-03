package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.AirLineInfo;

public interface AirLineInfoDaoMapper {

	List<AirLineInfo> findAirLineInfoById(Map<String, Object> map);

	List<AirLineInfo> findAirLineAllInfo();

	List<AirLineInfo> findAirLineWithPage(Map<String, Object> map);

	int getAirLineCount();

	int saveAirLine(AirLineInfo airLine);

	int updateAirLineInfo(AirLineInfo airLine);

	int delAirLineInfo(int airLineId);

	List<AirLineInfo> findAirLineWithRequest(Map<String, Object> map);

}
