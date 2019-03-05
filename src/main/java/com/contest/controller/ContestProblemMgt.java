package com.contest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.pojo.AjaxPojo;
import com.contest.pojo.ProblemPojo;
import com.contest.service.ContestProblemMgtService;

@Controller
public class ContestProblemMgt {
	
	@Autowired
	private ContestProblemMgtService contestProblemMgtService;

	//管理主页面跳转
	@RequestMapping("/")
	public String gotoProblemPage(Model model) {
		
	/*	ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		problemModelWithBLOBs.setDescription("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		problemModelWithBLOBs.setInput("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		problemModelWithBLOBs.setStatus(1);
		
		contestProblemService.addProblem(problemModelWithBLOBs);*/
		
		model.addAttribute("msg", "123456");
		return "problem";
	}
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.GET})
	public String gotoAddProblemPage() {
		return "add_problem";
	}
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.POST})
	@ResponseBody
	public AjaxPojo addProblem(@RequestBody ProblemPojo problemPojo) {
		
		//System.out.println(problemPojo);
		//增加问题后需要测试 才能设为0
		problemPojo.setStatus(1);
		contestProblemMgtService.addProblem(problemPojo);
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
		
	}
}
