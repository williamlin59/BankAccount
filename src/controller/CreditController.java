/**
 * Filename: CreditController.java
 * Description: Controller that is in charge of the credit operation used by the bank admins.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

/**
 * Class Name: CreditController
 * Description: Controls the credit operation. Implements the Controller interface.
 */
public class CreditController implements Controller{
	private final static int BANK =-1; // variable for setting the account's authority
	private final static int DEPOSITE =1; // variable for keeping track of the operation type

	/**
     * Control method specifically to operate the credit operation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	@Override
	public boolean control(ArrayList<Account> accounts,SQL sql) {

		if(accounts==null){ // exit when there is no account to credit
			System.out.println("No account for credit");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to credit to:");
		int accountNo=in.nextInt();

		for(int i = 0;i<accounts.size();i++){ // for each account
			if(accounts.get(i).getAccountNo()==accountNo){
				System.out.println("Please input the amount that you want to credit:");
				double amount = in.nextDouble();
				ActionRule actionRule = new ActionRule(accounts.get(i));
				//accounts.get(i);
				//validation checking
				if(actionRule.CheckFreezeAccount()){ // cannot credit a frozen account
					System.out.println("debit transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // cannot credit a closed account
					System.out.println("Account has been closed");
					continue;
				}
				else{
					accounts.get(i).credit(amount); // credit the account
					Record record =new Record();
					record.setAccountNo(accountNo); // reset account number
					record.setCredit(amount); // actual credit operation
					record.setBalance(accounts.get(i).getBalance()); // reset the balance
					record.setAuthority(BANK); // set to bank authority
					record.setType(DEPOSITE); // type is "deposit"
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
		}
		System.out.println("Account No doesn't exist"); // exit when account does not exist


		return false;
	}

}

