package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.LoginLogInfo;

public interface LoginLogInfoService {

	void saveLogInfo(LoginLogInfo loginLogInfo);

	List<LoginLogInfo> findLoginLogWithPage(Map<String, Object> map);

	int getLoginLogCount();

	void updateLoginLogInfo(Map<String, Object> map);

	boolean delLoginLogInfo(int loginId);

	List<LoginLogInfo> findAllLoginInfo();

}
