package com.kh.theaterProject.model;

public class SeatsVO {
	private String playingNo;
	private String hallNo;// CHAR(2),                   --PK
	private String X;
	private String Y;
	private String customerNo ;
	private String bookingNo ;
	
	



	public String getPlayingNo() {
		return playingNo;
	}


	public void setPlayingNo(String playingNo) {
		this.playingNo = playingNo;
	}


	public SeatsVO() {
		super();
	}

	




	public String getBookingNo() {
		return bookingNo;
	}


	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}


	public SeatsVO(String playingNo, String hallNo, String x, String y, String customerNo, String bookingNo) {
		super();
		this.playingNo = playingNo;
		this.hallNo = hallNo;
		X = x;
		Y = y;
		this.customerNo = customerNo;
		this.bookingNo = bookingNo;
	}


	public String getHallNo() {
		return hallNo;
	}


	public void setHallNo(String hallNo) {
		this.hallNo = hallNo;
	}


	public String getX() {
		return X;
	}


	public void setX(String x) {
		X = x;
	}


	public String getY() {
		return Y;
	}


	public void setY(String y) {
		Y = y;
	}


	public String getCustomerNo() {
		return customerNo;
	}


	public void setCustomerNo(String status) {
		this.customerNo = status;
	}


	@Override
	public String toString() {
		 if(customerNo==null){return (X+Y);}else{
			return "---";
		}
	}
	
	
	
}
