package admin;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import admin.AdminFrame;
import staff.StaffFrame;

public class LogInStaffAdmin {

	public static JFrame frame;
	private JTextField anTF;


	Connection conn =  null;
	Statement stmt;
	PreparedStatement pst;
	static ResultSet rs = null;
	static String Pass[] = new String[20];
	static String username[] = new String[20];
	int line;
	String user,pass;
	private JPasswordField passTF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInStaffAdmin window = new LogInStaffAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
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
	
	public LogInStaffAdmin() throws SQLException {
		initialize();
		conn = dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	
	

	private int searchStaff(String staffUser) throws SQLException {
		String sql2 = "Select Username from staff";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql2);
		for(line=0;rs.next();line++) {
			username[line] = rs.getString("Username");
			if(username[line].matches(staffUser)){
				return 1;
			}
		}
		return -1;

	}

	private int searchStaffPass(String staffPass) throws SQLException {
		String sql2 = "Select Password from staff";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql2);
		for(line=0;rs.next();line++) {
			Pass[line] = rs.getString("Password");
			if(Pass[line].matches(staffPass)){
				return 1;
			}
		}
		return -1;

	}
	

	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton logJB = new JButton("LOGIN");
		logJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		logJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		logJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			try {
				int x,y;
				user = anTF.getText();
				pass = passTF.getText();
			
				if(user.equalsIgnoreCase("admin")&&pass.matches("091254")) {
					AdminFrame newFrame = new AdminFrame();
					AdminFrame.frame1.setVisible(true);
					frame.dispose();
				}
				else if(!user.equalsIgnoreCase("admin")&&pass.matches("091254")) {
					JOptionPane.showMessageDialog(null, "Incorrect Username","",JOptionPane.ERROR_MESSAGE);

				}
				else if(user.equalsIgnoreCase("admin")&&!pass.matches("091254")) {
					JOptionPane.showMessageDialog(null, "Incorrect Password","",JOptionPane.ERROR_MESSAGE);

				}
				else if (user.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input Username","",JOptionPane.ERROR_MESSAGE);
				 }
					
				else{
					x = searchStaff(user); 
					 if (pass.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input Password","",JOptionPane.ERROR_MESSAGE);
					 }
					 else if(x>0){
						  y = searchStaffPass(pass);
						  if (y>0) {
								StaffFrame newFrame  = new StaffFrame(user);
								StaffFrame.frame3.setVisible(true);
								 conn.close();
								 frame.dispose();
						  }
						  else if(y<0) {
								JOptionPane.showMessageDialog(null, "Incorrect Password","",JOptionPane.ERROR_MESSAGE);
										}
						  
					 			}
					 
					else if(x<0) {
						JOptionPane.showMessageDialog(null, "Incorrect Username","",JOptionPane.ERROR_MESSAGE);

								}
				

				}
					  
					  
				}

			catch (SQLException e) {
				
			}
			}
		});
		logJB.setBounds(192, 211, 89, 23);
		frame.getContentPane().add(logJB);
		
		JButton exitJB = new JButton("EXIT");
		exitJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		exitJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		exitJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		exitJB.setBounds(291, 211, 89, 23);
		frame.getContentPane().add(exitJB);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(88, 160, 100, 19);
		frame.getContentPane().add(lblPassword);
		
		anTF = new JTextField();
		anTF.setBounds(192, 129, 150, 20);
		frame.getContentPane().add(anTF);
		anTF.setColumns(10);
		
		JLabel lblNotHaveAccount = new JLabel("Staff Login");
		lblNotHaveAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNotHaveAccount.setBounds(10, 11, 170, 28);
		frame.getContentPane().add(lblNotHaveAccount);
		
		passTF = new JPasswordField();
		passTF.setBounds(192, 160, 150, 20);
		frame.getContentPane().add(passTF);
		
		JLabel lblUsername = new JLabel("USERNAME :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(88, 130, 100, 19);
		frame.getContentPane().add(lblUsername);
	}

}
