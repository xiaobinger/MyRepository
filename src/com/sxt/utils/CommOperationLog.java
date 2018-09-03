package com.sxt.utils;

import com.sxt.entity.OperationLogInfo;
import com.sxt.service.OperationLogInfoServiceImpI;

public class CommOperationLog implements CommOperations{
	public static void saveOpreationLogInfo(String operaUserName,String operaInfo,
			String operaTime,String remark){
		OperationLogInfoServiceImpI operationLogInfoService=new OperationLogInfoServiceImpI();
		OperationLogInfo operationLogInfo=new OperationLogInfo(operaUserName, operaInfo, operaTime, remark); 
		operationLogInfoService.saveOpreationLogInfo(operationLogInfo);
	}
}
