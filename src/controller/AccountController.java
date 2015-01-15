/**
 * Filename: AccountController.java
 * Description: Controller that is in charge of interactions between the user and admin accounts and the database.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;

/**
 * Class Name: AccountController
 * Description: Controls the operation of the Admin and User account pages.
 */
public class AccountController {
    /**
     * In charge of operations concerning the Admin account page.
     * @param admin This is the admin in charge of the admin account.
     * @param sql This is the database that stores account information.
     * @return void
     */
	public void AdmincontrolAccount(Admin admin,SQL sql) throws SQLException{
	 	int noOfAccounts=admin.viewAccount(sql.DbConnector()); //get number of accounts
	 	ArrayList<Account> userAccounts = null;
	 	sql.DbConnector().close();	// close database
	 	Scanner in =new Scanner(System.in);
	 	boolean flag =true; // flag to signify what page is active
	 	do{
	 	System.out.println("Welcome to xxx bank, "+admin.getFirst());
	 	System.out.println("Here are all accounts in the system:");
	 	if(noOfAccounts ==0){ // Notifies admin when there isn't any account opened
	 		//System.out.println("You haven't opened any accountyet");
	 		System.out.println("No accounts in the system yet.");
	 	}
	 	else{
	 		admin.accountsCleaner();
	 		admin.viewAccount(sql.DbConnector());
	 		userAccounts =admin.getAccounts(); // get the accounts and display their information
	 		for(int i=0;i<userAccounts.size();i++){
	 			if(userAccounts.get(i).getAccountStatus()!=-1){
	 			System.out.println("Account Number: "+userAccounts.get(i).getAccountNo());
	 			System.out.println("Account Type: "+(userAccounts.get(i).getAccountType()==1?"saving":"checking"));
	 			System.out.println("Opening Date:"+userAccounts.get(i).getCreateDate ());
	 			System.out.println("Balance :"+userAccounts.get(i).getBalance());
	 			System.out.println("");
	 			}
	 		}
	 	}
	 	//admin.viewRecords(null, noOfAccounts);

	 		System.out.println("Please enter the command");
	 		//System.out.println("1. create new acount");
	 		System.out.println("1. credit cash");
	 		System.out.println("2. debit cash");
	 		System.out.println("3. show balance");
	 		System.out.println("4. Close one account");
	 		System.out.println("5. Check Account Record");
	 		System.out.println("6. Back to main menu");
	 		System.out.println("7. Calculate interests");
	 		System.out.println("8. Calculate penalty");
	 		String userInput = in.nextLine();
	 		switch(userInput){

	 			case "1":  // delegate to the credit controller
	 				CreditController credit = new CreditController();
	 				credit.control(userAccounts,sql);
	 				
	 				break;
	 			case "2": // delegate to the debit controller
	 				DebitController debit = new DebitController();
	 				 debit.control(userAccounts,sql);
	 				 
	 				break;
	 			case "3": // delegate to the show balance controller
	 				ShowBalanceController balance = new ShowBalanceController();
	 				
	 				 balance.control(userAccounts,sql);
	 				break;
	 			case "4": // delegate to the close account controller
	 				//flag=false;
	 				CloseAccountController close =new CloseAccountController();
	 				close.control(userAccounts,sql);
	 				break;
	 			case "5": // delegate to the show records controller
	 				ShowRecordsController records = new ShowRecordsController();
	 				System.out.println(userAccounts.size());
	 				records.control(userAccounts,sql);
	 				break;
	 			case "6": // get out of account page
	 				flag=false;
	 				break;
	 			case "7": // delegate to the interest controller
	 				InterestController interest =new InterestController();
	 				interest.control(userAccounts,sql);
	 				break;
	 			case "8": // delegate to the penalty controller
	 				PenaltyController penalty =new PenaltyController();
	 				penalty.control(userAccounts,sql);
	 				break;
	 			default: // detects invalid input
	 				System.err.println("Invalid input");
	 				
	 		}
	 		
	 	}while(flag ==true);
	 	
	 	

	}

	/**
     * In charge of operations concerning the User account page.
     * @param user This is the user in charge of the user account.
     * @param sql This is the database that stores account information.
     * @return void
     */
	public void UsercontrolAccount(User user,SQL sql) throws SQLException{
	 	int noOfAccounts=user.viewAccount(sql.DbConnector()); // get number of accounts
	 	ArrayList<Account> userAccounts = null;
	 	sql.DbConnector().close(); // close database

	 	Scanner in =new Scanner(System.in);
	 	boolean flag =true; // flag to signify what page is active
	 	do{
	 	System.out.println("Welcome to xxx bank, "+user.getFirst());
	 	System.out.println("Here are all accounts you have:");
	 	if(noOfAccounts ==0){ // notify user when no accounts have been opened
	 		System.out.println("You haven't opened any accountyet");
	 		//System.out.println("No accounts in the system yet.");
	 	}
	 	else{
	 		user.accountsCleaner();
	 		user.viewAccount(sql.DbConnector());
	 		userAccounts =user.getAccounts(); // get the accounts and display their information
	 		for(int i=0;i<userAccounts.size();i++){
	 			if(userAccounts.get(i).getAccountStatus()!=-1){
	 			System.out.println("Account Number: "+userAccounts.get(i).getAccountNo());
	 			System.out.println("Account Type: "+(userAccounts.get(i).getAccountType()==1?"saving":"checking"));
	 			System.out.println("Opening Date:"+userAccounts.get(i).getCreateDate ());
	 			System.out.println("Balance :"+userAccounts.get(i).getBalance());
	 			System.out.println("Account Status:"+userAccounts.get(i).getAccountStatus());
	 			System.out.println("");
	 			}
	 		}
	 	}


	 		System.out.println("Please enter the command");
	 		System.out.println("1. create new acount");
	 		//System.out.println("2. credit cash");
	 		//System.out.println("3. debit cash");
	 		System.out.println("2. Show balance");
	 		System.out.println("3. Transfer money");
	 		System.out.println("4. Show account records");
	 		System.out.println("5. Back to main menu");
	 		
	 		String userInput = in.nextLine();
	 		switch(userInput){
	 			case "1": // delegate to the account creation controller
	 			 	AccountCreateController account = new AccountCreateController();
	 			 	
	 				account.create(user.getLoginID(),sql);
	 				user.accountsCleaner();
	 				user.viewAccount(sql.DbConnector());
	 				userAccounts =user.getAccounts();
	 				break;
	 			/*case "2":

	 				credit.control(userAccounts,sql);
	 				
	 				break;
	 			case "3":
	 				 debit.control(userAccounts,sql);
	 				 
	 				break;*/
	 			case "2": // delegate to the show balance controller
	 			 	ShowBalanceController balance = new ShowBalanceController();
	 				 balance.control(userAccounts,sql);
	 				break;
	 			case "3": // delegate to the transfer controller
	 				TransferController transfer = new TransferController();
	 				transfer.control(userAccounts,sql);
	 				//userAccounts.clear();
	 				break;
	 			case "4": // delegate to the control records controller
	 				ShowRecordsController records = new ShowRecordsController();
	 				records.control(userAccounts,sql);
	 				break;
	 			case "5": // get out of account page
	 				flag=false;
	 				break;
	 			default: // detects invalid input
	 				System.err.println("Invalid input");
	 				
	 		}
	 		
	 	}while(flag ==true);
	 	
	 	

	}
}