/*
 The class of DeditAccountTransactionRule implements TransactionRules.
 It implements the transaction rules for a credit account.
 
 */

package Rules;

import DataSource.Account;

public class DeditAccountTransactionRule implements TransactionRules{

	private double minimumBalance = 0;
	private Account account;
	
    // construct DeditAccountTransactionRule with the account you want to apply it to.
    // and the minimumbalance for this account
	public DeditAccountTransactionRule(Account account, double minimumbalance) {

        this.account = account;
		this.minimumBalance = minimumbalance;
	}

    // override this rule to the function canCredit()
    // check the amount if it is smaller than 0, return incorrect.
	@Override
	public boolean canCredit(double amount) {
		// TODO Auto-generated method stub
		if (amount > 0)
			return true;
		else{
			System.out.println("Incorrect Amount");
			return false;
		}
	}
    
    // override this rule to the function canDedit()
    // check the amount if it is smaller than 0, return incorrect.
    // check the if it is larger than minimum balance.
	@Override
	public boolean canDedit(double amount) {
		// TODO Auto-generated method stub
		if (amount <= 0){
			System.out.println("Incorrect Amount");
			return false;
		}
		else if(account.getBalance() - minimumBalance < amount){
			System.out.println("Insufficient funds");
			return false;
		}
		return true;

	}

}
