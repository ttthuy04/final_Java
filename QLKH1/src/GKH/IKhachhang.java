package GKH;

import java.sql.Connection;
import java.sql.SQLException;

public interface IKhachhang {
	Connection  getCon()throws SQLException;
	Khachhang getbySTK(String stk);
	void updateKH(Khachhang kh);

}
