/*
 TransactionRules is an interface of diferent transaction rules.
 It is implemented by different kind of transaction rules.
 
 */
package Rules;

public interface TransactionRules {
    
    // check the account if it can be credit
	public boolean canCredit(double amount);
    
    // check the account if it can be debit.
	public boolean canDedit(double amount);
	
}
