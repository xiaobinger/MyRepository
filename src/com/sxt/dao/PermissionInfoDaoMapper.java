package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.PermissionInfo;

public interface PermissionInfoDaoMapper {

	List<PermissionInfo> findPermissionAllInfo();

	List<PermissionInfo> findPermissionWithPage(Map<String, Object> map);

	int getPermissionCount();

	int savePermission(PermissionInfo permission);

	PermissionInfo findPermiName(String permiName);

	int delPermissionInfo(int permiId);

	int updatePermissionInfo(PermissionInfo permission);

	PermissionInfo findPermissionById(Integer id);

}
