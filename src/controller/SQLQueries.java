/**
 * Filename: SQLQueries.java
 * Description: Program that defines database query operations for the bank application.
 */

package controller;

/**
 * Class Name: SQLQueries
 * Description: Defines database query operations for the application.
 */
public class SQLQueries {
	private static final String SELECT_ACCOUNT= "Select * FROM Account"; // variable for select account query
	private static final String SELECT_USER= "Select * FROM User" ; // variable for select user query
	private static final String INSERT_ACCOUNT = " insert into Account (UserLoginID,AccountNo, AccountType, Balance, OpenDate,timeStamp,Currency)"
	        									+ " values (?, ?, ?, ?, ?,?,?)"; // variable for insert account query
	private static final String INSERT_USER = " insert into User (FirstName,MiddleName, LastName,Address, LoginID,Password,CreateDate,TimeStamp,Gender,Email,Phone,Cell,Country,State,ZipCode,SocialSecurity,DateOfBirth)"
		        							+ " values (?, ?, ?, ?, ?,?, ?,?,?, ?, ?, ?, ?,?, ?,?,?)"; // variable for insert user query
	private static final String LOGIN_CHECKING ="select * from User where LoginID = ? and Password =?"; // variable for login checking query

	/**
     * In charge of retrieving select account query.
     * @return the select account query
     */
	public String SelectAccountQuery(){
		return SELECT_ACCOUNT;
	}

    /**
     * In charge of retrieving select user query.
     * @return the select user query
     */
	public String SelectUserQuery(){
		return SELECT_USER;
	}

    /**
     * In charge of retrieving insert account query.
     * @return the insert account query
     */
	public String InsertAccountQuery(){
		return INSERT_ACCOUNT;
	}

    /**
     * In charge of retrieving insert user query.
     * @return the insert user query
     */
	public String InsertUserQuery(){
		return INSERT_USER;
	}

    /**
     * In charge of retrieving login checking query.
     * @return the login checking query
     */
	public String LOGIN_CHECKING(){
		return LOGIN_CHECKING;
	}

}
