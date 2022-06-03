package modelos;

public class Organizador {
	private int iD;
	private String titulo;
	private String correo;
	private String username;
	private String password;
	private String sitioweb;
	private String categoria;
	
	public Organizador(int iD, String titulo, String correo, String username, String password, String sitioweb, String categoria) {
		super();
		this.iD = iD;
		this.titulo = titulo;
		this.correo = correo;
		this.username = username;
		this.password = password;
		this.sitioweb = sitioweb;
		this.categoria = categoria;
	}
	
	public Organizador() {
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return iD;
	}
	
	public void setId(int iD) {
		this.iD = iD;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSitioweb() {
		return sitioweb;
	}

	public void setSitioweb(String sitioweb) {
		this.sitioweb = sitioweb;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
