/**
 * Filename: ShowBalanceController.java
 * Description: Controller that is in charge of the Show Balance operation of the bank application.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import Rules.ActionRule;

/**
 * Class Name: ShowBalanceController
 * Description: Controls the Show Balance operation. Implements the Controller interface.
 */
public class ShowBalanceController implements Controller{
    /**
     * Control method specifically to operate the show balance operation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if operation succeeded, false otherwise
     */
	public boolean control(ArrayList<Account> accounts,SQL sql) {
		if(accounts==null){ // operation fails if there are no accounts
			System.out.println("No account for close");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to show balance:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){ // go through each account
			if(accounts.get(i).getAccountNo()==accountNo){ // check if account number is consistent
				ActionRule actionRule = new ActionRule(accounts.get(i));
				if(actionRule.CheckFreezeAccount()){ // notify account holder that the account is frozen
					System.out.println(" The account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // notify account holder that the account is closed
					System.out.println("Account has been closed");
					continue;
				}
				else{ // display balance
					System.out.println("Account "+accounts.get(i).getAccountNo()+"'s balanace is "+accounts.get(i).getBalance());
					return true;
				}
			}
		}
		System.out.println("Account No doesn't exist"); // notify account holder that the account does not exist
		return false;
	}
}
