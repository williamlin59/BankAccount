/**
 * Filename: UserRegistration.java
 * Description: Controller that is in charge of the account registration operation for users and admins.
 */

package controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;

import DataSource.Admin;
import DataSource.User;

/**
 * Class Name: UserRegistration
 * Description: Controls the account registration operation. Implements the RegistrationController interface.
 */
public class UserRegistration implements RegistrationController{
    /**
     * UserController method specifically for registering new user accounts.
     * @param newUser This is the user who is registering for an account.
     * @param password This is the user's new password.
     * @param checking This is the object used for registration error checking.
     * @return the new user
     */
	public User UserController(User newUser,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException{
		 Scanner in =new Scanner (System.in);
		 String loginID; // initialize the registration input fields
		 String inputPassword;
		 String firstName;
		 String lastName;
		 String address;
		 String email;
		 String phoneNo;
		 String cell;
		 String zip;



		 do{ // user enters login ID and is checked accordingly
		 System.out.println("(Manditory) Please enter your Login ID (At least 8 characters mixed with upper lower case and digits):");
		 loginID=in.nextLine();
		 }while(!loginID.matches(checking.getLOGIN_PATTERN()));
		 newUser.setLoginID(loginID);

		 do{ // user enters password and is checked accordingly
			 System.out.println("(Manditory) Please enter your PassWord (At least 8 characters mixed with upper lower case and digits):");
			 inputPassword =in.nextLine();
		 }while(!inputPassword.matches(checking.getLOGIN_PATTERN()));
		 newUser.setPassword (password.passwordConvertor(inputPassword));

		 do{ // user enters first name and is checked accordingly
			 System.out.println("(Manditory) Please enter your FirstName (No digits):");
			 firstName =in.nextLine();
		 }while(!firstName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 newUser.setFirst(firstName);

			 System.out.println("Please enter your MiddleName:"); // user enters middle name
			 newUser.setMiddle(in.nextLine());
		 do{ // user enters last name and is checked accordingly
			 System.out.println("(Manditory) Please enter your LastName (No digits):");
			 lastName =in.nextLine();
		 }while(!lastName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 newUser.setLast(lastName);

		 do{ // user enters home address and is checked accordingly
			 System.out.println("(Manditory) Please enter your home address:");
			 address =in.nextLine();
		 }while(address.length()>500||address.isEmpty());
		 newUser.setAddress(address);

		 System.out.println("Please enter your gender:"); // user enters gender
		 newUser.setGender(in.nextLine());
		 do{ // user enters email address and is checked accordingly
			 System.out.println(" (Manditory) Please enter your Email:");
			 email =in.nextLine();
		 }while(!email.matches(checking.getEMAIL_PATTERN()));
		 newUser.setEmail (email);

		 do{ // user enters phone number and is checked accordingly
			 System.out.println("(Manditory) Please enter your Phone No:");
			 phoneNo=in.nextLine();
		 }while(!phoneNo.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 newUser.setPhone(phoneNo);

		 do{ // user enters cell phone number and is checked accordingly
			 System.out.println(" (Manditory) Please enter your Cell:");
			 cell=in.nextLine();
		 }while(!cell.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 newUser.setCell(cell);


		 System.out.println("Please enter your Country:"); // user enters country and state
		 newUser.setCountry(in.nextLine());
		 System.out.println("Please enter your State:");
		 newUser.setState(in.nextLine());

		 do{ // user enters zip code and is checked accordingly
			 System.out.println("(Manditory) Please enter your State ZipCode:");
			 zip=in.nextLine();
		 }while(!zip.matches(checking.getFIVE_DIGITS_PATTERN()));
		 newUser.setZip(zip);

		 System.out.println("Please enter your Social Security No:"); // user enters social security number
		 newUser.setSocialSecurity(in.nextLine());
		 System.out.println("Please enter your Date of Birth:"); // user enters date of birth
		 newUser.setDOB( new java.sql.Date(new java.util.Date().getTime()));
		 return newUser;

	}


    /**
     * Admin Controller method specifically for registering new admin accounts.
     * @param admin This is the admin who is registering for an account.
     * @param password This is the admin's new password.
     * @param checking This is the object used for registration error checking.
     * @return the new admin
     */
	@Override
	public Admin AdminController(Admin admin, Password password,
			ErrorChecking checking) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {
		Scanner in =new Scanner (System.in);
		 String loginID; // initialize the registration input fields
		 String inputPassword;
		 String firstName;
		 String lastName;
		 String branch;
		 String department;
		 String phoneNo;
		 String workingID;
		 String email;


		 do{ // admin enters login ID and is checked accordingly
		 System.out.println("(Manditory) Please enter your Login ID (At least 8 characters mixed with upper lower case and digits):");
		 loginID=in.nextLine();
		 }while(!loginID.matches(checking.getLOGIN_PATTERN()));
		 admin.setLoginID(loginID);

		 do{ // admin enters password and is checked accordingly
			 System.out.println("(Manditory) Please enter your PassWord (At least 8 characters mixed with upper lower case and digits):");
			 inputPassword =in.nextLine();
		 }while(!inputPassword.matches(checking.getLOGIN_PATTERN()));
		 admin.setPassword (password.passwordConvertor(inputPassword));

		 do{ // admin enters first name and is checked accordingly
			 System.out.println("(Manditory) Please enter your FirstName (No digits):");
			 firstName =in.nextLine();
		 }while(!firstName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 admin.setFirst(firstName);

			 System.out.println("Please enter your MiddleName:"); // admin enters middle name
			 admin.setMiddle(in.nextLine());
		 do{ // admin enters last name and is checked accordingly
			 System.out.println("(Manditory) Please enter your LastName (No digits):");
			 lastName =in.nextLine();
		 }while(!lastName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 admin.setLast(lastName);

		 do{ // admin enters branch and is checked accordingly
			 System.out.println("(Manditory) Please enter your branch:");
			 branch =in.nextLine();
		 }while(branch.length()>500||branch.isEmpty());
		 admin.setBranch(branch);

		 System.out.println("Please enter your gender:");
		 admin.setGender(in.nextLine()); // admin enters gender


		 do{ // admin enters phone number and is checked accordingly
			 System.out.println("(Manditory) Please enter your Phone No:");
			 phoneNo=in.nextLine();
		 }while(!phoneNo.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 admin.setPhone(phoneNo);

		 do{ // admin enters department and is checked accordingly
			 System.out.println(" (Manditory) Please enter your department:");
			 department=in.nextLine();
		 }while(department.length()>500||department.isEmpty());
		 admin.setDepartment(department);
		 do{ // admin enters email address and is checked accordingly
			 System.out.println(" (Manditory) Please enter your Email:");
			 email =in.nextLine();
		 }while(!email.matches(checking.getEMAIL_PATTERN()));
		 admin.setEmail (email);

			 System.out.println(" (Manditory) Please enter your workingID:"); // admin enters working ID
			 workingID=in.nextLine();

			 admin.setWorkingID(workingID);


		 return admin;
	}



}
