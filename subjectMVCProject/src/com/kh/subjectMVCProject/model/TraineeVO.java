package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {

	private int no;// --pk seq
	private String student_num;// --학생번호 fk(STUDENT)
	private String lesson_abbre;// --강의명 fk(LESSON)
	private String section;// --전공, 부전공, 교양
	private Date tdate;// --입력 날짜

	public TraineeVO() {
		super();
	}

	public TraineeVO(int no, String student_num, String lesson_abbre, String section, Date tdate) {
		super();
		this.no = no;
		this.student_num = student_num;
		this.lesson_abbre = lesson_abbre;
		this.section = section;
		this.tdate = tdate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStudent_num() {
		return student_num;
	}

	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}

	public String getLesson_abbre() {
		return lesson_abbre;
	}

	public void setLesson_abbre(String lesson_abbre) {
		this.lesson_abbre = lesson_abbre;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", student_num=" + student_num + ", lesson_abbre=" + lesson_abbre + ", section="
				+ section + ", tdate=" + tdate + "]";
	}

}
