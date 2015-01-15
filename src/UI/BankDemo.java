package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BankDemo {

	private JFrame frame;
	private JTextField text1;
	private JTextField text2;
	private JLabel label1;
	private JLabel label3;
	private JLabel label2;
    private String UserName;
    private Double Money;
    private JButton Button4;
	private Double Total=0.0;
	private String Action;
	private JLabel label4;
	private JLabel lblTotal;
    /**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public BankDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//Initialize: set events for different buttons, texts, etc.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		text1 = new JTextField();
		text1.setFont(new Font("Tahoma", Font.BOLD, 18));
		text1.setBounds(53, 61, 203, 37);
		frame.getContentPane().add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setFont(new Font("Tahoma", Font.BOLD, 18));
		text2.setBounds(53, 125, 203, 37);
		frame.getContentPane().add(text2);
		text2.setColumns(10);
		
		JButton Button2 = new JButton("Deposite");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					UserName=text1.getText();
					Money=Double.parseDouble(text2.getText());
				    Action="Deposite";
				    Total=Total+Money;
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "invalid input");
				}
			}
		});
		Button2.setFont(new Font("Tahoma", Font.BOLD, 16));
		Button2.setBounds(53, 206, 116, 37);
		frame.getContentPane().add(Button2);
		
		JButton Button3 = new JButton("Show");
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label1.setText(UserName);
				label2.setText(Action);
				label3.setText(Double.toString(Money));
				label4.setText(Double.toString(Total));
				
			}
		});
		Button3.setFont(new Font("Tahoma", Font.BOLD, 16));
		Button3.setBounds(322, 206, 116, 37);
		frame.getContentPane().add(Button3);
		
		label1 = new JLabel("User");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label1.setBounds(53, 277, 158, 48);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("Action");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label2.setBounds(237, 277, 158, 48);
		frame.getContentPane().add(label2);
		
		label3 = new JLabel("Money");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label3.setBounds(445, 277, 158, 48);
		frame.getContentPane().add(label3);
		
		Button4 = new JButton("Withdraw");
		Button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					UserName=text1.getText();
					Money=Double.parseDouble(text2.getText());
				    Action="Withdraw";
				    Total=Total-Money;
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "invalid input");
				}
			}
		});
		Button4.setFont(new Font("Tahoma", Font.BOLD, 16));
		Button4.setBounds(186, 206, 116, 37);
		frame.getContentPane().add(Button4);
		
		label4 = new JLabel(".........");
		label4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBounds(210, 400, 209, 63);
		frame.getContentPane().add(label4);
		
		lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotal.setBounds(210, 339, 209, 63);
		frame.getContentPane().add(lblTotal);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankDemo window = new BankDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
