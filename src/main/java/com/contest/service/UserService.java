package com.contest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.mapper.UserAndRolesMapper;
import com.contest.pojo.UserAndMultiRoles;

@Service
public class UserService {

	@Autowired
	private UserAndRolesMapper userAndRolesMapper;
	
	public UserAndMultiRoles findByUserName(String username) {
		return userAndRolesMapper.findByUserName(username);
	}
}
