package com.contest.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
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
import com.contest.exception.ContestCommonException;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.pojo.AjaxPojoWithObj;
import com.contest.service.ContestProblemService;
import com.contest.service.compile.pojo.RunResultPojo;

@Controller
public class ContestProblem {

	@Autowired
	private ContestProblemService contestProblemService;
	
	@Value("${contest.admin.role}")
	private String ROLE_ADMIN;

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
	public AjaxPojoWithObj codeSubmit(@PathVariable String id, @RequestParam(required = true) String codeInput) {
		
		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		
		String username = userDetails.getUsername();

		List<RunResultPojo> runResultList = null;
		
		try {
			runResultList = contestProblemService.codeSubmit(username, Integer.parseInt(id) ,codeInput);
		} catch (Exception e) {
			throw new ContestCommonException(e.getMessage());
		} 
		
		AjaxPojoWithObj ajaxPojo = new AjaxPojoWithObj();
		ajaxPojo.setObject(runResultList);
		//全是0则problem解决
		ajaxPojo.setCode(0);
		for(RunResultPojo runResult : runResultList) {
			if(runResult.getResultCode() != 0) {
				//2 - 失败
				//1 - 编译未通过
				//0 - 成功
				ajaxPojo.setCode(2);
				break;
			}
		}

		return ajaxPojo;
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


	//登录成功后执行
	@RequestMapping("/")
	public String index(Model model) {
	/*	MsgPojo msg = new MsgPojo("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);*/
		
		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		model.addAttribute("admin", "1");
		
		for(GrantedAuthority ga : authorities) {
		
			if(ROLE_ADMIN.equals(ga.getAuthority())) {
				model.addAttribute("admin", "0");
				break;
			}
		}
		
		model.addAttribute("username", userDetails.getUsername());
		
		
		return "main";
	}
	
	@RequestMapping(value="/listproblem", method= {RequestMethod.GET})
	@ResponseBody
	public AjaxPojoWithObj listProblem() {
		List<ProblemModel> problemList = contestProblemService.listProblem();
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取列表成功");
		ajaxPojoWithObj.setObject(problemList);
		return ajaxPojoWithObj;
		
	}

}
