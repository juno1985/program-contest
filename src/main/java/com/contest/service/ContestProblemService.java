package com.contest.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.contest.common.FileUtils;
import com.contest.common.StringHTMLConvertion;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.service.compile.CompileAndRun;
import com.contest.service.compile.CompileAndRunImpl;
import com.contest.service.compile.pojo.CompileResult;

@Service
public class ContestProblemService {

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

	public ProblemModelWithBLOBs getSingleProblem(int id) {
		return problemModelMapper.selectByPrimaryKey(id);
	}
	
	public List<ProblemModel> listProblem() {
		List<ProblemModel> listProblem = problemModelMapper.selectByExample(null);
		return listProblem;
	}
	
	public void codeSubmit(String username, Integer problemId, String codeInput) {
		
		saveSubmitCodeToFile(username, codeInput);
		String compilePath = userCodeSubmitPath + username + "\\";
		try {
			compile(USER_CODE_FILE, compilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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