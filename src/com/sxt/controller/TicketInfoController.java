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
import com.sxt.entity.TicketInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.TicketInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping("/ticketInfoController/")
public class TicketInfoController extends CommController{
	@Resource(name="ticketInfoService")
	private TicketInfoService ticketInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	
	@RequestMapping("findTicketBookWithPage")
	@ResponseBody
	public String findTicketBookWithPage(HttpServletRequest req,Integer page,Integer rows,
			String bookNumber,Integer custId,String bookTime,String airLineNumber,
			Integer rankId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("bookNumber", bookNumber);
		map.put("custId", custId);
		map.put("bookTime", bookTime);
		map.put("airLineNumber", airLineNumber);
		map.put("rankId", rankId);
		List<TicketInfo> list=ticketInfoService.findTicketBookWithPage(map);
		int count=ticketInfoService.getBookCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.BOOKMENU+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("getSumMoneyById")
	@ResponseBody
	public String getSumMoneyById(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		float SumMoney=0.0f;
		for(String id:ids){
			TicketInfo ticket=ticketInfoService.getBookInfoById(Integer.parseInt(id));
			SumMoney+=ticket.getBookPrice()*ticket.getBookSum();
		}
		return toJson(SumMoney);
	}
	
	
	
	@RequestMapping("delTicketBookInfo")
	@ResponseBody
	public String delTicketBookInfo(HttpServletRequest req){
		String[] ids=req.getParameterValues("id[]");
		int count=0;
		for(String id:ids){
			boolean flag=ticketInfoService.delTicketBookInfo(Integer.parseInt(id));
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.BOOKMENU+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("exportExcel")
	@ResponseBody
	public String exportExcel(HttpServletRequest req){
		String srcFile=req.getRealPath("/")+"resource/templateOrder.xls";
		String targetFile="C://Users//Administrator//Desktop//订单报表明细.xls";
		Map<String,Object>map=new HashMap<String,Object>();
		List<TicketInfo>list=ticketInfoService.findAllBooks();
		
		map.put("books", list);
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
