package com.academichub.server.databaseSchema;

import java.sql.Date;

/**
 * @author PRAVEEN
 *
 */
public class Attendance {
	private Date date;
	private String present,absent;
	public Attendance(Date date, String present, String absent) {
		super();
		this.date = date;
		this.present = present;
		this.absent = absent;
	}
	public Date getDate() {
		return date;
	}
	public String getPresent() {
		return present;
	}
	public String getAbsent() {
		return absent;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	
	@Override
	public String toString() {
		return "Attendance [date=" + date + ", present=" + present + ", absent=" + absent + "]";
	}
	
	

}
