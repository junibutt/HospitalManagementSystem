import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

class DB
{
   private static Connection con;
    private DB()
    {

    }
   public static Connection connection()
   {
	       if(con==null)
           {
            Statement statement = null;
        	ResultSet resultSet = null;
 
 	       // Step 1: Loading or 
        	// registering Oracle JDBC driver class
	        try {
 
 	           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			   System.out.println("Loaded Successfully");
        	}
 	       catch(ClassNotFoundException cnfex) {
 
 	           System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
        	    cnfex.printStackTrace();
                return null;
        	}
 
        // Step 2: Opening database connection
 	       try {
 
 	           String msAccDB = "C:\\Users\\iLaptop.pk\\Documents\\hospital.accdb";
        	    String dbURL = "jdbc:ucanaccess://"+ msAccDB; 
 
				System.out.println("Connecting ............");
 	           // Step 2.A: Create and 
        	    // get connection using DriverManager class
            	con = DriverManager.getConnection(dbURL); 
				System.out.println("Connection Successfully");
           }
           catch(Exception e)
           {
            return null;
           }
           }
           return con;        
   }
   public static void main(String[] args) {
    DB.connection();
    DB.connection();
    DB.connection();
   }
 }