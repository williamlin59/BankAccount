/*
 The class of SavingsAccountInterestRule implements InterstRules.
 It implements the saving account interest rules.
 
 */
package Rules;

import DataSource.Account;

public class SavingsAccountInterestRule implements InterestRules{
	
	private double rate = 0.01;
	private Account savingsAccount;
		
    // construct SavingsAccountInterestRule with the account you want to apply it to.
	public SavingsAccountInterestRule(Account account) {
        
		savingsAccount = account;
	}

    // override this rule to the function ApplyInterest()
    // calculate the interest by the specific rate.
	@Override
	public void ApplyInterest() {

        savingsAccount.setBalance(savingsAccount.getBalance() * (1 + rate));
		
	}

}
