package com.academichub.server.databaseSchema;

public class Marks {
	private String rno,cat1,cat2,cat3;

	public Marks(String rno, String cat1, String cat2, String cat3) {
		super();
		this.rno = rno;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
	}

	public String getRno() {
		return rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getCat3() {
		return cat3;
	}

	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}

	@Override
	public String toString() {
		return "Marks [rno=" + rno + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3 + "]";
	}
	
	
}
