package com.contest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.common.SecurityUserUtils;
import com.contest.common.StringHTMLConvertion;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.pojo.MsgPojo;
import com.contest.pojo.UserAndMultiRoles;
import com.contest.service.ContestProblemService;
import com.contest.service.UserService;

@Controller
public class ContestProblem {

	@Autowired
	private ContestProblemService contestProblemService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/main", method = { RequestMethod.GET })
	public String mainPage() {
		return "main";
	}

	@RequestMapping(value = "/challenge/{id}/problem", method = { RequestMethod.GET })
	public String problemPage(@PathVariable String id, Model model) {

		ProblemModelWithBLOBs problemModelWithBLOBs = contestProblemService.getSingleProblem(Integer.parseInt(id));

		problemModelWithBLOBs.setInput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getInput()));
		problemModelWithBLOBs.setOutput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getOutput()));

		model.addAttribute("problemAttr", problemModelWithBLOBs);

		return "problem";
	}

	@RequestMapping(value = "/challeng/{id}/submit", method = { RequestMethod.POST })
	@ResponseBody
	public String codeSubmit(@PathVariable String id, @RequestParam(required = true) String codeInput) {

		/*
		 * System.out.println("id: " + id); System.out.println("codeInput: " +
		 * codeInput);
		 */

		contestProblemService.codeSubmit(codeInput);

		return "problem";
	}

	// 测试缓存request/response
	@RequestMapping(value = "/test/response/buffer")
	public void testResponseBuffer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sessionId = request.getSession().getId();

		System.out.println("------>" + sessionId);

		String result = String.format("{\"Session ID\" : \"%s\"}", sessionId);

		response.setContentType("application/json");
		response.getWriter().write(result);
	}

	// 测试mybatis mapper
	@RequestMapping(value = "/test/mybatis")
	@ResponseBody
	public UserAndMultiRoles testMybatis() {
		return userService.findByUserName("admin");
	}

	// 测试springsecurity集成
	@RequestMapping("/")
	public String index(Model model) {
	/*	MsgPojo msg = new MsgPojo("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);*/
		
		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		
		model.addAttribute("username", userDetails.getUsername());
		
		return "main";
	}

}
