package consumer;

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


import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CreateAccount {

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
	private JPasswordField passTF;
	int i;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
					CreateAccount.frame2.setVisible(true);
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
	
	public CreateAccount() {
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
		frame2.setBounds(100, 100, 454, 372);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new TitledBorder(null, "Accounts Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 57, 318, 263);
		frame2.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(10, 28, 84, 21);
		panel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 60, 84, 21);
		panel.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
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
				String sql1 = "Select AccountNumber from consumer";
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
				pass = passTF.getText();
				gender = (male.isSelected())? male.getLabel(): female.getLabel();
				no = contactTF.getText();
				if(!name.isEmpty()&&!add.isEmpty()&&!gender.isEmpty()&&!no.isEmpty()&&!pass.isEmpty()) {
					Object[] confirmation = {
							
							"Confirmation of your details: \nPress OK to Proceed\nPress CANCEL to Edit\n\nName: \t"+name+"\nAddress: \t"+add+
							"\nGender: \t"+gender+"\nContact no.: \t"+no+"\nPassword: \t"+pass	
						};
						
						int conF = JOptionPane.showConfirmDialog(null, confirmation,"Your Information",JOptionPane.OK_CANCEL_OPTION);
						
						if (conF == JOptionPane.OK_OPTION) {
					
					String sql = "insert into consumer (AccountNumber,Name,Address,Gender,Contact,Password,Bill,Arrears,Previous,Present,Date,Paid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
					pst = conn.prepareStatement(sql);
					if(id==0) {
						id = 001;
						pst.setInt(1,  001);

					}
					else if (id>0) {
						id++;
						pst.setInt(1,  id);

					}
					
					pst.setString(2, name);
					pst.setString(3, add);
					pst.setString(4, gender);
					pst.setString(5, no);
					pst.setString(6, pass);
					pst.setDouble(7, 0.0);
					pst.setDouble(8, 0.0);
					pst.setInt(9, 0);
					pst.setInt(10, 0);
					pst.setString (11,"");
					pst.setBoolean(12, false);

					
					pst.execute();
					JOptionPane.showMessageDialog(null, " Hello "+name+ "\nWelcome to Water District Consumer Payout Online!!\nYour ID Number is: "+id+ "\nSuccesfully Created Account! :-) ","",JOptionPane.INFORMATION_MESSAGE);
					LogIn newFrame = new LogIn();
					LogIn.frame.setVisible(true);
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
				try {
					LogIn newFrame = new LogIn();
					LogIn.frame.setVisible(true);
					conn.close();
					frame2.dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
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
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(10, 156, 84, 31);
		panel.add(lblPassword);
		
		passTF = new JPasswordField();
		passTF.setBounds(104, 157, 204, 20);
		panel.add(passTF);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(10, 11, 418, 35);
		frame2.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Creating NEW ACCOUNT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(67, 0, 320, 35);
		panel_1.add(lblNewLabel);
	}
}
