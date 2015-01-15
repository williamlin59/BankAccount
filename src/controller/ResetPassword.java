/**
 * Filename: ResetPassword.java
 * Description: Controller that is in charge of the reset password operation.
 */

package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.security.SecureRandom;
import java.math.BigInteger;

import controller.EmailSender;
import DataSource.Admin;
import DataSource.User;

/**
 * Class Name: ResetPassword
 * Description: Controls the reset password operation.
 */
public class ResetPassword {
    /**
     * In charge of resetting the user's password when he or she requests it.
     * @param password This is the user's old password.
     * @param sql This is the database that stores account information.
     * @return void
     */
	public ResetPassword(Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		LoginController login = new LoginController();
		User newUser =new User();
		Admin admin =new Admin(); // initialize user and admin

		boolean flag;
		 System.out.println("Input 1 for login in as an user. Input 2 for login as an admin.");
		 String input = in.nextLine();
		 switch(input){
		 case "1": // logging in as user
			 	do{ // delegate to the login controller to control user login
			 		 flag=login.UserLloginController(newUser,password, sql);

			 	}while(flag==false);

			 	break;
		 case "2": // logging in as admin
			 	do{ // delegate to the login controller to control admin login
			 		 flag=login.AdminLoginController(admin,password, sql);

			 	}while(flag==false);

			 	break;
		 }

		 System.out.println("Please Enter Your New Password:"); // user enters new password
		 String newPW = in.nextLine();
         String newPW1 = password.passwordConvertor(newPW); // generates new password

		if(! newUser.updatePassword( sql.DbConnector() , newPW1 , newUser.getLoginID() ) ){
			System.out.println("Error: Account "+ login +" does not exist!"); // exit if login does not exist
			return;
		}

		System.out.println("Success: Password has been reset!!");
		return;
	}

}

