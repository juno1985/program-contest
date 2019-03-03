package com.contest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.model.ProblemModelWithBLOBs;
import com.contest.service.ContestProblemService;

@Controller
public class ContestProblems {
	
	@Autowired
	private ContestProblemService contestProblemService;

	@RequestMapping("/")
	public String gotoProblemPage(Model model) {
		
		ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		problemModelWithBLOBs.setDescription("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		problemModelWithBLOBs.setInput("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		problemModelWithBLOBs.setStatus(1);
		
		contestProblemService.addProblem(problemModelWithBLOBs);
		
		model.addAttribute("msg", "123456");
		return "problem";
	}
}
