package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.LoginLogInfo;

public interface LoginLogInfoDaoMapper {

	void saveLogInfo(LoginLogInfo loginLogInfo);

	List<LoginLogInfo> findLoginLogWithPage(Map<String, Object> map);

	int getLoginLogCount();

	void updateLoginLogInfo(Map<String, Object> map);

	int delLoginLogInfo(int loginId);

	List<LoginLogInfo> findAllLoginInfo();

}
