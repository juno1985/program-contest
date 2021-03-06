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
import com.contest.model.AllUsersCodeHistoryPojo;
import com.contest.model.CodeHistModel;
import com.contest.model.ProblemCodeRestrictModelWithBLOBs;
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
	
	@RequestMapping(value = "/portal", method = { RequestMethod.GET })
	public String indexPage() {
		return "portal";
	}

	/*
	 * @RequestMapping(value = "/main", method = { RequestMethod.GET }) public
	 * String mainPage() { return "main"; }
	 */

	@RequestMapping(value = "/challenge/{id}/problem", method = { RequestMethod.GET })
	public String problemPage(@PathVariable String id, Model model) {

		ProblemModelWithBLOBs problemModelWithBLOBs = contestProblemService.getSingleProblem(Integer.parseInt(id));

		problemModelWithBLOBs.setInput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getInput()));
		problemModelWithBLOBs.setOutput(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getOutput()));
		problemModelWithBLOBs.setExplanation(StringHTMLConvertion.StringToHTML(problemModelWithBLOBs.getExplanation()));
		model.addAttribute("problemAttr", problemModelWithBLOBs);
		int problemId = problemModelWithBLOBs.getId();
		ProblemCodeRestrictModelWithBLOBs codeRestrict = contestProblemService.getProblemCodeRestrictInHTML(problemId);
		if (codeRestrict != null)
			model.addAttribute("codeRestrict", codeRestrict);
		else
			model.addAttribute("codeRestrict", null);

		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		String username = userDetails.getUsername();
		model.addAttribute("username", username);

		/*
		 * if(isAdmin()) { model.addAttribute("isAdmin", true); }else {
		 * model.addAttribute("isAdmin", false); }
		 */

		return "problem";
	}

	@RequestMapping(value = "/challeng/{id}/submit", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxPojoWithObj codeSubmit(@PathVariable String id, @RequestParam(required = true) String codeInput) {

		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();

		String username = userDetails.getUsername();

		List<RunResultPojo> runResultList = null;

		try {
			runResultList = contestProblemService.codeSubmit(username, Integer.parseInt(id), codeInput);
		} catch (Exception e) {
			throw new ContestCommonException(e.getMessage());
		}

		AjaxPojoWithObj ajaxPojo = new AjaxPojoWithObj();
		ajaxPojo.setObject(runResultList);
		// 全是0则problem解决
		ajaxPojo.setCode(0);
		for (RunResultPojo runResult : runResultList) {
			if (runResult.getResultCode() != 0) {
				// 2 - 失败
				// 1 - 编译未通过
				// 0 - 成功
				ajaxPojo.setCode(2);
				break;
			}
		}
		
		if(ajaxPojo.getCode() == 0) {
			//记录通过
			contestProblemService.setUserProblemSolveState(username, Integer.parseInt(id), 0);
		}else {
			//记录未通过
			contestProblemService.setUserProblemSolveState(username, Integer.parseInt(id), 1);
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

	// 登录成功后执行
	@RequestMapping("/main")
	public String index(Model model) {

		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		model.addAttribute("admin", "1");

		for (GrantedAuthority ga : authorities) {

			if (ROLE_ADMIN.equals(ga.getAuthority())) {
				model.addAttribute("admin", "0");
				break;
			}
		}

		model.addAttribute("username", userDetails.getUsername());

		return "main";
	}

	@RequestMapping(value = "/listproblem", method = { RequestMethod.GET })
	@ResponseBody
	public AjaxPojoWithObj listProblem(@RequestParam String sum) {
		List<ProblemModel> problemList = contestProblemService.listProblem(Integer.parseInt(sum));
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取列表成功");
		ajaxPojoWithObj.setObject(problemList);
		return ajaxPojoWithObj;

	}

	// 查询个人某个问题提交历史
	@RequestMapping(value = "/challenge/{id}/hist", method = { RequestMethod.GET })
	@ResponseBody
	public AjaxPojoWithObj problemPersonalHist(@PathVariable(name = "id") String problemId, Model model) {

		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		String username = userDetails.getUsername();

		List<CodeHistModel> codeHistModelList = contestProblemService
				.getProblemPersonalHist(Integer.parseInt(problemId), username);

		model.addAttribute("username", username);

		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		if (codeHistModelList.size() > 0) {
			ajaxPojoWithObj.setCode(0);
			ajaxPojoWithObj.setMesg("获取历史成功");
			ajaxPojoWithObj.setObject(codeHistModelList);
		} else {
			ajaxPojoWithObj.setCode(1);
			ajaxPojoWithObj.setMesg("没有提交历史");
		}

		return ajaxPojoWithObj;
	}

	// 查询用户自己的历史代码
	@RequestMapping(value = "/challenge/{id}/code", method = { RequestMethod.GET })
	@ResponseBody
	public AjaxPojoWithObj problemPersonalCode(@PathVariable(name = "id") String codeId) {
		String code = contestProblemService.getProblemPersonCode(Integer.parseInt(codeId));
		code = StringHTMLConvertion.StringToHTML(code);
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取历史代码成功");
		ajaxPojoWithObj.setObject(code);
		return ajaxPojoWithObj;
	}

	// 查询成功历史
	@RequestMapping(value = "/challenge/{id}/allhist", method = { RequestMethod.GET })
	@ResponseBody
	public AjaxPojoWithObj problemAllUsersHist(@PathVariable(name = "id") String problemId) {
		List<AllUsersCodeHistoryPojo> allUsersCodeHistoryList = contestProblemService
				.getProblemAllUsersCode(Integer.parseInt(problemId));
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取历史成功");
		ajaxPojoWithObj.setObject(allUsersCodeHistoryList);
		return ajaxPojoWithObj;
	}

	// 查询所有提交历史
	@RequestMapping(value = "/challenge/{id}/allsubmit", method = { RequestMethod.GET })
	@ResponseBody
	public AjaxPojoWithObj problemAllSubmitHist(@PathVariable(name = "id") String problemId) {
		List<AllUsersCodeHistoryPojo> allUsersCodeHistoryList = contestProblemService
				.getProblemAllSubmitHist(Integer.parseInt(problemId));
		AjaxPojoWithObj ajaxPojoWithObj = new AjaxPojoWithObj();
		ajaxPojoWithObj.setCode(0);
		ajaxPojoWithObj.setMesg("获取历史成功");
		ajaxPojoWithObj.setObject(allUsersCodeHistoryList);
		return ajaxPojoWithObj;
	}
}
