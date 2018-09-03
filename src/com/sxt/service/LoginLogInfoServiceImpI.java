package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.LoginLogInfoDaoMapper;
import com.sxt.entity.LoginLogInfo;

@Transactional
@Component("loginLogInfoService")
public class LoginLogInfoServiceImpI implements LoginLogInfoService {

	@Resource(name="loginLogInfoDaoMapper")
	private LoginLogInfoDaoMapper loginLogInfoDaoMapper;

	@Override
	public void saveLogInfo(LoginLogInfo loginLogInfo) {
		loginLogInfoDaoMapper.saveLogInfo(loginLogInfo);
	}

	@Override
	public List<LoginLogInfo> findLoginLogWithPage(Map<String, Object> map) {
		List<LoginLogInfo> list=loginLogInfoDaoMapper.findLoginLogWithPage(map);
		return list;
	}

	@Override
	public int getLoginLogCount() {
		int count=loginLogInfoDaoMapper.getLoginLogCount();
		return count;
	}

	@Override
	public void updateLoginLogInfo(Map<String, Object> map) {
		loginLogInfoDaoMapper.updateLoginLogInfo(map);
		
	}

	@Override
	public boolean delLoginLogInfo(int loginId) {
		int flag=loginLogInfoDaoMapper.delLoginLogInfo(loginId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<LoginLogInfo> findAllLoginInfo() {
		
		return loginLogInfoDaoMapper.findAllLoginInfo();
	}
}
