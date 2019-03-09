package com.contest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contest.common.StringHTMLConvertion;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.service.ContestProblemService;

@Controller
public class ContestProblem {
	
	@Autowired
	private ContestProblemService contestProblemService;
	
	@RequestMapping(value="/main", method= {RequestMethod.GET})
	public String mainPage() {
		return "main";
	}
	
	@RequestMapping(value="/challenge/{id}/problem", method= {RequestMethod.GET})
	public String problemPage(@PathVariable String id, Model model) {
		
		ProblemModelWithBLOBs problemModelWithBLOBs = contestProblemService.getSingleProblem(Integer.parseInt(id));
		
		problemModelWithBLOBs.setInput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getInput()));
		problemModelWithBLOBs.setOutput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getOutput()));
		
		model.addAttribute("problemAttr", problemModelWithBLOBs);
		
		return "problem";
	}
	
	/contest/challeng/" + id + "submit

}
