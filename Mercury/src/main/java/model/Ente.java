package model;

public class Ente {

	private String email;
	private String password;
	private String nomeEnte;
	private String nomeResponsabile;
	private String cognomeResponsabile;
	
	public Ente(String nomeEnte, String email) {
		this.nomeEnte = nomeEnte;
		this.email = email;
	}
	
	public Ente(String email,String password, String nomeEnte, String nomeResponsabile, String cognomeResponsabile ) {
		this.password = password;
		this.nomeEnte = nomeEnte;
		this.email = email;
		this.nomeResponsabile = nomeResponsabile;
		this.cognomeResponsabile =cognomeResponsabile;
	}
	
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeEnte() {
		return nomeEnte;
	}

	public void setNomeEnte(String nomeEnte) {
		this.nomeEnte = nomeEnte;
	}


	public String getNomeResponsabile() {
		return nomeResponsabile;
	}

	public void setNomeResponsabile(String nomeResponsabile) {
		this.nomeResponsabile = nomeResponsabile;
	}



	public String getCognomeResponsabile() {
		return cognomeResponsabile;
	}



	public void setCognomeResponsabile(String cognomeResponsabile) {
		this.cognomeResponsabile = cognomeResponsabile;
	}
	

}
