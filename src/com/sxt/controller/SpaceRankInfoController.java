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
import com.sxt.entity.SpaceRankInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.SpaceRankInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/spaceRankInfoController/")
public class SpaceRankInfoController extends CommController{
	@Resource(name="spaceRankInfoService")
	private SpaceRankInfoService spaceRankInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findSpaceRankAllInfo")
	@ResponseBody
	public String findSpaceRankAllInfo(){
		List<SpaceRankInfo> list=spaceRankInfoService.findSpaceRankAllInfo();
		return toJson(list);
	}
	
	@RequestMapping("findSpaceRankWithPage")
	@ResponseBody
	public String findSpaceRankWithPage(HttpServletRequest req,Integer page,Integer rows,
			String airLineNumber,Integer rankId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("airLineNumber", airLineNumber);
		map.put("rankId", rankId);
		List<SpaceRankInfo> list=spaceRankInfoService.findSpaceRankWithPage(map);
		int count=spaceRankInfoService.getSpaceRankCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SPACEMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("saveSpaceRank")
	@ResponseBody
	public String saveSpaceRank(HttpServletRequest req,SpaceRankInfo spaceRank){
		boolean flag=spaceRankInfoService.saveSpaceRank(spaceRank);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SPACEMENU+"-"+CommOperationLog.OPERADD,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("updateSpaceRankInfo")
	@ResponseBody
	public String updateSpaceRankInfo(HttpServletRequest req,SpaceRankInfo spaceRank){
		boolean flag=spaceRankInfoService.updateSpaceRankInfo(spaceRank);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SPACEMENU+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("delSpaceRankInfo")
	@ResponseBody
	private String delSpaceRankInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		int count=0;
		for(String id:ids){
			boolean flag=spaceRankInfoService.delSpaceRankInfo(Integer.parseInt(id));
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SPACEMENU+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
	
	@RequestMapping("findSpaceRankById")
	@ResponseBody
	public String findSpaceRankById(Integer id){
		SpaceRankInfo spaceRank=spaceRankInfoService.findSpaceRankById(id);
		return toJson(spaceRank);
	}
	
	@RequestMapping("getSpacePrice")
	public String getSpacePrice(HttpServletRequest req){
		Integer airLineId=Integer.parseInt(req.getParameter("airLineId"));
		Integer rankId=Integer.parseInt(req.getParameter("spaceId"));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("airLineId", airLineId);
		map.put("rankId",rankId);
		float spacePrice=spaceRankInfoService.getSpacePrice(map);
		return toJson(spacePrice);
	}
}
