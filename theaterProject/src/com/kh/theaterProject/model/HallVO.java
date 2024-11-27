package com.kh.theaterProject.model;

public class HallVO {
	private String no;// CHAR(2),                   --PK
	private int seats;// NUMBER(3) NOT NULL,     --좌석수
	private int price;// NUMBER(6) NOT NULL      --상영료
	private int row;
	private int col;
	


	

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public HallVO(String no, int price, int row, int col) {
		super();
		this.no = no;
		this.price = price;
		this.row = row;
		this.col = col;
	}

	public HallVO(String no, int seats, int price, int row, int col) {
		super();
		this.no = no;
		this.seats = seats;
		this.price = price;
		this.row = row;
		this.col = col;
	}

	public HallVO() {
		super();
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
        return String.format(
            "%-10s %-10d %-10d %-10d %-10d",
            no != null ? no : "N/A",
            seats,
            price,
            row,
            col
        );
    }
	
    public static String getHeader() {
        return String.format(
            "%-10s %-10s %-10s %-10s %-10s",
            "Hall No", "Seats", "Price","Row","Col"
        );
    }
}
