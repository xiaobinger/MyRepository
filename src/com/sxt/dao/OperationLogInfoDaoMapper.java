package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.LoginLogInfo;
import com.sxt.entity.OperationLogInfo;

public interface OperationLogInfoDaoMapper {

	void saveOpreationLogInfo(OperationLogInfo operationLogInfo);

	List<LoginLogInfo> findOperationLogWithPage(Map<String, Object> map);

	int getOperationLogCount();

	int delOperationLogInfo(int operaId);

}
