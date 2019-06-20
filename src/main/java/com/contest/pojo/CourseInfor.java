package com.contest.pojo;

public class CourseInfor {
	
	private Integer cid;
	private String infor;
	public CourseInfor() {
		super();
	}
	public CourseInfor(Integer cid, String infor) {
		super();
		this.cid = cid;
		this.infor = infor;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	@Override
	public String toString() {
		return "CourseInfor [cid=" + cid + ", infor=" + infor + "]";
	}
	
	

}
