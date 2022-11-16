package model;

public class Main {
	
	public static void main(String[] args) {
                Utilities.connessione();
		Zona z = new Zona(1,1,1);
		LocalDate day = LocalDate.of(2020,06,07);
		UtenteRegistrato u = new UtenteRegistrato("emai@email.com", z, "fiera", "mensile", day);
		
		NewsLetter newsLetter = Utilities.news(u);
		
		Utilities.email(newsLetter);
		
		
	}
		
}
