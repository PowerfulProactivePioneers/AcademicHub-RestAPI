package com.academichub.server.responseClass;

public class Status {
	String msg;

	public Status(String msg) {
		super();
		this.msg = msg;
	}
	

	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return "Status [msg=" + msg + "]";
	}

}
