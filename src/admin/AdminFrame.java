package admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import consumer.LogIn;

public class AdminFrame {

	static public JFrame frame1;
	private JTextField nameTF;
	private JTextField addTF;
	private JTable table;
	JTextField xId = new JTextField();
	JTextField newEdit = new JTextField();
	JPasswordField newPass = new JPasswordField();
	JLabel totalConsumer = new JLabel("");
	JLabel totalStaff = new JLabel("");
	ButtonGroup bg = new ButtonGroup();
	JRadioButton male1= new JRadioButton("Male");
	JRadioButton female1 = new JRadioButton("Female");
	Date date = new Date();
	String fmt = "yy-MM-dd HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	String dt = sdf.toString();

	DefaultTableModel model;
	DefaultTableModel model1;
	Connection conn =  null;
	Statement stmt;
	PreparedStatement pst;
	static ResultSet rs;
	int i;
	private int line;
	static int idRec[] = new int[20];
	static double arrRec[] = new double[20];
	private JTable table_1;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	void clear() {
		xId.setText("");
		newEdit.setText("");
		bg.clearSelection();
	}
	
	private void deleteConsumerRecords() {
		Object [] mes = {
				"Input the Account Number",xId
		};
	int res = JOptionPane.showConfirmDialog(null,mes,"Deleting Account Record",JOptionPane.OK_CANCEL_OPTION);
	try
			{		
		if(res == JOptionPane.OK_OPTION) {
			int id = Integer.parseInt(xId.getText());

				int y =searchId(id);
			
					 if(y>0) {
						stmt.executeUpdate("delete from consumer where AccountNumber='"+id+"'");
						JOptionPane.showMessageDialog(null, "Succesfully Deleted Records!","",JOptionPane.INFORMATION_MESSAGE);	
					}	
					 else if(id==0) {
							JOptionPane.showMessageDialog(null, "Please input the Account ID! ","",JOptionPane.ERROR_MESSAGE);	
					 }
					else if(y<0) {
								JOptionPane.showMessageDialog(null, "Incorrect ID!!","",JOptionPane.ERROR_MESSAGE);	
					}
						}
				
										}
			catch( SQLException err)
			{
			}
		catch(NumberFormatException ee)
		{
		JOptionPane.showMessageDialog(null, "Please Input the ID","",JOptionPane.ERROR_MESSAGE);	
		}
	}

	private void deleteStaffRecords() {
		Object [] mes = {
				"Input the EmployeeID",xId
		};
	int res = JOptionPane.showConfirmDialog(null,mes,"Deleting Account Record",JOptionPane.OK_CANCEL_OPTION);
	try
			{		
		if(res == JOptionPane.OK_OPTION) {
			int id = Integer.parseInt(xId.getText());

				int y =searchStaffId(id);
			
					 if(y>0) {
						stmt.executeUpdate("delete from staff where EmployeeID='"+id+"'");
						JOptionPane.showMessageDialog(null, "Succesfully Deleted Records!","",JOptionPane.INFORMATION_MESSAGE);	
					}	
					 else if(id==0) {
							JOptionPane.showMessageDialog(null, "Please input the Account ID! ","",JOptionPane.ERROR_MESSAGE);	
					 }
					else if(y<0) {
								JOptionPane.showMessageDialog(null, "Incorrect ID!!","",JOptionPane.ERROR_MESSAGE);	
					}
						}
				
										}
			catch( SQLException err)
			{
			}
		catch(NumberFormatException ee)
		{
		JOptionPane.showMessageDialog(null, "Please Input the ID","",JOptionPane.ERROR_MESSAGE);	
		}
	}
	private int searchId(int ID) throws SQLException {
		
		String sql = "Select AccountNumber from consumer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		for ( i=0;rs.next();i++) {
			idRec[i] = rs.getInt(1);
			if(idRec[i]==ID) {
				return 1;
			}
			

		}
		return -1;

		
	}
	
	private int searchStaffId(int ID) throws SQLException {
		
		String sql = "Select EmployeeID from staff";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		for ( i=0;rs.next();i++) {
			idRec[i] = rs.getInt(1);
			if(idRec[i]==ID) {
				return 1;
			}
			

		}
		return -1;

		
	}
	private Connection dbConnect() { // Step 1 - Get a connection to SQLite database	
		try 
		{   // Step 1.1 - load Java's JDBC SQLite Driver
			Class.forName("org.sqlite.JDBC");			
			// Step 1.2 - get a DB Connection
			conn = DriverManager.getConnection("JDBC:sqlite:D:\\Christian A\\Judel Jean\\waterDistrict.sqlite"); // created using SQLite Manager (SQLiteManager_4.6.6_1430708940)
			// prompt user if connection attempt is successful
			return conn;
		}
		catch (Exception err)
		{
			JOptionPane.showMessageDialog(null, "Connection unsuccessful. Exception -> "+err);
			return null;
		}
	}
	
	private void resultSetToTableModel(ResultSet rs)
	{
		model.setRowCount(0);
		int count=0;		
		try {
			while (rs.next()) {
				Object[] row = {rs.getString("AccountNumber"), rs.getString("Name"),rs.getString("Address"),rs.getString("Gender"),
						rs.getString("Contact"),rs.getString("Password"),rs.getString("Bill"),rs.getString("Arrears"),rs.getString("Previous"),
						rs.getString("Present"),rs.getString("Date"),rs.getString("Paid")
						};
				
				model.addRow(row);
				count++;
				totalConsumer.setText("Total: "+count);
			}
			
		} catch (Exception err) {
			
			err.printStackTrace();
		}
		
	}
	
	private void resultSetToTableModelStaff(ResultSet rs)
	{
		model1.setRowCount(0);
		int count=0;		
		try {
			while (rs.next()) {
				Object[] row = {rs.getString("EmployeeID"), rs.getString("Name"),rs.getString("Address"),rs.getString("Gender"),
						rs.getString("Contact"),rs.getString("Username"),rs.getString("Password")
						};
				
				model1.addRow(row);
				count++;
				totalStaff.setText("Total: "+count);
			}
			
		} catch (Exception err) {
			
			err.printStackTrace();
		}
		
	}
	
	
	
	
	public AdminFrame() {
		initialize();
		conn = dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.getContentPane().setBackground(new Color(102, 255, 255));
		frame1.setBounds(100, 100, 772, 540);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(10, 38, 360, 173);
		frame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 45, 66, 14);
		panel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(10, 70, 76, 14);
		panel.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(10, 95, 66, 14);
		panel.add(lblGender);
		bg.add(female1);
		bg.add(male1);
		
		JLabel lblFilters = new JLabel("FILTERS");
		lblFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFilters.setBounds(10, 11, 109, 23);
		panel.add(lblFilters);
		
		nameTF = new JTextField();
		nameTF.setBounds(86, 44, 186, 20);
		panel.add(nameTF);
		nameTF.setColumns(10);
		
		addTF = new JTextField();
		addTF.setColumns(10);
		addTF.setBounds(86, 69, 186, 20);
		panel.add(addTF);
		
		JRadioButton male = new JRadioButton("Male");
		male.setFont(new Font("Tahoma", Font.BOLD, 12));
		male.setBounds(86, 93, 76, 23);
		panel.add(male);
		bg.add(male);
		JRadioButton female = new JRadioButton("Female");
		female.setFont(new Font("Tahoma", Font.BOLD, 12));
		female.setBounds(194, 93, 76, 23);
		panel.add(female);
		bg.add(female);
		bg.add(female1);
		bg.add(male1);
		
		JButton btnQue = new JButton(">>");
		btnQue.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnQue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name = nameTF.getText();
				try
				{
					String query = "select * from consumer where Name='"+name+"'"; // select all columns from the student (SQLite DB) where Course is as specified
					pst = conn.prepareStatement(query);					
					rs = pst.executeQuery();
					
					//Step 4 - Convert ResultSet to TableModel
					resultSetToTableModel(rs);

				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
				
				try
				{
					String query = "select * from staff where Name='"+name+"'"; // select all columns from the student (SQLite DB) where Course is as specified
					pst = conn.prepareStatement(query);					
					rs = pst.executeQuery();
					
					//Step 4 - Convert ResultSet to TableModel
					resultSetToTableModelStaff(rs);

				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
			}
		});
		btnQue.setBounds(276, 43, 74, 23);
		panel.add(btnQue);
		
		JButton button = new JButton(">>");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String add = addTF.getText();
				try
				{
					String query = "select * from consumer where Address='"+add+"'"; // select all columns from the student (SQLite DB) where Course is as specified
					pst = conn.prepareStatement(query);					
					rs = pst.executeQuery();
					
					//Step 4 - Convert ResultSet to TableModel
					resultSetToTableModel(rs);

				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
				try
				{
					String query = "select * from staff where Address='"+add+"'"; // select all columns from the student (SQLite DB) where Course is as specified
					pst = conn.prepareStatement(query);					
					rs = pst.executeQuery();
					
					//Step 4 - Convert ResultSet to TableModel
					resultSetToTableModelStaff(rs);

				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
				
				
			}
		});
		button.setBounds(276, 68, 74, 23);
		panel.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				if(male.isSelected()) {
					String query = "select * from consumer where Gender='Male'"; // select all columns from the student (SQLite DB) where Course is as specified
					try {
						pst = conn.prepareStatement(query);
						rs = pst.executeQuery();
						resultSetToTableModel(rs);


					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					

					String query1 = "select * from staff where Gender='Male'"; // select all columns from the student (SQLite DB) where Course is as specified
					try {
						pst = conn.prepareStatement(query1);
						rs = pst.executeQuery();
						resultSetToTableModelStaff(rs);


					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					

					
					//Step 4 - Convert ResultSet to TableModel
				}
				else if(female.isSelected()) {
					String query = "select * from consumer where Gender='Female'"; // select all columns from the student (SQLite DB) where Course is as specified
					try {
						pst = conn.prepareStatement(query);
						rs = pst.executeQuery();
						resultSetToTableModel(rs);


					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					

					String query1 = "select * from staff where Gender='Female'"; // select all columns from the student (SQLite DB) where Course is as specified
					try {
						pst = conn.prepareStatement(query1);
						rs = pst.executeQuery();
						resultSetToTableModelStaff(rs);


					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					

					
					
				}

				
			}
		});
		button_1.setBounds(276, 93, 74, 23);
		panel.add(button_1);
		
				JButton btnExit = new JButton("LOGOUT");
				btnExit.setBounds(10, 139, 89, 23);
				panel.add(btnExit);
				btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnExit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?","",JOptionPane.OK_CANCEL_OPTION);
						if(res == JOptionPane.OK_OPTION) {
							try {
								LogInStaffAdmin newFrame = new LogInStaffAdmin();
								LogInStaffAdmin.frame.setVisible(true);
								conn.close();
								frame1.dispose();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(380, 38, 366, 183);
		frame1.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnLoad = new JButton("Load Consumer Records");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String query = "select * from consumer";
				try {
					pst = conn.prepareStatement(query);
					rs = pst.executeQuery();
					resultSetToTableModel(rs);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

							
			}
		});
		btnLoad.setBounds(10, 99, 195, 33);
		panel_1.add(btnLoad);
		
		JButton btnHighestWalletBalance = new JButton("Add Staff Records");
		btnHighestWalletBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHighestWalletBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnHighestWalletBalance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateStaffRecords NEWFRAME = new CreateStaffRecords();
				CreateStaffRecords.frame2.setVisible(true);
				frame1.dispose();
				
			}
		});
		btnHighestWalletBalance.setBounds(184, 11, 172, 33);
		panel_1.add(btnHighestWalletBalance);
		
		JButton btnLowestWalletBalance = new JButton("Load Arrears Records");
		btnLowestWalletBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLowestWalletBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLowestWalletBalance.addMouseListener(new MouseAdapter() {
	

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String sql = "Select * from consumer where Arrears >0";
				try {
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();
					resultSetToTableModel(rs);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLowestWalletBalance.setBounds(184, 55, 172, 33);
		panel_1.add(btnLowestWalletBalance);
		
		JButton btnDeleteRecords = new JButton("Delete Records");
		btnDeleteRecords.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeleteRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String choice[] = new String [] {
						"Consumers","Staff"
				};
				JComboBox <String> ee = new  JComboBox<String>(choice);
				Object [] mes = {
						"Select the following wants to delete: \n",ee
				};
			int res = JOptionPane.showConfirmDialog(null,mes,"Deleting Records",JOptionPane.OK_CANCEL_OPTION);
			String chc = ee.getItemAt(ee.getSelectedIndex());
			if (res==JOptionPane.OK_OPTION) {
				if(chc.equalsIgnoreCase("Consumers")) {
						deleteConsumerRecords();
				}
				else if(chc.equalsIgnoreCase("Staff")) {
					deleteStaffRecords();
				}
				
				
			}
			
			}
		});
		btnDeleteRecords.setBounds(10, 11, 158, 33);
		panel_1.add(btnDeleteRecords);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nameTF.setText("");
				addTF.setText("");
				bg.clearSelection();

			}
		});
		btnClear.setBounds(10, 55, 158, 33);
		panel_1.add(btnClear);
		
		JButton btnLoadStaffRecords = new JButton("Load Staff Records");
		btnLoadStaffRecords.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLoadStaffRecords.setBounds(10, 143, 195, 33);
		panel_1.add(btnLoadStaffRecords);
		btnLoadStaffRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from staff";
				try {
					pst = conn.prepareStatement(query);
					rs = pst.executeQuery();
					resultSetToTableModelStaff(rs);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 258, 736, 98);
		frame1.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AccountNumber", "Name", "Address", "Gender", "Contact", "Password", "Bill","Arrears","Previous","Present","Date","Paid"
			}

		));
		model = (DefaultTableModel) table.getModel();
		
		totalConsumer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalConsumer.setBounds(222, 224, 98, 23);
		frame1.getContentPane().add(totalConsumer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 401, 736, 90);
		frame1.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Name", "Address", "Gender", "Contact", "Username", "Password"
			}
		));
		model1 = (DefaultTableModel) table_1.getModel();
		JLabel lblConsumerTabble = new JLabel("Consumers Table");
		lblConsumerTabble.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsumerTabble.setBounds(20, 224, 192, 23);
		frame1.getContentPane().add(lblConsumerTabble);
		
		JLabel lblStaffTable = new JLabel("Staff Table");
		lblStaffTable.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStaffTable.setBounds(20, 367, 192, 23);
		frame1.getContentPane().add(lblStaffTable);
		
		totalStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalStaff.setBounds(222, 367, 98, 23);
		frame1.getContentPane().add(totalStaff);
		
		JLabel lblTodayIs = new JLabel("Today is : "+date.toString());
		lblTodayIs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTodayIs.setBounds(10, 11, 342, 23);
		frame1.getContentPane().add(lblTodayIs);
	}
}
