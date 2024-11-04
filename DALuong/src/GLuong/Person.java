package GLuong;

public class Person {
	private String manv, hoten;

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public Person() {
		
	}

	public Person(String manv, String hoten) {
		super();
		this.manv = manv;
		this.hoten = hoten;
	}
	
}
