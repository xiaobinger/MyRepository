package com.sxt.realm;

import java.util.HashMap;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.sxt.dao.UserInfoDaoMapper;
import com.sxt.entity.PermissionInfo;
import com.sxt.entity.RolerInfo;
import com.sxt.entity.UserInfo;

public class MyRealm extends AuthorizingRealm {
	@Resource(name="userInfoDaoMapper")
	private UserInfoDaoMapper userInfoDaoMapper;
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken  usernamePasswordToken=(UsernamePasswordToken)token;
		String uname=usernamePasswordToken.getUsername();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userName", uname);
		UserInfo user=userInfoDaoMapper.findUserByUserName(map);
		SimpleAuthenticationInfo info=null;
		if(user.getUserName().equals(uname)){
			info=new SimpleAuthenticationInfo(user.getUserName(), user.getUserPass(),getName()) ;
		}
		return info;
	}

	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String uname=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo   info=new SimpleAuthorizationInfo();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userName", uname);
		UserInfo user=userInfoDaoMapper.findUserByUserName(map);
		//角色
		if(user.getUserName().equals(uname)){
			Set<RolerInfo>rolers=user.getSetRolers();
			Set<PermissionInfo>setPermission=user.getSetPermission();
			Iterator<RolerInfo>iter=rolers.iterator();
			Set<String>setRolerName=new HashSet<String>();
			Iterator<PermissionInfo>it=setPermission.iterator();
			Set<String>setPermissName=new HashSet<String>();
			while(iter.hasNext()){
				RolerInfo roler=iter.next();
				setRolerName.add(roler.getRolerName());
			}
			while(it.hasNext()){
				PermissionInfo permission=it.next();
				setPermissName.add(permission.getPermiName());
			}
			info.addStringPermissions(setPermissName);
			info.addRoles(setRolerName);
		}
		return info;
	}





}
