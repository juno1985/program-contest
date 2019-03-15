package com.contest.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserPojo {
	
	private Integer id;
	
	@NotEmpty(message="用户名不为空")
	@Size(min=4,message="最小长度2")
	private String username;
	
	@NotEmpty(message="密码不为空")
	@Size(min=6,message="最小长度2")
	private String password;
	
	private String phone;
	
	@Email
	private String email;

	public UserPojo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserPojo [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + "]";
	}

}
