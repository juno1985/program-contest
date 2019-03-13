package com.contest.service.compile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.contest.service.compile.pojo.CaseModel;
import com.contest.service.compile.pojo.CompileResult;
import com.contest.service.compile.pojo.RunResult;

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
	public List<RunResult> RunCode(String codeClazz, List<CaseModel> caseModelList, long timeout,  String workSpace)
			throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("java", codeClazz);
		processBuilder.redirectErrorStream(true);
		processBuilder.directory(new File(workSpace));

		List<RunResult> runResultList = new ArrayList<RunResult>();

		for (CaseModel caseModel : caseModelList) {
			RunResult runResult = new RunResult();

			Process p = processBuilder.start();
			
			RunImplSubThread runThread = new RunImplSubThread(p);

			runThread.setInput(caseModel.getInput());
			runThread.start();
			//等待负责输入的子线程die后,主进程才能继续执行
			//由于会限制程序运行时间,此处注释掉
			//runThread.join();
			
			//判断是否code运行超时
			if(isRunCodeTimeOut(runThread, timeout)) {
				runResult.setResultCode(2);
				runResult.setResultState(false);
			}
			//判断code运行结果是否pass
			else {
				String strAnswer = caseModel.getOutput().trim();
				String codeOutput = runThread.getResultString().trim();
				if(isCodeOutputAcceptable(codeOutput, strAnswer)) {
					runResult.setResultCode(0);
					runResult.setResultState(true);
				}
				else {
					runResult.setResultCode(1);
					runResult.setResultState(false);
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
			/*try {
				throw new TimeoutException("Thread did not finish within time limit");
			} catch (TimeoutException e) {
				e.printStackTrace();
			}*/
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
