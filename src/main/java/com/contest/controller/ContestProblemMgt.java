package com.contest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.pojo.AjaxPojo;
import com.contest.pojo.CasePojo;
import com.contest.pojo.ProblemPojo;
import com.contest.service.ContestProblemMgtService;

@Controller
public class ContestProblemMgt {
	
	@Autowired
	private ContestProblemMgtService contestProblemMgtService;

/*	//管理主页面跳转
	@RequestMapping(path="/mgt", method= {RequestMethod.GET})
	public String gotoProblemPage() {
		
		return "mgt_problem";
	}*/
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.GET})
	public String gotoAddProblemPage() {
		return "add_problem";
	}
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.POST})
	@ResponseBody
	public AjaxPojo addProblem(@RequestBody ProblemPojo problemPojo) {
		
		contestProblemMgtService.addProblem(problemPojo);
		
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
		
	}
	

	
	@RequestMapping(value="/mgt/addcase", method= {RequestMethod.POST})
	@ResponseBody
	public AjaxPojo addCase(@RequestBody CasePojo casePojo) {
		contestProblemMgtService.addCase(casePojo);
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
	}
	
	@RequestMapping(value="/mgt/addcase", method= {RequestMethod.GET})
	@ResponseBody
	public AjaxPojo addCase() {
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
	}
}
