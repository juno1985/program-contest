package com.contest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.contest.common.FileUtils;
import com.contest.common.StringHTMLConvertion;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.ProblemCasesModelMapper;
import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemCasesModelExample;
import com.contest.model.ProblemCasesModelWithBLOBs;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.service.compile.CompileAndRun;
import com.contest.service.compile.CompileAndRunImpl;
import com.contest.service.compile.pojo.CaseModel;
import com.contest.service.compile.pojo.CompileResult;
import com.contest.service.compile.pojo.RunResultPojo;

@Service
public class ContestProblemService {
	
	@Autowired
	private ProblemCasesModelMapper problemCasesModelMapper;

	@Autowired
	private ProblemModelMapper problemModelMapper;
	@Value("${usercode.submit.path}")
	private String userCodeSubmitPath;
	// 运行code超时时间 单位:秒
	@Value("${usercode.submit.timeout.seconds}")
	private long timeout;
	// 编译的工作目录
/*	@Value("${usercode.compile.workspace}")
	private String workSpace;*/
		
	@Value("${usercode.maincode.file}")
	private String USER_CODE_FILE;
	@Value("${usercode.timeout.limit}")
	private Long TIMEOUT_SECONDS;

	public ProblemModelWithBLOBs getSingleProblem(int id) {
		return problemModelMapper.selectByPrimaryKey(id);
	}
	
	public List<ProblemModel> listProblem() {
		List<ProblemModel> listProblem = problemModelMapper.selectByExample(null);
		return listProblem;
	}
	
	public List<RunResultPojo> codeSubmit(String username, Integer problemId, String codeInput) throws IOException, InterruptedException {
		//保存用户提交代码
		saveSubmitCodeToFile(username, codeInput);
		String compilePath = userCodeSubmitPath + username + "\\";
		//编译代码
		try {
			compile(USER_CODE_FILE, compilePath);
		} catch (Exception e) {
			//编译未通过,会被异常handler捕获
			throw new ContestCommonException(e.getMessage());
		}
		//运行结果保存
		List<RunResultPojo> runResultList = new ArrayList<RunResultPojo>();
		//problem case保存
		List<CaseModel> caseModelList = new ArrayList<CaseModel>();
		ProblemCasesModelExample problemCasesModelExample = new ProblemCasesModelExample();
		ProblemCasesModelExample.Criteria problemCasesModelCriteria = problemCasesModelExample.createCriteria();
		problemCasesModelCriteria.andFidEqualTo(problemId);
		List<ProblemCasesModelWithBLOBs> problemCasesList = problemCasesModelMapper.selectByExampleWithBLOBs(problemCasesModelExample);
		for(ProblemCasesModelWithBLOBs pCases : problemCasesList) {
			CaseModel caseModel = new CaseModel();
			BeanUtils.copyProperties(pCases, caseModel);
			caseModelList.add(caseModel);
		}
		
		//Solution.java -> Solution
		String className = StringUtils.substringBefore(USER_CODE_FILE, ".");
		
		//运行代码
		CompileAndRun compileCode = new CompileAndRunImpl();
		runResultList = compileCode.RunCode(className, caseModelList,TIMEOUT_SECONDS,compilePath);
		return runResultList;
	}

	public void saveSubmitCodeToFile(String username, String codeInput) {
		
		if(!userCodeSubmitPath.endsWith("\\")){
			userCodeSubmitPath += userCodeSubmitPath + "\\";
		}
		
		String compileFile = userCodeSubmitPath + username + "\\" + USER_CODE_FILE;
		
		try {
			FileUtils.saveStrToFile(compileFile, codeInput);
		} catch (IOException e) {
			
			throw new ContestCommonException(e.getMessage());
		}
	
	}

	// 测试编译
	public CompileResult compile(String codeFileName, String compilePath) throws IOException, InterruptedException {

		CompileAndRun compileCode = new CompileAndRunImpl();

		CompileResult result = compileCode.CompileCode(codeFileName, compilePath);

		if(result.getResultCode() != 0)
		{
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
}