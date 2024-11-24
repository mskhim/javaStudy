package com.kh.theaterProject.model;

public class CinemaVO {
	private String no;// char(2),                     --PK
	private String name;// varchar(30) not null,      --이름
	private int runningtime;// number(3) not null, --러닝타임
	private String status;// char(1) default 0 
	
	public CinemaVO() {
		super();
	}

	public CinemaVO(String no, String name, int runningtime) {
		super();
		this.no = no;
		this.name = name;
		this.runningtime = runningtime;
	}

	public CinemaVO(String no, String name, int runningtime, String status) {
		super();
		this.no = no;
		this.name = name;
		this.runningtime = runningtime;
		this.status = status;
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

	public int getRunningtime() {
		return runningtime;
	}

	public void setRunningtime(int runningtime) {
		this.runningtime = runningtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public static String getHeader() {
        return String.format(
            "%-10s %-20s %-15s %-10s",
            "No", "Name", "Running Time", "Status"
        );
    }
	
	@Override
	   public String toString() {
        return String.format(
            "%-10s %-20s %-15d %-10s",
            no != null ? no : "N/A",
            name != null ? name : "N/A",
            runningtime,
            getStatusText()
        );
    }

    // status 값을 텍스트로 변환
    private String getStatusText() {
        if (status == null) {
            return "-";
        } else {
        	return "상영중";
        }
    }

}
