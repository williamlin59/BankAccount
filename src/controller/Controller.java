/**
 * Filename: Controller.java
 * Description: Interface that holds the methods to implement for all associated controllers.
 */

package controller;

import java.util.ArrayList;

import DataSource.Account;

/**
 * Interface Name: Controller
 * Description: Interface that holds the methods to implement for all associated controllers.
 */
public interface Controller {
    /**
     * General control method to be implemented by all associated controllers.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	public boolean control(ArrayList<Account> accounts,SQL sql);
}
