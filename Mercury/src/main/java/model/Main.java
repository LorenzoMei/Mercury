package model;

public class Main {
	
	public static void main(String[] args) {
		
		Zona zona = new Zona("Lazio", "Roma", "Roma");
		
		Utilities.connessione();
		
		UtenteRegistrato utenteRegistrato = new UtenteRegistrato("bbb", zona, "boh", "settimanale");
		Utilities.iscrizioneNews(utenteRegistrato);
		
		Utilities.close();
	}
		
}
