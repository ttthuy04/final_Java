package GTS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IThisinh {
	Connection getCon()throws SQLException ;
	//lay danh sach
	List<Thisinh> getTS();
	//Them moi
	void insertTS(Thisinh ts);
	
}
