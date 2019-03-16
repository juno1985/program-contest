package com.contest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.exception.UserAlreadyExistsException;
import com.contest.mapper.UserAndRolesMapper;
import com.contest.pojo.UserAndMultiRoles;
import com.contest.pojo.UserPojo;

@Service
public class UserService {

	@Autowired
	private UserAndRolesMapper userAndRolesMapper;
	
	public UserAndMultiRoles findByUserName(String username) {
		return userAndRolesMapper.findByUserName(username);
	}

	
	public void addUser(UserPojo userPojo) {
		
		throw new UserAlreadyExistsException("用户名已经存在");
	}
}
