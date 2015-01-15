/**
 * Filename: DebitController.java
 * Description: Controller that is in charge of the debit operation used by the bank admins.
 */

package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;
import Rules.*;

/**
 * Class Name: DebitController
 * Description: Controls the debit operation. Implements the Controller interface.
 */
public class DebitController implements Controller{
	private final static int BANK =-1; // variable for setting the account's authority
	private final static int WITHDRAW =2; // variable for keeping track of the operation type

	/**
     * Control method specifically to operate the debit operation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	public boolean control(ArrayList<Account> accounts,SQL sql)   {
		
		
		if(accounts==null){ // exit when there is no account to debit
			System.out.println("No account for Debit");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to debit to:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){ // for each account
			if(accounts.get(i).getAccountNo()==accountNo){
				//need change the min value in future
				DeditAccountTransactionRule debitRule =new DeditAccountTransactionRule(accounts.get(i),0);
				ActionRule actionRule = new ActionRule(accounts.get(i));
				System.out.println("Please input the amount that you want to debit:");
				double amount = in.nextDouble();

				if(actionRule.CheckFreezeAccount()){ // cannot debit a frozen account
					System.out.println("debit transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // cannot debit a closed account
					System.out.println("Account has been closed");
					continue;
				}
				else if(!debitRule.canDedit(amount)){ // exit when there is not enough money to debit
					System.out.println("Insufficient balance for debit transaction");
					return false;
				} else
					try { // exit when debit exceeds the daily limit
						if(!actionRule.amountLimits(sql.DbConnector(),accounts.get(i).getAccountNo())){
							System.out.println("It has debited over 10000 today");
							return false;
						}
						else{
							
							accounts.get(i).debit(amount); // debit the account
							Record record =new Record();
							record.setAccountNo(accountNo); // reset the account number
							record.setDebit(amount); // actual debit operation
							record.setBalance(accounts.get(i).getBalance()); // reset the balance
							record.setAuthority(BANK); // set to bank authority
							record.setType(WITHDRAW); // type is "withdraw"
							try {
								accounts.get(i).update(sql.DbConnector()); // update the database
								record.insertRecord(sql.DbConnector());
							} catch (SQLException e) { // quit current debit operations
								// TODO Auto-generated catch block
								System.err.println("Can't connect to Database to finish Debit");
								e.printStackTrace();
							}
							return true;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		}
		System.out.println("Account No doesn't exist"); //exit when account does not exist
		return false;
	}
}