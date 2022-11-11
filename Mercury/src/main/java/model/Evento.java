package model;

import java.util.Date;

public class Evento {
private String nome;
private String descrizione;
private Zona zona;
private String tipo;
private Date dataInizio;
private Date dataFine;
private Ente ente;

public Evento(String nome, String descrizione, Zona zona, String tipo, Date dataInizio, Date dataFine, Ente ente) {
	this.nome = nome;
	this.descrizione = descrizione;
	this.zona = zona;
	this.tipo = tipo;
	this.dataInizio = dataInizio;
	this.dataFine = dataFine;
	this.ente = ente;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getDescrizione() {
	return descrizione;
}

public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}

public Zona getZona() {
	return zona;
}

public void setZona(Zona zona) {
	this.zona = zona;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public Date getDataInizio() {
	return dataInizio;
}

public void setDataInizio(Date dataInizio) {
	this.dataInizio = dataInizio;
}

public Date getDataFine() {
	return dataFine;
}

public void setDataFine(Date dataFine) {
	this.dataFine = dataFine;
}

public Ente getEnte() {
	return ente;
}

public void setEnte(Ente ente) {
	this.ente = ente;
} 



}
