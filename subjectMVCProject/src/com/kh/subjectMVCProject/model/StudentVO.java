package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class StudentVO {
	private int no; // --pk
	private String num; // --학번(연도 학과 번호 00/00/0000) uk
	private String name; // --이름
	private String id; // --아이디 uk
	private String pwd; // --비밀번호
	private String subject_num; // --학과번호 fk
	private String birthday; // --생년월일
	private String phone; // --핸드폰번호
	private String address; // --주소
	private String email; // --이메일
	private Date sdate; // --등록일자

	public StudentVO() {
		super();
	}

	public StudentVO(int no, String num, String name, String id, String pwd, String subject_num, String birthday,
			String phone, String address, String email, Date sdate) {
		super();
		this.no = no;
		this.num = num;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.subject_num = subject_num;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.sdate = sdate;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSubject_num() {
		return subject_num;
	}

	public void setSubject_num(String subject_num) {
		this.subject_num = subject_num;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "StudentVO [no=" + no + ", num=" + num + ", name=" + name + ", id=" + id + ", pwd=" + pwd
				+ ", subject_num=" + subject_num + ", birthday=" + birthday + ", phone=" + phone + ", address="
				+ address + ", email=" + email + ", sdate=" + sdate + "]";
	}

}
