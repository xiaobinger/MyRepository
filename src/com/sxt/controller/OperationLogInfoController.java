package com.sxt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.LoginLogInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;

@Controller
@RequestMapping("/operationLogInfoController/")
public class OperationLogInfoController extends CommController{
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findOperationLogWithPage")
	@ResponseBody
	public String findOperationLogWithPage(Integer page,Integer rows,String operaUserName,String operaTime){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("page",(page-1)*rows);
		map.put("rows",rows);
		map.put("operaUserName",operaUserName);
		map.put("operaTime",operaTime);
		List<LoginLogInfo> list=operationLogInfoService.findOperationLogWithPage(map);
		int count=operationLogInfoService.getOperationLogCount();
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		return toJson(mapData);
	}
	
	@RequestMapping("delOperationLogInfo")
	@ResponseBody
	public String delOperationLogInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		boolean flag=false;
		for(String strId:ids){
			flag=operationLogInfoService.delOperationLogInfo(Integer.parseInt(strId));
		}
		return toJson(flag);
	}
	
}
