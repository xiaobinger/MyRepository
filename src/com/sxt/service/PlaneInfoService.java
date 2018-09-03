package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.PlaneInfo;

public interface PlaneInfoService {

	List<PlaneInfo> findPlaneWithPage(Map<String, Object> map);

	int getPlaneCount();

	boolean savePlane(PlaneInfo plane);

	boolean delPlaneInfo(int planeId);

	PlaneInfo findPlaneById(Integer id);

	boolean updatePlaneInfo(PlaneInfo plane);

	List<PlaneInfo> findPlaneAllInfo();


}
