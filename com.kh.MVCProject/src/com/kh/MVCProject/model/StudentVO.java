package com.kh.MVCProject.model;

public class StudentVO {
private int	code;
private String name;
private String birth;
private int	kor;
private int	math;
private int	eng;
private int	total;
private double	avg;
private int	rank;

public StudentVO() {
}

public StudentVO(int code, String name, String birth, int kor, int math, int eng, int total, double avg, int rank) {
	super();
	this.code = code;
	this.name = name;
	this.birth = birth;
	this.kor = kor;
	this.math = math;
	this.eng = eng;
	this.total = total;
	this.avg = avg;
	this.rank = rank;
}
public StudentVO(int code, String name, String birth, int kor, int math, int eng) {
	super();
	this.code = code;
	this.name = name;
	this.birth = birth;
	this.kor = kor;
	this.math = math;
	this.eng = eng;
}

public int getKor() {
	return kor;
}
public void setKor(int kor) {
	this.kor = kor;
}
public int getMath() {
	return math;
}
public void setMath(int math) {
	this.math = math;
}
public int getEng() {
	return eng;
}
public void setEng(int eng) {
	this.eng = eng;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public double getAvg() {
	return avg;
}
public void setAvg(double avg) {
	this.avg = avg;
}

public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}
public int getCode() {
	return code;
}

public void setCode(int code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBirth() {
	return birth;
}
public void setBirth(String birth) {
	this.birth = birth;
}
public int getkor() {
	return kor;
}
public void setkor(int kor) {
	this.kor = kor;
}
@Override
public String toString() {
    // Data row only
    return String.format(
        "%-10d %-15s %-10s %5d %5d %5d %7d %7.2f %5d",
        code, name, birth, kor, math, eng, total, avg, rank
    );
}

// Static method for header
public static String getHeader() {
    return String.format(
        "%-10s %-15s %-10s %5s %5s %5s %7s %7s %5s",
        "Code", "Name", "Birth", "Kor", "Math", "Eng", "Total", "Avg", "Rank"
    );
}



}
