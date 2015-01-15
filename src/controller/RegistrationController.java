/**
 * Filename: RegistrationController.java
 * Description: Interface that holds the methods to implement for all associated registration controllers.
 */

package controller;




import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import DataSource.Admin;
import DataSource.User;

/**
 * Interface Name: RegistrationController
 * Description: Interface that holds the methods to implement for all associated registration controllers.
 */
public interface RegistrationController {
    /**
     * General control method to be implemented by all associated registration controllers.
     * @param newUser This is the user who is registering for an account.
     * @param password This is the user's new password.
     * @param checking This is the object used for registration error checking.
     * @return the new user
     */
	public User UserController(User newUser,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException;

	/**
     * General control method to be implemented by all associated registration controllers.
     * @param newUser This is the admin who is registering for an account.
     * @param password This is the admin's new password.
     * @param checking This is the object used for registration error checking.
     * @return the new admin
     */
	public Admin AdminController(Admin admin,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException; 
	
}
