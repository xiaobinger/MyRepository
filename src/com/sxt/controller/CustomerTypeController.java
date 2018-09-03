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

import com.sxt.entity.CityInfo;
import com.sxt.entity.CustomerType;
import com.sxt.entity.Discount;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.CustomerTypeService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/customerTypeController/")
public class CustomerTypeController extends CommController{
	@Resource(name="customerTypeService")
	private CustomerTypeService customerTypeService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findDiscountAllInfo")
	@ResponseBody
	public String findDiscountAllInfo(){
		List<Discount> list=customerTypeService.findDiscountAllInfo();
		return toJson(list);
	}
	
	@RequestMapping("saveCustomType")
	@ResponseBody
	public String saveCustomType(CustomerType cusType,HttpServletRequest req){
		boolean flag=customerTypeService.saveCustomType(cusType);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.CUSTYPE+"-"+CommOperationLog.OPERADD,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("findCustomTypeWithPage")
	@ResponseBody
	public String findCustomTypeWithPage(HttpServletRequest req,Integer page,Integer rows,String cusTypeName){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("page",(page-1)*rows);
		map.put("rows",rows);
		map.put("cusTypeName",cusTypeName);
		List<CityInfo> list=customerTypeService.findCustomTypeWithPage(map);
		int count=customerTypeService.getCusTypeCount();
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.CUSTYPE+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("findCusTypeById")
	@ResponseBody
	public String findCusTypeById(Integer id){
		CustomerType cusType=customerTypeService.findCusTypeById(id);
		return toJson(cusType);
	}
	
	@RequestMapping("updateCusTypeInfo")
	@ResponseBody
	public String updateCusTypeInfo(CustomerType cusType,HttpServletRequest req){
		boolean flag=customerTypeService.updateCusTypeInfo(cusType);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.CUSTYPE+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	
	@RequestMapping("findCusTypeAllInfo")
	@ResponseBody
	public String findCusTypeAllInfo(){
		List<CustomerType>list=customerTypeService.findCusTypeAllInfo();
		return toJson(list);
	}
}
