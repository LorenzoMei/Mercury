package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
	
	public static void aggiungiEnte(Ente ente) throws SQLIntegrityConstraintViolationException {
		
		Connection con = connessione();
	    
	    
/* inserimento dati dell'ente nella tabella utente */	    
		try {
			Statement st=con.createStatement();
	    String queryIns = " INSERT INTO utente ( nome, cognome, email, password, ruolo) "
	    		         + " VALUES ('"+ ente.getNomeResponsabile() +"','"+ ente.getCognomeResponsabile() + "','" + ente.getEmail() 
	    		         + "','" + ente.getPassword() + " ','ente');";
	    st.executeUpdate(queryIns);
	    } 
		
		catch(SQLIntegrityConstraintViolationException e){
	    	   
	    	   throw e ;
	    	   }
		
		catch(SQLException e){
	    	   
	    	   System.out.println(" errore aggiungi utente");
	    	   e.printStackTrace();
	    	   }
	    	   
	    	   
	    	   
/* inserimento dati dell'ente nella tabella ente  */	   	    	   
		try {
	        
	        int idUtente = 0;
	        	        
/* try per estrarre chiave id dell'utente */
	         try {
	        	 Statement st=con.createStatement();
	 	        ResultSet rst = st.executeQuery("SELECT idUtente from utente where email ='" + ente.getEmail() + "'");
	 	        
	 	       while(rst.next()) { idUtente = rst.getInt("idUtente");}
				
			} catch (SQLException e) {
				 System.out.println(" errore estrapolazione id utente");
		    	   e.printStackTrace();
			}
	         	     
	         
/* con id utente si va ad fare un INSERT nella tabella Ente   */                 
	    String queryIns = " INSERT INTO ente ( nomeEnte, stato, utenteFK) "
	    		         + " VALUES ('"+ ente.getNomeEnte() + " ','attivo','" + idUtente + "');";
	    Statement st=con.createStatement();
	    st.executeUpdate(queryIns);} 
		
		catch(SQLException e){   
	    	   System.out.println(" errore aggiungi ente");
	    	   e.printStackTrace();
	    	   }  
	}

    public static Object logIn(String email, String password) throws SQLException {
    
    	Connection con = connessione();
	    Statement st=con.createStatement();
	    Object utente = null;
	    
	    try {
	    	String query = "Select * from utente where email='" + email + "' AND password='" + password + "'";
	    	ResultSet rst = st.executeQuery(query);
	    	while(rst.next()) {
	    		if(rst.getString("ruolo").equals("ente")) {
	    			Ente ente = new Ente(rst.getString("email"), rst.getString("password"),
	    					    rst.getString("idUtente"), rst.getString("nome"), rst.getString("cognome"));
	    			
	    			query = "Select nomeEnte from ente where utenteFK='" + ente.getNomeEnte()+"'";
	    			rst = st.executeQuery(query);
	    			
	    			while(rst.next()) {
	    				ente.setNomeEnte(rst.getString("nomeEnte"));
	    			}
	    			utente = ente;
	    			
	    		}
	    		else {            
	    			Amministratore admin = new Amministratore(rst.getString("email"), rst.getString("password"),
    					    rst.getString("nome"), rst.getString("cognome"));
	    			utente = admin;
	    			
	    		}
	    	}
	    	
			
		} 	catch(SQLException e){
	    	   
	    	   System.out.println(" errore log in");
	    	   e.printStackTrace();
	    	   };
	    	   	    
	    return utente;
    	
    	
    }

}
