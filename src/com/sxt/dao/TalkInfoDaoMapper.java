package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.TalkInfo;

public interface TalkInfoDaoMapper {

	int publishTalk(Map<String, Object> map);

	List<TalkInfo> findAllInnerTalk(Map<String, Object> map);

	int delTalkInfo(Integer talkId);

	int OutTalk(Map<String, Object> map);

	TalkInfo findOutMessage(Map<String, Object> map);

	int updataOutMessage(Map<String, Object> map);

}
