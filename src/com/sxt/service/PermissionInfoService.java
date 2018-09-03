package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.PermissionInfo;

public interface PermissionInfoService {

	List<PermissionInfo> findPermissionAllInfo();

	List<PermissionInfo> findPermissionWithPage(Map<String, Object> map);

	int getPermissionCount();

	boolean savePermission(PermissionInfo permission);

	boolean findPermiName(String permiName);

	PermissionInfo findPermissionById(Integer id);

	boolean updatePermissionInfo(PermissionInfo permission);

	boolean delPermissionInfo(int PermiId);

}
