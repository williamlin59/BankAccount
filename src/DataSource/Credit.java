/*
 This class is a type of account 
 This class implements type Account

 */

package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Rules.ActionRule;
import Rules.CheckingAccountInterestRule;
import Rules.CreditAccountTransactionRule;
import Rules.InterestRules;
import Rules.TransactionRules;

public class Credit implements Account{
	
	private static final int CREDIT =1;
	
	private int accountNo = 0; 
	private double balance = 0.0;
	private String  loginID ;
	private int accountType = CREDIT;
	private Date createDate,timeStamp;
	private long today =new java.util.Date().getTime();
	private java.sql.Timestamp date = new java.sql.Timestamp(today);
	private int accountStatus ;
	
	//composition of rules and records which apply to this account
	public TransactionRules tranactionrule = new CreditAccountTransactionRule(this,0);
	public InterestRules interestrule = new CheckingAccountInterestRule(this);
	public ActionRule actionrule = new ActionRule(this);
	private ArrayList<Record> records = new ArrayList<Record>();
	
	//accessors and modifier
	@Override
	public int getAccountNo () {
		return accountNo;
	}
	
	@Override
	public void setAccountNo (int input) {
		accountNo = input;
	}
	
	@Override
	public double getBalance () {
		return balance;
	}
	
	@Override
	public void setBalance (double input) {
		balance = input;
	}
	
	@Override
	public String getLoginID () {
		
		return loginID;
	}
	
	@Override
	public void setLoginID (String input) {
		loginID = input;
	}

	@Override
	public int getAccountType () {
		return accountType;
	}
	
	@Override
	public void setAccountType (int input) {
		accountType = input;
	}
	
	@Override
	public Date getCreateDate () {
		return createDate;
	}
	
	@Override
	public void setcreateDate (Date date)  {
		createDate= date;
	}
	
	@Override
	public Date getTimeStamp () {
		return timeStamp;
	}
	
	@Override
	public void setTimeStamp (Date date)  {
		
		timeStamp= date;
	}
	
	@Override
	public int getAccountStatus() {
		return accountStatus;
	}

	@Override
	public void setAccountStauts(int input) {
		accountStatus = input;
	}
		
	@Override
	public ArrayList<Record> getRecords() {
		return records;
	}
	
	//generated new account number for opening account
	@Override
	public int accountNoGenerator(){
		int n = (int)Math.floor( Math.random() * 100000 + 1 );
		NumberFormat formatter = new DecimalFormat("00000");
		String number = formatter.format(n);
		System.out.println("Number with lading zeros: " + number);
		return Integer.parseInt(number);
	}
	
	
	//updates database 
	@Override
	public void update(Connection conn){
		
			String query = "UPDATE Account SET AccountType = ?, Balance = ?, timeStamp=? ,AccountStatus =? WHERE AccountNo = ? ";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1,this.getAccountType());
				preparedStmt.setDouble(2,this.getBalance ());
				preparedStmt.setTimestamp(3, this.date);
				preparedStmt.setInt(4,this.getAccountStatus());
				preparedStmt.setInt(5,this.getAccountNo());

				preparedStmt.executeUpdate();
				preparedStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
	}
	
	//create new account in database
	@Override
	public void create(Connection conn) {
		String query = " insert into Account (UserLoginID,AccountNo, AccountType, Balance, OpenDate,timeStamp,AccountStatus)"
	       + " values (?, ?, ?, ?, ?,?,?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			System.out.println(this.getLoginID());
			preparedStmt.setString (1, this.getLoginID());
			preparedStmt.setInt (2, this.getAccountNo());
			preparedStmt.setInt  (3, this.getAccountType());
			preparedStmt.setDouble(4, this.getBalance ());
			preparedStmt.setTimestamp(5, this.date);
			preparedStmt.setTimestamp(6, this.date);
			preparedStmt.setInt  (7, getAccountStatus());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//checks and modifier for account balance
	public boolean checkBalance(double amount){
		return balance >=amount;
	}
	
	public double credit(double amount){
		balance = balance +amount;
		return balance;
	}
	
	public double debit(double amount){
		balance = balance -amount;
		return amount;	
	}

	//get all records of this account from database
	public ArrayList<Record> viewRecords(Connection conn){
		String query = "select * from Records where AccountNo = ?";
		PreparedStatement statement;
		
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, this.getAccountNo());
			ResultSet table = statement.executeQuery();
			while(table.next()){
				Record record =new Record();
				record.setAccountNo( table.getInt("AccountNo"));
				record.setDebit(  table.getDouble("Debit"));
				record.setCredit( table.getDouble("Credit"));
				record.setBalance( table.getDouble("Balance"));
				record.setAuthority( table.getInt("Authority"));
				record.setType( table.getInt("Type"));
				record.setTimeStamp(table.getDate("TimeStamp"));
		        records.add(record);
			}

			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("3");
			e.printStackTrace();
			return null;
			}
		return records;    	
	}

	//applies interest to account
	public double calculateInterest() {
		double returnValue =0;
		boolean firstInterest =false;
		boolean secondInterest =false;
		boolean thirdInterest =false;
		long day = 30*60*60*24*1000;
		for(int i=0;i<records.size();i++){
			if(records.get(i).getBalance()<1000){
				continue;
			}
			 if((records.get(i).getBalance()>=1000)&&(records.get(i).getBalance()<2000)){
				firstInterest =true;
			}
			 if((records.get(i).getBalance()>=2000)&&(records.get(i).getBalance()<3000)){
				 secondInterest = true;
			}
			 if((records.get(i).getBalance()>=3000)){
				 thirdInterest =true;
			}
		}
		if(firstInterest ==true){
			returnValue =balance*0.01;
		}
		else if(secondInterest ==true){
			returnValue =balance*0.02;
		}
		else if(thirdInterest ==true){
			returnValue =balance*0.03;
		}
		balance =balance+returnValue;
		return returnValue;
	}

	//get record of last 30 days from database
	@Override
	public ArrayList<Record> ThirtyDaysRecords(Connection conn) {
		String query = "select * from Records where Date( TimeStamp)>= ( CURDATE() - INTERVAL 10 DAY ) AND AccountNo = ?";
		PreparedStatement statement;		
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, this.getAccountNo());
			ResultSet table = statement.executeQuery();
			while(table.next()){
				Record record =new Record();
				record.setAccountNo( table.getInt("AccountNo"));
				record.setDebit(  table.getDouble("Debit"));
				record.setCredit( table.getDouble("Credit"));
				record.setBalance( table.getDouble("Balance"));
				record.setAuthority( table.getInt("Authority"));
				record.setType( table.getInt("Type"));
				record.setTimeStamp(table.getDate("TimeStamp"));
		        records.add(record);
			}
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		return records;    
	}

	//applies appropriate interest or penalty
	@Override
	public double compute() {
		boolean penalty = false;
		for(int i=0;i<records.size();i++){
			if(records.get(i).getBalance()<100){
				penalty = true;
			}
		}
		if(penalty = false){
			return 0;
		}
		else {
			balance = balance -25;
			return 25;
		}

	}
}	
