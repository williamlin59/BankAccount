package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataSource.User;

public class testSQL {
	public void testUserSQL(Connection conn,String Query, User user){
		User testUser = new User();
		String query = Query;
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			ResultSet table = statement.executeQuery();
			//get the information table of testUser
			if(table.next()){
				testUser.setFirst( table.getString("FirstName"));
				testUser.setMiddle(  table.getString("MiddleName"));
				testUser.setLast( table.getString("LastName"));
				testUser.setAddress( table.getString("Address"));
				testUser.setLoginID( table.getString("LoginID"));
				testUser.setPassword( table.getString("Password"));
				testUser.setcreateDate( table.getDate("CreateDate"));
				testUser.setTimeStamp(table.getDate("TimeStamp"));
				testUser.setGender(table.getString("Gender"));
				testUser.setEmail( table.getString("Email"));
				testUser.setPhone( table.getString("Phone"));
				testUser.setCell (table.getString("Cell"));
				testUser.setCountry( table.getString("Country"));
				testUser.setState( table.getString("State"));
				testUser.setZip( table.getString("ZipCode"));
				testUser.setSocialSecurity( table.getString("SocialSecurity"));
				testUser.setDOB (table.getDate("DateOfBirth"));

		        
			}
			else{
				System.out.println("user doesn't exist in database");
				
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("cannot connect to database");
			
			
			} 
			//print value of User FirstName
			System.out.println("First Name:");
			System.out.println(user.getFirst());
			System.out.println(testUser.getFirst());
			
			//print value of User MiddlerName
			System.out.println("MiddleName");
			System.out.println(user.getMiddle());
			System.out.println(testUser.getMiddle());
			
			//print value of User LastName
			System.out.println("LastName");
			System.out.println(user.getLast());
			System.out.println(testUser.getLast());
			
			//print value of Address
			System.out.println("Address");
			System.out.println(user.getAddress());
			System.out.println(testUser.getAddress());
			
			//print value of LoginID
			System.out.println("LoginID");
			System.out.println(user.getLoginID());
			System.out.println(testUser.getLoginID());
			
			//print value of Password
			System.out.println("Password");
			System.out.println(user.getPassword());
			System.out.println(testUser.getPassword());
			
			//print value of CreateDate
			System.out.println("CreateDate");
			System.out.println(user.getCreateDate());
			System.out.println(testUser.getCreateDate());
			
			//print value of TimeStamp
			System.out.println("TimeStamp");
			System.out.println(user.getTimeStamp());
			System.out.println(testUser.getTimeStamp());
			
			//print value of Gender
			System.out.println("Gender");
			System.out.println(user.getGender());
			System.out.println(testUser.getGender());
			
			//print value of Email
			System.out.println("Email");
			System.out.println(user.getEmail());
			System.out.println(testUser.getEmail());
			
			//print value of Phone
			System.out.println("Phone");
			System.out.println(user.getPhone());
			System.out.println(testUser.getPhone());
			
			System.out.println("Cell");
			System.out.println(user.getCell());
			System.out.println(testUser.getCell());
			
			//print value of Country
			System.out.println("Country");
			System.out.println(user.getCountry());
			System.out.println(testUser.getCountry());
			
			//print value of State		
			System.out.println("State");
			System.out.println(user.getState());
			System.out.println(testUser.getState());
			
			//print value of ZipCode			
			System.out.println("ZipCode");
			System.out.println(user.getZip());
			System.out.println(testUser.getZip());
			
			//print value of SS
			System.out.println("SocialSecurity");
			System.out.println(user.getSocialSecurity());
			System.out.println(testUser.getSocialSecurity());
			
			//print value of DateOfBirth
			System.out.println("DateOfBirth");
			System.out.println(user.getDOB());
			System.out.println(testUser.getDOB());
	

	}
}
