package com.academichub.server;

import jakarta.persistence.*;

@Entity
@Table(name="student_faculty")
public class StudentFacultyDB {
	
	public StudentFacultyDB() {
		super();
	}

	@Id
	private long regno;
	
	@Column(name="name")
	private String name;
	
	@Column(name="uid")
	private String uid;
	
	@Column(name="dept")
	private String dept;
	
	@Column(name = "email")
	private String email;
	
	@Column(name="type")
	private String type;
	
	@Column(name = "section")
	private char section;

	
	public StudentFacultyDB(long regno, String name, String uid, String dept, String email, String type, char section) {
		super();
		this.regno = regno;
		this.name = name;
		this.uid = uid;
		this.dept = dept;
		this.email = email;
		this.type = type;
		this.section = section;
	}

	public long getRegno() {
		return regno;
	}

	public String getName() {
		return name;
	}

	public String getUid() {
		return uid;
	}

	public String getDept() {
		return dept;
	}

	public String getEmail() {
		return email;
	}

	public String getType() {
		return type;
	}

	public char getSection() {
		return section;
	}
	
	public void setRegno(long regno) {
		this.regno = regno;
	}

	public void setSection(char section) {
		this.section = section;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "StudentFacultyDB [regno=" + regno + ", name=" + name + ", uid=" + uid + ", dept=" + dept + ", email="
				+ email + ", type=" + type + ", section=" + section + "]";
	}
	
}
