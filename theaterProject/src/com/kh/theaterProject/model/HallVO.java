package com.kh.theaterProject.model;

public class HallVO {
	private String no;// CHAR(2),                   --PK
	private int seats;// NUMBER(3) NOT NULL,     --좌석수
	private int price;// NUMBER(6) NOT NULL      --상영료
	public HallVO() {
		super();
	}
	public HallVO(String no, int seats, int price) {
		super();
		this.no = no;
		this.seats = seats;
		this.price = price;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "HallVO [no=" + no + "관 , seats=" + seats + ", price=" + price + "]";
	}

}
