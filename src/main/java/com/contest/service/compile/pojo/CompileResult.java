package com.contest.service.compile.pojo;

import java.util.List;

public class CompileResult {

	private int resultCode;
	private List<String> resultString;
	public CompileResult() {
		super();
	}
	public CompileResult(int resultCode, List<String> resultString) {
		super();
		this.resultCode = resultCode;
		this.resultString = resultString;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public List<String> getResultString() {
		return resultString;
	}
	public void setResultString(List<String> resultString) {
		this.resultString = resultString;
	}
	
}
