package Model;

public class NewsLetter {
private Evento[] eventi;
private UtenteRegistrato utente;

public NewsLetter(Evento[] eventi, UtenteRegistrato utente) {
	this.eventi = eventi;
	this.utente = utente;
}

public UtenteRegistrato getUtente() {
	return utente;
}

public void setUtente(UtenteRegistrato utente) {
	this.utente = utente;
}

public Evento[] getEventi() {
 return eventi;
}

public void setEventi(Evento[] eventi) {
	this.eventi = eventi;
}

}
