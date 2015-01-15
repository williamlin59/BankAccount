/*
 This is the interface account that different type of 
 Users that will be implemented

 */

package DataSource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public interface AccountHolder {
	
	//all the accessors and mutator method needed by controller
	public String getLoginID ();
	public void setLoginID (String input);
	
	public String getGender ();
	public void setGender (String input);
	
	public String getPhone ();
	public void setPhone (String input);
	
	public String getFirst();
	public void setFirst(String input);

	public String getMiddle();
	public void setMiddle(String input);
	
	public String getLast();
	public void setLast(String input);
	
	public String getPassword ();
	public void setPassword (String input);
	
	public Date getCreateDate ();
	public void setcreateDate (Date date);
	
	public Date getTimeStamp ();
	public void setTimeStamp (Date date);
	
	public String getEmail();
	public void setEmail(String input);
	
	public void setAccounts(ArrayList<Account> accounts);
	public ArrayList<Account> getAccounts();
	
	//update to database
	public boolean update(Connection conn);
	public boolean create(Connection conn);
	
	//get from database
	public boolean view(Connection conn);
	public int viewAccount(Connection conn);
}
