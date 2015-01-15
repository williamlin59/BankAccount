/*
 This class is record data structure that sores information about all
 transactions

 */

package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Record {
	private int AccountNo;
	private double credit;
	private double debit;
	private double balance;
	private int authority;
	private int type;
	private Date timeStamp;
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	
	//accessors and mutator
	public void setAccountNo(int input){
		AccountNo= input;
	}
	
	public int getAccountNo(){
		return AccountNo;
	}
	
	public void setDebit(double input){
		debit = input;
	}
	
	public double getDebit(){
		return debit;
	}
	
	public void setCredit(double input){
		credit = input;
	}
	
	public double getCredit(){
		return credit;
	}
	
	public void setBalance(double input){
		balance = input;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setAuthority(int input){
		authority = input;
	}
	
	public int getAuthority(){
		return authority;
	}
	
	public void setType(int input){
		type = input;
	}
	
	public int getType(){
		return type;
	}
	
	public void setTimeStamp(Date input){
		timeStamp = input;
	}
	
	public Date getTimeStamp(){
		return timeStamp;
	}
	
	//insert record into the database
	public boolean insertRecord(Connection conn){
		String query = " insert into Records (AccountNo,Credit, Debit,Balance, Timestamp,Authority,Type)"
			       + " values (?, ?, ?, ?, ?,?, ?)";
				PreparedStatement preparedStmt;
				try {
					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setInt(1, this.getAccountNo());
					preparedStmt.setDouble (2, this.getCredit());
					preparedStmt.setDouble  (3, this.getDebit());
					preparedStmt.setDouble(4, this.getBalance());
					preparedStmt.setTimestamp(5, this.date);
					preparedStmt.setInt(6,this.getAuthority());
					preparedStmt.setInt(7, this.getType());
			
					preparedStmt.execute();

					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println("Can't insert record");
					return false;
				}
				return true;
	}

}

