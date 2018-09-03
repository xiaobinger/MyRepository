package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.SpaceRankInfo;

public interface SpaceRankInfoDaoMapper {

	List<SpaceRankInfo> findSpaceRankAllInfo();

	List<SpaceRankInfo> findSpaceRankWithPage(Map<String, Object> map);

	int getSpaceRankCount();

	int saveSpaceRank(SpaceRankInfo spaceRank);

	int delSpaceRankInfo(int spaceId);

	SpaceRankInfo findSpaceRankById(Integer id);

	int updateSpaceRankInfo(SpaceRankInfo spaceRank);

	float getSpacePrice(Map<String, Object> map);

}
