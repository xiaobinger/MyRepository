package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.UserInfo;

public interface UserInfoService {

	UserInfo login(Map<String, Object> map);

	List<UserInfo> findUserWithPage(Map<String, Object> map);

	int getUserCount();

	UserInfo findUserById(Integer id);

	boolean updateUserInfo(UserInfo user);

	boolean updateRolerOfUser(Map<String, Object> map);

	boolean deleteRolerofUser(Map<String, Object> map);

	boolean saveUser(UserInfo user);

	boolean findUserName(String userName);

	Integer getUserId(String userName);

	boolean delUserInfo(int userId);

	List<UserInfo> findUserWithPageByRolerId(Map<String, Object> map);

	int getUserCountByRolerId(Integer rolerId);

	List<UserInfo> findUserAllInfo();

	boolean deleteAllRolerofUser(int userId);

}
