package com.contest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.contest.common.FileUtils;
import com.contest.common.SecurityUserUtils;
import com.contest.common.StringHTMLConvertion;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.CodeHistModelMapper;
import com.contest.mapper.CodeHistModelMapperExt;
import com.contest.mapper.ProblemCasesModelMapper;
import com.contest.mapper.ProblemCodeRestrictModelMapper;
import com.contest.mapper.ProblemModelMapper;
import com.contest.mapper.RunCodeCaseModelMapper;
import com.contest.mapper.UserProblemSolveStateModelMapper;
import com.contest.mapper.UserProblemSolveStateModelMapperExt;
import com.contest.model.AllUsersCodeHistoryPojo;
import com.contest.model.CodeHistModel;
import com.contest.model.CodeHistModelExample;
import com.contest.model.ProblemCasesModelExample;
import com.contest.model.ProblemCasesModelWithBLOBs;
import com.contest.model.ProblemCodeRestrictModelExample;
import com.contest.model.ProblemCodeRestrictModelWithBLOBs;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelExample;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.model.RunCodeCaseModel;
import com.contest.model.UserProblemSolveStateModel;
import com.contest.model.UserProblemSolveStateModelExample;
import com.contest.pojo.enumrator.RunCaseResult;
import com.contest.service.compile.CompileAndRun;
import com.contest.service.compile.CompileAndRunImpl;
import com.contest.service.compile.pojo.CaseModel;
import com.contest.service.compile.pojo.CompileResult;
import com.contest.service.compile.pojo.RunResultPojo;

@Service
public class ContestProblemService {

	@Autowired
	private UserService userService;
	@Autowired
	private ProblemCodeRestrictModelMapper problemCodeRestrictModelMapper;
	@Autowired
	private ProblemCasesModelMapper problemCasesModelMapper;
	@Autowired
	private CodeHistModelMapper codeHistModelMapper;
	@Autowired
	private CodeHistModelMapperExt codeHistModelMapperExt;
	@Autowired
	private RunCodeCaseModelMapper runCodeCaseModelMapper;
	@Autowired
	private ProblemModelMapper problemModelMapper;
	@Autowired
	private UserProblemSolveStateModelMapper userProblemSolveStateModelMapper;
	@Autowired
	private UserProblemSolveStateModelMapperExt userProblemSolveStateModelMapperExt;
	@Value("${usercode.submit.path}")
	private String userCodeSubmitPath;
	// 运行code超时时间 单位:秒
	@Value("${usercode.submit.timeout.seconds}")
	private long timeout;
	@Value("${usercode.maincode.file}")
	private String USER_CODE_FILE;
	@Value("${usercode.timeout.limit}")
	private Long TIMEOUT_SECONDS;
	@Value("${contest.admin.role}")
	private String ROLE_ADMIN;

	public ProblemModelWithBLOBs getSingleProblem(int id) {
		return problemModelMapper.selectByPrimaryKey(id);
	}

	// 只返回status=1的问题, status=2的为未准备好或考试题目
	public List<ProblemModel> listProblem(Integer sum) {
		List<ProblemModel> listProblem = null;
		// admin则返回完成problem list
		if (isAdmin()) {
			listProblem = problemModelMapper.selectByExample(null);
		} else {
			ProblemModelExample problemModelExample = new ProblemModelExample();
			ProblemModelExample.Criteria problemModelExampleCri = problemModelExample.createCriteria();
			problemModelExampleCri.andStatusEqualTo(1);
			listProblem = problemModelMapper.selectByExample(problemModelExample);
		}
		String flag = getProblemFlag(sum);
		return getSolvedUnSolvedProblemList(listProblem, flag);
	}

	private String getProblemFlag(Integer sum) {
		if (sum == 3 || sum == 0)
			return null;
		else if (sum == 1)
			return "solved";
		else
			return "unsolved";
	}

	// 返回所有问题列表、已解决、未解决
	private List<ProblemModel> getSolvedUnSolvedProblemList(List<ProblemModel> listProblem, String flag) {
		if (flag == null)
			return listProblem;
		Integer userId = getUserId();
		UserProblemSolveStateModelExample userProblemSolveStateModelExample = new UserProblemSolveStateModelExample();
		UserProblemSolveStateModelExample.Criteria userProblemSolveStateModelCri = userProblemSolveStateModelExample
				.createCriteria();
		List<UserProblemSolveStateModel> userProblemList = null;
		// 得到所有解决的问题
		userProblemSolveStateModelCri.andUidEqualTo(userId).andStateEqualTo(0);
		userProblemList = userProblemSolveStateModelMapper.selectByExample(userProblemSolveStateModelExample);
		// 所有解决问题的ID集合
		Set<Integer> filterProblemSet = new HashSet<Integer>();
		for (UserProblemSolveStateModel stateModel : userProblemList) {
			filterProblemSet.add(stateModel.getFid());
		}
		List<ProblemModel> filterProblemList = new ArrayList<ProblemModel>();
		if ("solved".equals(flag)) {
			for (ProblemModel problemModel : listProblem) {
				if (filterProblemSet.contains(problemModel.getId())) {
					filterProblemList.add(problemModel);
				}
			}
		}else {
			for (ProblemModel problemModel : listProblem) {
				if (!filterProblemSet.contains(problemModel.getId())) {
					filterProblemList.add(problemModel);
				}
			}
		}

		return filterProblemList;
	}

	@Transactional
	public List<RunResultPojo> codeSubmit(String username, Integer problemId, String codeInput)
			throws IOException, InterruptedException {
		// 处理stub code
		codeInput = appendStubCode(problemId, codeInput);

		// 保存用户提交代码
		saveSubmitCodeToFile(username, codeInput);
		String compilePath = userCodeSubmitPath + username + "\\";
		// 编译代码
		try {
			compile(USER_CODE_FILE, compilePath);
		} catch (Exception e) {
			// 编译未通过,会被异常handler捕获
			throw new ContestCommonException(e.getMessage());
		}

		// 运行结果保存
		List<RunResultPojo> runResultList = new ArrayList<RunResultPojo>();

		// problem case保存
		List<CaseModel> caseModelList = new ArrayList<CaseModel>();
		ProblemCasesModelExample problemCasesModelExample = new ProblemCasesModelExample();
		ProblemCasesModelExample.Criteria problemCasesModelCriteria = problemCasesModelExample.createCriteria();
		problemCasesModelCriteria.andFidEqualTo(problemId);
		List<ProblemCasesModelWithBLOBs> problemCasesList = problemCasesModelMapper
				.selectByExampleWithBLOBs(problemCasesModelExample);
		for (ProblemCasesModelWithBLOBs pCases : problemCasesList) {
			CaseModel caseModel = new CaseModel();
			BeanUtils.copyProperties(pCases, caseModel);
			caseModelList.add(caseModel);
		}

		// Solution.java -> Solution
		String className = StringUtils.substringBefore(USER_CODE_FILE, ".");

		// 运行代码
		CompileAndRun compileCode = new CompileAndRunImpl();
		runResultList = compileCode.RunCode(className, caseModelList, TIMEOUT_SECONDS, compilePath);

		// 记录用户运行结果
		insertRunCodeHist(problemId, username, runResultList, codeInput);

		return runResultList;
	}

	public void saveSubmitCodeToFile(String username, String codeInput) {

		if (!userCodeSubmitPath.endsWith("\\")) {
			userCodeSubmitPath += userCodeSubmitPath + "\\";
		}

		String compileFile = userCodeSubmitPath + username + "\\" + USER_CODE_FILE;

		try {
			FileUtils.saveStrToFile(compileFile, codeInput);
		} catch (IOException e) {

			throw new ContestCommonException(e.getMessage());
		}

	}

	public String appendStubCode(Integer problemId, String codeInput) {
		ProblemCodeRestrictModelWithBLOBs stubCodeModel = getProblemCodeRestrictInText(problemId);
		if (stubCodeModel == null) {
			return codeInput;
		}
		if (stubCodeModel.getHasPrefix()) {
			codeInput = stubCodeModel.getPrefix() + codeInput;
		}
		if (stubCodeModel.getHasSuffix()) {
			codeInput = codeInput + stubCodeModel.getSuffix();
		}
		return codeInput;
	}

	// 测试编译
	public CompileResult compile(String codeFileName, String compilePath) throws IOException, InterruptedException {

		CompileAndRun compileCode = new CompileAndRunImpl();

		CompileResult result = compileCode.CompileCode(codeFileName, compilePath);

		if (result.getResultCode() != 0) {
			String errMsg = "";
			for (String ss : result.getResultString()) {
				errMsg += ss;
			}

			errMsg = StringUtils.substringAfter(errMsg, USER_CODE_FILE);
			errMsg = USER_CODE_FILE + errMsg;
			errMsg = StringHTMLConvertion.StringToHTML(errMsg);
			throw new ContestCommonException(errMsg);
		}

		return result;
	}

	private void insertRunCodeHist(Integer problemId, String username, List<RunResultPojo> runResultList,
			String codeInput) {
		// t_code_hist插入用户提交记录
		CodeHistModel codeHistModel = new CodeHistModel();
		codeHistModel.setCode(codeInput);
		codeHistModel.setProblemId(problemId);
		codeHistModel.setSubmitTime(new Date());
		Integer userId = userService.getUserPrimaryKey(username);
		codeHistModel.setUserId(userId);
		if (isCodeSuccess(runResultList)) {
			codeHistModel.setResult("success");
		} else {
			codeHistModel.setResult("failure");
		}
		codeHistModelMapperExt.insertCodeHistAndGetKey(codeHistModel);

		// t_run_code_case_hist插入用户提交记录
		RunCodeCaseModel runCodeCaseModel = new RunCodeCaseModel();
		for (RunResultPojo resultPojo : runResultList) {
			runCodeCaseModel.setCaseId(resultPojo.getCaseId());
			if (resultPojo.getResultCode() == 0) {
				runCodeCaseModel.setResult(RunCaseResult.SUCCESS.getResult());
			} else if (resultPojo.getResultCode() == 1) {
				runCodeCaseModel.setResult(RunCaseResult.FAILURE.getResult());
			} else {
				runCodeCaseModel.setResult(RunCaseResult.OVERTIME.getResult());
			}
			runCodeCaseModel.setCodeId(codeHistModel.getId());
			runCodeCaseModelMapper.insert(runCodeCaseModel);
		}

	}

	private boolean isCodeSuccess(List<RunResultPojo> runResultList) {
		for (RunResultPojo resultPojo : runResultList) {
			if (resultPojo.getResultCode() != 0) {
				return false;
			}
		}
		return true;
	}

	public List<CodeHistModel> getProblemPersonalHist(Integer problemId, String userName) {

		Integer userId = userService.getUserPrimaryKey(userName);

		CodeHistModelExample codeHistModelExa = new CodeHistModelExample();
		CodeHistModelExample.Criteria codeHistModelCri = codeHistModelExa.createCriteria();

		if (userName != null)
			codeHistModelCri.andUserIdEqualTo(userId);

		codeHistModelCri.andProblemIdEqualTo(problemId);
		List<CodeHistModel> codeHistModelList = codeHistModelMapper.selectByExample(codeHistModelExa);

		return codeHistModelList;

	}

	private Integer getUserId() {
		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		String username = userDetails.getUsername();
		return userService.getUserPrimaryKey(username);
	}

	public String getProblemPersonCode(Integer codeId) {
		CodeHistModel codeHistModel = codeHistModelMapper.selectByPrimaryKey(codeId);
		if (!isAdmin()) {
			UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
			String username = userDetails.getUsername();
			Integer userId = userService.getUserPrimaryKey(username);
			if (!codeHistModel.getUserId().equals(userId)) {
				throw new ContestCommonException("你聪明我也不傻");
			}
		}
		return codeHistModel.getCode();
	}

	// 获取所有用户历史
	public List<AllUsersCodeHistoryPojo> getProblemAllUsersCode(int problemId) {
		return codeHistModelMapperExt.getAllUsersHistByProblemId(problemId);

	}

	public ProblemCodeRestrictModelWithBLOBs getProblemCodeRestrictInHTML(int problemId) {
		ProblemCodeRestrictModelExample problemCodeRestrictModelExample = new ProblemCodeRestrictModelExample();
		ProblemCodeRestrictModelExample.Criteria problemCodeRestrictModelCri = problemCodeRestrictModelExample
				.createCriteria();
		problemCodeRestrictModelCri.andFidEqualTo(problemId);
		List<ProblemCodeRestrictModelWithBLOBs> codeRestrictList = problemCodeRestrictModelMapper
				.selectByExampleWithBLOBs(problemCodeRestrictModelExample);
		if (codeRestrictList.size() > 1) {
			throw new ContestCommonException("获取code restrition失败");
		} else if (codeRestrictList.size() == 0) {
			return null;
		} else {
			ProblemCodeRestrictModelWithBLOBs codeRestrict = codeRestrictList.get(0);
			if (codeRestrict.getHasPrefix()) {
				codeRestrict.setPrefix(StringHTMLConvertion.StringToHTML(codeRestrict.getPrefix()));
			}
			if (codeRestrict.getHasSuffix()) {
				codeRestrict.setSuffix(StringHTMLConvertion.StringToHTML(codeRestrict.getSuffix()));
			}
			return codeRestrict;
		}
	}

	public ProblemCodeRestrictModelWithBLOBs getProblemCodeRestrictInText(int problemId) {
		ProblemCodeRestrictModelExample problemCodeRestrictModelExample = new ProblemCodeRestrictModelExample();
		ProblemCodeRestrictModelExample.Criteria problemCodeRestrictModelCri = problemCodeRestrictModelExample
				.createCriteria();
		problemCodeRestrictModelCri.andFidEqualTo(problemId);
		List<ProblemCodeRestrictModelWithBLOBs> codeRestrictList = problemCodeRestrictModelMapper
				.selectByExampleWithBLOBs(problemCodeRestrictModelExample);
		if (codeRestrictList.size() > 1) {
			throw new ContestCommonException("获取code restrition失败");
		} else if (codeRestrictList.size() == 0) {
			return null;
		} else {
			return codeRestrictList.get(0);
		}
	}

	// 判断是否有ADMIN ROLE
	public boolean isAdmin() {
		UserDetails userDetails = SecurityUserUtils.getCurrentUserDetails();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for (GrantedAuthority ga : authorities) {
			if (ROLE_ADMIN.equals(ga.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	public List<AllUsersCodeHistoryPojo> getProblemAllSubmitHist(int problemId) {

		return codeHistModelMapperExt.getAllUsersSubmitByProblemId(problemId);
	}

	// 记录问题是否通过
	public void setUserProblemSolveState(String username, Integer problemId, Integer state) {
		Integer userId = userService.getUserPrimaryKey(username);
		UserProblemSolveStateModelExample userProblemSolveStateModelExample = new UserProblemSolveStateModelExample();
		UserProblemSolveStateModelExample.Criteria userProblemSolveStateModelCri = userProblemSolveStateModelExample
				.createCriteria();
		userProblemSolveStateModelCri.andUidEqualTo(userId).andFidEqualTo(problemId);
		List<UserProblemSolveStateModel> UserProblemSolveStateModelList = userProblemSolveStateModelMapper
				.selectByExample(userProblemSolveStateModelExample);
		if (UserProblemSolveStateModelList.size() > 1) {
			throw new ContestCommonException("获取状态失败");
		} else if (UserProblemSolveStateModelList.size() == 1) {
			UserProblemSolveStateModel userProblemSolveStateModel = UserProblemSolveStateModelList.get(0);
			if (userProblemSolveStateModel.getState() != state) {
				Map<String, Integer> params = new HashMap<String, Integer>();
				params.put("stateId", state);
				params.put("id", userProblemSolveStateModel.getId());
				userProblemSolveStateModelMapperExt.updateStateByPrimaryKey(params);
			}
		} else {
			UserProblemSolveStateModel userProblemSolveStateModel = new UserProblemSolveStateModel();
			userProblemSolveStateModel.setUid(userId);
			userProblemSolveStateModel.setFid(problemId);
			userProblemSolveStateModel.setState(state);
			userProblemSolveStateModelMapper.insert(userProblemSolveStateModel);

		}
	}

}