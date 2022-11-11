package Mercury;

public class Ente {

	private String email;
	private String password;
	private String nomeEnte;
	
	public Ente(String email,String password, String nomeEnte) {
		this.password = password;
		this.nomeEnte = nomeEnte;
		this.email = email;
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
	
	
	
	

}
