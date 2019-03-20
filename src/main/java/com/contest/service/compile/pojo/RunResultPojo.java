package com.contest.service.compile.pojo;

public class RunResultPojo {

	//0 - success
	//1 - failed
	//2 - timeout
	private int caseId;
	private int resultCode;
	
	public RunResultPojo() {
		
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	
	
}
