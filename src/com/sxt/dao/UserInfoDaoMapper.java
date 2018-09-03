package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.UserInfo;

public interface UserInfoDaoMapper {

	UserInfo login(Map<String, Object> map);

	List<UserInfo> findUserWithPage(Map<String, Object> map);

	int getUserCount();

	UserInfo findUserById(Integer id);

	int updateUserInfo(UserInfo user);

	int updateRolerOfUser(Map<String, Object> map);

	int deleteRolerofUser(Map<String, Object> map);

	int saveUser(UserInfo user);

	UserInfo findUserName(String userName);

	Integer getUserId(String userName);

	int delUserInfo(int userId);

	List<UserInfo> findUserWithPageByRolerId(Map<String, Object> map);

	int getUserCountByRolerId(Integer rolerId);

	List<UserInfo> findUserAllInfo();

	int deleteAllRolerofUser(Integer userId);

	UserInfo findUserByUserName(Map<String, Object> map);

}
