/**
 * Filename: AccountController.java
 * Description: Program that runs the bank application.
 */

package controller;
import java.io.IOException;

import java.security.GeneralSecurityException;

import DataSource.*;

import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class Name: Main
 * Description: Runs the bank application.
 */
public class Main {
    /**
     * Runs the bank application.
     * @param args Not used.
     * @return void
     */
	 public static void main(String[] args) throws SQLException, GeneralSecurityException, NoSuchProviderException, IOException {
		 SQL sql = new SQL(); // initialize database
		 EmailSender sender=new EmailSender(); // initialize email sender
		 Password password =new Password(); // initialize password
		 ErrorChecking checking =new ErrorChecking(); // initialize error checking capabilities
		 LoginController login = new LoginController(); // initialize controllers
		 Account newAccount = null;
		 AccountCreateController account = new AccountCreateController();
		 AccountController accountControl = new AccountController(); 
		 UserRegistration registration = new UserRegistration();
		 User newUser =new User(); // initialize user
		 Admin admin =new Admin(); // initialize admin
		 Scanner in = new Scanner(System.in);
		 boolean validOption=false;
		 System.out.println("Welcome to out Account registration page");
		 do{ // runs account registration page
			 System.out.println("Input 1 for login in, 2 for registrating user,3 for registrating admin, 4 for changing password 5 for emailing password");
			 String choice = in.nextLine();
			 if(Integer.parseInt(choice)==1){
				 boolean flag;
				 System.out.println("Input 1 for login in as an user. Input 2 for login as an admin.");
				 String input = in.nextLine();
				 switch(input){
				 case "1":
					 	do{
					 		 flag=login.UserLloginController(newUser,password, sql); 
					 		
					 	}while(flag==false);
					 	
					 	accountControl.UsercontrolAccount(newUser, sql);
					 	break;
				 case "2":
					 	do{
					 		 flag=login.AdminLoginController(admin,password, sql); 
					 		
					 	}while(flag==false);
					 	
					 	accountControl.AdmincontrolAccount(admin, sql);
					 	break;
				 }
				
					 
			 }

		   
		 
			 else if(Integer.parseInt(choice)==2){ // user registration

				 boolean validation =true;
				 
				 do{
					 
						 newUser = registration.UserController(newUser,password,checking);
						 validation=newUser.create(sql.DbConnector());

						 sql.DbConnector().close();

	
					
				 }while(validation ==false);
				 validation = false;
				 String body =newUser.getFirst()+" "+newUser.getMiddle()+" "+newUser.getLast()+"\n"+"Thanks for registrating at the xxx online bank. Here is your log in detail:\n"+"LoginID: "+newUser.getLoginID()+"\n";
				 sender.send(newUser.getEmail(),body,"Registration Confirmation");
				 System.out.println("Registration completed, an email has been sent to your email address"); // registration complete
				 do{ // opening accounts (saving or checking)
					 System.out.println("Do you want to open an account?(y for yes, n for no)");
					 String option = in.nextLine();
					 switch(option){
					 	case "y":
					 		account.create(newUser.getLoginID(),sql);
					 		validation = true;
					 		break;
					 	case "n":
					 		validation =true;
					 		break;
					 	default:
					 		System.out.println("Invalid Input");
					 		break;
					 }
					
				 }while(validation == false);
			 }
			 else if(Integer.parseInt(choice)==3){ // admin registration
				 boolean validation =true;
				 
				 do{
					 
						 admin = registration.AdminController(admin,password,checking);
						 validation=admin.create(sql.DbConnector());

						 sql.DbConnector().close();

	
					
				 }while(validation ==false);
				 validation = false;
				 String body =admin.getFirst()+" "+admin.getMiddle()+" "+admin.getLast()+"\n"+"Thanks for registrating at the xxx online bank. Here is your log in detail:\n"+"LoginID: "+admin.getLoginID()+"\n";
				 sender.send(admin.getEmail(),body,"Registration Confirmation");
				 System.out.println("Registration completed, an email has been sent to your email address"); // registration complete
				/* do{
					 System.out.println("Do you want to open an account?(y for yes, n for no)");
					 String option = in.nextLine();
					 switch(option){
					 	case "y":
					 		account.create(newUser.getLoginID(),sql);
					 		validation = true;
					 		break;
					 	case "n":
					 		validation =true;
					 		break;
					 	default:
					 		System.out.println("Invalid Input");
					 		break;
					 }
					
				 }while(validation == false);
				//sender.send("williamlin59@outlook.com","test","password reset");*/
				
			 }
			 
			 else if(Integer.parseInt(choice)==4){ // delegate to ResetPassword for reset password operation
				 ResetPassword resetpassword = new ResetPassword(password, sql);
			}
			 
			 
			 else if(Integer.parseInt(choice)==5){ // delegate to Forget Password for forget password operation
				 ForgetPassword forgetpassword = new ForgetPassword(newUser,password, sql);
			 }
			 
			 
			 
			 
			 else{ // input is invalid
				 System.err.println("Invalid Option");
				 
			 }
			 newUser=new User(); // create new user
		 }while(validOption ==false);
	 }
}