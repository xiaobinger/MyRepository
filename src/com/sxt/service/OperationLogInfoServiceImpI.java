package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.OperationLogInfoDaoMapper;
import com.sxt.entity.LoginLogInfo;
import com.sxt.entity.OperationLogInfo;

@Transactional
@Component("operationLogInfoService")
public class OperationLogInfoServiceImpI implements OperationLogInfoService {

	@Resource(name="operationLogInfoDaoMapper")
	private OperationLogInfoDaoMapper operationLogInfoDaoMapper;

	@Override
	public void saveOpreationLogInfo(OperationLogInfo operationLogInfo) {
		operationLogInfoDaoMapper.saveOpreationLogInfo(operationLogInfo);
		
	}

	@Override
	public List<LoginLogInfo> findOperationLogWithPage(Map<String, Object> map) {
		List<LoginLogInfo> list=operationLogInfoDaoMapper.findOperationLogWithPage(map);
		return list;
	}

	@Override
	public int getOperationLogCount() {
		int count=operationLogInfoDaoMapper.getOperationLogCount();
		return count;
	}

	@Override
	public boolean delOperationLogInfo(int operaId) {
		int flag=operationLogInfoDaoMapper.delOperationLogInfo(operaId);
		if(flag!=0){
			return true;
		}
		return false;
	}
}
