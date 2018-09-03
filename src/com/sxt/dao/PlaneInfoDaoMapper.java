package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.PlaneInfo;

public interface PlaneInfoDaoMapper {

	List<PlaneInfo> findPlaneWithPage(Map<String, Object> map);

	int getPlaneCount();

	int savePlane(PlaneInfo plane);

	int delPlaneInfo(int planeId);

	PlaneInfo findPlaneById(Integer id);

	int updatePlaneInfo(PlaneInfo plane);

	List<PlaneInfo> findPlaneAllInfo();

}
