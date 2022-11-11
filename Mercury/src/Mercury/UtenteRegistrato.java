package Mercury;

public class UtenteRegistrato {
	private String email;
	private Zona zona;
	private String tipo;
	private String cadenza;
	
	public UtenteRegistrato(String email,Zona zona, String tipo,String cadenza) {
		this.email = email;
		this.zona = zona;
		this.tipo = tipo;
		this.cadenza = cadenza;
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
	public String getCadenza() {
		return cadenza;
	}
	public void setCadenza(String cadenza) {
		this.cadenza = cadenza;
	}
	
	
	

}
