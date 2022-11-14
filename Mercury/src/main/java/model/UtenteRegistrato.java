package model;

import java.time.LocalDate;

public class UtenteRegistrato {
	private String email;
	private Zona zona;
	private String tipo;
	private String cadenza;
	private LocalDate ultimoEmail;
	

	public UtenteRegistrato(String email, Zona zona, String tipo, String cadenza, LocalDate ultimoEmail) {
		this.email = email;
		this.zona = zona;
		this.tipo = tipo;
		this.cadenza = cadenza;
		this.ultimoEmail = ultimoEmail;
	}
	
	public LocalDate getUltimoEmail() {
		return ultimoEmail;
	}
	public void setUltimoEmail(LocalDate ultimoEmail) {
		this.ultimoEmail = ultimoEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getCadenza() {
		int a = 0;
		if(cadenza.equals("mensile")) {
			a=30;
		} else 	if(cadenza.equals("settimanale")) {
			a=7;
		}
		return a;
	}
	public void setCadenza(String cadenza) {
		this.cadenza = cadenza;
	}
	
	
	

}
