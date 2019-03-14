package com.contest.mapper;

import com.contest.pojo.UserAndMultiRoles;

public interface UserAndRolesMapper {
	
	public UserAndMultiRoles findByUserName(String username);

}
