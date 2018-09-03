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

import com.sxt.entity.CustomerInfo;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.CustomerInfoService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/customerInfoController/")
public class CustomerInfoController extends CommController{
	@Resource(name="customerInfoService")
	private CustomerInfoService customerInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	
	@RequestMapping("findCustomerWithPage")
	@ResponseBody
	public String findCustomerWithPage(HttpServletRequest req,Integer page,Integer rows,String custNumber,String custSex,Integer cusTypeId,String custName){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("custNumber", custNumber);
		map.put("custName", custName);
		map.put("cusTypeId", cusTypeId);
		map.put("custSex", custSex);
		List<CustomerInfo> list=customerInfoService.findCustomerWithPage(map);
		for(CustomerInfo c:list){
			if("0".equals(c.getCustSex())){
				c.setCustSex("女");
			}else if("1".equals(c.getCustSex())){
				c.setCustSex("男");
			}
		}
		int count=customerInfoService.getCustomerCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("findCustomerById")
	@ResponseBody
	public String findCustomerById(Integer id){
		CustomerInfo customer=customerInfoService.findCustomerById(id);
		return toJson(customer);
	}
	
	@RequestMapping("findCustomerAllInfo")
	@ResponseBody
	public String findCustomerAllInfo(){
		List<CustomerInfo> list=customerInfoService.findCustomerAllInfo();
		return toJson(list);
	}
	
	@RequestMapping("updateCustomerInfo")
	@ResponseBody
	public String updateCustomerInfo(CustomerInfo customer,HttpServletRequest req){
		boolean flag=customerInfoService.updateCustomerInfo(customer);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("delCustomerInfo")
	@ResponseBody
	public String delCustomerInfo(HttpServletRequest req){
		int count=0;
		String[] arrCustomerId=req.getParameterValues("id[]");
		for(String strCustomerId:arrCustomerId){
			boolean flag=customerInfoService.delCustomerInfo(Integer.parseInt(strCustomerId));
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.CUSTOMMENU+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
}
