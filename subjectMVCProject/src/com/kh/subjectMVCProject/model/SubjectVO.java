package com.kh.subjectMVCProject.model;

public class SubjectVO {
	private int no;// --시퀀스로 작성 pk
	private String num;// --학과 번호 01,02,03,04 uk
	private String name;// --학과명

	public SubjectVO() {
		super();
	}

	public SubjectVO(int no, String num, String name) {
		super();
		this.no = no;
		this.num = num;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SubjectVO [no=" + no + ", num=" + num + ", name=" + name + "]";
	}

}
