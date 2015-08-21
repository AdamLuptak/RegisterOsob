package filesSavers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	    private Connection DBConnection;
	    private Connection connection;

	    private String driverClass;

	    private String url;

	    private String user;

	    private String password;
	    final String DATABASE_PROPERTIES = "database.properties";
	    
	    public Connection connect(){
	   
	        try {
	           Class.forName("com.mysql.jdbc.Driver");

	            System.out.println("Connection Success");
	        } catch (Exception e) {
	            System.out.println("Connection  error");
	        }
	   String url = "jdbc:mysql://localhost:3305/person";
	  
	        try {
	            DBConnection = DriverManager.getConnection(url, "root", "deckaa");
	            System.out.println("Database connect");
	        } catch (SQLException se) {
	             System.out.println("Database conenect " + se);
	            
	        }
	    
	       return DBConnection;
	    }
	    
	    
	    private void loadProperties() {
	        try {
	            Properties properties = new Properties();
	            properties.load(getClass().getResourceAsStream(DATABASE_PROPERTIES));
	            //driverClass = properties.getProperty("driverClass");
	            url = properties.getProperty("url");
	            user = properties.getProperty("user");
	            password = properties.getProperty("password");
	        } catch (IOException e) {
	          //  throw new ApplicationException(1, "Cannot load database configuration file '" + DATABASE_PROPERTIES + "'", e);
	        }
	    }
	}


