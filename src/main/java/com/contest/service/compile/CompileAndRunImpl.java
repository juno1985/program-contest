package com.contest.service.compile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.contest.common.StringCompareUtils;
import com.contest.exception.ContestCommonException;
import com.contest.service.compile.pojo.CaseModel;
import com.contest.service.compile.pojo.CompileResult;
import com.contest.service.compile.pojo.RunResultPojo;

@Component
public class CompileAndRunImpl implements CompileAndRun {

	

	public CompileAndRunImpl() {

	}

	@Override
	public CompileResult CompileCode(String code, String workSpace) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("javac", workSpace + code);
		processBuilder.redirectErrorStream(true);
		processBuilder.directory(new File(workSpace));
		Process p = processBuilder.start();
		ComplieImplSubThread compileThread = new ComplieImplSubThread(p);

		compileThread.start();
		int result = p.waitFor();
		compileThread.join();
		CompileResult compileResult = new CompileResult();
		compileResult.setResultCode(result);
		compileResult.setResultString(compileThread.getResultString());
		return compileResult;
	}

	@Override
	public List<RunResultPojo> RunCode(String codeClazz, List<CaseModel> caseModelList, long timeout,  String workSpace)
			throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("java", codeClazz);
		processBuilder.redirectErrorStream(true);
		processBuilder.directory(new File(workSpace));

		List<RunResultPojo> runResultList = new ArrayList<RunResultPojo>();

		for (CaseModel caseModel : caseModelList) {
			RunResultPojo runResult = new RunResultPojo();

			Process p = processBuilder.start();
			//向子线程输入
			RunImplInputThread inputThread = new RunImplInputThread(p.getOutputStream(), caseModel.getInput());
			inputThread.start();
			
			//从子线程得到输出
			RunImplOutputThread outputThread = new RunImplOutputThread(p.getInputStream());
			outputThread.start();
			
			//等待负责输入的子线程die后,主进程才能继续执行
			//由于会限制程序运行时间,此处注释掉
			//runThread.join();
			
			//判断输入运行是否超时
			//这里的线程监控有点暴力,今后再想想如何控制子线的生命周期
			if(isRunCodeTimeOut(inputThread, timeout)) {
				throw new ContestCommonException("程序输入超时");
			}
			
			//判断是否code运行超时
			if(isRunCodeTimeOut(outputThread, timeout)) {
				//timeout
				runResult.setResultCode(2);
				runResult.setCaseId(caseModel.getId());
			
			}
			//判断code运行结果是否pass
			else {
				String strAnswer = StringCompareUtils.ReplaceEnterChars(caseModel.getOutput()).trim();
				String codeOutput = StringCompareUtils.ReplaceEnterChars(outputThread.getResultString()).trim();
			
				if(isCodeOutputAcceptable(codeOutput, strAnswer)) {
					//pass
					runResult.setResultCode(0);
					runResult.setCaseId(caseModel.getId());
				}
				else {
					//non-pass
					runResult.setResultCode(1);
					runResult.setCaseId(caseModel.getId());
				}
			}
		

			runResultList.add(runResult);

		}
		return runResultList;

	}
	//判断code运行是否超时
	public boolean isRunCodeTimeOut(Thread thread, long timeout) {
		
		try {
			TimeUnit.SECONDS.timedJoin(thread,timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//超时
		if(thread.isAlive()) {
			
			thread.interrupt();
			
			//timeout
			return true;
		}
		//not timeout
		return false;

	}
	//判断运行结果是否符合答案
	public boolean isCodeOutputAcceptable(String codeOutput, String strAnswer) {
		//code output accepted
		if (strAnswer.equals(codeOutput)) {
			return true;
		} 
		//code output not acceptable
		else {
			return false;
		}
	}


}
