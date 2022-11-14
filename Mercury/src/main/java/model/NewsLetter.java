package model;

import java.util.ArrayList;

public class NewsLetter {
private ArrayList<Evento> eventi = new ArrayList<Evento>();
private UtenteRegistrato utente;



public NewsLetter(ArrayList<Evento> eventi, UtenteRegistrato utente) {
	super();
	this.eventi = eventi;
	this.utente = utente;
}

public UtenteRegistrato getUtente() {
	return utente;
}

public void setUtente(UtenteRegistrato utente) {
	this.utente = utente;
}

public ArrayList<Evento> getEventi() {
	return eventi;
}

public void setEventi(ArrayList<Evento> eventi) {
	this.eventi = eventi;
}

}
