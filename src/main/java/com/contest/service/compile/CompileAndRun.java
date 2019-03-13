package com.contest.service.compile;

import java.io.IOException;
import java.util.List;

import com.contest.service.compile.pojo.CaseModel;
import com.contest.service.compile.pojo.CompileResult;
import com.contest.service.compile.pojo.RunResult;


public interface CompileAndRun {
	
	public CompileResult CompileCode(String codePath, String workSpace) throws IOException, InterruptedException;
	public List<RunResult> RunCode(String codeClazz, List<CaseModel> caseModelList, long timeout, String workSpace) throws IOException,InterruptedException;

}
