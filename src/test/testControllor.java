package test;

import java.util.ArrayList;

import DataSource.Account;
import DataSource.Debit;

import controller.CreditController;
import controller.DebitController;
import controller.SQL;

//testController: get accounts' information for account list
public class testControllor {
	DebitController debit = new DebitController();
	CreditController credit = new CreditController();
	SQL sql = new SQL();
	ArrayList <Account> accounts;
	public testControllor(ArrayList <Account> input){
		accounts = input;
	}
	public void test(){
		debit.control(accounts,sql);
		credit.control(accounts,sql);
		//print the Account No and Balance of all accounts
		for(int i = 0;i < accounts.size(); i++){
			System.out.print("Account No "+ accounts.get(i).getAccountNo());
			System.out.println(accounts.get(i).getBalance());
		}
	}
	
	
}
