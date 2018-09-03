package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.RolerInfo;

public interface RolerInfoDaoMapper {

	List<RolerInfo> findRolerAllInfo();

	List<RolerInfo> findRolerWithPage(Map<String, Object> map);

	int getRolerCount();

	int updatePermissionOfRoler(Map<String, Object> map);

	Integer getRolerId(String rolerName);

	int saveRoler(RolerInfo roler);

	RolerInfo findRolerName(String rolerName);

	RolerInfo findRolerById(Integer id);

	int deletePermissionofRoler(Map<String, Object> map);

	int delRolerInfo(int rolerId);

	List<RolerInfo> findRolerWithPageByPermiId(Map<String, Object> map);

	int getRolerCountByPermiId(Integer permiId);

	void deleteAllPermissionofRoler(Integer rolerId);

}
