package GTS;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Gui_insertTS extends JFrame {
	
	
	private JTextField txtSoBD;
	private JTextField txtHoten;
	private JTextField txtTongD;
	private JComboBox cboNganhH;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JTable table;

	private XLTS xlts = new XLTS();
	private DefaultTableModel model;

	public Gui_insertTS() {
		setTitle("Quan ly tuyen sinh");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// panel chinh giu thanh phan
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10,10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//panel chua truong nhap lieu
		JPanel fromPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Thong tin sv
		 txtSoBD = new JTextField(15);
		 txtHoten = new JTextField(15);
		 txtTongD = new JTextField(15);
		 cboNganhH =new JComboBox<>(new String [] {"Tri tue nhan tao","Co khi","Cong trinh"});
		 rdoNam = new JRadioButton("Nam");
		 rdoNu = new JRadioButton("Nu");
		
		//nhom nam nu vao la 1
		ButtonGroup gr = new ButtonGroup();
		gr.add(rdoNam);
		gr.add(rdoNu);
		
		
		JButton btnThem = new JButton("Them ");
		//hang 1 
		gbc.gridx= 0;
		gbc.gridy =0;
		fromPanel.add(new JLabel("So bao danh"),gbc);
		gbc.gridx =1;
		fromPanel.add(txtSoBD,gbc);
		
		//hang 2
		
		gbc.gridx= 0;
		gbc.gridy =1;
		fromPanel.add(new JLabel("Ho ten"),gbc);
		gbc.gridx =1;
		fromPanel.add(txtHoten,gbc);
		
		//hang 3
		gbc.gridx =0;
		gbc.gridy = 2;
		fromPanel.add(new JLabel("Gioi tinh"),gbc);
		JPanel gender = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		gender.add(rdoNam);
		gender.add(rdoNu);
		gbc.gridx =1;
		fromPanel.add(gender,gbc);
		
		//hang 4
		gbc.gridx= 0;
		gbc.gridy =3;
		fromPanel.add(new JLabel("Nganh hoc"),gbc);
		gbc.gridx =1;
		fromPanel.add(cboNganhH,gbc);
				
		//hang 5
		gbc.gridx= 0;
		gbc.gridy =4;
		fromPanel.add(new JLabel("Tong diem"),gbc);
		gbc.gridx =1;
		fromPanel.add(txtTongD,gbc);
		
		
		//button 
		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.add(btnThem);
		
		//bang jtable
		 model = new DefaultTableModel(new String [] {"SBD","Ho ten","GT","Nganh hoc","Diem","Hoc bong"},0);
		 table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		//su kien cho nut them
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String gt = rdoNam.isSelected() ? "Nam" : "Nữ";
                Thisinh ts = new Thisinh(txtSoBD.getText(), txtHoten.getText(), gt,
                                         (String) cboNganhH.getSelectedItem(),
                                         Integer.parseInt(txtTongD.getText()));
                xlts.insertTS(ts);
                loadTable();  
            }
        });
		
		
		
		mainPanel.add(fromPanel, BorderLayout.NORTH);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
	        // Add main panel to the frame
	    add(mainPanel);
	    loadTable();
	}
	private void loadTable() {
        model.setRowCount(0);
        for (Thisinh ts : xlts.getTS()) {
            model.addRow(new Object[]{ts.getSoBD(), ts.getHoten(), ts.getGT(), ts.getNganhH(), ts.getTongD(), ts.Hocbong()});
        }
    }
	public static void main(String[] args) {
		new Gui_insertTS().setVisible(true);
	}

}
