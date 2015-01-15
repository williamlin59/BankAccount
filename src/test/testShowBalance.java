package test;

import java.util.ArrayList;

import DataSource.Account;

import controller.SQL;
import controller.ShowBalanceController;

public class testShowBalance {
	private ShowBalanceController test; //= new ShowBalanceController();
	ArrayList<Account> input;
	//show balance of the AccountList
	public void test (ArrayList<Account> input, SQL sql){
		test.control(input,sql);
	}
	
}
