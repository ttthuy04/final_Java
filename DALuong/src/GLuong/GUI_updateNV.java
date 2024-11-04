package GLuong;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI_updateNV extends JFrame{
	private JTextField txtma;
	private JTextField txthoten;
	private JComboBox cbodiachi;
	private JTextField txtluong;
	private DefaultTableModel model;
	private JTable table;
	private XLLuong xll = new XLLuong();
	public GUI_updateNV() {
		setTitle("Quan li nhan vien");
		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		//panel chinhs de chinh sua cac thanh phan
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10,10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		
		//panel chua phan nhap lieu
		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		
		
		//thong tin nhan vien
		 txtma= new JTextField(15);
		 txthoten= new JTextField(15);
		 cbodiachi = new JComboBox<>(new String[] {"Ha Noi","Hai Phong","Quang ninh"});
		 txtluong= new JTextField(15);
		
		 JButton btnTimkiem = new JButton("Tim kiem");
		 JButton btnUpdate = new JButton("Chinh sua");
		 //hang 1
		
		gbc.gridx=0;
		gbc.gridy=0;
		formPanel.add(new JLabel("Ma nhan vien"),gbc);
		gbc.gridx=1;
		formPanel.add(txtma,gbc);
		 
		//hang 2
		gbc.gridx=0;
		gbc.gridy=1;
		formPanel.add(new JLabel("Ho ten"),gbc);
		gbc.gridx=1;
		formPanel.add(txthoten,gbc);
		 //hang 3
		gbc.gridx=0;
		gbc.gridy=2;
		formPanel.add(new JLabel("Dia Chi"),gbc);
		gbc.gridx=1;
		formPanel.add(cbodiachi,gbc);
		
		//hang 4
		gbc.gridx=0;
		gbc.gridy=3;
		formPanel.add(new JLabel("Luong"),gbc);
		gbc.gridx=1;
		formPanel.add(txtluong,gbc);
		
		//button 
		JPanel btnpanel = new JPanel(new FlowLayout());
		btnpanel.add(btnTimkiem);
		btnpanel.add(btnUpdate);
		//table
		
		 model = new DefaultTableModel(new String [] {"MaNV","Hoten","Diachi","Luong"},0);
		 table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		
		
		
		
		
		
		mainPanel.add(formPanel,BorderLayout.NORTH);
		mainPanel.add(btnpanel,BorderLayout.SOUTH);
		mainPanel.add(scroll,BorderLayout.CENTER);
		add(mainPanel);
		loadNV();
		
		//xuli su kien
		btnTimkiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma = txtma.getText();
				Nhanvien nv = xll.getNVbyMa(ma);
				if(nv!=null ) {
					txthoten.setText(nv.getHoten());
					cbodiachi.setSelectedItem(nv.getDiachi() );
					txtluong.setText(String.valueOf(nv.getLuong()));
				}
				else {
					JOptionPane.showMessageDialog(null, "Khong tim thay ma ");
				}
				
			}
		});
		//update 
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma = txtma.getText();
				String hoten= txthoten.getText();
				String diachi = (String)cbodiachi.getSelectedItem();
				Double luong = Double.parseDouble(txtluong.getText());
				
				Nhanvien nv= new Nhanvien(ma,hoten,diachi,luong);
				xll.updateNV(nv);
				loadNV();
			}
		});
	}
	
	
	
	
	//load du lieju khi chay
	
	public void loadNV() {
		model.setRowCount(0);// xoa du lieu cu
		String sql = "Select *from Nhanvien";
		try {
			Statement stmt = xll.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("MaNV");
				String hoten = rs.getString("Hoten");
				String diachi = rs.getString("Diachi");
				Double luong = rs.getDouble("Luong");
				model.addRow(new Object[] {ma, hoten,diachi,luong});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new GUI_updateNV().setVisible(true);
	}

}
