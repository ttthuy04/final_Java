package GLuong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XLLuong {
	private static Connection cn;
	private static void connectToDatabase() {
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dburl = "jdbc:sqlserver://localhost:1433;databaseName=DLLuong;user=sa;password=123456@;trustServerCertificate=true;";
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
	
	//getNVbyma de lay tat ca thong tin nhan vien trong bang
	public Nhanvien getNVbyMa(String ma) {
		String sql = "select *from Nhanvien where MaNV =? ";
		try {
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setString(1,ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return new Nhanvien(
						rs.getString("MaNV"),
						rs.getString("Hoten"),
						rs.getString("Diachi"),
						rs.getDouble("Luong")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public void updateNV(Nhanvien nv) {
		String sql = "update Nhanvien set Hoten=?,Diachi=?,Luong=? where MaNV =? ";
		try {
			PreparedStatement stmt = cn.prepareStatement(sql);
			stmt.setString(1, nv.getHoten());
			stmt.setString(2, nv.getDiachi());
			stmt.setDouble(3,nv.getLuong());
			stmt.setString(4, nv.getManv());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
