package com.contest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.pojo.AjaxPojoWithObj;
import com.contest.pojo.UserPojo;
import com.contest.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/regist", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxPojoWithObj registNewUser(@RequestBody @Valid UserPojo userPojo, BindingResult bindingResult) {
		
		userService.addUser(userPojo);
		
		AjaxPojoWithObj ajaxPojo = new AjaxPojoWithObj();
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			List<String> mesList = new ArrayList<String>();
			for(int i=0;i<errorList.size();i++) {
				mesList.add(errorList.get(i).getDefaultMessage());
			}
			ajaxPojo.setObject(mesList);
			ajaxPojo.setCode(1);
			ajaxPojo.setMesg("注册失败");
			return ajaxPojo;
		}
		
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("注册成功");
	
		return ajaxPojo;
	}
	
	
}
