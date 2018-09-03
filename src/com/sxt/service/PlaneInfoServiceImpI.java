package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.PlaneInfoDaoMapper;
import com.sxt.entity.PlaneInfo;

@Transactional
@Component("planeInfoService")
public class PlaneInfoServiceImpI implements PlaneInfoService {
	@Resource(name="planeInfoDaoMapper")
	private PlaneInfoDaoMapper planeInfoDaoMapper;

	@Override
	public List<PlaneInfo> findPlaneWithPage(Map<String, Object> map) {
		return planeInfoDaoMapper.findPlaneWithPage(map);
	}

	@Override
	public int getPlaneCount() {
		return planeInfoDaoMapper.getPlaneCount();
	}

	@Override
	public boolean savePlane(PlaneInfo plane) {
		int flag=planeInfoDaoMapper.savePlane(plane);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPlaneInfo(int planeId) {
		int flag=planeInfoDaoMapper.delPlaneInfo(planeId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public PlaneInfo findPlaneById(Integer id) {
		return planeInfoDaoMapper.findPlaneById(id);
	}

	@Override
	public boolean updatePlaneInfo(PlaneInfo plane) {
		int flag=planeInfoDaoMapper.updatePlaneInfo(plane);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<PlaneInfo> findPlaneAllInfo() {
		return planeInfoDaoMapper.findPlaneAllInfo();
	}
}
