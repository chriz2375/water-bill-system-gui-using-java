package staff;

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
import java.text.DecimalFormat;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import admin.LogInStaffAdmin;
import consumer.LogIn;

public class StaffFrame {

	public static JFrame frame3;
	private JTextField anTF = new JTextField();
	private JTextField nameTF = new JTextField();
	private JTextField addressTF = new JTextField();
	private JTextField genderTF = new JTextField();
	private JTextField cnTF = new JTextField();
	DecimalFormat df = new DecimalFormat("0.00");
	ButtonGroup bg = new ButtonGroup();
	JRadioButton male1= new JRadioButton("Male");
	JRadioButton female1 = new JRadioButton("Female");
	static JTextField xId = new JTextField();
	JTextField newEdit = new JTextField();
	static JPasswordField newPass = new JPasswordField();
	
	Date date = new Date();
	String fmt = "yy-MM-dd HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	String dt = sdf.toString();

	static Connection conn = null;
	static Statement stmt;
	PreparedStatement pst;
	static ResultSet rs;
	static String userA;
	String id,name,add,gender,no,userName,bill,arr;
	String cosId,cosName,cosPaid;
	double cosArr,cosBil;
	static int idRec[] = new int[20];
	static String Pass[] = new String[20];
	static String username[] = new String[20];
	
	int i,consumerId;
	static int line;
	private JTextField pvTF;
	private JTextField psTF;
	private JTextField cmTF;
	private JTextField idNo;

	/**
	 * Launch the application.
	 */

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffFrame window = new StaffFrame();
					StaffFrame.frame3.setVisible(true);

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
		newPass.setText("");
	
	}
	  
	
	private void editName() throws SQLException {
		clear();
		int o2;

		
					
					Object [] mes2 = {
							"Input you New Name:",newEdit
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					String t  = newEdit.getText();
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						try {
							stmt = conn.createStatement();
							stmt.executeUpdate("update staff set Name= '"+t+"'where EmployeeID='"+id+"'");
							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
													
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			}
			
	
	private void editAdd() throws SQLException {
		clear();
		int o,o2;
	
					
					Object [] mes2 = {
							"Input your new Address :",newEdit
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					String t  = newEdit.getText();
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						try {
							String query = ("update staff set Address= '"+t+"'where EmployeeID='"+id+"'");
							pst = conn.prepareStatement(query);	
							pst.execute();
							pst.close();

							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
													
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			}
			
	
	private void editCont() throws SQLException {
		clear();
		int o,o2;

					Object [] mes2 = {
							"Input your new Contact no. :",newEdit
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					String t  = newEdit.getText();
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						try {
							String query = ("update staff set Contact= '"+t+"'where EmployeeID='"+id+"'");
							pst = conn.prepareStatement(query);	
							pst.execute();
							pst.close();
							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
													
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			
			
	
	}
	private void editGender() throws SQLException {
		clear();
		String t;
		int o2;

				do {	
					Object [] mes2 = {
							"Select your new Gender :",male1,female1
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					 t  = (male1.isSelected())? male1.getLabel() : female1.getLabel();
				
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						if (t.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please Select what Gender! ","",JOptionPane.ERROR_MESSAGE);	

						}
						else {
							try {
								String query = ("update staff set Gender= '"+t+"'where EmployeeID='"+id+"'");
								pst = conn.prepareStatement(query);	
								pst.execute();
								pst.close();

								JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
									}
						}
												
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			}while (t.isEmpty());
			}
	
	private void editUser() throws SQLException {
		clear();
		int o2;

					Object [] mes2 = {
							"Input your new Username:",newEdit
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					String t  = newEdit.getText();
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						try {
							String query = ("update staff set Username= '"+t+"'where EmployeeID='"+id+"'");
							pst = conn.prepareStatement(query);	
							pst.execute();
							pst.close();

							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
													
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			}
				
	private void editPass() throws SQLException {
		clear();
		int o2;
	
					Object [] mes2 = {
							"Input your new Password :",newPass
					};
					o2 = JOptionPane.showConfirmDialog(null,mes2,"Updating Account Record",JOptionPane.OK_CANCEL_OPTION);
					String t  = newPass.getText();
					if(o2==JOptionPane.OK_OPTION) {
						// insert the data
						try {
							String query = ("update staff set Password= '"+t+"'where EmployeeID='"+id+"'");
							pst = conn.prepareStatement(query);	
							pst.execute();
							pst.close();

							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
													
					}
					else if(o2==JOptionPane.CANCEL_OPTION||o2==JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "Edit Cancel!","",JOptionPane.INFORMATION_MESSAGE);

					}
			}

	public void setInfo() {
		anTF.setText(id);
		nameTF.setText(name);
		addressTF.setText(add);
		genderTF.setText(gender);
		cnTF.setText(no);

	}
	
	public void extRec() throws SQLException {

		String sql = "select * from staff where Username = '" +userA+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			id = rs.getString("EmployeeID");
			name = rs.getString("Name");
			add = rs.getString("Address");
			no = rs.getString("Contact");
			gender = rs.getString("Gender");
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection dbConnect() throws SQLException { // Step 1 - Get a connection to SQLite database	
		try 
		{   // Step 1.1 - load Java's JDBC SQLite Driver
			Class.forName("org.sqlite.JDBC");			
			// Step 1.2 - get a DB Connection
			conn = DriverManager.getConnection("JDBC:sqlite:C:\\Users\\user\\Desktop\\Christian & Judel\\CPE121-Object Oriented Programming\\Database\\waterDistrict.sqlite"); // created using SQLite Manager (SQLiteManager_4.6.6_1430708940)
			// prompt user if connection attempt is successful
			return conn;
		}
		catch (Exception err)
		{
			JOptionPane.showMessageDialog(null, "Connection unsuccessful. Exception -> "+err);
			return null;
		}
		
	}
	
	public StaffFrame() throws SQLException {
		initialize();
		conn = dbConnect();
	}
	

	public StaffFrame(String user) throws SQLException {
		// TODO Auto-generated constructor stub
		initialize();
		conn = dbConnect();
		userA = user;
		extRec() ;
		setInfo();

	}

	private int searchConsumerId(int ID) throws SQLException {
		
		String sql = "Select AccountNumber from consumer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		for ( i=0;rs.next();i++) {
			idRec[i] = rs.getInt(1);
			if(ID==idRec[i]) {
				return 1;
			}
			

		}
		return -1;

		
	}
	
	private void loadConsumerRecords() throws SQLException {
		String sql = "Select * from consumer where AccountNumber='"+consumerId+"'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		cosName= rs.getString("Name");
		cosBil= rs.getDouble(7);
		cosArr= rs.getDouble(8);
		cosPaid= rs.getString("Paid");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame3 = new JFrame();
		frame3.getContentPane().setBackground(new Color(30, 144, 255));
		frame3.setBounds(100, 100, 543, 348);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Staff Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 320, 152);
		frame3.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Number :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 24, 120, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 49, 120, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 74, 120, 14);
		panel.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setBounds(10, 99, 120, 14);
		panel.add(lblGender);
		
		JLabel lblContactNumber = new JLabel("Contact Number :");
		lblContactNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContactNumber.setBounds(10, 124, 120, 14);
		panel.add(lblContactNumber);
		anTF.setEditable(false);
		
		anTF.setBounds(140, 22, 170, 20);
		panel.add(anTF);
		anTF.setColumns(10);
		nameTF.setEditable(false);
		
		nameTF.setColumns(10);
		nameTF.setBounds(140, 47, 170, 20);
		panel.add(nameTF);
		addressTF.setEditable(false);
		
		addressTF.setColumns(10);
		addressTF.setBounds(140, 72, 170, 20);
		panel.add(addressTF);
		genderTF.setEditable(false);
		
		genderTF.setColumns(10);
		genderTF.setBounds(140, 97, 170, 20);
		panel.add(genderTF);
		cnTF.setEditable(false);
		
		cnTF.setColumns(10);
		cnTF.setBounds(140, 122, 170, 20);
		panel.add(cnTF);
		
		JButton logoutJB = new JButton("LOGOUT");
		logoutJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to LOGOUT?","",JOptionPane.OK_CANCEL_OPTION);
				if(res==JOptionPane.OK_OPTION) {
					try {
						LogInStaffAdmin newFrame = new LogInStaffAdmin();
						LogInStaffAdmin.frame.setVisible(true);
						conn.close();
						frame3.dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		logoutJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		logoutJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		logoutJB.setBounds(340, 11, 90, 23);
		frame3.getContentPane().add(logoutJB);
		
		JButton updateJB = new JButton("Update Account");
		updateJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		updateJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					try {
						extRec();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				setInfo();
				try {
				String choice[] = new String[] {
						"Name","Address","Contact","Gender","Username","Password"
				};
				JComboBox<String> ee = new JComboBox(choice);
				
				Object [] mes = {
						"Select the following Column wants to Edit: \n",ee
				};
			int res = JOptionPane.showConfirmDialog(null,mes,"Update Account Record",JOptionPane.OK_CANCEL_OPTION);
			String chc = ee.getItemAt(ee.getSelectedIndex());
			
			if (res==JOptionPane.OK_OPTION) {
				if(chc.equalsIgnoreCase("Name")) {
					
						editName();
					
				}
				else if(chc.equalsIgnoreCase("Address")) {
					try {
						editAdd();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(chc.equalsIgnoreCase("Contact")) {
					try {
						editCont();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(chc.equalsIgnoreCase("Gender")) {
					try {
						editGender();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (chc.equalsIgnoreCase("Username")) {
					try {
						editUser();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (chc.equalsIgnoreCase("Password")) {
					try {
						editPass();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				}
				catch(NumberFormatException ee)
				{
				JOptionPane.showMessageDialog(null, "Please Input the ID","",JOptionPane.ERROR_MESSAGE);	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		updateJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateJB.setBounds(340, 45, 150, 23);
		frame3.getContentPane().add(updateJB);
		
		JButton vrJB = new JButton("View Record");
		vrJB.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent arg0) {
				try {
					extRec();
					setInfo();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		vrJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		vrJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		vrJB.setBounds(340, 79, 150, 23);
		frame3.getContentPane().add(vrJB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Consumer Inputs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(10, 174, 396, 125);
		frame3.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPrevious = new JLabel("Previous :");
		lblPrevious.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrevious.setBounds(10, 27, 75, 14);
		panel_1.add(lblPrevious);
		
		JLabel lblPresent = new JLabel("Present :");
		lblPresent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPresent.setBounds(10, 52, 75, 14);
		panel_1.add(lblPresent);
		
		pvTF = new JTextField();
		pvTF.setColumns(10);
		pvTF.setBounds(95, 25, 106, 20);
		panel_1.add(pvTF);
		
		psTF = new JTextField();
		psTF.setColumns(10);
		psTF.setBounds(95, 50, 106, 20);
		panel_1.add(psTF);
		
		JLabel lblCubicmeter = new JLabel("Cubic/Meter");
		lblCubicmeter.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCubicmeter.setBounds(270, 27, 87, 14);
		panel_1.add(lblCubicmeter);
		
		cmTF = new JTextField();
		cmTF.setEditable(false);
		cmTF.setColumns(10);
		cmTF.setBounds(257, 50, 106, 20);
		panel_1.add(cmTF);
		cmTF.setText("24.33");
		
		JLabel lblAccountNumber = new JLabel("Account Number :");
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setBounds(10, 77, 120, 14);
		panel_1.add(lblAccountNumber);
		
		idNo = new JTextField();
		idNo.setColumns(10);
		idNo.setBounds(140, 75, 137, 20);
		panel_1.add(idNo);
		
		JButton payJB = new JButton("Save");
		payJB.setBounds(416, 230, 100, 23);
		frame3.getContentPane().add(payJB);
		payJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				int id,present,prev;
				double totalBill,arrears=0.0,cubicPerMeter;
				id = Integer.parseInt(idNo.getText());
			
					int y = searchConsumerId(id);
					if (y>0) {
						consumerId = id;
						cubicPerMeter = Double.parseDouble(cmTF.getText());
						present = Integer.parseInt(psTF.getText());
						prev = Integer.parseInt(pvTF.getText());
						
					
						
						totalBill = (present - prev)*cubicPerMeter;
						if (totalBill==0) {
							totalBill=218;
						}
						else if(prev>present) {
							JOptionPane.showMessageDialog(null, "Incorrect Input of Previous and Present Reading","",JOptionPane.ERROR_MESSAGE);
							
						}
						else {
							loadConsumerRecords();
							
							
							Object[] confirmation = {
									
									"Confirmation of your details: \nPress OK to Proceed\nPress CANCEL to Edit\n\nAccountNumber: \t"+consumerId+"\nName: \t"+cosName+
									"\nPrevious: \t"+prev+"\nPresent: \t"+present+"\nBill: \t"+	df.format(totalBill)
								};
								
								int conF = JOptionPane.showConfirmDialog(null, confirmation,"Your Information",JOptionPane.OK_CANCEL_OPTION);
							if(conF==JOptionPane.OK_OPTION) {
								
								if (cosBil>0||(cosBil>0&&cosArr>0)) {
								arrears = cosBil+cosArr;
								}
								
								String query = ("update consumer set Bill= '"+totalBill
														   +"' ,Arrears='"+arrears
														   +"' ,Previous='"+prev
														   +"' ,Present='"+present
														   +"' ,Paid='FALSE',Date='"+sdf.format(date)
														   +"'where AccountNumber='"+consumerId+"'");
								pst = conn.prepareStatement(query);
								pst.execute();
								JOptionPane.showMessageDialog(null, "Succesfully Uploaded Records!","",JOptionPane.INFORMATION_MESSAGE);
							}
						}
				
						
						
					}
					else if (y<0) {
						JOptionPane.showMessageDialog(null, "Incorrect Account Number of Consumer","",JOptionPane.ERROR_MESSAGE);
					}
					else  {
						JOptionPane.showMessageDialog(null, "Please input Account Number of Consumer","",JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (NumberFormatException eq) {
					JOptionPane.showMessageDialog(null, "Wrong Input or Lacking Data Entry","",JOptionPane.ERROR_MESSAGE);

				}
				
				
		
				
				

			}
		});
		payJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		payJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
