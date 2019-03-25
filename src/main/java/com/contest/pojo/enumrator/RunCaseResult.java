package com.contest.pojo.enumrator;

public enum RunCaseResult {
	
	OVERTIME("overtime"),SUCCESS("success"),FAILURE("failure");
	
	private String result;

	private RunCaseResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}


}
