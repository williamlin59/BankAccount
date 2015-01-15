package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import DataSource.Account;
import DataSource.Admin;
import DataSource.User;
import controller.AccountController;
import controller.AccountCreateController;
import controller.EmailSender;
import controller.ErrorChecking;
import controller.LoginController;
import controller.Password;
import controller.SQL;
import controller.UserRegistration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class LoginPage {

	private JFrame frmAaaa;
	private JTextField IDText;
	private JTextField PasswordText;
    private String Username=null;
    private String Password=null;
	/**
	 * Launch the application.
	 */
    
    private Admin admin;

	/**
	 * Create the application.
	 */
	public LoginPage(Admin admin) {
		initialize();
		this.admin = admin;
		//Logic logic = new Logic();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAaaa = new JFrame();
		frmAaaa.setTitle("Welcome to xxx Bank");
		frmAaaa.setBounds(100, 100, 450, 300);
		frmAaaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAaaa.getContentPane().setLayout(null);
		
		IDText = new JTextField();
		IDText.setBounds(175, 34, 204, 37);
		frmAaaa.getContentPane().add(IDText);
		IDText.setColumns(10);
		
		PasswordText = new JTextField();
		PasswordText.setBounds(175, 96, 204, 37);
		frmAaaa.getContentPane().add(PasswordText);
		PasswordText.setColumns(10);
		
		JLabel IDLabel = new JLabel("Login ID");
		IDLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		IDLabel.setBounds(45, 34, 96, 37);
		frmAaaa.getContentPane().add(IDLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		PasswordLabel.setBounds(45, 96, 96, 37);
		frmAaaa.getContentPane().add(PasswordLabel);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Username=IDText.getText();
				Password=PasswordText.getText();
				System.out.println(Username);
				System.out.println(Password);
				//admin.setLoginID(Username);
				//admin.setPassword(Password);
				
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		LoginButton.setBounds(66, 172, 124, 37);
		frmAaaa.getContentPane().add(LoginButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		CancelButton.setBounds(235, 172, 124, 37);
		frmAaaa.getContentPane().add(CancelButton);
	}
	public String getUsername(){
		return Username;
	}
	
	public String getPassword(){
		return Password;
	}
	//UI activity
	public static void main(String[] args) {
		SQL sql = new SQL();
		 //1LoginPage loginPage = new LoginPage();
		 EmailSender sender=new EmailSender();
		 Password password =new Password();
		 ErrorChecking checking =new ErrorChecking();
		 LoginController login = new LoginController();
		 Account newAccount = null;
		 AccountCreateController account = new AccountCreateController();
		 AccountController accountControl = new AccountController(); 
		 UserRegistration registration = new UserRegistration();
		 User newUser =new User();
		 Admin admin =new Admin();
		 Scanner in = new Scanner(System.in);
		 boolean validOption=false;
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
		 	boolean a =true;
		 	LoginPage window = new LoginPage(admin);
				try {
					
					
					window.frmAaaa.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println(admin.getLoginID());
				//if(window.getUsername()!=null)
					//JOptionPane.showMessageDialog(null,"aaa");
			}
			
		//});
	//}


}
