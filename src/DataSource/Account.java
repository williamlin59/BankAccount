/*
 This is the interface account that different type of 
 account class will implement

 */

package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import Rules.CheckingAccountInterestRule;
import Rules.CreditAccountTransactionRule;
import Rules.InterestRules;
import Rules.TransactionRules;

public interface Account {
	
	//all the accessors and mutator method needed by controller
	public int getAccountNo ();
	public void setAccountNo (int input);
	
	public double getBalance ();
	public void setBalance (double input);
	
	public String getLoginID ();
	public void setLoginID (String input);
	
	public int getAccountType ();
	public void setAccountType (int input);
	
	public Date getCreateDate ();
	public void setcreateDate (Date date);
	
	public Date getTimeStamp ();
	public void setTimeStamp (Date date);
	
	//generated new account number for opening account
	public int accountNoGenerator();
	
	//store in database
	public void update(Connection conn);
	public void create(Connection conn);
	
	//check and modify balance
	public boolean checkBalance(double amount);
	public double credit(double amount);
	public double debit(double amount);
	
	//check and modify database
	public int getAccountStatus ();
	public void setAccountStauts (int input);
	
	//gets records from database
	public ArrayList<Record> viewRecords(Connection conn);
	public ArrayList<Record> getRecords();
	public ArrayList<Record> ThirtyDaysRecords(Connection conn);
	
	//applies interest
	public double calculateInterest();
	//applies appropriate interest or penalty
	public double compute();

}

