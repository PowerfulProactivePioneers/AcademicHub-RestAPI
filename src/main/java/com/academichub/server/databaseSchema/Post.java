package com.academichub.server.databaseSchema;

public class Post {
	private String title,desc,files,cid,date,posted_date;
	private boolean assignment;
	private int pid;
	public Post() {
		super();
	}
	
	

	public Post(String title, String desc, String files, String cid, String date, String posted_date,
			boolean assignment) {
		super();
		this.title = title;
		this.desc = desc;
		this.files = files;
		this.cid = cid;
		this.date = date;
		this.posted_date = posted_date;
		this.assignment = assignment;
	}

	
	


	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPosted_date() {
		return posted_date;
	}

	public void setPosted_date(String posted_date) {
		this.posted_date = posted_date;
	}

	public boolean isAssignment() {
		return assignment;
	}

	public void setAssignment(boolean assignment) {
		this.assignment = assignment;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", desc=" + desc + ", files=" + files + ", cid=" + cid + ", date=" + date
				+ ", posted_date=" + posted_date + ", assignment=" + assignment + "]";
	}
	
	
}
