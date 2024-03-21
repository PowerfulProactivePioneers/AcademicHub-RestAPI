package com.academichub.server.responseClass;

public class ClassMarkResponse {
	private String rno;
	private int mark;
	
	public ClassMarkResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassMarkResponse(String rno, int mark) {
		super();
		this.rno = rno;
		this.mark = mark;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	@Override
	public String toString() {
		return "ClassExamResponse [rno=" + rno + ", mark=" + mark + "]";
	}
	
}
