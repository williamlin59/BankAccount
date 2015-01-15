/**
 * Filename: SQL.java
 * Description: Program that defines database operations for the bank application.
 */

package controller;
import java.sql.Connection;
import DataSource.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

//import com.mysql.jdbc.PreparedStatement;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

/**
 * Class Name: SQL
 * Description: Defines database operations for the application.
 */
public class SQL {
	private static final String DRIVER_NAME ="com.mysql.jdbc.Driver"; // variable for database driver
	private static final String DB_ADDRESS ="jdbc:mysql://db4free.net/banckaccount"; // variable for database address
	private static final String LOGIN_INFO= "?user=bzzchj11&password=bzzchj11";  // variable for login info
	private Connection conn=null;

	/**
     * In charge of connecting to the database.
     * @return conn the connection to the database
     */
	public Connection DbConnector() throws SQLException{
		try { // connecting
            Class.forName(DRIVER_NAME).newInstance();
        } catch (Exception ex) {
            System.err.println("can't connect to internet\n");
        }
        
        try {
            conn = DriverManager.getConnection(DB_ADDRESS +LOGIN_INFO);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
	}
	
    /**
     * In charge of checking the database connection.
     * @param args Not used.
     * @return void
     */
    public static void main(String[] args) throws SQLException {

    	SQL sql =new SQL();
    	sql.DbConnector();
    	//sql.CreateAccount();
        //sql.selectTable();
        sql.conn.close(); // close database
       // }
    }
}
