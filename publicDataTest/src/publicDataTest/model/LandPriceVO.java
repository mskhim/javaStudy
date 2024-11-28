package publicDataTest.model;

public class LandPriceVO {
	private double gpslati;
	private double gpslong; 
	private String nodeid; 
	private String nodenm; 
	private int nodeno;
	public LandPriceVO(double gpslati, double gpslong, String nodeid, String nodenm, int nodeno) {
		super();
		this.gpslati = gpslati;
		this.gpslong = gpslong;
		this.nodeid = nodeid;
		this.nodenm = nodenm;
		this.nodeno = nodeno;
	}
	public LandPriceVO() {
		super();
	}
	public double getGpslati() {
		return gpslati;
	}
	public void setGpslati(double gpslati) {
		this.gpslati = gpslati;
	}
	public double getGpslong() {
		return gpslong;
	}
	public void setGpslong(double gpslong) {
		this.gpslong = gpslong;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodenm() {
		return nodenm;
	}
	public void setNodenm(String nodenm) {
		this.nodenm = nodenm;
	}
	public int getNodeno() {
		return nodeno;
	}
	public void setNodeno(int nodeno) {
		this.nodeno = nodeno;
	}
	@Override
	public String toString() {
		return "LandPrice [gpslati=" + gpslati + ", gpslong=" + gpslong + ", nodeid=" + nodeid + ", nodenm=" + nodenm
				+ ", nodeno=" + nodeno + "]";
	} 

}
