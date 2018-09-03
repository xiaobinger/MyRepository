package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.RolerInfoDaoMapper;
import com.sxt.entity.RolerInfo;

@Transactional
@Component("rolerInfoService")
public class RolerInfoServiceImpI implements RolerInfoService {
	
	@Resource(name="rolerInfoDaoMapper")
	private RolerInfoDaoMapper rolerInfoDaoMapper;

	@Override
	public List<RolerInfo> findRolerAllInfo() {
		List<RolerInfo> list=rolerInfoDaoMapper.findRolerAllInfo();
		return list;
	}

	@Override
	public List<RolerInfo> findRolerWithPage(Map<String, Object> map) {
		List<RolerInfo> list=rolerInfoDaoMapper.findRolerWithPage(map);
		return list;
	}

	@Override
	public int getRolerCount() {
		int count=rolerInfoDaoMapper.getRolerCount();
		return count;
	}

	@Override
	public boolean findRolerName(String rolerName) {
		RolerInfo roler=rolerInfoDaoMapper.findRolerName(rolerName);
		if(roler!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean saveRoler(RolerInfo roler) {
		int flag=rolerInfoDaoMapper.saveRoler(roler);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public Integer getRolerId(String rolerName) {
		Integer rolerId=rolerInfoDaoMapper.getRolerId(rolerName);
		return rolerId;
	}

	@Override
	public boolean updatePermissionOfRoler(Map<String, Object> map) {
		int flag=rolerInfoDaoMapper.updatePermissionOfRoler(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public RolerInfo findRolerById(Integer id) {
		RolerInfo roler=rolerInfoDaoMapper.findRolerById(id);
		return roler;
	}

	@Override
	public boolean deletePermissionofRoler(Map<String, Object> map) {
		int flag=rolerInfoDaoMapper.deletePermissionofRoler(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delRolerInfo(int rolerId) {
		int flag=rolerInfoDaoMapper.delRolerInfo(rolerId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<RolerInfo> findRolerWithPageByPermiId(Map<String, Object> map) {
		List<RolerInfo> list=rolerInfoDaoMapper.findRolerWithPageByPermiId(map);
		return list;
	}

	@Override
	public int getRolerCountByPermiId(Integer permiId) {
		int count=rolerInfoDaoMapper.getRolerCountByPermiId(permiId);
		return count;
	}

	@Override
	public void deleteAllPermissionofRoler(Integer rolerId) {
		rolerInfoDaoMapper.deleteAllPermissionofRoler(rolerId);
		
	}
}
