package com.contest.pojo;

import java.util.List;

import com.contest.model.RoleModel;
import com.contest.model.UserModel;

public class UserAndMultiRoles extends UserModel{
	
	private List<RoleModel> roles;

	public UserAndMultiRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	

}
