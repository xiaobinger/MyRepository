package com.sxt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.PermissionInfo;
import com.sxt.entity.RolerInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.RolerInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/rolerInfoController/")
public class RolerInfoController extends CommController{
	@Resource(name="rolerInfoService")
	private RolerInfoService rolerInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findRolerAllInfo")
	@ResponseBody
	public String findRolerAllInfo(){
		List<RolerInfo> list=rolerInfoService.findRolerAllInfo();
		return toJson(list);
	}
	@RequestMapping("findRolerWithPage")
	@ResponseBody
	public String findRolerWithPage(Integer page,Integer rows,String rolerNumber,String rolerName,HttpServletRequest req){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] permiId=req.getParameterValues("permiId[]");
		List<Integer> listPermiId=new ArrayList<Integer>();
		if(permiId!=null&&permiId.length>0){
				for(String p:permiId){
					listPermiId.add(Integer.parseInt(p));
				}
		}
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("rolerNumber", rolerNumber);
		map.put("rolerName", rolerName);
		map.put("listPermiId", listPermiId);
		List<RolerInfo> list=rolerInfoService.findRolerWithPage(map);
		int count=rolerInfoService.getRolerCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.ROLER+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	
	
	@RequestMapping("findRolerById")
	@ResponseBody
	public String findRolerById(Integer id){
		RolerInfo roler=rolerInfoService.findRolerById(id);
		return toJson(roler);
	}
	@RequestMapping("findPermissionOfRoler")
	@ResponseBody
	public String findPermissionOfRoler(Integer id){
		RolerInfo roler=rolerInfoService.findRolerById(id);
		List<Integer> listPermiId=new ArrayList<Integer>();
		Set<PermissionInfo> setPermission=roler.getSetPermission();
		Iterator<PermissionInfo>iter=setPermission.iterator();
		while(iter.hasNext()){
			listPermiId.add(iter.next().getPermiId());
		}
		return toJson(listPermiId);
	}
	@RequestMapping("findRolerWithPageByPermiId")
	@ResponseBody
	public String findRolerWithPageByPermiId(Integer page,Integer rows,Integer permiId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("permiId", permiId);
		List<RolerInfo> list=rolerInfoService.findRolerWithPageByPermiId(map);
		int count=rolerInfoService.getRolerCountByPermiId(permiId);
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		return toJson(mapData);
	}
	
	@RequestMapping("deletePermissionofRoler")
	@ResponseBody
	public String deletePermissionofRoler(HttpServletRequest req,Integer permiId){
		int count=0;
		String[] arrRolerId=req.getParameterValues("id[]");
		for(String strRolerId:arrRolerId){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("rolerId",Integer.parseInt(strRolerId));
			map.put("permiId",permiId);
			boolean flag=rolerInfoService.deletePermissionofRoler(map);
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.PERMISSION+"-"+"剔除角色权限",
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
	
	@RequestMapping("updateRolerInfo")
	@ResponseBody
	public String updateRolerInfo(RolerInfo roler,HttpServletRequest req){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] permiId=req.getParameterValues("permiId[]");
		List<Integer> listPermiId=new ArrayList<Integer>();
		if(permiId!=null&&permiId.length>0){
				for(String p:permiId){
					listPermiId.add(Integer.parseInt(p));
				}
		}
		rolerInfoService.deleteAllPermissionofRoler(roler.getRolerId());
		
		boolean flag=false;
		int count=0;
		for(Integer pId:listPermiId){
			map.put("rolerId", roler.getRolerId());
			map.put("permiId", pId);
			flag=rolerInfoService.updatePermissionOfRoler(map);
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.ROLER+"-"+"权限分配",
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
}
