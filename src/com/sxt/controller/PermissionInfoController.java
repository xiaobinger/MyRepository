package com.sxt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.PermissionInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.PermissionInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/permissionInfoController/")
public class PermissionInfoController extends CommController{
	@Resource(name="permissionInfoService")
	private PermissionInfoService permissionInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findPermissionAllInfo")
	@ResponseBody
	public String findPermissionAllInfo(){
		List<PermissionInfo> list=permissionInfoService.findPermissionAllInfo();
		return toJson(list);
	}
	@RequestMapping("findPermissionWithPage")
	@ResponseBody
	public String findPermissionWithPage(Integer page,Integer rows,String permiNumber,String permiName,HttpServletRequest req){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows", rows);
		map.put("permiNumber", permiNumber);
		map.put("permiName", permiName);
		List<PermissionInfo>list=permissionInfoService.findPermissionWithPage(map);
		int count=permissionInfoService.getPermissionCount();
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows", list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.PERMISSION+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
		
	}
	
	
	@RequestMapping("findPermissionById")
	@ResponseBody
	public String findPermissionById(Integer id){
		PermissionInfo permission=permissionInfoService.findPermissionById(id);
		return toJson(permission);
	}
	
	
}
