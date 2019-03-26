package com.contest.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contest.config.ContestPasswordEncoder;
import com.contest.exception.ContestCommonException;
import com.contest.exception.UserAlreadyExistsException;
import com.contest.exception.UserNotFoundException;
import com.contest.mapper.RoleModelMapper;
import com.contest.mapper.UserAndRolesMapper;
import com.contest.mapper.UserAttrModelMapper;
import com.contest.mapper.UserModelMapper;
import com.contest.mapper.UserRoleModelMapper;
import com.contest.model.RoleModel;
import com.contest.model.RoleModelExample;
import com.contest.model.UserAttrModel;
import com.contest.model.UserModel;
import com.contest.model.UserModelExample;
import com.contest.model.UserRoleModel;
import com.contest.pojo.UserAndMultiRoles;
import com.contest.pojo.UserPojo;

@Service
public class UserService {

	@Autowired
	private UserAndRolesMapper userAndRolesMapper;
	@Autowired
	private UserModelMapper userModelMapper;
	@Autowired
	private UserAttrModelMapper userAttrModelMapper;
	@Autowired
	private RoleModelMapper roleModelMapper;
	@Autowired
	private UserRoleModelMapper userRoleModelMapper;
	
	@Value("${contest.user.role}")
	private String USER_ROLE;
	
	public UserAndMultiRoles findByUserName(String username) {
		return userAndRolesMapper.findByUserName(username);
	}

	@Transactional
	public void addUser(UserPojo userPojo) {
		
		UserModelExample userModelExample = new UserModelExample();
		UserModelExample.Criteria userCriteria = userModelExample.createCriteria();
		userCriteria.andUsernameEqualTo(userPojo.getUsername());
		List<UserModel> userModelList = userModelMapper.selectByExample(userModelExample);
		
		if(userModelList.size() > 0) {
			throw new UserAlreadyExistsException("用户名已经存在");
		}
		else 
		{
			//增加用户
			UserModel userModel = new UserModel();
			userModel.setUsername(userPojo.getUsername());
			userModel.setPassword(new ContestPasswordEncoder().encode(userPojo.getPassword()));
			userModelMapper.insert(userModel);
			
			//这里不能catch,否则事务不起作用
			/*if(true)
				throw new ContestCommonException("模仿出错 测试事务");*/
		
			//增加用户附属信息
			UserAttrModel userAttrModel = new UserAttrModel();
			BeanUtils.copyProperties(userPojo, userAttrModel);
			Integer userId = getUserPrimaryKey(userPojo.getUsername());
			userAttrModel.setUid(userId);
			userAttrModelMapper.insertSelective(userAttrModel);
			
			//增加用户的ROLE
			Integer roleId = getRolePrimaryKey(USER_ROLE);
			UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUid(userId);
			userRoleModel.setRid(roleId);
			userRoleModelMapper.insert(userRoleModel);
		}
		
	}
	
	public Integer getUserPrimaryKey(String username) {
		UserModelExample userModelExample = new UserModelExample();
		UserModelExample.Criteria userCriteria = userModelExample.createCriteria();
		userCriteria.andUsernameEqualTo(username);
		List<UserModel> userModelList = userModelMapper.selectByExample(userModelExample);
		if(userModelList.size() > 1) throw new UserAlreadyExistsException("有相同用户名");
		if(userModelList.size() < 1) throw new UserNotFoundException("没有该用户");
		Integer id = userModelList.get(0).getId();
		return id;
	}
	
	public Integer getRolePrimaryKey(String rolename) {
		RoleModelExample roleModelExample = new RoleModelExample();
		RoleModelExample.Criteria roleCriteria = roleModelExample.createCriteria();
		roleCriteria.andRolenameEqualTo(rolename);
		List<RoleModel> roleModelList = roleModelMapper.selectByExample(roleModelExample);
		if(roleModelList.size() > 1) throw new ContestCommonException("ROLE存在重复");
		Integer id = roleModelList.get(0).getId();
		return id;
	}
}
