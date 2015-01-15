/*
 CheckUserRule
 Check the user account information and read it from the database.
 Check the different types of an account such as debit or credit.
 
 */

package Rules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataSource.Account;
import DataSource.User;

public class CheckUserRule {
    
    // check the user of an account and read it out of the database
	public boolean check(Connection conn,int accountNo,Account account,User user){
		String query = "select * from Account,User where AccountNo = ? AND Account.UserLoginID =User.LoginID ";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, accountNo);
			ResultSet table = statement.executeQuery();
			if(table.next()){
		        user.setFirst( table.getString("User.FirstName"));
		        user.setMiddle(  table.getString("User.MiddleName"));
		        user.setLast( table.getString("User.LastName"));
		        user.setPhone(table.getString("User.Phone"));
		        user.setEmail(table.getString("User.Email"));
		        account.setAccountNo( table.getInt("Account.AccountNo"));
		        account.setBalance( table.getDouble("Account.Balance"));
		        account.setAccountStauts( table.getInt("Account.AccountStatus"));
		        
		        return true;
			}
			else{
				System.out.println("2");
				return false;
			}
			}catch (SQLException e) {
				System.out.println("3");
			e.printStackTrace();
			return false;
			}
	}
    
    // check the type of the account and the type is represented by integers.
	public int checkAccountType(Connection conn,int accountNo){
		String query = "select AccountType from Account,User where AccountNo = ? ";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, accountNo);
			ResultSet table = statement.executeQuery();
			if(table.next()){
		        return table.getInt("AccountType");
			}
			else{
				System.out.println("2");
				return -1;
			}
			}catch (SQLException e) {
				System.out.println("3");
			e.printStackTrace();
			return -1;
			}       

	}
}
