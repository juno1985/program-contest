package com.contest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.contest.exception.UserNotFoundException;
import com.contest.model.RoleModel;
import com.contest.pojo.UserAndMultiRoles;
import com.contest.service.UserService;


public class ContestUserService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAndMultiRoles userAndMultiRoles = userService.findByUserName(username);

		if (userAndMultiRoles == null) {
			throw new UserNotFoundException("用户名不存在");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		// 用于添加用户的权限 只要把用户权限添加到authorities就万事大吉了
		for (RoleModel role : userAndMultiRoles.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		return new org.springframework.security.core.userdetails.User(userAndMultiRoles.getUsername(),
				userAndMultiRoles.getPassword(), authorities);
	}

}
