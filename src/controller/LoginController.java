/**
 * Filename: LoginController.java
 * Description: Controller that is in charge of how the user and admin logs in to their accounts.
 */

package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.Scanner;

import DataSource.Admin;
import DataSource.User;

/**
 * Class Name: LoginController
 * Description: Controls the login operation for both admins and users.
 */
public class LoginController {
    /**
     * In charge of login operations for user accounts.
     * @param user This is the user in charge of the account.
     * @param password This is the user's password.
     * @param sql This is the database that stores account information.
     * @return true if user logs in, false otherwise
     */
	public 	boolean UserLloginController(User user,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
			Scanner in = new Scanner(System.in);
			boolean login=false;
			 System.out.println("Please enter your Login ID:"); // user enters login information
			 user.setLoginID(in.nextLine());
			 System.out.println("Please enter your PassWord:");
			 user.setPassword (password.passwordConvertor(in.nextLine()));
			 login =user.view(sql.DbConnector());
			  
			 if(login== true){ // user succeeds in logging in
					 return login;
			 }
			 else{ // logging in to nonexistent account
				 System.out.println("User doesn't exist");
				 return false;
			 }
			 
		}

    /**
     * In charge of login operations for admin accounts.
     * @param admin This is the admin in charge of the account.
     * @param password This is the admin's password.
     * @param sql This is the database that stores account information.
     * @return true if admin logs in, false otherwise
     */
	public 	boolean AdminLoginController(Admin admin,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		boolean login=false;
		 System.out.println("Please enter your Login ID:"); // admin enters login information
		 admin.setLoginID(in.nextLine());
		 System.out.println("Please enter your PassWord:");
		 admin.setPassword (password.passwordConvertor(in.nextLine()));
		 login =admin.view(sql.DbConnector());
		 
		 if(login== true){ // admin succeeds in logging in
				 return login;
		 }
		 else{ // logging in to nonexistent account
			 System.out.println("User doesn't exist");
			 return false;
		 }
		 
	}
}