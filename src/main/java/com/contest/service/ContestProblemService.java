package com.contest.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.contest.common.FileUtils;
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
		
		

	public ProblemModelWithBLOBs getSingleProblem(int id) {
		return problemModelMapper.selectByPrimaryKey(id);
	}
	
	public List<ProblemModel> listProblem() {
		List<ProblemModel> listProblem = problemModelMapper.selectByExample(null);
		return listProblem;
	}
	
	public void codeSubmit(String codeInput) {
		
		saveSubmitCodeToFile(codeInput);
		
		try {
			compile("Solution.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveSubmitCodeToFile(String codeInput) {
		try {
			FileUtils.saveStrToFile(userCodeSubmitPath+"Solution.java", codeInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	// 测试编译
	public CompileResult compile(String codeFileName) throws IOException, InterruptedException {

		CompileAndRun compileCode = new CompileAndRunImpl();

		CompileResult result = compileCode.CompileCode(codeFileName, userCodeSubmitPath);

		System.out.println("编译结果: " + result.getResultCode());
		for (String ss : result.getResultString()) {
			System.out.println(ss);
		}

		return result;
	}
}