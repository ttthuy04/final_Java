package GKH;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;





public class GUI_updateKH extends JFrame{
	private JTextField txtsotk;
	private JTextField txthoten;
	private JTextField txtGT;
	private DefaultTableModel model;
	private JTextField txtsodu;
	private JComboBox cboDiachi;
	private XLKH xlkh = new XLKH();
	private JTable table;

	public GUI_updateKH() {
		setTitle("Quan ly KH");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//panel chinh giu thanh phan
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10,10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		//panel chua truong nhajp lieu
		JPanel fromPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		
		//thong tin khach hang
		 txtsotk = new JTextField(15);
		  txthoten = new JTextField(15);
		  txtGT = new JTextField(15);
		 cboDiachi = new JComboBox<>(new String [] {"Ha Noi ", "Hai Phong","Bac giang"});
		  txtsodu = new JTextField(15);
		   
		  
		  
		  JButton btnTimkiem = new JButton("Tim kiem khach hang");
		  JButton btnupdate = new JButton("Chinh sua ");
		  
		  
		  //hang 1
		  gbc.gridx =0;
		  gbc.gridy =0;
		  fromPanel.add(new JLabel("So TK"),gbc);
		  gbc.gridx=1;
		  fromPanel.add(txtsotk,gbc);
		  
		  
		  //hang 2
		  gbc.gridx =0;
		  gbc.gridy =1;
		  fromPanel.add(new JLabel("Ho Ten"),gbc);
		  gbc.gridx=1;
		  fromPanel.add(txthoten,gbc);
		  
		  //hang 3
		  gbc.gridx =0;
		  gbc.gridy =2;
		  fromPanel.add(new JLabel("Gioi tinh"),gbc);
		  gbc.gridx=1;
		  fromPanel.add(txtGT,gbc);
		  
		  //hang 4
		  gbc.gridx =0;
		  gbc.gridy =3;
		  fromPanel.add(new JLabel("Dia chi"),gbc);
		  gbc.gridx=1;
		  fromPanel.add(cboDiachi,gbc);
		  
		  //hang 5
		  gbc.gridx =0;
		  gbc.gridy =4;
		  fromPanel.add(new JLabel("So du"),gbc);
		  gbc.gridx=1;
		  fromPanel.add(txtsodu,gbc);
		  
		  //button 
		  JPanel btnpanel = new JPanel(new FlowLayout());
		  btnpanel.add(btnTimkiem);
		  btnpanel.add(btnupdate);
		  //table 
		   model =new  DefaultTableModel(new String [] {"STK", "Ho ten","GT","Dia chi","So du"},0);
		   table = new JTable(model);
		  JScrollPane scrollBar = new JScrollPane(table);
		  
		
		  
		  
		  mainPanel.add(fromPanel,BorderLayout.NORTH);
		  mainPanel.add(btnpanel,BorderLayout.SOUTH);
		  mainPanel.add(scrollBar,BorderLayout.CENTER);
		  add(mainPanel);
		  loadKhachHang();
		  
		  
		  
		  //nut tim kiem
		  btnTimkiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String soTK = txtsotk.getText();
				Khachhang kh = xlkh.getbySTK(soTK);
				if(kh!=null) {
					txthoten.setText(kh.getHoten());
					txtGT.setText(kh.getGt());
					cboDiachi.setSelectedItem(kh.getDiachi());
					txtsodu.setText(String.valueOf(kh.getSodu()));
				}
				else {
					JOptionPane.showMessageDialog(null, "Khong tim thay");
				}
				
			}
		});
		  
		  btnupdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String soTK = txtsotk.getText();
				String hoten = txthoten.getText();
				String gt = txtGT.getText();
				String diachi = (String) cboDiachi.getSelectedItem();
				double sodu = Double.parseDouble(txtsodu.getText());
				
				
				Khachhang kh = new Khachhang(soTK, hoten, gt,diachi,sodu);
				xlkh.updateKH(kh);
				loadKhachHang();
			}
		});
		
		  setVisible(true);
	}
	public void loadKhachHang() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        String sql = "SELECT * FROM tbKH";
        try {
            Statement stmt = xlkh.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String soTK = rs.getString("SoTK");
                String hoTen = rs.getString("Hoten");
                String gT = rs.getString("GT");
                String diaChi = rs.getString("Diachi");
                double soDu = rs.getDouble("Sodu");
                model.addRow(new Object[]{soTK, hoTen, gT, diaChi, soDu});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		new GUI_updateKH().setVisible(true);
	}

}
