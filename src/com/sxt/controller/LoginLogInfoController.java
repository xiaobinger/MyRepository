package com.sxt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.LoginLogInfo;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.LoginLogInfoService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping("/loginLogInfoController/")
public class LoginLogInfoController extends CommController{
	@Resource(name="loginLogInfoService")
	private LoginLogInfoService loginLogInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findLoginLogWithPage")
	@ResponseBody
	public String findLoginLogWithPage(Integer page,Integer rows,String loginName,String loginTime,HttpServletRequest req){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("page",(page-1)*rows);
		map.put("rows",rows);
		map.put("loginName",loginName);
		map.put("loginTime",loginTime);
		List<LoginLogInfo> list=loginLogInfoService.findLoginLogWithPage(map);
		int count=loginLogInfoService.getLoginLogCount();
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.LOGINLOG+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
		
	}
	
	@RequestMapping("delLoginLogInfo")
	@ResponseBody
	public String delLoginLogInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		boolean flag=false;
		for(String strId:ids){
			flag=loginLogInfoService.delLoginLogInfo(Integer.parseInt(strId));
		}
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.LOGINLOG+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("exportExcel")
	@ResponseBody
	public String exportExcel(HttpServletRequest req){
		String srcFile=req.getRealPath("/")+"resource/templateLoginLog.xls";
		String targetFile="C://Users//Administrator//Desktop//登录日志明细.xls";
		Map<String,Object>map=new HashMap<String,Object>();
		List<LoginLogInfo>list=loginLogInfoService.findAllLoginInfo();
		for(LoginLogInfo loginLog:list){
			try {
				loginLog.setInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E").parse(loginLog.getLoginTime()));
				if(loginLog.getLogoutTime()!=null){
					loginLog.setOutTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E").parse(loginLog.getLogoutTime()));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("loginLog", list);
		 XLSTransformer transformer = new XLSTransformer();
		 try {
			 transformer.transformXLS(srcFile, map, targetFile);
			return toJson(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return toJson(false);
	}
}
