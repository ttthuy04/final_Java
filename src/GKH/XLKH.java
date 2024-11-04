package GKH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XLKH implements IKhachhang{

	private static Connection cn;
	private static void connectToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dburl = "jdbc:sqlserver://localhost:1433;databaseName=DLKH;user=sa;password=123456@;trustServerCertificate=true;";
            cn = DriverManager.getConnection(dburl);
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//Connection  getCon();
	public Connection getCon() throws SQLException{
		if(cn == null || cn.isClosed()) {
			connectToDatabase();
		}
		return cn;
		
		
		
		
	}
	
	
	//Khachhang getbySTK(); lay thong tin stk 
	
	
	public Khachhang getbySTK(String stk) {
		String sql = "Select *from tbKH where SoTK =? ";
		try {
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setString(1, stk);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return new Khachhang(
						rs.getString("SoTK"),
						rs.getString("Hoten"),
						rs.getString("GT"),
						rs.getString("Diachi"),
						rs.getDouble("Sodu")
						
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//void updateKH(Khachhang kh); vi khong co select nen khong can rs
	public void updateKH(Khachhang kh) {
		String sql = "update tbKH set Hoten =? , GT = ? , Diachi=? ,Sodu=? where SoTK=?";
		try {
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setString(1, kh.getHoten());
			stmt.setString(2, kh.getGt());
			stmt.setString(3, kh.getDiachi());
			stmt.setDouble(4, kh.getSodu());
			stmt.setString(5, kh.getStk());
			 stmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

