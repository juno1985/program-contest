package com.contest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.pojo.AjaxPojo;
import com.contest.pojo.UserPojo;

@Controller
public class UserController {

	@RequestMapping(value = "/user/regist", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxPojo registNewUser(UserPojo userPojo) {
		System.out.println(userPojo);
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("success");
		return ajaxPojo;
	}
}
