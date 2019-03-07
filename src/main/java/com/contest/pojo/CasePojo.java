package com.contest.pojo;

import javax.validation.constraints.NotEmpty;

public class CasePojo {
	@NotEmpty(message="问题序号id不能为空")
	private Integer fid;
	private String input;
	private String output;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}

	
}
