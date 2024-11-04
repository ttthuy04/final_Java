package GTS;

public class Thisinh {
	private String SoBD, Hoten, GT, NganhH;
	private double TongD;
	
	public Thisinh() {
		
	}
	public Thisinh(String SoBD, String Hoten, String GT, String NganhH,double TongD) {
		this.SoBD=SoBD;
		this.Hoten=Hoten;
		this.GT=GT;
		this.NganhH=NganhH;
		this.TongD=TongD;
	}
	public String getSoBD() {
		return SoBD;
	}
	public void setSoBD(String soBD) {
		SoBD = soBD;
	}
	public String getHoten() {
		return Hoten;
	}
	public void setHoten(String hoten) {
		Hoten = hoten;
	}
	public String getGT() {
		return GT;
	}
	public void setGT(String gT) {
		GT = gT;
	}
	public String getNganhH() {
		return NganhH;
	}
	public void setNganhH(String nganhH) {
		NganhH = nganhH;
	}
	public double getTongD() {
		return TongD;
	}
	public void setTongD(double tongD) {
		TongD = tongD;
	}
	public String  Hocbong() {
		if(getTongD()>29) {
			return "HB";
		}
		else {
			return "";
		}
	}

}
