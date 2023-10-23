package com.academichub.server.responseClass;

public class AttendanceList {
	private String date;
	private Boolean attendance;
	public AttendanceList(String date, Boolean attendance) {
		super();
		this.date = date;
		this.attendance = attendance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getAttendance() {
		return attendance;
	}
	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}
	
	
	
}
