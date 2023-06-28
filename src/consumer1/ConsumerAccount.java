package consumer1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import consumer.LogIn;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ConsumerAccount {

	public static JFrame frame3;
	private JTextField anTF = new JTextField();
	private JTextField nameTF = new JTextField();
	private JTextField addressTF = new JTextField();
	private JTextField genderTF = new JTextField();
	private JTextField cnTF = new JTextField();
	private JTextField billTF = new JTextField();
	private JTextField arrearsTF = new JTextField();
	ButtonGroup bg = new ButtonGroup();
	JRadioButton male1= new JRadioButton("Male");
	JRadioButton female1 = new JRadioButton("Female");
	JTextField xId = new JTextField();
	JTextField newEdit = new JTextField();
	JPasswordField newPass = new JPasswordField();
	
	Connection conn = null;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	String userA,id,name,add,gender,no,userName,bill,arr;
	static int idRec[] = new int[20];
	int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsumerAccount window = new ConsumerAccount();
					window.frame3.setVisible(true);
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
							stmt.executeUpdate("update consumer set Name= '"+t+"'where AccountNumber='"+id+"'");
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
							String query = ("update consumer set Address= '"+t+"'where AccountNumber='"+id+"'");
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
							String query = ("update consumer set Contact= '"+t+"'where AccountNumber='"+id+"'");
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
								String query = ("update consumer set Gender= '"+t+"'where AccountNumber='"+id+"'");
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
							String query = ("update consumer set Username= '"+t+"'where AccountNumber='"+id+"'");
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
							String query = ("update accounts set Password= '"+t+"'where AccountNumber='"+id+"'");
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
		billTF.setText(bill);
		arrearsTF.setText(arr);
		
	}
	
	public void extRec() throws SQLException {
		conn  = dbConnect();

		String sql = "select * from consumer where Username = '" +userA+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			id = rs.getString("AccountNumber");
			name = rs.getString("Name");
			add = rs.getString("Address");
			no = rs.getString("Contact");
			gender = rs.getString("Gender");
			bill = rs.getString("Bill");
			arr = rs.getString("Arrears");
			
		
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
	
	public ConsumerAccount() {
		initialize();
	}
	
	public ConsumerAccount(String user) throws SQLException {

		initialize();
		userA =user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame3 = new JFrame();
		frame3.getContentPane().setBackground(new Color(30, 144, 255));
		frame3.setBounds(100, 100, 543, 300);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new TitledBorder(null, "Consumer Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 320, 210);
		frame3.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Number :");
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
		
		JLabel lblBill = new JLabel("Bill :");
		lblBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBill.setBounds(10, 149, 120, 14);
		panel.add(lblBill);
		
		JLabel lblArrears = new JLabel("Arrears :");
		lblArrears.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblArrears.setBounds(10, 174, 120, 14);
		panel.add(lblArrears);
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
		billTF.setEditable(false);
		
		billTF.setColumns(10);
		billTF.setBounds(140, 147, 170, 20);
		panel.add(billTF);
		arrearsTF.setEditable(false);
		
		arrearsTF.setColumns(10);
		arrearsTF.setBounds(140, 172, 170, 20);
		panel.add(arrearsTF);
		
		JButton payJB = new JButton("PAY");
		payJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		payJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		payJB.setBounds(340, 113, 150, 23);
		frame3.getContentPane().add(payJB);
		
		JButton logoutJB = new JButton("LOGOUT");
		logoutJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to LOGOUT?","",JOptionPane.OK_CANCEL_OPTION);
				if(res==JOptionPane.OK_OPTION) {
					try {
						LogIn newFrame = new LogIn();
						LogIn.frame.setVisible(true);
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
		logoutJB.setBounds(10, 228, 89, 23);
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
					try {
						editName();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	}
}
