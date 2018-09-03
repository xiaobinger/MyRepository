package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.TalkInfo;

public interface TalkInfoService {

	boolean publishTalk(Map<String, Object> map);

	List<TalkInfo> findAllInnerTalk(Map<String, Object> map);

	boolean delTalkInfo(Integer talkId);

	boolean OutTalk(Map<String, Object> map);

	TalkInfo findOutMessage(Map<String, Object> map);

	boolean updataOutMessage(Map<String, Object> map);

}
