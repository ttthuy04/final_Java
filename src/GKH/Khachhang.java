package GKH;

public class Khachhang {
	private String stk, hoten, gt, diachi;
	private double sodu;
	public String getStk() {
		return stk;
	}
	public void setStk(String stk) {
		this.stk = stk;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getGt() {
		return gt;
	}
	public void setGt(String gt) {
		this.gt = gt;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public double getSodu() {
		return sodu;
	}
	public void setSodu(double sodu) {
		this.sodu = sodu;
	}
	public Khachhang() {
		
	}
	public Khachhang(String stk, String hoten, String gt, String diachi, double sodu) {
		super();
		this.stk = stk;
		this.hoten = hoten;
		this.gt = gt;
		this.diachi = diachi;
		this.sodu = sodu;
	}
	

}
