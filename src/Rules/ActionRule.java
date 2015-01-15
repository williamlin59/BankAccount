/*
 ActionRule
 Set up different action rules to account:
 Freeze or close or reactivate an account.
 Check the status of the account.
 Apply interest rules.
 Check the amount limits of an account.
 
 */
package Rules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataSource.Account;

public class ActionRule {
	
	private static final int ACTIVE = 1;
	private static final int FROZEN = 0;
	private static final int CLOSED = -1;

	private Account account;
	// construct an actionrule with an account
	public ActionRule(Account account) {
		this.account = account;
	}
	// check if it is closed
	public boolean CanLogin() {
		return (account.getAccountStatus() != CLOSED);
	}
	
	// freeze an account
	public boolean FreezeAccount(){
		if(account.getAccountStatus() != FROZEN){
			account.setAccountStauts(FROZEN);
			return true;
		}
		else
			return false;
	}
    
	// close an account
	public boolean CloseAccount(){
		if(account.getAccountStatus() != CLOSED){
			account.setAccountStauts(CLOSED);
			return true;
		}
		else
			return false;
	}
	
    // reacitivate an account
	public boolean ReactivateAccount(){
		if(account.getAccountStatus() != ACTIVE){
			account.setAccountStauts(ACTIVE);
			return true;
		}
		else
			return false;
	}

    // apply interest rules
	public void ApplyInterest(InterestRules interestrule) {
		if(account.getAccountStatus() == ACTIVE)
			interestrule.ApplyInterest();
	}
    
    // check if it is freezed
	public boolean CheckFreezeAccount(){
		return account.getAccountStatus() == FROZEN;
	}
    
    // check if it is closed
	public boolean CheckClosedAccount(){
		return account.getAccountStatus() == CLOSED;
	}
    
    // check the amount limits of an account
	public boolean amountLimits(Connection conn,int accountNo){
		String query = "select sum(Balance) as amount from Records where DATE(TimeStamp) =CURDATE() AND AccountNo = ?";
		PreparedStatement statement;
		double amount = 0;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, accountNo);
			ResultSet table = statement.executeQuery();
			if(table.next()){
				amount = table.getDouble("amount");

			}

			}catch (SQLException e) {
			e.printStackTrace();
			return false;
			}
		
	    if(amount >10000){
	    	return false;
	    }
		return true;
		
	}
    
    // check the amount of an account
	public boolean amountChecking(double amount){
		return amount<=10000;
	}
}

