package model;

import java.time.LocalDate;
import java.util.Date;

public class Evento {
private String nome;
private String descrizione;
private Zona zona;
private String tipo;
private LocalDate dataInizio;
private LocalDate dataFine;
private Ente ente;

public Evento(String nome, String descrizione, Zona zona, String tipo, LocalDate dataInizio2, LocalDate dataFine2, Ente ente) {
	this.nome = nome;
	this.descrizione = descrizione;
	this.zona = zona;
	this.tipo = tipo;
	this.dataInizio = dataInizio2;
	this.dataFine = dataFine2;
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

public LocalDate getDataInizio() {
	return dataInizio;
}

public void setDataInizio(LocalDate dataInizio) {
	this.dataInizio =  dataInizio;
}

public LocalDate getDataFine() {
	return (LocalDate) dataFine;
}

public void setDataFine(LocalDate dataFine) {
	this.dataFine = dataFine;
}

public Ente getEnte() {
	return ente;
}

public void setEnte(Ente ente) {
	this.ente = ente;
} 



}
