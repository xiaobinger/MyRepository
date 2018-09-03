package com.sxt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.CityInfo;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.PrivadeInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.CityInfoService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/cityInfoController/")
public class CityInfoController extends CommController{
	@Resource(name="cityInfoService")
	private CityInfoService cityInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findAllCityInfo")
	@ResponseBody
	public String findAllCityInfo() throws IOException{
		List<CityInfo> list=cityInfoService.findAllCityInfo();
		return toJson(list);
	}
	
	@RequestMapping("findPrivadeAllInfo")
	@ResponseBody
	public String findPrivadeAllInfo() throws IOException{
		List<PrivadeInfo> list=cityInfoService.findPrivadeAllInfo();
		return toJson(list);
	}
	
	@RequestMapping("saveCity")
	@ResponseBody
	public String saveCity(CityInfo city,HttpServletRequest req){
		boolean flag=cityInfoService.saveCity(city);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.CITYMENU+"-"+CommOperationLog.OPERADD,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("findCityWithPage")
	@ResponseBody
	public String findCityWithPage(Integer page,Integer rows,HttpServletRequest req){
		Map<String,Object>map=new HashMap<String,Object>();
		String[] privadeId=req.getParameterValues("privadeId[]");
		List<Integer> listPrivadeId=new ArrayList<Integer>();
		if(privadeId!=null&&privadeId.length>0){
				for(String pId:privadeId){
					listPrivadeId.add(Integer.parseInt(pId));
			}
		}
		map.put("page",(page-1)*rows);
		map.put("rows",rows);
		map.put("listPrivadeId",listPrivadeId);
		List<CityInfo> list=cityInfoService.findCityWithPage(map);
		int count=cityInfoService.getCityCount();
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.CITYMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
}
