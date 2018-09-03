package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.PermissionInfoDaoMapper;
import com.sxt.entity.PermissionInfo;


@Transactional
@Component("permissionInfoService")
public class PermissionInfoServiceImpI implements PermissionInfoService {
	@Resource(name="permissionInfoDaoMapper")
	private PermissionInfoDaoMapper permissionInfoDaoMapper;

	@Override
	public List<PermissionInfo> findPermissionAllInfo() {
		List<PermissionInfo> list=permissionInfoDaoMapper.findPermissionAllInfo();
		return list;
	}

	@Override
	public List<PermissionInfo> findPermissionWithPage(Map<String, Object> map) {
		List<PermissionInfo> list=permissionInfoDaoMapper.findPermissionWithPage(map);
		return list;
	}

	@Override
	public int getPermissionCount() {
		int count=permissionInfoDaoMapper.getPermissionCount();
		return count;
	}

	@Override
	public boolean savePermission(PermissionInfo permission) {
		int flag=permissionInfoDaoMapper.savePermission(permission);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean findPermiName(String permiName) {
		PermissionInfo permission=permissionInfoDaoMapper.findPermiName(permiName);
		if(permission!=null){
			return true;
		}
		return false;
	}

	@Override
	public PermissionInfo findPermissionById(Integer id) {
		PermissionInfo permission=permissionInfoDaoMapper.findPermissionById(id);
		return permission;
	}

	@Override
	public boolean updatePermissionInfo(PermissionInfo permission) {
		int flag=permissionInfoDaoMapper.updatePermissionInfo(permission);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPermissionInfo(int PermiId) {
		int flag=permissionInfoDaoMapper.delPermissionInfo(PermiId);
		if(flag!=0){
			return true;
		}
		return false;
	}
}
