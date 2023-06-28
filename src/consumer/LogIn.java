package consumer;

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

public class LogIn {

	public static JFrame frame;
	private JTextField anTF;


	Connection conn =  null;
	Statement stmt;
	PreparedStatement pst;
	static ResultSet rs = null;
	static String Pass[] = new String[20];
	static int userAc[] = new int[20];
	int user,line;
	String pass;
	private JPasswordField passTF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	
	public LogIn() throws SQLException {
		initialize();
		conn = dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	
	

	private int searchUser(int user1) throws SQLException {
		String sql2 = "Select AccountNumber from consumer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql2);
		for(line=0;rs.next();line++) {
			userAc[line] = rs.getInt(1);
			if(userAc[line]== user1){
				return 1;
			}
		}
		return -1;

	}

	private int searchPass(String pass) throws SQLException {
		String sql = "Select Password from consumer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		for(line=0;rs.next();line++) {
			Pass[line] = rs.getString("Password");
			if(Pass[line].matches(pass)){
				return 1;
			}
		}
		return -1;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 144, 255));
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
				user = Integer.parseInt(anTF.getText());
				pass = passTF.getText();
			
			
					 x = searchUser(user);
					
					  if(x>0){
					y = searchPass(pass);
					 if (pass.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input Password","",JOptionPane.ERROR_MESSAGE);
					 }
					 else if(y>0){
						 ConsumerAccount newFrame = new ConsumerAccount(user);
						 ConsumerAccount.frame3.setVisible(true);
						 conn.close();
						 frame.dispose();
					 			}
					else if(y<0) {
						JOptionPane.showMessageDialog(null, "Incorrect Password","",JOptionPane.ERROR_MESSAGE);

								}
					
					else  {
						JOptionPane.showMessageDialog(null, "Incorrect Username","",JOptionPane.ERROR_MESSAGE);

						  }

					 			}
					  else {
							JOptionPane.showMessageDialog(null, "Please Input Username","",JOptionPane.ERROR_MESSAGE);

					  }
					  
					  
				}

			catch (SQLException e) {
				
			}
			
	catch (NumberFormatException ee) {
		JOptionPane.showMessageDialog(null, "Wrong Inputs","",JOptionPane.ERROR_MESSAGE);

			}
			}
		});
		logJB.setBounds(192, 211, 89, 23);
		frame.getContentPane().add(logJB);
		
		JButton signJB = new JButton("SIGN UP");
		signJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		signJB.setFont(new Font("Tahoma", Font.BOLD, 12));
		signJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					CreateAccount newFrame = new CreateAccount();
				CreateAccount.frame2.setVisible(true);
				conn.close();
				frame.dispose();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		signJB.setBounds(192, 53, 89, 23);
		frame.getContentPane().add(signJB);
		
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
		
		JLabel lblUsername = new JLabel("ACCOUNT NUMBER :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(50, 129, 138, 19);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(88, 160, 100, 19);
		frame.getContentPane().add(lblPassword);
		
		anTF = new JTextField();
		anTF.setBounds(192, 129, 150, 20);
		frame.getContentPane().add(anTF);
		anTF.setColumns(10);
		
		JLabel lblNotHaveAccount = new JLabel("Don't have an Account?");
		lblNotHaveAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNotHaveAccount.setBounds(10, 50, 170, 28);
		frame.getContentPane().add(lblNotHaveAccount);
		
		passTF = new JPasswordField();
		passTF.setBounds(192, 160, 150, 20);
		frame.getContentPane().add(passTF);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
