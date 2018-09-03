package com.sxt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.dao.UserInfoDaoMapper;
import com.sxt.entity.LoginLogInfo;
import com.sxt.entity.OperationLogInfo;
import com.sxt.entity.RolerInfo;
import com.sxt.entity.TalkInfo;
import com.sxt.entity.UserInfo;
import com.sxt.service.LoginLogInfoService;
import com.sxt.service.OperationLogInfoService;
import com.sxt.service.RolerInfoService;
import com.sxt.service.TalkInfoService;
import com.sxt.service.UserInfoService;
import com.sxt.utils.CommController;
import com.sxt.utils.CommOperationLog;

@Controller
@RequestMapping("/userInfoController/")
public class UserInfoController extends CommController{
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	@Resource(name="rolerInfoService")
	private RolerInfoService rolerInfoService;
	@Resource(name="loginLogInfoService")
	private LoginLogInfoService loginLogInfoService;
	@Resource(name="operationLogInfoService")
	private OperationLogInfoService operationLogInfoService;
	@Resource(name="userInfoDaoMapper")
	private UserInfoDaoMapper userInfoDaoMapper;
	@Resource(name="talkInfoService")
	public TalkInfoService talkInfoService;
	
	@RequestMapping("findUserWithPage")
	@ResponseBody
	public String findUserWithPage(Integer page,Integer rows,String userNumber,String userName,String userSex,HttpServletRequest req){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] rolerId=req.getParameterValues("rolerId[]");
		List<Integer> listRolerId=new ArrayList<Integer>();
		if(rolerId!=null&&rolerId.length>0){
				for(String r:rolerId){
				listRolerId.add(Integer.parseInt(r));
			}
		}
		map.put("page", (page-1)*rows);
		map.put("rows",rows);
		map.put("userNumber", userNumber);
		map.put("userName", userName);
		map.put("listRolerId", listRolerId);
		map.put("userSex", userSex);
		List<UserInfo> list=userInfoService.findUserWithPage(map);
		for(UserInfo u:list){
			if("0".equals(u.getUserSex())){
				u.setUserSex("女");
			}else if("1".equals(u.getUserSex())){
				u.setUserSex("男");
			}
		}
		int count=userInfoService.getUserCount();
		Map<String,Object> mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
		operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
				CommOperationLog.SYSMENU+"-"+CommOperationLog.USER+"-"+CommOperationLog.OPERFIND,
				formatTime(new Date()),userInfo.getRemark()));
		return toJson(mapData);
	}
	
	@RequestMapping("findUserById")
	@ResponseBody
	public String findUserById(Integer id){
		UserInfo user=userInfoService.findUserById(id);
		return toJson(user);
	}
	@RequestMapping("findRolerOfUser")
	@ResponseBody
	public String findRolerOfUser(Integer id){
		UserInfo user=userInfoService.findUserById(id);
		List<Integer> listRolerId=new ArrayList<Integer>();
		Set<RolerInfo>rolers=user.getSetRolers();
		Iterator<RolerInfo>iter=rolers.iterator();
		while(iter.hasNext()){
			listRolerId.add(iter.next().getRolerId());
		}
		return toJson(listRolerId);
	}
	
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public String updateUserInfo(UserInfo user,HttpServletRequest req){
		boolean flag=userInfoService.updateUserInfo(user);
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.USER+"-"+CommOperationLog.OPERUPDATE,
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	@RequestMapping("saveUser")
	@ResponseBody
	public String saveUser(UserInfo user,HttpServletRequest req){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] rolerId=req.getParameterValues("rolerId[]");
		List<Integer> listRolerId=new ArrayList<Integer>();
		if(rolerId!=null&&rolerId.length>0){
				for(String r:rolerId){
				listRolerId.add(Integer.parseInt(r));
			}
		}
		userInfoService.deleteAllRolerofUser(user.getUserId());
		boolean flag=false;
		int count=0;
		for(Integer rId:listRolerId){
			map.put("userId", user.getUserId());
			map.put("rolerId", rId);
			flag=userInfoService.updateRolerOfUser(map);
			if(flag==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.USER+"-"+"角色分配",
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("delUserInfo")
	@ResponseBody
	public String delUserInfo(HttpServletRequest req){
		int count=0;
		String[] arrUserId=req.getParameterValues("id[]");
		for(String strUserId:arrUserId){
			boolean flag_delRolerOfUser=userInfoService.deleteAllRolerofUser(Integer.parseInt(strUserId));
			boolean flag=userInfoService.delUserInfo(Integer.parseInt(strUserId));
			if(flag==false||flag_delRolerOfUser==false){
				count++;
			}
		}
		if(count==0){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.USER+"-"+CommOperationLog.OPERDEL,
					formatTime(new Date()),userInfo.getRemark()));
			return toJson(true);
		}
		return toJson(false);
	}
	
	@RequestMapping("deleteRolerofUser")
	@ResponseBody
	public String deleteRolerofUser(HttpServletRequest req,Integer rolerId){
		String[] arrUserId=req.getParameterValues("id[]");
		boolean flag=false;
		for(String strUserId:arrUserId){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("rolerId",rolerId);
			map.put("userId",Integer.parseInt(strUserId));
			flag=userInfoService.deleteRolerofUser(map);
		}
		if(flag){
			UserInfo userInfo=((UserInfo)req.getSession().getAttribute("user"));
			operationLogInfoService.saveOpreationLogInfo(new OperationLogInfo(userInfo.getUserName(),
					CommOperationLog.SYSMENU+"-"+CommOperationLog.ROLER+"-"+"剔除用户角色",
					formatTime(new Date()),userInfo.getRemark()));
		}
		return toJson(flag);
	}
	
	@RequestMapping("findUserWithPageByRolerId")
	@ResponseBody
	public String findUserWithPageByRolerId(Integer page,Integer rows,Integer rolerId){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("page",(page-1)*rows);
		map.put("rows",rows);
		map.put("rolerId",rolerId);
		List<UserInfo> list=userInfoService.findUserWithPageByRolerId(map);
		int count=userInfoService.getUserCountByRolerId(rolerId);
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("total",count);
		mapData.put("rows",list);
		return toJson(mapData);
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public void logout(HttpServletRequest req,HttpServletResponse resp){
		Subject  subject=SecurityUtils.getSubject();
			String LoginTime=(String)req.getSession().getAttribute("loginTime");
			String logoutTime=formatTime(new Date());
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("loginTime",LoginTime);
			map.put("logoutTime",logoutTime);
			map.put("loginName",(String)subject.getPrincipal());
			loginLogInfoService.updateLoginLogInfo(map);
			req.getSession().removeAttribute("talkList");
			subject.logout();
			try {
				resp.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	@RequestMapping("login")
	@ResponseBody
	public String login(String userName,String userPass,HttpServletRequest req){
		Subject  subject=SecurityUtils.getSubject();
		HttpSession session=req.getSession();
		try {
			UsernamePasswordToken  token=new UsernamePasswordToken(userName,new Md5Hash(userPass, userName+userPass, 10).toString());
			subject.login(token);
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("userName", subject.getPrincipal());
			UserInfo userInfo=userInfoDaoMapper.findUserByUserName(map1);
			subject.getSession().setAttribute("user", userInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String loginTime=formatTime(new Date());
		LoginLogInfo loginLogInfo=new LoginLogInfo(userName, req.getRemoteAddr(), 
				loginTime, null);
		session.setAttribute("loginTime", loginTime);
		loginLogInfoService.saveLogInfo(loginLogInfo);
		
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("talkType","InnerMessage");
			List<TalkInfo> listTalk=talkInfoService.findAllInnerTalk(map);
			req.getSession().setAttribute("talkList", listTalk);
		
		return toJson(subject.isAuthenticated());
		
	}
	
	@RequestMapping("regist")
	@ResponseBody
	public String regist(UserInfo user){
		int count=userInfoService.getUserCount();
		user.setUserNumber((count+1000)+"");
		boolean flag_findUserName=userInfoService.findUserName(user.getUserName());
		if(flag_findUserName==true){
			return toJson("exist");
		}
		String salt=user.getUserName()+user.getUserPass();
		user.setUserPass(new Md5Hash(user.getUserPass(), salt, 10).toString());
		boolean flag=userInfoService.saveUser(user);
		Integer rolerId=rolerInfoService.getRolerId("普通员工");
		Integer userId=userInfoService.getUserId(user.getUserName());
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("rolerId",rolerId);
		map.put("userId",userId);
		userInfoService.updateRolerOfUser(map);
		return toJson(flag);
		
	}
	
	@RequestMapping("getNewTime")
	@ResponseBody
	public String getNewTime(){
		Long time=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss E");
		Date d=new Date(time);
		return sdf.format(d);
	}
	
	@RequestMapping("findUserAllInfo")
	@ResponseBody
	public String findUserAllInfo(){
		List<UserInfo> list=userInfoService.findUserAllInfo();
		return toJson(list);
	}
	
}
