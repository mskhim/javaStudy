package com.kh.theaterProject.model;

import java.sql.Timestamp;

public class PlayingVO {
	private String no;// char(3),                     --PK상영작NO
	private String  hall_no;// char(2),                --FK상영관NO
	private String cinema_no;// char(3),              --FK영화NO    
	private Timestamp starttime;// date not null,        --시작시간
	private int remain;// number(3) not null,      --잔여좌석
	private String status;// char(1) default 0        --상영상태
	
	public PlayingVO() {
		super();
	}



	public PlayingVO(String no, String hall_no, String cinema_no, Timestamp starttime, int remain, String status) {
		super();
		this.no = no;
		this.hall_no = hall_no;
		this.cinema_no = cinema_no;
		this.starttime = starttime;
		this.remain = remain;
		this.status = status;
	}



	public PlayingVO(String no, String hall_no, String cinema_no, Timestamp starttime) {
		super();
		this.no = no;
		this.hall_no = hall_no;
		this.cinema_no = cinema_no;
		this.starttime = starttime;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getHall_no() {
		return hall_no;
	}

	public void setHall_no(String hall_no) {
		this.hall_no = hall_no;
	}

	public String getCinema_no() {
		return cinema_no;
	}

	public void setCinema_no(String cinema_no) {
		this.cinema_no = cinema_no;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PlayingVO [no=" + no + ", hall_no=" + hall_no + ", cinema_no=" + cinema_no + ", starttime=" + starttime
				+ ", remain=" + remain + ", status=" + status + "]";
	}

}
