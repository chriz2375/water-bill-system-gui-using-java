package consumer;

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
import javax.swing.border.TitledBorder;

public class ConsumerAccount {

	public static JFrame frame3;
	private JTextField anTF = new JTextField();
	private JTextField nameTF = new JTextField();
	private JTextField addressTF = new JTextField();
	private JTextField genderTF = new JTextField();
	private JTextField cnTF = new JTextField();
	private JTextField billTF = new JTextField();
	private JTextField arrearsTF = new JTextField();
	private JTextField pvTF = new JTextField();
	private JTextField psTF= new JTextField();
	private JTextField paidTF= new JTextField();
	private JTextField dateTF= new JTextField();
	private JTextField cpmTF= new JTextField();
	private JTextField money= new JTextField();
	
	
	Date date1 = new Date();
	String fmt = "yy-MM-dd HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	

	DecimalFormat df = new DecimalFormat("0.00");
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
	String name,add,gender,no,date,paid;
	static int idRec[] = new int[20];
	int userA,i,prev,pres;
	double arr,bil;
	

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
	private void payBills() {
		double mon = 0,change=0;
	
		do {		
		try {
	
			Object[] mes = {
				"Your Bill is:\t"+df.format(bil)+"\nInput Cash Tendered\n",money	
			};
			int x = JOptionPane.showConfirmDialog(null, mes,"Paying Bill",JOptionPane.OK_CANCEL_OPTION);
			if(x==JOptionPane.OK_OPTION) {
				mon = Double.parseDouble(money.getText());
				 if(mon>bil) {
					 change= mon-bil;
		
						bil = 0*(bil-mon);

						String query = ("update consumer set Bill='"+bil+
																"',Date='"+sdf.format(date1)+
																"',Paid = 'TRUE' where AccountNumber='"+userA+"'");
						try {
							stmt = conn.createStatement();
							stmt.execute(query);
							JOptionPane.showMessageDialog(null, "Succesfully Pay Bill\nYour Change: \t"+df.format(change),"",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
				 }
				 
				 else if(bil==mon) {
					 change= mon-bil;
								
						String query = ("update consumer set Bill='0.00',Date='"+sdf.format(date1) +"' where AccountNumber='"+userA+"'");
						try {
							stmt = conn.createStatement();
							stmt.execute(query);
							JOptionPane.showMessageDialog(null, "Succesfully Pay Bill\nYour Change: \t"+df.format(change),"",JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
				 }
				 


					else if (mon<bil) {
						JOptionPane.showMessageDialog(null, "Insufficient Amount to pay for Bill","Paying Arrears",JOptionPane.ERROR_MESSAGE);		
					}
			
				}
				else if (x==JOptionPane.CANCEL_OPTION) {
					int res1 = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel your payment?","Paying Bill",JOptionPane.OK_CANCEL_OPTION );
						if(res1==JOptionPane.OK_OPTION) {
							break;
						}
					else {
						break;
					}
				}
				 
				 
				 
			
		}
		catch(NumberFormatException ee) {
			JOptionPane.showConfirmDialog(null, "Wrong Inputs!!","Paying Bill",JOptionPane.ERROR_MESSAGE);

		}
		
		}while(mon<bil);
	}
	private void payArrears() {
		double mon = 0,change;
		int y = JOptionPane.showConfirmDialog(null, 
				"Hello "+name+" !! Since you hava an Arrears, you must pay it first.\n Press OK to Procced payment  "	,"Notice",
				JOptionPane.OK_CANCEL_OPTION);
		do {
			
		try {
		if(y==JOptionPane.OK_OPTION) {
			Object[] mes = {
				"Your Arrears is: \t"+df.format(arr)+"\nInput Cash Tendered\n",money	
			};
			int x = JOptionPane.showConfirmDialog(null, mes,"Paying Arrears",JOptionPane.OK_CANCEL_OPTION);
			if(x==JOptionPane.OK_OPTION) {
				mon = Double.parseDouble(money.getText());
				 if(mon<arr) {
						JOptionPane.showMessageDialog(null, "Insufficient Amount to pay for Arrears","Paying Arrears",JOptionPane.ERROR_MESSAGE);
				 }

				 
					else if(mon>arr){
						change = (mon-arr);
						arr = 0*(mon-arr);
						String query = ("update consumer set Arrears='"+arr+
								"',Date='"+sdf.format(date1)+"' where AccountNumber='"+userA+"'");						
						try {
							stmt = conn.createStatement();
							stmt.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Succesfully Pay Arrears\nYour Change: "+df.format(change),"",JOptionPane.INFORMATION_MESSAGE);
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					
					}
			
				}
				else if (x==JOptionPane.CANCEL_OPTION) {
					int res1 = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel your payment?","Paying Arrears",JOptionPane.OK_CANCEL_OPTION );
						if(res1==JOptionPane.OK_OPTION) {
							break;
						}
					else {
						break;
					}
				}
				 
				 
				 
			}
		}
		catch(NumberFormatException ee) {
			JOptionPane.showConfirmDialog(null, "Wrong Inputs!!","Paying Arrears",JOptionPane.ERROR_MESSAGE);

		}
		
		}while(mon<arr);
	}
	void clear() {
		xId.setText("");
		newEdit.setText("");
		bg.clearSelection();
		money.setText("");
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
							stmt.executeUpdate("update consumer set Name= '"+t+"'where AccountNumber='"+userA+"'");
							JOptionPane.showMessageDialog(null, "Succesfully Updated Records!","",JOptionPane.INFORMATION_MESSAGE);
							stmt.close();
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
							String query = ("update consumer set Address= '"+t+"'where AccountNumber='"+userA+"'");
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
							String query = ("update consumer set Contact= '"+t+"'where AccountNumber='"+userA+"'");
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
								String query = ("update consumer set Gender= '"+t+"'where AccountNumber='"+userA+"'");
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
							String query = ("update accounts set Password= '"+t+"'where AccountNumber='"+userA+"'");
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
		anTF.setText(""+userA);
		nameTF.setText(name);
		addressTF.setText(add);
		genderTF.setText(gender);
		cnTF.setText(no);
		billTF.setText(""+df.format(bil));
		arrearsTF.setText(""+df.format(arr));
		cpmTF.setText(""+24.33);
		pvTF.setText(""+prev);
		psTF.setText(""+pres);
		dateTF.setText(date);
		if(bil==0) {
			paidTF.setText("TRUE");
		}
		else
		paidTF.setText(paid);
	}
	
	public void extRec() throws SQLException {

		String sql = "select * from consumer where AccountNumber = '" +userA+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			name = rs.getString("Name");
			add = rs.getString("Address");
			no = rs.getString("Contact");
			gender = rs.getString("Gender");
			bil = rs.getDouble(	7);
			arr = rs.getDouble(8);
			prev = rs.getInt(9);
			pres = rs.getInt(10);
			date = rs.getString("Date");
			paid = rs.getString("Paid");
			pst.close();
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
	
	public ConsumerAccount() throws SQLException {
		conn  = dbConnect();
		initialize();
	}
	
	public ConsumerAccount(int accId) throws SQLException {
		conn  = dbConnect();
		initialize();
		userA =accId;
		extRec() ;
		setInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame3 = new JFrame();
		frame3.getContentPane().setBackground(new Color(30, 144, 255));
		frame3.setBounds(100, 100, 592, 295);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new TitledBorder(null, "Consumer Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 320, 174);
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
		bg.add(female1);
		bg.add(male1);
		
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
		
		JButton payJB = new JButton("PAY");
		payJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				try {	
				if (arr>0) {
					payArrears();
					
					extRec();
					setInfo();
				}
				else if (bil>0){
					clear();
					payBills();
					
						extRec();
						setInfo();

				}
				else {
					JOptionPane.showMessageDialog(null, "As of now, you are currently paid! \n Arrears: 0.00 \n Bills: 0.00");
				}
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
				
		});
		payJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		payJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		payJB.setBounds(180, 196, 150, 23);
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
		logoutJB.setBounds(122, 230, 89, 23);
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
						"Name","Address","Contact","Gender","Password"
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
						extRec();
						setInfo();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(chc.equalsIgnoreCase("Address")) {
					try {
						editAdd();
						extRec();
						setInfo();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(chc.equalsIgnoreCase("Contact")) {
					try {
						editCont();	
						extRec();
						setInfo();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(chc.equalsIgnoreCase("Gender")) {
					try {
						editGender();	
						extRec();
						setInfo();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
				else if (chc.equalsIgnoreCase("Password")) {
					try {
						editPass();	
						extRec();
						setInfo();
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
		updateJB.setBounds(10, 196, 150, 23);
		frame3.getContentPane().add(updateJB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 255));
		panel_1.setBounds(340, 11, 226, 218);
		frame3.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBill = new JLabel("Bill :");
		lblBill.setBounds(10, 11, 61, 14);
		panel_1.add(lblBill);
		lblBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblArrears = new JLabel("Arrears :");
		lblArrears.setBounds(10, 36, 52, 14);
		panel_1.add(lblArrears);
		lblArrears.setFont(new Font("Tahoma", Font.BOLD, 12));
		billTF.setBounds(81, 9, 135, 20);
		panel_1.add(billTF);
		billTF.setEditable(false);
		
		billTF.setColumns(10);
		arrearsTF.setBounds(81, 34, 135, 20);
		panel_1.add(arrearsTF);
		arrearsTF.setEditable(false);
		
		arrearsTF.setColumns(10);
		
		JLabel lblReading = new JLabel("Reading");
		lblReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReading.setBounds(10, 84, 86, 14);
		panel_1.add(lblReading);
		
		JLabel lblPrevious = new JLabel("Previous");
		lblPrevious.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrevious.setBounds(35, 109, 61, 14);
		panel_1.add(lblPrevious);
		
		pvTF.setEditable(false);
		pvTF.setColumns(10);
		pvTF.setBounds(109, 107, 107, 20);
		panel_1.add(pvTF);
		
		JLabel lblPresent = new JLabel("Present");
		lblPresent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPresent.setBounds(35, 134, 61, 14);
		panel_1.add(lblPresent);
		
		psTF.setEditable(false);
		psTF.setColumns(10);
		psTF.setBounds(109, 132, 107, 20);
		panel_1.add(psTF);
		
		JLabel lblPaid = new JLabel("Paid?");
		lblPaid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaid.setBounds(10, 168, 61, 14);
		panel_1.add(lblPaid);
		
		paidTF.setEditable(false);
		paidTF.setColumns(10);
		paidTF.setBounds(109, 166, 107, 20);
		panel_1.add(paidTF);
		
		JLabel lblDateUploaded = new JLabel("Date Configure");
		lblDateUploaded.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateUploaded.setBounds(10, 193, 107, 14);
		panel_1.add(lblDateUploaded);
		
		dateTF.setEditable(false);
		dateTF.setColumns(10);
		dateTF.setBounds(118, 191, 98, 20);
		panel_1.add(dateTF);
		
		JLabel lblCubicPerMeter = new JLabel("Cubic Per Meter");
		lblCubicPerMeter.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCubicPerMeter.setBounds(10, 59, 120, 14);
		panel_1.add(lblCubicPerMeter);
		
		cpmTF.setEditable(false);
		cpmTF.setColumns(10);
		cpmTF.setBounds(145, 57, 71, 20);
		panel_1.add(cpmTF);
	}
}
