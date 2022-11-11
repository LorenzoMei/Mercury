package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilities {
	private static Connection con;

	public static Connection connessione() {
		
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	String url ="jdbc:mysql://127.0.0.1/mercurydb";
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

	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Errore chiusura connessione");
			e.printStackTrace();
		}
	}
	
	public static void aggiungiEnte(Ente ente) throws SQLException {
		
		Connection con = connessione();
	    Statement st=con.createStatement();
	    
/* inserimento dati dell'ente nella tabella utente */	    
		try {
	    String queryIns = " INSERT INTO utente ( nome, cognome, email, password, ruolo) "
	    		         + " VALUES ('"+ ente.getNomeResponsabile() +"','"+ ente.getCognomeResponsabile() + "','" + ente.getEmail() 
	    		         + "','" + ente.getPassword() + " ','ente');";
	    
	    st.executeUpdate(queryIns);} 
		
		catch(SQLException e){
	    	   
	    	   System.out.println(" errore aggiungi utente");
	    	   e.printStackTrace();
	    	   };
	    	   
/* inserimento dati dell'ente nella tabella ente  */	   	    	   
		try {
	        
	        int idUtente = 0;
	        	        
/* try per estrarre chiave id dell'utente */
	         try {
	 	        ResultSet rst = st.executeQuery("SELECT idUtente from utente where email ='" + ente.getEmail() + "'");
	 	        
	 	       while(rst.next()) { idUtente = rst.getInt("idUtente");}
				
			} catch (SQLException e) {
				 System.out.println(" errore estrapolazione id utente");
		    	   e.printStackTrace();
			}
	         	     
	         
/* con id utente si va ad fare un INSERT nella tabella Ente   */                 
	    String queryIns = " INSERT INTO ente ( nomeEnte, stato, utenteFK) "
	    		         + " VALUES ('"+ ente.getNomeEnte() + " ','attivo','" + idUtente + "');";
	    
	    st.executeUpdate(queryIns);} 
		
		catch(SQLException e){   
	    	   System.out.println(" errore aggiungi ente");
	    	   e.printStackTrace();
	    	   }  
	}
}
