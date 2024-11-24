package com.kh.theaterProject.model;

import java.sql.Date;

public class CustomerVO {
	private String no;// CHAR(5),                          --PK
	private String name;// VARCHAR2(10) NOT NULL,          --이름
	private String id;// VARCHAR2(20)NOT NULL,             --UQ 아이디
	private String pwd;// VARCHAR2(20)NOT NULL,            --비밀번호
	private Date birth;// DATE,                          --생년월일
	private String phone;// VARCHAR2(20),                  --UQ핸드폰번호
	private int bookCount;// NUMBER(3) DEFAULT 0,       --예매횟수
	private Date registDate;// DATE NOT NULL  
	public CustomerVO() {
		super();
	}
	
	public CustomerVO(String no, String name, String id, String pwd, Date birth, String phone, int bookCount,
			Date registDate) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.birth = birth;
		this.phone = phone;
		this.bookCount = bookCount;
		this.registDate = registDate;
	}

	public CustomerVO(String no, String name, String id, String pwd, Date birth, String phone, Date registDate) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.birth = birth;
		this.phone = phone;
		this.registDate = registDate;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	@Override
	public String toString() {
		return "CustomerVO [no=" + no + ", name=" + name + ", id=" + id + ", pwd=" + pwd + ", birth=" + birth
				+ ", phone=" + phone + ", bookCount=" + bookCount + ", registDate=" + registDate + "]";
	}
	
	

}
