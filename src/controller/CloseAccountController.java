/**
 * Filename: CloseAccountController.java
 * Description: Controller that is in charge of closing bank accounts.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

/**
 * Class Name: CloseAccountController
 * Description: Controls the closing of a user's bank accounts. Implements the Controller interface.
 */
public class CloseAccountController implements Controller {
	private final static int BANK =-1; // variable for setting the account's authority
	private final static int CLOSE =-1; // variable for keeping track of the operation type

    /**
     * Control method specifically to operate the closing of accounts.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {
			Record record =new Record();
			if(accounts==null){ // exits if there is no account to close
				System.out.println("No account for close");
				return false;
			}
			Scanner in =new Scanner(System.in);
			System.out.println("Please input the account number that you want to close:");
			int accountNo=in.nextInt();
			
			for(int i = 0;i<accounts.size();i++){ // for each account
				
				if(accounts.get(i).getAccountNo()==accountNo){ // check if account number is consistent
					ActionRule actionRule = new ActionRule(accounts.get(i));
					if(actionRule.CheckFreezeAccount()){ // cannot close frozen account
						System.out.println("The account has been frozon, please defrost the account first");
						return false;
					}
					if(actionRule.CheckClosedAccount()){ // cannot close account that is already closed
						System.out.println("Account has been closed");
						continue;
					}
					//accounts.get(i);
					//validation checking
					/*if(! accounts.get(i).CanCredit(amount)){
						System.out.println("Cannot complete credit:");
						return false;
					}*/
					else{
						accounts.get(i).setAccountStauts(CLOSE); // close the account
						record.setAccountNo(accountNo); // reset account number
					//record.setCredit(amount);
						record.setBalance(0); // balance is back to 0
						record.setAuthority(BANK); // set to bank authority
						record.setType(CLOSE); // type is "close"
					}
					try {
						accounts.get(i).update(sql.DbConnector()); // update the database
						record.insertRecord(sql.DbConnector());
						
					} catch (SQLException e) { // quit current credit operations
						System.err.println("Can't connect to Database to finish Credit");
						e.printStackTrace();
					}
					
					return true;
				}
			}
			System.out.println("Account No doesn't exist"); // exit when account does not exist
			
			
			return false;
	}

}
