package com.kh.subjectMVCProject.model;

public class LessonVO {

	private int no;// number, --pk
	private String abbre;// varchar2(2) not null, --강의명 uq
	private String name;// varchar2(20) not null --과목 이름

	public LessonVO() {
		super();
	}

	public LessonVO(int no, String abbre, String name) {
		super();
		this.no = no;
		this.abbre = abbre;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAbbre() {
		return abbre;
	}

	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Lesson [no=" + no + ", abbre=" + abbre + ", name=" + name + "]";
	}

}
