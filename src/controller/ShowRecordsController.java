/**
 * Filename: ShowRecordsController.java
 * Description: Controller that is in charge of the Show Records operation of the bank application.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

/**
 * Class Name: ShowRecordsController
 * Description: Controls the Show Records operation. Implements the Controller interface.
 */
public class ShowRecordsController implements Controller {

    /**
     * Control method specifically to operate the show records operation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if operation succeeded, false otherwise
     */
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {
		
		ArrayList<Record> records;
		System.out.println("Please input the account number that you want to show records:");
		Scanner in =new Scanner(System.in);
		int accountNo=in.nextInt();
		
		for(int i = 0;i<accounts.size();i++){ // for each account

			if(accounts.get(i).getAccountNo()==accountNo){
				ActionRule actionRule = new ActionRule(accounts.get(i));
				if(actionRule.CheckFreezeAccount()){ // cannot display record for frozen account
					System.out.println(" The account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // cannot display record for closed account
					System.out.println("Account has been closed");
					continue;
				}
				else{
				try {
					records=accounts.get(i).viewRecords(sql.DbConnector());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				for(int j=0;j<records.size();j++){ // print out the record
					System.out.println("Account No :"+records.get(j).getAccountNo());
					System.out.println("Credit :"+records.get(j).getCredit());
					System.out.println("Debit :"+records.get(j).getDebit());
					System.out.println("Balance :"+records.get(j).getBalance());
					System.out.println("Time:"+records.get(j).getTimeStamp());
					System.out.println("Authority :"+records.get(j).getAuthority());
					System.out.println("");
				}
				return true;
				}
			}
			
		}
		
		System.out.println("Account No doesn't exist"); // exit if account does not exist
		return false;
	}

}