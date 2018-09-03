package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.TalkInfoDaoMapper;
import com.sxt.entity.TalkInfo;

@Transactional
@Component("talkInfoService")
public class TalkInfoServiceImpI implements TalkInfoService {

	@Resource(name="talkInfoDaoMapper")
	private TalkInfoDaoMapper talkInfoDaoMapper;

	@Override
	public boolean publishTalk(Map<String, Object> map) {
		int flag=talkInfoDaoMapper.publishTalk(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<TalkInfo> findAllInnerTalk(Map<String, Object> map) {
		
		return talkInfoDaoMapper.findAllInnerTalk(map);
	}

	@Override
	public boolean delTalkInfo(Integer talkId) {
		int flag=talkInfoDaoMapper.delTalkInfo(talkId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean OutTalk(Map<String, Object> map) {
		int flag=talkInfoDaoMapper.OutTalk(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public TalkInfo findOutMessage(Map<String, Object> map) {
		return talkInfoDaoMapper.findOutMessage(map);
	}

	@Override
	public boolean updataOutMessage(Map<String, Object> map) {
		int flag=talkInfoDaoMapper.updataOutMessage(map);
		if(flag!=0){
			return true;
		}
		return false;
	}
}
