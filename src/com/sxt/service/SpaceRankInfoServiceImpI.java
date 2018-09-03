package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.SpaceRankInfoDaoMapper;
import com.sxt.entity.SpaceRankInfo;

@Transactional
@Component("spaceRankInfoService")
public class SpaceRankInfoServiceImpI implements SpaceRankInfoService {
	
	@Resource(name="spaceRankInfoDaoMapper")
	private SpaceRankInfoDaoMapper spaceRankInfoDaoMapper;

	@Override
	public List<SpaceRankInfo> findSpaceRankAllInfo() {
		return spaceRankInfoDaoMapper.findSpaceRankAllInfo();
	}

	@Override
	public List<SpaceRankInfo> findSpaceRankWithPage(Map<String, Object> map) {
		return spaceRankInfoDaoMapper.findSpaceRankWithPage(map);
	}

	@Override
	public int getSpaceRankCount() {
		return spaceRankInfoDaoMapper.getSpaceRankCount();
	}

	@Override
	public boolean saveSpaceRank(SpaceRankInfo spaceRank) {
		int flag=spaceRankInfoDaoMapper.saveSpaceRank(spaceRank);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delSpaceRankInfo(int spaceId) {
		int flag=spaceRankInfoDaoMapper.delSpaceRankInfo(spaceId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public SpaceRankInfo findSpaceRankById(Integer id) {
		return spaceRankInfoDaoMapper.findSpaceRankById(id);
	}

	@Override
	public boolean updateSpaceRankInfo(SpaceRankInfo spaceRank) {
		int flag=spaceRankInfoDaoMapper.updateSpaceRankInfo(spaceRank);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public float getSpacePrice(Map<String, Object> map) {
		return spaceRankInfoDaoMapper.getSpacePrice(map);
	}

}
