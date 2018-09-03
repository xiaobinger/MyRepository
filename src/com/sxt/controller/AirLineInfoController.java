package com.sxt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.AirLineInfo;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.AirLineInfoService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/airLineInfoController/")
public class AirLineInfoController extends CommController{
	@Resource(name="airLineInfoService")
	private AirLineInfoService airLineInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findAirLineInfoById")
	@ResponseBody
	public void findAirLineInfoById(Integer airLineId,HttpServletResponse resp)
			throws IOException{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("airLineId", airLineId);
		AirLineInfo airLine=airLineInfoService.findAirLineInfoById(map);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		StringBuffer sbf=new StringBuffer();
		sbf.append(airLine.getAirLineNumber());
		sbf.append(",");
		sbf.append(airLine.getPlaneSince());
		sbf.append(",");
		sbf.append(airLine.getDeparCity());
		sbf.append(",");
		sbf.append(airLine.getArrivalCity());
		sbf.append(",");
		sbf.append(airLine.getAirLineTime());
		sbf.append(",");
		sbf.append(airLine.getOutTime());
		sbf.append(",");
		sbf.append(airLine.getInTime());
		sbf.append(",");
		sbf.append(airLine.getSendState());
		out.print(sbf.toString());
	}
	
	@RequestMapping("findAirLineAllInfo")
	@ResponseBody
	public String findAirLineAllInfo(){
		List<AirLineInfo> list=airLineInfoService.findAirLineAllInfo();
		return toJson(list);
	}
	
	@RequestMapping("findAirLineWithPage")
	@ResponseBody
	public String findAirLineWithPage(HttpServletRequest req,Integer page,Integer rows,String airLineNumber,Integer planeId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("airLineNumber", airLineNumber);
		map.put("planeId", planeId);
		List<AirLineInfo> list=airLineInfoService.findAirLineWithPage(map);
		List<AirLineInfo> listAirLine=new ArrayList<AirLineInfo>();
		listAirLine.add(list.get(0));
		for(int i=0;i<list.size();i++){
			if(list.get(i).getAirLineId()!=list.get(0).getAirLineId()){
				listAirLine.add(list.get(i));
			}
		}
		int count=airLineInfoService.getAirLineCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",listAirLine);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.FLIGHTMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
		
	}
	
	@RequestMapping("saveAirLine")
	@ResponseBody
	public String saveAirLine(AirLineInfo airLine,HttpServletRequest req){
		boolean flag=airLineInfoService.saveAirLine(airLine);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.FLIGHTMENU+"-"+CommOperationLog.OPERADD,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag); 
	}
	
	@RequestMapping("updateAirLineInfo")
	@ResponseBody
	public String updateAirLineInfo(AirLineInfo airLine,HttpServletRequest req){
		boolean flag=airLineInfoService.updateAirLineInfo(airLine);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.FLIGHTMENU+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag); 
	}
	
	@RequestMapping("findAirLineById")
	@ResponseBody
	public String findAirLineById(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("airLineId", id);
		AirLineInfo airLine=airLineInfoService.findAirLineInfoById(map);
		return toJson(airLine);
	}
	
	@RequestMapping("delAirLineInfo")
	@ResponseBody
	public String delAirLineInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		int count=0;
		for(String id:ids){
			boolean flag=airLineInfoService.delAirLineInfo(Integer.parseInt(id));
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.FLIGHTMENU+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
}
