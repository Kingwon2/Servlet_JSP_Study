package tb;


import java.sql.Date;

public class MemberBean {
	private String id;
	private String pwd;
	private String name;
	private String sex;
	private String area;
	
	
	public MemberBean() {
		
	}


	public MemberBean(String id, String pwd, String name, String sex,String area) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.sex = sex;
		this.area = area;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}
}
