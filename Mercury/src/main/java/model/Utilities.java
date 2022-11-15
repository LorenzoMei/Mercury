package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilities {
	private static Connection con;

	public static void connessione() {
			
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	String url ="jdbc:mysql://127.0.0.1/mercurydb";
	    	con = DriverManager.getConnection(url, "root", "admin");
		}
	    catch(ClassNotFoundException e) {
	    	System.out.println("errore");
	    	e.printStackTrace();
	    }
	    catch(SQLException e) {
	    	System.out.println("Errore get connection");
	    	e.printStackTrace();
	    }
		
	}

	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Errore chiusura connessione");
			e.printStackTrace();
		}
	}
	
	public static void aggiungiEnte(Ente ente) {
		Utilities.connessione();

		Statement st = null;
/* inserimento dati dell'ente nella tabella utente */	    
		try {
			st = con.createStatement();
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
		
		Utilities.close();
	}

	public static List<Evento> listaEventi(){
		Utilities.connessione();

		List<Evento> listaEventi = null;
		try {

			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM evento join zona on zona.idZona = evento.zonaFk join ente on ente.idEnte "
					+ "= evento.enteFk join utente on ente.utenteFk=utente.idUtente WHERE stato = 'attivo'");
			
			listaEventi = new ArrayList<Evento>();
			
			while(rst.next()) {
				String nome = rst.getString("nome");
				String descrizione = rst.getString("descrizione");
				String tipo = rst.getString("tipo");		
				
				LocalDate dataInizio = LocalDate.parse(rst.getString("dataInizio"));
				LocalDate dataFine = LocalDate.parse(rst.getString("dataFine"));
				
				int regione = rst.getInt("regionefk");
				int provincia = rst.getInt("provinciafk");
				int comune = rst.getInt("comunefk");
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
		Utilities.close();
		return listaEventi;
	}

	public static void iscrizioneNews(UtenteRegistrato utenteRegistrato) {
		Utilities.connessione();

		try {
			Statement st = con.createStatement();
			int idZona = -1;
			
			int regione = utenteRegistrato.getZona().getRegione();
			int provincia = utenteRegistrato.getZona().getProvincia();
			int comune = utenteRegistrato.getZona().getComune();
			LocalDate day = utenteRegistrato.getUltimoEmail();
			ResultSet rst = st.executeQuery("SELECT idZona FROM zona WHERE regionefk =" + Integer.toString(regione) + " AND "
					+ "provinciafk =" + Integer.toString(provincia) + " AND comunefk=" + Integer.toString(comune) );
			
			
			if(rst.next()) {
				idZona = rst.getInt("idZona");
			}
								
			int result = st.executeUpdate("INSERT INTO utenteregistrato (email, tipo, cadenza, zonaFk, data) VALUES ('" + utenteRegistrato.getEmail() + "', '" 
					+ utenteRegistrato.getTipo() + "', '" + utenteRegistrato.getCadenza() + "', " 
					+ Integer.toString(idZona) + ", '"+ day.toString() + "')" );
			
			if(result != 1) {
				System.out.println("Iscrizione newsletters non andata a buon fine");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilities.close();
	}

	public static void bannaEnte(String email) {
		Utilities.connessione();

		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT idEnte FROM ente join utente on "
					+ "ente.utenteFk = utente.idUtente WHERE email = '" + email +"'");
			
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
		Utilities.close();
		
	}
	
	public static Object login(String email, String password) {
		Utilities.connessione();
	    
	    Object utente = null;
	    
	    try {
	    	Statement st = con.createStatement();
	    	String query = "Select * from utente where email='" + email + "' AND password='" + password + "'";
	    	ResultSet rst = st.executeQuery(query);
	    	
	    	if(rst.next()) {
	    		if(rst.getString("ruolo").equals("ente")) {
	    			String cognomeResponsabile = rst.getString("cognome");
	    			String nomeResponsabile = rst.getString("nome");
	    			
	    			String query2 = "Select nomeEnte from ente where utenteFk = " + Integer.toString(rst.getInt("idUtente"));
	    			Statement st2 = con.createStatement();
	    			ResultSet rst2 = st2.executeQuery(query2);
	    			String nomeEnte = "";
	    	
	    			if(rst2.next()) {
	    				nomeEnte = rst2.getString("nomeEnte");
	    			}
	    			
	    			Ente ente = new Ente(email, password, nomeEnte, nomeResponsabile, cognomeResponsabile);
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
	    
	    Utilities.close();
	    return utente;
    	
    	
    }
	
	public static List<Ente> listaEnti(){
		Utilities.connessione();

		List<Ente> listaEnti = null;
		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM ente join utente on ente.utenteFk = utente.idUtente WHERE stato = 'attivo'");
			
			listaEnti = new ArrayList<Ente>();
			
			while(rst.next()) {
				String email = rst.getString("email");			
				String nomeEnte = rst.getString("nomeEnte");
				String nomeResponsabile = rst.getString("nome");
				String cognomeResponsabile = rst.getString("cognome");
				Ente ente = new Ente(email, nomeEnte, nomeResponsabile, cognomeResponsabile);
				
				listaEnti.add(ente);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilities.close();
		return listaEnti;
	}
	
	public static void aggiungiEvento(Evento evento) {
		Utilities.connessione();

		try {
			Statement st = con.createStatement();
			int idZona = 0;
			int idEnte = 0;
			
			
		//ESTRAPOLA idZona	
			try {
	       ResultSet rst = st.executeQuery("SELECT idZona FROM zona WHERE regionefk =" + evento.getZona().getRegione() + " AND "
						+ "provinciafk =" + evento.getZona().getProvincia() + " AND comunefk=" + evento.getZona().getComune() );	
			
			while(rst.next()) { idZona = rst.getInt("idZona");}

			
			
		} catch (SQLException e) {
			System.out.println("Errore estrapolazione idzona");
			e.printStackTrace();
		}
			//ESTRAPOLA idEnte
			try {
	 	        ResultSet rst = st.executeQuery("SELECT idEnte from ente where nomeEnte ='" + evento.getEnte().getNomeEnte()+ "'");
	 	        
	 	       while(rst.next()) { idEnte = rst.getInt("idEnte");}
				
			} catch (SQLException e) {
				 System.out.println(" errore estrapolazione id utente");
		    	   e.printStackTrace();
			}
			
System.out.println(idEnte);

			//inserimento evento nella tabella  
			String queryIns = "INSERT into evento (nome, descrizione, tipo, dataInizio, dataFine, zonaFK, enteFK) " + 
			                   "VALUES ('" + evento.getNome() + "','" + evento.getDescrizione() + "','" + evento.getTipo() 
			                   + "','"+ evento.getDataInizio() + "','" + evento.getDataFine() + "',3 , 1);";
			st.executeUpdate(queryIns);
			
		} catch (SQLException e) {
			System.out.println("Errore aggiungi evento");
			e.printStackTrace();
		}
		Utilities.close();
	}

	public static NewsLetter news(UtenteRegistrato u) {
		 Utilities.connessione();

		 ArrayList<Evento> eventi = new ArrayList<Evento>();
		 LocalDate dataUltimoEmail = u.getUltimoEmail();
	     LocalDate dataOggi = LocalDate.now();          
	
	/* si controlla se la data dell'utlimo email con la data attuale sia superiore alla cadenza indicata dall'utente*/ 
	     
	     if(ChronoUnit.DAYS.between(dataUltimoEmail , dataOggi) >  u.getCadenza()) {

	     /* Estrapolare i dati degli evento che ci servono dal database facendo join tra evento, zona e ente
	  	   e selezionare solo quelli dopo la data odierna */	
	    	 
	    	 if(u.getTipo().equals("") && u.getZona().getComune()==0 && u.getZona().getProvincia()==0 && u.getZona().getRegione()==0) {
	    		
	    		 try {
	    				
						Statement st = con.createStatement();
						String query = "select * from mercurydb.evento join mercurydb.zona on evento.zonaFK=idZona join mercurydb.ente on evento.enteFK=ente.idEnte "
								     + "where (dataInizio > CAST( curdate() AS DATE) and dataInizio < CAST( curdate()+7 AS DATE))";
						ResultSet rst = st.executeQuery(query);
	
						while(rst.next()) {
							Zona z = new Zona(rst.getInt("regionefk"), rst.getInt("provinciafk"),rst.getInt("comunefk"));
							Ente e = new Ente("privato", "privato", rst.getString("nomeEnte"),"privato", "privato");
							Evento evento = new Evento(rst.getString("nome"), rst.getString("descrizione"), z, "" + rst.getString("tipo"), LocalDate.parse( rst.getString("dataInizio")), LocalDate.parse( rst.getString("dataFine")), e);
					        eventi.add(evento);
						                 }
					 }  catch (SQLException e) {
						 System.out.println(" errore newsletter");
				    	   e.printStackTrace();
					                           }
	  	                             
	    		 
	    	 } else {
	    		

				try {
				
						Statement st = con.createStatement();
						String query = "select * from mercurydb.evento join mercurydb.zona on evento.zonaFK=idZona join mercurydb.ente on evento.enteFK=ente.idEnte "
								     + "where (dataInizio > CAST( curdate() AS DATE))";
						ResultSet rst = st.executeQuery(query);
	
						while(rst.next()) {
							Zona z = new Zona(rst.getInt("regionefk"), rst.getInt("provinciafk"),rst.getInt("comunefk"));
							Ente e = new Ente("privato", "privato", rst.getString("nomeEnte"),"privato", "privato");
							Evento evento = new Evento(rst.getString("nome"), rst.getString("descrizione"), z, "" + rst.getString("tipo"), LocalDate.parse( rst.getString("dataInizio")), LocalDate.parse( rst.getString("dataFine")), e);
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
		} 	
		//filtra zona in base all'utente		
	
			for( int k = 0; k < eventi.size(); k++) {	
				
					if(u.getZona().getComune() != 0) {
						if(eventi.get(k).getZona().getComune() != u.getZona().getComune()) {
							eventi.remove(k);
							k--;
						} 
					} else if(u.getZona().getProvincia() != 0 ) {
						if(eventi.get(k).getZona().getProvincia() != u.getZona().getProvincia()) {
							eventi.remove(k);
							k--;
					    } 
					} else if(u.getZona().getRegione() != 0) {
						if(eventi.get(k).getZona().getRegione() != u.getZona().getRegione()) {
							eventi.remove(k);
							k--;
					    } 
					} 
				
			} }
			
			NewsLetter lettera = new NewsLetter(eventi,u);
			Utilities.close();
			return lettera;	
	}
	
	public static ArrayList<String> getComune(String provincia) {
		Utilities.connessione();

		ArrayList<String> listaComuni = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM comuni join provincia on provincia.idProvincia = comuni.provinciafk"
					+ " WHERE nomeProvincia = '" + provincia + "'");
			
			listaComuni = new ArrayList<String>();
			
			while(rst.next()) {
				String comune = rst.getString("comune");
				
				listaComuni.add(comune);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		Utilities.close();
		return listaComuni;
	}
	
	public static ArrayList<String> getRegione() {
		Utilities.connessione();

		ArrayList<String> regioneList = new ArrayList<String>();
		
		try {
			Statement st = con.createStatement();
			String query = "select nomeRegione from regioni";
		ResultSet rst = st.executeQuery(query);
		
		while(rst.next()) {
			regioneList.add(rst.getString("nomeRegione"));
		     }
		}catch (SQLException e) {
			 System.out.println(" errore estrapolazione regione");
	    	   e.printStackTrace();
		}
		Utilities.close();
		return regioneList;		
	}
	
	public static int getIdRegione(String regione) {
		Utilities.connessione();
		
		int id=-1;
		
		try {
			Statement st = con.createStatement();
			String query = "SELECT idRegioni FROM regioni WHERE nomeRegione='" + regione +"'";
			ResultSet rst = st.executeQuery(query);
		
			while(rst.next()) {
				id = rst.getInt("idRegioni");
			}
		}catch (SQLException e) {
			 System.out.println(" errore estrapolazione regione");
	    	   e.printStackTrace();
		}
		
		Utilities.close();
		return id;	
	}
	
	public static int getIdProvinciaFromRegione(int idRegione, String provincia) {
		Utilities.connessione();
		
		int id=-1;
		
		try {
			Statement st = con.createStatement();
			String query = "SELECT idProvincia FROM provincia WHERE nomeProvincia='" + provincia +
					"' AND regionefk = " + Integer.toString(idRegione);
			ResultSet rst = st.executeQuery(query);
		
			while(rst.next()) {
				id = rst.getInt("idProvincia");
			}
		}catch (SQLException e) {
			 System.out.println(" errore estrapolazione regione");
	    	   e.printStackTrace();
		}
		Utilities.close();
		return id;	
	}
	
	public static int getIdComuneFromProvincia(int idProvincia, String comune) {
		Utilities.connessione();
		
		int id=-1;
		
		try {
			Statement st = con.createStatement();
			String query = "SELECT idComuni FROM comuni WHERE comune='" + comune +
					"' AND provinciafk = " + Integer.toString(idProvincia);
			ResultSet rst = st.executeQuery(query);
		
			while(rst.next()) {
				id = rst.getInt("idComuni");
			}
		}catch (SQLException e) {
			 System.out.println(" errore estrapolazione regione");
	    	   e.printStackTrace();
		}
		Utilities.close();
		return id;	
	}
	
	public static ArrayList<String> getProvincia(String regione) {
		Utilities.connessione();

		ArrayList<String> provinciaLista = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT * from provincia join regioni on provincia.regionefk = regioni.idRegioni WHERE "
					+ "nomeRegione = '"+ regione + "'");
						
			while(rst.next()) {
				provinciaLista.add(rst.getString("nomeProvincia"));}
					
		} catch (Exception e) {
			System.out.println("Errore getProvincia");
			e.printStackTrace();
		}
		
		Utilities.close();
		return provinciaLista;
	}
	
	public static ArrayList<Evento> filtraEventiPerZona(String filtroRegione, String filtroProvincia, String filtroComune, List<Evento> listaEventi){
		Utilities.connessione();

		ArrayList<Evento> listaEventiFiltrata = new ArrayList<Evento>();
		
		for(int i = 0; i < listaEventi.size(); i++) {
			try {
				Statement st = con.createStatement();
				ResultSet rst = st.executeQuery("SELECT * FROM comuni join provincia on comuni.provinciafk = provincia.idProvincia join "
						+ "regioni on provincia.regionefk = regioni.idRegioni WHERE idRegioni = " + Integer.toString(listaEventi.get(i).getZona().getRegione())
						+ " AND idProvincia = " + Integer.toString(listaEventi.get(i).getZona().getProvincia()) + " AND "
						+ "idComuni = " + Integer.toString(listaEventi.get(i).getZona().getComune()));
				
				while(rst.next()) {
					if(filtroRegione != null) {
						if(filtroProvincia != null) {
							if(filtroComune != null) {
								if(rst.getString("nomeRegione").equals(filtroRegione) && rst.getString("nomeProvincia").equals(filtroProvincia) &&
										rst.getString("comune").equals(filtroComune)) {
									listaEventiFiltrata.add(listaEventi.get(i));
								}
							}
							else {
								if(rst.getString("nomeRegione").equals(filtroRegione) && rst.getString("nomeProvincia").equals(filtroProvincia)) {
									listaEventiFiltrata.add(listaEventi.get(i));
								}
							}
						}
						else {
							if(rst.getString("nomeRegione").equals(filtroRegione)) {
								listaEventiFiltrata.add(listaEventi.get(i));
							}
						}
					}
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		Utilities.close();
		return listaEventiFiltrata;
	}
	
	public static ArrayList<String> getTipo() {
		Utilities.connessione();
		ArrayList<String> listaTipo = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rst = st.executeQuery("SELECT distinct tipo from evento");
						
			while(rst.next()) {
				listaTipo.add(rst.getString("tipo"));}
					
		} catch (Exception e) {
			System.out.println("Errore getTipo");
			e.printStackTrace();
		}
		
		Utilities.close();
		return listaTipo;
	}
}
