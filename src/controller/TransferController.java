/**
 * Filename: TransferController.java
 * Description: Controller that is in charge of the transfer operation used by the users.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Credit;
import DataSource.Debit;
import DataSource.Record;
import DataSource.User;
import Rules.ActionRule;
import Rules.CheckUserRule;
import Rules.DeditAccountTransactionRule;

/**
 * Class Name: TransferController
 * Description: Controls the transfer operation. Implements the Controller interface.
 */
public class TransferController implements Controller{
	Account fromAccount; // initialize account to transfer from
	Account ToAccount; // initialize account to transfer to
	ActionRule actionRule;
	private final static int TRANSFER =3; // variable for keeping track of the operation type

    /**
     * Control method specifically to operate the transfer operation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql)  {

		//Record record =new Record();
		if(accounts==null){ // no account to transfer from
			System.out.println("No account for transfer");
			return false;
		}
		if(accounts.size()==1){ // only one account to transfer from
			System.out.println("Only one account available in the system.");
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to transfer from:");
		int accountNo=in.nextInt();

		for(int i = 0;i<accounts.size();i++){ // for each account
			if(accounts.get(i).getAccountNo()==accountNo){
				//System.out.println("Please input the amount that you want to credit:");
				//double amount = in.nextDouble();
				actionRule = new ActionRule(accounts.get(i));
				//accounts.get(i);
				//validation checking
				if(actionRule.CheckFreezeAccount()){ // no transfer for frozen accounts
					System.out.println("debit transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // no transfer for closed accounts
					System.out.println("Account has been closed");
					continue;
				} else
					try { // quit transaction when the daily limit is reached
						if(!actionRule.amountLimits(sql.DbConnector(),accounts.get(i).getAccountNo())){
							System.out.println("It has debited over 10000 today");
							return false;
						}
						else{
							fromAccount = accounts.get(i);
							/*accounts.get(i).credit(amount);
							record.setAccountNo(1accountNo);
							record.setCredit(amount);
							record.setBalance(accounts.get(i).getBalance());
							record.setAuthority(BANK);
							record.setType(DEPOSITE);*/
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		System.out.println("Please input the accountNo that you want to transfer money to:");
		accountNo =in.nextInt();


		User toUser=new User(); // initialize user to transfer money to

		ToAccount=new Credit();
		CheckUserRule checkUser = new CheckUserRule();

		try {
			int flag =checkUser.checkAccountType(sql.DbConnector(),accountNo);
			if(flag<0){ // account does not exist
				System.out.println("Account No " + accountNo+" doesn't exist in the system");
				return false;
			}
			else if(flag==1){
				ToAccount=new Credit(); // initialize credit for transfer
			}
			else if (flag==2){
				ToAccount=new Debit(); // initialize debit for transfer
			}



			//
			try {
				if(!checkUser.check(sql.DbConnector(),accountNo,ToAccount,toUser)){

					return false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Please choose using the phone number or using the email address for transfer\n 1 for phone number, 2 for email address");
		int mux=in.nextInt();
		Scanner in2 =new Scanner(System.in);

		if (mux == 1){ // get phone number of user to transfer money to
		System.out.println("Please input the phone number that you want to transfer to:");
		String phone=in2.nextLine();
			if (!phone.equals( toUser.getPhone())){
				return false;
			}
		}
		else if (mux == 2){ // get email of user to transer money to
			System.out.println("Please input the email address that you want to transfer to:");
			String email=in2.nextLine();
			if (!email.equals( toUser.getEmail())){
				return false;
			}

		//String option = null;*/
		boolean flag =true;
		do{
			String option =null;
			System.out.println("Do you want to transfer to account: "+accountNo );
			System.out.println("Owned by: "+toUser.getFirst());
			System.out.println("input 1 for yes, 2 for no");
			option = in.nextLine();

			if(option.equals("1")){ // proceed with transaction
				ActionRule actionRule = new ActionRule(fromAccount);
		//need to be modified in the future
				DeditAccountTransactionRule debitRule =new DeditAccountTransactionRule(fromAccount,0);
				System.out.println("Please input the amount of money that you want to transfer money to :");
				double amount = in.nextDouble();
				if(actionRule.CheckFreezeAccount()){ // no transfer for frozen accounts
					System.out.println(" Transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){ // no transfer for closed accounts
					System.out.println("Account has been closed");
					return false;
				}
				else if(! debitRule.canDedit(amount)){ // stop transaction when there is not enough money
					System.out.println("Insufficient balance for debit transaction");
					return false;
				}
				else{

					Record fromRecord = new Record();
					fromAccount.debit(amount);
					ToAccount.credit(amount); // transfer money

					fromRecord.setAccountNo(fromAccount.getAccountNo()); // reset account number
					fromRecord.setDebit(amount); // actual debit part of transfer
					fromRecord.setBalance(fromAccount.getBalance()); // reset balance
					fromRecord.setAuthority(ToAccount.getAccountNo()); // set to user's account authority
					fromRecord.setType(TRANSFER); // type is "transfer"
					Record ToRecord =new Record();
					ToRecord.setAccountNo(ToAccount.getAccountNo()); // reset account number
					ToRecord.setCredit(amount); // actual credit part of transfer
					ToRecord.setBalance(ToAccount.getBalance()); // reset balance
					ToRecord.setAuthority(fromAccount.getAccountNo()); // set to user's account authority
					ToRecord.setType(TRANSFER); // type is "transfer"
					try {
						ToAccount.update(sql.DbConnector()); //update database
						fromAccount.update(sql.DbConnector());
						fromRecord.insertRecord(sql.DbConnector());
						ToRecord.insertRecord(sql.DbConnector());

					} catch (SQLException e) { // quit current transfer operations
						System.err.println("Can't connect to Database to finish Credit");
						e.printStackTrace();
						return false;
					}

				return true;

				}
			}
			else if (option.equals("2")){
				flag =false;
			}
			else{ // exit with invalid input
				System.out.println("Invalid input");
				flag = false;
			}
		}while(!flag);
		
	
			
		}
		return true;
	}
	

}
