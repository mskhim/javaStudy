package studentHome;

public class student {
private int	code;
private String name;
private String birth;
private int	kor;
private int	math;
private int	eng;
private int	total;
private int	avg;
private int	rank;

public student(int code, String name, String birth, int kor, int math, int eng, int total, int avg, int rank) {
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
public student(int code, String name, String birth, int kor, int math, int eng) {
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
public int getAvg() {
	return avg;
}
public void setAvg(int avg) {
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
	return "student [code=" + code + ", name=" + name + ", birth=" + birth + ", kor=" + kor + ", math=" + math
			+ ", eng=" + eng + ", total=" + total + ", avg=" + avg + ", rank=" + rank + "]";
}



}
