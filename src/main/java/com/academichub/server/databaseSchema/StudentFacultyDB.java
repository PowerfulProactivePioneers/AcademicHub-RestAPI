package com.academichub.server.databaseSchema;

public class StudentFacultyDB {
	
	public StudentFacultyDB() {
		super();
	}

	private String id;

	private String name;

	private String uid;

	private String dept;
	
	private String email;
	
	private String type;
	
	private char section;

	public StudentFacultyDB(String id, String name, String uid, String dept, String email, String type, char section) {
		super();
		this.id = id;
		this.name = name;
		this.uid = uid;
		this.dept = dept;
		this.email = email;
		this.type = type;
		this.section = section;
	}

	@Override
	public String toString() {
		return "StudentFacultyDB [id=" + id + ", name=" + name + ", uid=" + uid + ", dept=" + dept + ", email=" + email
				+ ", type=" + type + ", section=" + section + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public char getSection() {
		return section;
	}

	public void setSection(char section) {
		this.section = section;
	}

	
	
}
