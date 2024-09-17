package ute.tri.configs;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import ute.tri.models.UserModel;
public class DBConnectSQLServer {
    // Connection parameters
    private final String serverName = "LAPTOP-FHP5T214";
    private final String dbName = "ltweb";
    private final String portNumber = "1433";
    private final String instance="";//MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "1234567@a$";
    /*
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabase";
    private static final String USER = "yourUsername";
    private static final String PASSWORD = "yourPassword";
*/
    // Construct
        // Default constructor
    public Connection getConnection() throws Exception {

            // Build the connection URL
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName; ;
            if (instance == null || instance.trim().isEmpty()) 
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            return DriverManager.getConnection(url, userID, password);

    }
 public class main {
     // Main method to test the connection
     public static void main(final String[] args) {
         try {
             // Create an instance of DB_Connection and get a connection
             Connection conn = new DBConnectSQLServer().getConnection();         
             // Print the connection object (it will print connection details if successful)
             System.out.println(conn);
         } catch (Exception e) {
             // Handle exceptions and print the stack trace
             e.printStackTrace();
         }
     }
 }
public List<UserModel> findAll() {
	// TODO Auto-generated method stub
	return null;
}
public UserModel findByUserName(String username) {
	// TODO Auto-generated method stub
	return null;
}

}
 
 

