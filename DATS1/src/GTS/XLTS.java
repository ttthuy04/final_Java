package GTS;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class XLTS implements IThisinh{
	
	private static Connection cn;
	private static void connectToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dburl = "jdbc:sqlserver://localhost:1433;databaseName=DLTS;user=sa;password=123456@;trustServerCertificate=true;";
            cn = DriverManager.getConnection(dburl);
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	//trien khai getcon
	public Connection getCon() throws SQLException{
		if(cn == null || cn.isClosed()) {
			connectToDatabase();
		}
		return cn;
		
	}
	
	//lay danh sach
	
	public List<Thisinh> getTS(){
		List<Thisinh> list = new ArrayList<Thisinh>();
		String query = "select *from Thisinh";
		try(Connection conn = getCon();
			Statement st = conn.createStatement() ;
			ResultSet rs = st.executeQuery(query)){
			
			while(rs.next()) {
				list.add(new Thisinh (
					rs.getString("SoBD"),
					rs.getString("Hoten"),
					rs.getString("GT"),
					rs.getString("NganhH"),
					rs.getDouble("TongD")
					));
				
				
			}
			
		}catch (SQLException e) {
			
		}
		return list;
				
	}
	//them moi thi sinh
	public void insertTS(Thisinh ts) {
		String sql = "insert into Thisinh values (?,?,?,?,?)";
		try (Connection conn = getCon();
				PreparedStatement ps = conn.prepareStatement(sql)
				) {
			ps.setString(1, ts.getSoBD());
			ps.setString(2, ts.getHoten());
			ps.setString(3, ts.getGT());
			ps.setString(4, ts.getNganhH());
			ps.setDouble(5, ts.getTongD());
			//thuc thi
			ps.executeUpdate();
			System.out.println("Them thanh cong");
		} catch (Exception e) {
			System.err.println("Loi khi them " +e.getMessage());
		}
	}
	
	

}
