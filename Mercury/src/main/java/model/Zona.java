package model;

public class Zona {
	private int regione;
	private int provincia;
	private int comune;
	
	public Zona(int regione, int provincia, int comune) {
		this.regione = regione;
		this.provincia = provincia;
		this.comune = comune;
	}
	public int getRegione() {
		return regione;
	}
	public void setRegione(int regione) {
		this.regione = regione;
	}
	public int getProvincia() {
		return provincia;
	}
	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}
	public int getComune() {
		return comune;
	}
	public void setComune(int comune) {
		this.comune = comune;
	}
	
	

}
