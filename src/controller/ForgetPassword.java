/**
 * Filename: ForgetPassword.java
 * Description: Controller that is in charge of the forget password operation.
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
import DataSource.User;

/**
 * Class Name: ForgetPassword
 * Description: Controls the forget password operation.
 */
public class ForgetPassword {

    /**
     * In charge of operations concerning what to do when the user forgets his or her password.
     * @param user This is the user in charge of the account with the password.
     * @param sql This is the database that stores account information.
     * @return void
     */
	public ForgetPassword(User user,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your Login ID:");
		String login = in.nextLine(); // get the user's login ID


		SecureRandom random = new SecureRandom(); // randomly generate new password for user
		String newPW = new BigInteger(130, random).toString(32);
		String newPW1 = password.passwordConvertor(newPW);
		System.out.println(newPW);

		if(! user.updatePassword( sql.DbConnector() , newPW1 , login ) ){
			System.out.println("Account "+ login +" does not exist!"); // exits when login does not exist
			//System.out.println("Account "+ user.getLoginID());
			return;
		}

		EmailSender sender=new EmailSender();
		user.setLoginID(login); // reset login ID
		user.setPassword(newPW1); // reset the password
		user.view(sql.DbConnector());

		String body =user.getFirst()+" "+user.getMiddle()+" "+user.getLast()+"\n"+"Your new password for:"+user.getLoginID()+" is: "+newPW
				+"\n"+"you can reset your password once you login\n";
		 sender.send(user.getEmail(),body,"Password Reset"); // send email confirming password reset
		 System.out.println("An email has been sent to your email address");
		
	}

}
