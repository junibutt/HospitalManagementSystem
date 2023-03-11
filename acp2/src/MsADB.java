 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
 
public class MsADB {
 
    public static void main(String[] args) 
	{
 
 	       // variables
        	Connection connection = null;
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
        	}
 
        // Step 2: Opening database connection
 	       try {
 
 	           String msAccDB = "C:\\Users\\iLaptop.pk\\Desktop\\acp.accdb";
        	    String dbURL = "jdbc:ucanaccess://"+ msAccDB; 
 
				System.out.println("Connecting ............");
 	           // Step 2.A: Create and 
        	    // get connection using DriverManager class
            	connection = DriverManager.getConnection(dbURL); 
				System.out.println("Connection Successfully");
 
		DatabaseMetaData dbmd = connection.getMetaData();

		String catalog = null; 
		String schema = null;
		String table = "Emp"; 
		String[ ] types = null;
		ResultSet rs =dbmd.getTables(catalog , schema , table , types );

		ResultSetMetaData md = rs.getMetaData(); 
		// get number of columns
		int nCols = md.getColumnCount();
		System.out.println(nCols);
		// print column names
		for(int i=1; i < nCols; ++i)
			System.out.println( md.getColumnName( i)+",");
	     	// output resultset
		while ( rs.next() )
		{	for(int i=1; i < nCols; ++i)
			System.out.print( rs.getString( i)+",");
			System.out.println( rs.getString(nCols) );
		}
}
catch(Exception e)
{}
}
}

/*

	       // Step 2.B: Creating JDBC Statement 
        	statement = connection.createStatement();
 
 	           // Step 2.C: Executing SQL and 
        	    // retrieve data into ResultSet
		int id=8989;
		String n="UUUUUUUUUU";
		String fn="WWWWWWWW";
		
		//String q="insert into Emp (ID,Name,FName) values("+id+",'"+n+"','"+fn+"')";
//String q="delete from Emp where id=511";		
//statement.executeUpdate(q);
String createLehigh = "Create table Lehigh " +
 	"(SSN Number not null, Name VARCHAR(32), " + "Marks Integer)";
statement.executeUpdate(createLehigh);
 	        //resultSet = statement.executeQuery("SELECT * FROM Emp");
		resultSet = statement.executeQuery("SELECT * FROM Emp where id<9000");
 
 	           System.out.println("ID\tName\tFName");
        	    System.out.println("==\t================\t");
 
 	           // processing returned data and printing into console
        	   while(resultSet.next()) {
	                System.out.println( resultSet.getInt("ID") +"\t" + 
                	        resultSet.getString(1) + "\t" + 
        	                resultSet.getString(2)) ;
	
						}
}
        catch(SQLException sqlex)
	{
            sqlex.printStackTrace();
        }
        finally {
            // Step 3: Closing database connection
            try {
                if(null != connection) {
                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();
 
                    // and then finally close connection
                    connection.close();
                			}
            	}
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            	}
        }
}    
}*/