package bookTest2;

public class Books {
	private int Id;    
	private String Title; 
	private String Publisher; 
	private String Year; 
	private int Price;
	public Books(int id, String title, String publisher, String year, int price) {
		super();
		Id = id;
		Title = title;
		Publisher = publisher;
		Year = year;
		Price = price;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "books [Id=" + Id + ", Title=" + Title + ", Publisher=" + Publisher + ", Year=" + Year + ", Price="
				+ Price + "]";
	}
	
	
	
}
