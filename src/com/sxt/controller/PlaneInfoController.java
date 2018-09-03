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
import com.sxt.entity.PlaneInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.PlaneInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/planeInfoController/")
public class PlaneInfoController extends CommController{
	@Resource(name="planeInfoService")
	private PlaneInfoService planeInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findPlaneWithPage")
	@ResponseBody
	public String findPlaneWithPage(HttpServletRequest req,Integer page,Integer rows,
			String planeNumber,String planeSince){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("planeNumber", planeNumber);
		map.put("planeSince", planeSince);
		List<PlaneInfo> list=planeInfoService.findPlaneWithPage(map);
		int count=planeInfoService.getPlaneCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.PLANEMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("savePlane")
	@ResponseBody
	public String savePlane(HttpServletRequest req,PlaneInfo plane){
		boolean flag=planeInfoService.savePlane(plane);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.PLANEMENU+"-"+CommOperationLog.OPERADD,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	@RequestMapping("delPlaneInfo")
	@ResponseBody
	public String delPlaneInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		int count=0;
		for(String id:ids){
			boolean flag=planeInfoService.delPlaneInfo(Integer.parseInt(id));
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.PLANEMENU+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
	
	@RequestMapping("findPlaneById")
	@ResponseBody
	public String findPlaneById(Integer id){
		PlaneInfo plane=planeInfoService.findPlaneById(id);
		return toJson(plane);
	}
	
	@RequestMapping("updatePlaneInfo")
	@ResponseBody
	public String updatePlaneInfo(HttpServletRequest req,PlaneInfo plane){
		boolean flag=planeInfoService.updatePlaneInfo(plane);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.PLANEMENU+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("findPlaneAllInfo")
	@ResponseBody
	public String findPlaneAllInfo(){
		List<PlaneInfo> list=planeInfoService.findPlaneAllInfo();
		return toJson(list);
	}
}
