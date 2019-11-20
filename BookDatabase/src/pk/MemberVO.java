package pk;

import java.sql.Date;

public class MemberVO {
	private int id;
	private String name;
	private String publisher;
	private int price;
	
	public MemberVO() {
		System.out.println("MemberVO 생성자 호출");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
