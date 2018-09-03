package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.SpaceRankInfo;

public interface SpaceRankInfoService {

	List<SpaceRankInfo> findSpaceRankAllInfo();

	List<SpaceRankInfo> findSpaceRankWithPage(Map<String, Object> map);

	int getSpaceRankCount();

	boolean saveSpaceRank(SpaceRankInfo spaceRank);

	boolean delSpaceRankInfo(int spaceId);

	SpaceRankInfo findSpaceRankById(Integer id);

	boolean updateSpaceRankInfo(SpaceRankInfo spaceRank);

	float getSpacePrice(Map<String, Object> map);

}
