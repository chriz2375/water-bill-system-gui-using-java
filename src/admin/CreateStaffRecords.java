package admin;

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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import consumer.LogIn;

import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class CreateStaffRecords {

	public static JFrame frame2;
	private JTextField nameTF;
	private JTextField addTF;
	private JTextField contactTF;
	ButtonGroup bg = new ButtonGroup();
	

	Connection conn =  null;
	Statement stmt;
	PreparedStatement pst;
	static int idRec[] = new int[20];
	static ResultSet rs;
	private JTextField userTF1;
	private JPasswordField passTF;
	int i;
	private JTextField userTF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateStaffRecords window = new CreateStaffRecords();
					CreateStaffRecords.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
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
	
	public CreateStaffRecords() {
		initialize();
		conn = dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	
	private void initialize() {
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(new Color(30, 144, 255));
		frame2.setBounds(100, 100, 454, 351);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 39, 318, 263);
		panel.setBackground(new Color(175, 238, 238));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Staff Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame2.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 28, 84, 21);
		panel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(10, 60, 84, 21);
		panel.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(10, 92, 84, 21);
		panel.add(lblGender);
		
		JLabel lblContactNo = new JLabel("Contact No. :");
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContactNo.setBounds(10, 124, 84, 21);
		panel.add(lblContactNo);
		
		JRadioButton male = new JRadioButton("Male");
		male.setFont(new Font("Tahoma", Font.BOLD, 12));
		male.setBounds(104, 93, 80, 23);
		panel.add(male);
		
		JRadioButton female = new JRadioButton("Female");
		female.setFont(new Font("Tahoma", Font.BOLD, 12));
		female.setBounds(207, 93, 80, 23);
		panel.add(female);
		bg.add(male);
		bg.add(female);
		
		JButton btnCreate = new JButton("SAVE");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
				
				String name,no,add,gender,user = null,pass = null;
				int id = 0;
				String sql1 = "Select EmployeeID from staff";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				for(i=0;rs.next();i++) {
					idRec[i] = rs.getInt(1);
					if (idRec[0]==0) {
						break;
						}
					else
					id = idRec[i];
					
				}
				
				name = nameTF.getText();
				add = addTF.getText();
				user = "Staff-"+userTF.getText();
				pass = passTF.getText();
				gender = (male.isSelected())? male.getLabel(): female.getLabel();
				no = contactTF.getText();
				if(!name.isEmpty()&&!add.isEmpty()&&!gender.isEmpty()&&!no.isEmpty()&&!pass.isEmpty()&&!user.isEmpty()) {
					Object[] confirmation = {
							
							"Confirmation of your details: \nPress OK to Proceed\nPress CANCEL to Edit\n\nName: \t"+name+"\nAddress: \t"+add+
							"\nGender: \t"+gender+"\nContact no.: \t"+no+"\nUsername: \t"+user+"\nPassword: \t"+pass
						};
						
						int conF = JOptionPane.showConfirmDialog(null, confirmation,"Your Information",JOptionPane.OK_CANCEL_OPTION);
						
						if (conF == JOptionPane.OK_OPTION) {
							String sql = "insert into staff (EmployeeID,Name,Address,Gender,Contact,Username,Password) values (?,?,?,?,?,?,?)";
							pst = conn.prepareStatement(sql);
							if(id==0) {
								pst.setInt(1,  201901);

							}
							else if (id>0) {
								pst.setInt(1,  (id+1));

							}		
							pst.setString(2, name);
							pst.setString(3, add);
							pst.setString(4, gender);
							pst.setString(5, no);
							pst.setString(6, user);
							pst.setString(7, pass);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Succesfully Create Account","",JOptionPane.INFORMATION_MESSAGE);
							;
							AdminFrame newFrame = new AdminFrame();
							AdminFrame.frame1.setVisible(true);
							conn.close();
							frame2.dispose();
						}
					
					
					
			
				}
				else {
					JOptionPane.showMessageDialog(null, "Lacking Data Entry","",JOptionPane.ERROR_MESSAGE);
				}
				}
				
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Wrong Inputs! "+e,"",JOptionPane.ERROR_MESSAGE);
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreate.setBounds(28, 217, 89, 23);
		panel.add(btnCreate);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdminFrame newFrame = new AdminFrame();
				AdminFrame.frame1.setVisible(true);
				frame2.dispose();
			}
		});
		btnCancel.setBounds(179, 217, 89, 23);
		panel.add(btnCancel);
		
		nameTF = new JTextField();
		nameTF.setBounds(104, 30, 204, 20);
		panel.add(nameTF);
		nameTF.setColumns(10);
		
		addTF = new JTextField();
		addTF.setColumns(10);
		addTF.setBounds(104, 62, 204, 20);
		panel.add(addTF);
		
	
		contactTF = new JTextField();
		contactTF.setColumns(10);
		contactTF.setBounds(104, 126, 204, 20);
		panel.add(contactTF);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(10, 156, 84, 21);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(10, 188, 84, 21);
		panel.add(lblPassword);
		
		userTF1 = new JTextField();
		userTF1.setEditable(false);
		userTF1.setFont(new Font("Tahoma", Font.BOLD, 11));
		userTF1.setColumns(10);
		userTF1.setBounds(104, 157, 38, 20);
		panel.add(userTF1);
		userTF1.setText("Staff-");
		
		passTF = new JPasswordField();
		passTF.setBounds(104, 186, 204, 20);
		panel.add(passTF);
		
		userTF = new JTextField();
		userTF.setColumns(10);
		userTF.setBounds(141, 157, 167, 20);
		panel.add(userTF);
		
		JLabel lblNewStaffAccount = new JLabel("New STAFF ACCOUNT");
		lblNewStaffAccount.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewStaffAccount.setBounds(10, 0, 318, 38);
		frame2.getContentPane().add(lblNewStaffAccount);
	}
}
