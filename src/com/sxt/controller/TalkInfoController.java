package com.sxt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.entity.TalkInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.TalkInfoService;
import com.sxt.utils.CommController;

@Controller
@RequestMapping("/talkInfoController/")
public class TalkInfoController extends CommController {
	
	@Resource(name="talkInfoService")
	public TalkInfoService talkInfoService;
	
	
	@RequestMapping("publishTalk")
	@ResponseBody
	public String publishTalk(HttpServletRequest req){
		String talkInfo=req.getParameter("talkInfo");
		Map<String,Object> map=new HashMap<String,Object>();
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		map.put("userId",userInfo.getUserId());
		map.put("talkInfo",talkInfo);
		map.put("talkType","InnerMessage");
		boolean flag=talkInfoService.publishTalk(map);
		if(flag){
			List<TalkInfo> listTalk=talkInfoService.findAllInnerTalk(map);
			req.getSession().setAttribute("talkList", listTalk);
			return toJson(true);
		}
		return toJson(false);
	}
	
	@RequestMapping("delTalkInfo")
	@ResponseBody
	public void delTalkInfo(HttpServletRequest req,HttpServletResponse resp){
		Integer talkId=Integer.parseInt(req.getParameter("talkId"));
		boolean flag=talkInfoService.delTalkInfo(talkId);
		if(flag){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("talkType","InnerMessage");
			List<TalkInfo> listTalk=talkInfoService.findAllInnerTalk(map);
			req.getSession().setAttribute("talkList", listTalk);
			try {
				resp.sendRedirect("../index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("OutTalk")
	@ResponseBody
	public String OutTalk(HttpServletRequest req){
		String talkInfo=req.getParameter("talkInfo");
		Map<String,Object> map=new HashMap<String,Object>();
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		map.put("userId",userInfo.getUserId());
		map.put("talkInfo",talkInfo);
		map.put("talkType","OutMessage");
		TalkInfo talk=talkInfoService.findOutMessage(map);
		if(talk!=null){
			boolean flag=talkInfoService.updataOutMessage(map);
			if(flag){
				return toJson(true);
			}
			return toJson(false);
		}
		boolean flag=talkInfoService.OutTalk(map);
		if(flag){
			return toJson(true);
		}
		return toJson(false);
	}
}
