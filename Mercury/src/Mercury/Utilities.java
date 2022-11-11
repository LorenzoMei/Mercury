package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilities {

	public static Connection connessione() {
		Connection con;
		
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	String url ="jdbc:mysql://127.0.0.1/banca";
	    	con = DriverManager.getConnection(url, "root", "abcdefg");
	        return con;
		}
	    catch(ClassNotFoundException e) {
	    	System.out.println("errore");
	    	e.printStackTrace();
	    }
	    catch(SQLException e) {
	    	System.out.println("Errore get connection");
	    	e.printStackTrace();
	    }
		return con = null;
		}
	
	
}
