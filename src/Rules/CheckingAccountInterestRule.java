/*
 The class of CheckingAccountInterestRule implements InterstRules.
 It implements the checking account interest rules.
 
 */
package Rules;

import DataSource.Account;

public class CheckingAccountInterestRule implements InterestRules{

	private double rate = 0.0;
	private Account checkingAccount;
	
    // construct CheckingAccountInterestRule with the account you want to apply it to.
	public CheckingAccountInterestRule(Account account) {
        
		checkingAccount = account;
	}
    
    // override this rule to the function ApplyInterest()
    // calculate the interest by the specific rate.
	@Override
	public void ApplyInterest() {
        
		checkingAccount.setBalance(checkingAccount.getBalance() * (1 + rate));
		
	}

}
