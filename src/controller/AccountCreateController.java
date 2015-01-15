/**
 * Filename: AccountCreateController.java
 * Description: Controller that is in charge of bank account creation.
 */

package controller;

import java.sql.SQLException;
import java.util.Scanner;

import DataSource.*;

/**
 * Class Name: AccountCreateController
 * Description: Controls the creation of both the savings and checking bank accounts.
 */
public class AccountCreateController {
	private static final int ACTIVE = 1; // variable for keeping track of account's status

	/**
     * Creates either a savings or checking account depending on the user's input.
     * @param LoginID This is the user's inputted LoginID for his or her account
     * @param sql This is the database that stores account information.
     * @return newAccount the newly created account
     */
	public Account create(String LoginID,SQL sql) throws SQLException{
		Account newAccount=null; // initialize account
		boolean flag=true; // initialize flag to exit in case of invalid input
		 do{
			 System.out.println("Please enter the type of Account that you would love to open(we currently have saving and checking account):");
			 Scanner in = new Scanner(System.in);
			 String input =in.nextLine();
			 if(input.equalsIgnoreCase("saving")){ // creates savings account when user types "saving"
				 newAccount = new Credit();
				 newAccount.setAccountType(1); // set account type to saving
			 }
			 else if(input.equalsIgnoreCase("checking")){ //creates checking account when user types "checking"
				 newAccount = new Debit();
				 newAccount.setAccountType(2); // set account type to checking
			 }
			 else{
				 System.err.println("Invalid input"); // exit when there is invalid input
				 System.exit(0);
				 flag =false;
			 }
		 }while(flag == false);
	 		newAccount.setAccountNo(newAccount.accountNoGenerator()); // set user's account number
			 newAccount.setLoginID(LoginID); // set user's account ID
			 newAccount.setAccountStauts(ACTIVE); // set account status
			 newAccount.create(sql.DbConnector()); // create using database
			 sql.DbConnector().close(); // close database
		return newAccount;
		
	}
}