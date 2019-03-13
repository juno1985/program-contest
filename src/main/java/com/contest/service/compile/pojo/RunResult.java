package com.contest.service.compile.pojo;

public class RunResult {

	//0 - success
	//1 - failed
	//2 - timeout
	private int resultCode;
	//true - case success
	//false - case failed
	private Boolean resultState;
	public RunResult() {
		
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Boolean getResultState() {
		return resultState;
	}
	public void setResultState(Boolean resultState) {
		this.resultState = resultState;
	}
	
	
}
