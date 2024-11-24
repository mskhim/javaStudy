package com.kh.theaterProject.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BookingVO {
	private String no;// char(5), --PK
	private String playing_no;// char(3), --FK상영작NO
	private String customer_no;// char(5), --FK고객NO
	private String code;// char(15), --UQ예매 코드 생성 고객NO-상영작NO-예매NO로생성
	private int amount;// number(2) not null, --합계인원
	private int price;// number(7), --합계가격
	private Timestamp booking_date;// date not null --예매날짜
	private String hallName;
	private String cineName;
	private String cusName;
	private Timestamp startTime;
	private String status;
	
	
	public BookingVO(String no, String playing_no, String customer_no, String code, int amount, int price,
			Timestamp booking_date,  String cName, String hName,Timestamp startTime,String status,String cusName) {
		super();
		this.no = no;
		this.playing_no = playing_no;
		this.customer_no = customer_no;
		this.code = code;
		this.amount = amount;
		this.price = price;
		this.booking_date = booking_date;
		this.hallName = hName;
		this.cineName = cName;
		this.startTime = startTime;
		this.status = status;
		this.cusName = cusName;
		
	}

	public BookingVO() {
		super();
	}

	public BookingVO(String no, String playing_no, String customer_no, int amount, Timestamp booking_date) {
		super();
		this.no = no;
		this.playing_no = playing_no;
		this.customer_no = customer_no;
		this.amount = amount;
		this.booking_date = booking_date;
	}

	
	
	
	public BookingVO(String no, String playing_no, String customer_no, String code, int amount, int price,
			Timestamp booking_date) {
		super();
		this.no = no;
		this.playing_no = playing_no;
		this.customer_no = customer_no;
		this.code = code;
		this.amount = amount;
		this.price = price;
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

	public Timestamp getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Timestamp booking_date) {
		this.booking_date = booking_date;
	}

	public static String getHeader() {
		return String.format("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-20s %-10s %-10s %-20s %-10s", "No", "Playing No", "Customer No","Customer Name","hall","Cinema Name","Start Time", "Code",
				"Amount", "Price", "Booking Date","Status");
	}

	// 데이터 출력 부분
	@Override
	public String toString() {
		return String.format("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-20s %-10s %-10s %-20s %-10s ", no, playing_no, customer_no,cusName,hallName+"관",cineName,formatStartTime(), code, amount,
				price, booking_date,getStatusText());
	}
	
    private String formatStartTime() {
        if (startTime == null) {
            return "N/A";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(startTime);
    }
    private String getStatusText() {
        if (status == null) {
            return "만료";
        } else {
        if(status.equals("0"))
        	return "사용예정";
        else {
        	return "상영중";
        }
        }
    }
}
