package com.academichub.server.responseClass;

public class ClassDateResponse {
	private String rno;
	private Boolean status;
	public ClassDateResponse(String rno, Boolean status) {
		super();
		this.rno = rno;
		this.status = status;
	}
	public String getRno() {
		return rno;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ClassDateResponse [rno=" + rno + ", status=" + status + "]";
	}
	
	
}
