package com.sxt.service;

import java.util.List;
import java.util.Map;






import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.*;

import com.sxt.dao.UserInfoDaoMapper;
import com.sxt.entity.UserInfo;

@Transactional
@Component("userInfoService")
public class UserInfoServiceImpI implements UserInfoService {
	@Resource(name="userInfoDaoMapper")
	private UserInfoDaoMapper userInfoDaoMapper;

	@Override
	public UserInfo login(Map<String, Object> map) {
		UserInfo user=userInfoDaoMapper.login(map);
		return user;
	}

	@Override
	public List<UserInfo> findUserWithPage(Map<String, Object> map) {
		List<UserInfo> list=userInfoDaoMapper.findUserWithPage(map);
		return list;
	}

	@Override
	public int getUserCount() {
		int count=userInfoDaoMapper.getUserCount();
		return count;
	}

	@Override
	public UserInfo findUserById(Integer id) {
		UserInfo user=userInfoDaoMapper.findUserById(id);
		return user;
	}

	@Override
	public boolean updateUserInfo(UserInfo user) {
		int flag=userInfoDaoMapper.updateUserInfo(user);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRolerOfUser(Map<String, Object> map) {
		int flag=userInfoDaoMapper.updateRolerOfUser(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	

	@Override
	public boolean saveUser(UserInfo user) {
		int flag=userInfoDaoMapper.saveUser(user);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean findUserName(String userName) {
		UserInfo user=userInfoDaoMapper.findUserName(userName);
		if(user!=null){
			return true;
		}
		return false;
	}

	@Override
	public Integer getUserId(String userName) {
		Integer userId=userInfoDaoMapper.getUserId(userName);
		return userId;
	}

	@Override
	public boolean delUserInfo(int userId) {
		int flag=userInfoDaoMapper.delUserInfo(userId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<UserInfo> findUserWithPageByRolerId(Map<String, Object> map) {
		List<UserInfo> list=userInfoDaoMapper.findUserWithPageByRolerId(map);
		return list;
	}

	@Override
	public int getUserCountByRolerId(Integer rolerId) {
		int count=userInfoDaoMapper.getUserCountByRolerId(rolerId);
		return count;
	}

	@Override
	public List<UserInfo> findUserAllInfo() {
		List<UserInfo> list=userInfoDaoMapper.findUserAllInfo();
		return list;
	}

	@Override
	public boolean deleteRolerofUser(Map<String, Object> map) {
		int flag=userInfoDaoMapper.deleteRolerofUser(map);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAllRolerofUser(int userId) {
		int flag=userInfoDaoMapper.deleteAllRolerofUser(userId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	
}
