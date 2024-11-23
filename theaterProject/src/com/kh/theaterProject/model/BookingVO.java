package com.kh.theaterProject.model;

import java.sql.Date;

public class BookingVO {
	private String no;// char(5),                          --PK 
	private String playing_no;// char(3),                  --FK상영작NO
	private String customer_no;// char(5),                 --FK고객NO 
	private String code;// char(15),                       --UQ예매 코드 생성 고객NO-상영작NO-예매NO로생성
	private int amount;// number(2) not null,           --합계인원
	private int price;// number(7),                     --합계가격
	private Date booking_date;// date not null           --예매날짜
	public BookingVO() {
		super();
	}
	public BookingVO(String no, String playing_no, String customer_no, int amount, Date booking_date) {
		super();
		this.no = no;
		this.playing_no = playing_no;
		this.customer_no = customer_no;
		this.amount = amount;
		this.booking_date = booking_date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPlaying_no() {
		return playing_no;
	}
	public void setPlaying_no(String playing_no) {
		this.playing_no = playing_no;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	@Override
	public String toString() {
		return "BookingVO [no=" + no + ", playing_no=" + playing_no + ", customer_no=" + customer_no + ", code=" + code
				+ ", amount=" + amount + ", price=" + price + ", booking_date=" + booking_date + "]";
	}
	
}
