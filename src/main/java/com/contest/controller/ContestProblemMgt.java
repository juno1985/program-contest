package com.contest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.model.ProblemModel;
import com.contest.pojo.AjaxPojo;
import com.contest.pojo.AjaxPojoWithObj;
import com.contest.pojo.CasePojo;
import com.contest.pojo.ProblemPojo;
import com.contest.service.ContestProblemMgtService;

@Controller
public class ContestProblemMgt {
	
	@Autowired
	private ContestProblemMgtService contestProblemMgtService;

	//管理主页面跳转
	@RequestMapping(path="/mgt", method= {RequestMethod.GET})
	public String gotoProblemPage() {
		
	/*	ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		problemModelWithBLOBs.setDescription("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		problemModelWithBLOBs.setInput("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		problemModelWithBLOBs.setStatus(1);
		
		contestProblemService.addProblem(problemModelWithBLOBs);*/
		
		//model.addAttribute("msg", "123456");
		return "mgt_problem";
	}
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.GET})
	public String gotoAddProblemPage() {
		return "add_problem";
	}
	
	@RequestMapping(value="/mgt/addproblem", method= {RequestMethod.POST})
	@ResponseBody
	public AjaxPojo addProblem(@RequestBody ProblemPojo problemPojo) {
		
		//增加问题后需要测试 才能设为0
		problemPojo.setStatus(1);
		contestProblemMgtService.addProblem(problemPojo);
		
		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
		
	}
	
	@RequestMapping(value="/mgt/listproblem", method= {RequestMethod.GET})
	@ResponseBody
	public AjaxPojoWithObj listProblem() {
		List<ProblemModel> problemList = contestProblemMgtService.listProblem();
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取列表成功");
		ajaxPojoWithObj.setObject(problemList);
		return ajaxPojoWithObj;
		
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
}
