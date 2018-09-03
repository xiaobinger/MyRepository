package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.RolerInfo;

public interface RolerInfoService {

	List<RolerInfo> findRolerAllInfo();

	List<RolerInfo> findRolerWithPage(Map<String, Object> map);

	int getRolerCount();

	boolean findRolerName(String rolerName);

	boolean saveRoler(RolerInfo roler);

	Integer getRolerId(String rolerName);

	boolean updatePermissionOfRoler(Map<String, Object> map);

	RolerInfo findRolerById(Integer id);

	boolean deletePermissionofRoler(Map<String, Object> map);

	boolean delRolerInfo(int rolerId);

	List<RolerInfo> findRolerWithPageByPermiId(Map<String, Object> map);

	int getRolerCountByPermiId(Integer permiId);

	void deleteAllPermissionofRoler(Integer rolerId);

}
