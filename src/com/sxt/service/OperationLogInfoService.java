package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.LoginLogInfo;
import com.sxt.entity.OperationLogInfo;

public interface OperationLogInfoService {

	void saveOpreationLogInfo(OperationLogInfo operationLogInfo);

	List<LoginLogInfo> findOperationLogWithPage(Map<String, Object> map);

	int getOperationLogCount();

	boolean delOperationLogInfo(int operaId);

}
