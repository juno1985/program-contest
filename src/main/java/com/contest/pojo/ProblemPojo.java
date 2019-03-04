package com.contest.pojo;

import javax.validation.constraints.NotEmpty;

public class ProblemPojo {

	private Integer id;
	@NotEmpty(message="标题不能为空")
	private String title;
	private Integer status;
	private String description;
	private String input;
	private String output;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "ProblemPojo [id=" + id + ", title=" + title + ", status=" + status + ", description=" + description
				+ ", input=" + input + ", output=" + output + "]";
	}

	
}
