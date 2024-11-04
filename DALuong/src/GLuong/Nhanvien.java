package GLuong;

public class Nhanvien extends Person{
	private String diachi;
	private double luong;
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public Nhanvien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nhanvien(String manv, String hoten,String diachi,double luong) {
		super(manv, hoten);
		this.diachi=diachi;
		this.luong=luong;
	}
	

}
