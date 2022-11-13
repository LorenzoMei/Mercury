package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilities {
	private static Connection con;

	public static Connection connessione() {
		
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	String url ="jdbc:mysql://127.0.0.1/mercurydb";
	    	con = DriverManager.getConnection(url, "root", "intecs");
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
	    
	    st.executeUpdate(queryIns);
	    } 
		
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

	public static List<Evento> listaEventi(){
		List<Evento> listaEventi = null;
		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM evento join zona on"
					+ " zona.idZona = evento.zonaFk join ente on ente.idEnte = evento.enteFk");
			
			listaEventi = new ArrayList<Evento>();
			
			while(rst.next()) {
				String nome = rst.getString("nome");
				String descrizione = rst.getString("descrizione");
				String tipo = rst.getString("tipo");				
				Date dataInizio = rst.getDate("dataInizio");
				Date dataFine = rst.getDate("dataFine");
				
				String regione = rst.getString("regione");
				String provincia = rst.getString("provincia");
				String comune = rst.getString("comune");
				Zona zona = new Zona(regione, provincia, comune);
				
				String email = rst.getString("email");
				String nomeEnte = rst.getString("nomeEnte");
				
				Ente ente = new Ente(email, nomeEnte);
				Evento evento = new Evento(nome, descrizione, zona, tipo, dataInizio, dataFine, ente);
				
				listaEventi.add(evento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEventi;
	}
	
	public static void iscrizioneNews(UtenteRegistrato utenteRegistrato) {
		try {
			Statement st = con.createStatement();
			int idZona = -1;
			
			String regione = utenteRegistrato.getZona().getRegione();
			String provincia = utenteRegistrato.getZona().getProvincia();
			String comune = utenteRegistrato.getZona().getComune();
			
			ResultSet rst = st.executeQuery("SELECT idZona FROM zona WHERE regione = '" + regione + "' AND "
					+ "provincia = '" + provincia + "' AND " + "comune = '" + comune + "'");
			
			
			if(rst.next()) {
				idZona = rst.getInt("idZona");
			}
			
			int result = st.executeUpdate("INSERT INTO utenteregistrato (email, tipo, cadenza, zonaFk) VALUES ('" + utenteRegistrato.getEmail() + "', '" 
					+ utenteRegistrato.getTipo() + "', '" + utenteRegistrato.getCadenza() + "', " 
					+ Integer.toString(idZona) + ")" );
			
			if(result != 1) {
				System.out.println("Iscrizione newsletters non andata a buon fine");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bannaEnte(Ente ente) {
		try {
			Statement st = con.createStatement();
			
			ResultSet rst = st.executeQuery("SELECT idEnte FROM ente join utente on "
					+ "ente.utenteFk = utente.idUtente WHERE email = '" + ente.getEmail() +"'");
			
			if(rst.next()) {
				int n = st.executeUpdate("UPDATE ente SET stato = 'bannato' WHERE idEnte = " 
						+ Integer.toString(rst.getInt("idEnte")));
				
			}
			else {
				System.out.println("Ente non esistente");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Object login(String email, String password) throws SQLException {
	    
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
	
	
	
	public static void aggiungiEvento(Evento evento) throws SQLException {
			
			Connection con = connessione();
			Statement st = con.createStatement();
			
			try {
				int idZona = 0;
				int idEnte = 0;
				
				
			//ESTRAPOLA idZona	
				try {
		
				ResultSet rst = st.executeQuery("SELECT idZona from mercurydb.zona WHERE regione='"+ evento.getZona().getRegione()+"'"+ 
								"AND provincia='"+ evento.getZona().getProvincia() +"'"+ "AND comune='"+evento.getZona().getComune()+"'");
				
				while(rst.next()) { idZona = rst.getInt("idZona");}

				
				
			} catch (SQLException e) {
				System.out.println("Errore estrapolazione idzona");
				e.printStackTrace();
			}
				//ESTRAPOLA idEnte
				try {
		 	        ResultSet rst = st.executeQuery("SELECT idEnte from ente where nomeEnte ='" + evento.getEnte().getNomeEnte() + "'");
		 	        
		 	       while(rst.next()) { idEnte = rst.getInt("idEnte");}
					
				} catch (SQLException e) {
					 System.out.println(" errore estrapolazione id utente");
			    	   e.printStackTrace();
				}
				
				
				//inserimento evento nella tabella  
				String queryIns = "INSERT into evento (nome, descrizione, tipo, dataInizio, dataFine, zonaFK, enteFK) " + "VALUES ('" + evento.getNome() + "','" + 
								   evento.getDescrizione() + "','" + evento.getTipo() + "','"+ evento.getDataInizio() + "','" +
								   evento.getDataFine() + "','"  + idZona + "','" + idEnte + "');";
				st.executeUpdate(queryIns);
				
			} catch (SQLException e) {
				System.out.println("Errore aggiungi evento");
				e.printStackTrace();
			}
		}

	public static void getData() throws SQLException {
		Connection con = connessione();
		Statement st = con.createStatement();
		try {
			ResultSet rst = st.executeQuery("SELECT dataInizio FROM evento");
			while(rst.next()) { System.out.println(rst.getDate("dataInizio"));
			}
			
		} catch (SQLException e) {
			System.out.println("ERRORE GETDATA");
			e.printStackTrace();
		}
	}
	
public static NewsLetter news(UtenteRegistrato u) {
		
	 ArrayList<Evento> eventi = new ArrayList<Evento>();
	 LocalDate dataUltimoEmail = u.getUltimoEmail();
     LocalDate dataOggi = LocalDate.now();          

/* si controlla se la data dell'utlimo email con la data attuale sia superiore alla cadenza indicata dall'utente*/ 
     
     if(ChronoUnit.DAYS.between(dataUltimoEmail , dataOggi) >  u.getCadenza()) {
    	 
/* Estrapolare i dati degli evento che ci servono dal database facendo join tra evento, zona e ente
   e selezionare solo quelli dopo la data odierna */	
			try {

					Connection con = connessione();
					Statement st = con.createStatement();
					String query = "select * from mercurydb.evento join mercurydb.zona on evento.zonaFK=idZona join mercurydb.ente on evento.enteFK=ente.idEnte "
							     + "where (dataInizio > CAST( curdate() AS DATE))";
					ResultSet rst = st.executeQuery(query);

					while(rst.next()) {
						Zona z = new Zona(""+rst.getString("regione"), ""+rst.getString("provincia"), ""+rst.getString("comune"));
						Ente e = new Ente("privato", "privato", rst.getString("nomeEnte"),"privato", "privato");
						Evento evento = new Evento(rst.getString("nome"), rst.getString("descrizione"), z, "" + rst.getString("tipo"), rst.getDate("dataInizio"), rst.getDate("dataFine"), e);
				        eventi.add(evento);
					                 }
				 }  catch (SQLException e) {
					 System.out.println(" errore newsletter");
			    	   e.printStackTrace();
				                           }
  	                             };

/* si prende ArrayList eventi e si filtra in base all'utente zona comune e provincia*/		
  	                             
for( int i = 0; i < eventi.size(); i++) {
	
// filtra tipo in base all'utente	*/
  	    	
	    if (!u.getTipo().equals("")) {	
			if(!eventi.get(i).getTipo().equals(u.getTipo())) {
				eventi.remove(i);
				i--;
			}
		}
		
// filtra zona in base all'utente		
	
		if(!u.getZona().getComune().equals("")) {
			if(!eventi.get(i).getZona().getComune().equals(u.getZona().getComune())) {
				eventi.remove(i);
				i--;
			} 
		} else if(!u.getZona().getProvincia().equals("")) {
			if(!eventi.get(i).getZona().getProvincia().equals(u.getZona().getProvincia())) {
				eventi.remove(i);
				i--;
		    } 
		} else if(!u.getZona().getRegione().equals("")) {
			if(!eventi.get(i).getZona().getRegione().equals(u.getZona().getRegione())) {
				eventi.remove(i);
				i--;
		    } 
		} 
	
 } 
			
			NewsLetter lettera = new NewsLetter(eventi,u);
			return lettera;	
}
}

