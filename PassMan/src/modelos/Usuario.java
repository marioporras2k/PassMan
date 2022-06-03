package modelos;

public class Usuario {
	
	private String email;
	private String password;
	
	public Usuario(String username, String password) {
		super();
		this.email = username;
		this.password = password;
	}
	
	
	public Usuario() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
