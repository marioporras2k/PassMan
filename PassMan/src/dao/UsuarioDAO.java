package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.sun.webkit.ContextMenu.ShowContext;

import utils.EmailSender;
import modelos.Usuario;
import utils.ReallyStrongSecuredPassword;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UsuarioDAO extends AbstractDAO {

	
	Statement stm;
	ResultSet rs;
	Usuario miUsuario;
	public static String user;
	public static int iD;
	Connection con = conectar();

		
	public UsuarioDAO() {
		super();
		stm = null;
		rs = null;
		miUsuario = new Usuario();
		conectar();

	}
	
	public boolean pruebaLogin(Usuario miUsuario) throws NoSuchAlgorithmException, InvalidKeySpecException {
		//String miSeudo, String miContra
		
		//Conexion miConexion = new Conexion();
		
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		user=null;
		String sql = "SELECT Password, Username, iD FROM user WHERE Email = ? ";
		try {
			ReallyStrongSecuredPassword rsp = new ReallyStrongSecuredPassword();
			ps = con.prepareStatement(sql);
			ps.setString(1, miUsuario.getEmail());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if (rsp.validatePassword(miUsuario.getPassword(), (rs.getString(1)))) {
					user = rs.getString(2);
					iD = rs.getInt(3);
					System.out.println(rs.getString(2));
					return true;
				}else {
					return false;
				}
			}
			
		
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		}
	
	public char[] generatePass() {
		int length = 18;
        String symbol = "-/.^&*_!@%=+>)"; 
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String small_letter = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 


        String finalString = cap_letter + small_letter + 
                numbers + symbol; 

        Random random = new Random(); 

        char[] password = new char[length]; 

        for (int i = 0; i < length; i++) 
        { 
            password[i] = 
                    finalString.charAt(random.nextInt(finalString.length())); 

        } 
        return password;
	}
	
	public boolean registrarUsuario(String email, String password, String username) {
		PreparedStatement pr;
		EmailSender myEmailSender = new EmailSender();
		if(password.length()>7) {
			try {
				ReallyStrongSecuredPassword rsp = new ReallyStrongSecuredPassword();
				pr = (PreparedStatement) super.cn.prepareStatement("insert into user" + "(email,password,username) values(?,?,?);");
				pr.setString(1,email);
				pr.setString(2,rsp.generateStorngPasswordHash(password));
				pr.setString(3,username);
				pr.executeUpdate();
				myEmailSender.sendAnEmail(email);
				JOptionPane.showMessageDialog(null, "Correct registration, welcome to our team!");
				return true;
			}
			catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Contraseña muy débil, introduce más caracteres");
			return false;
		}	
	}
	
	
	
	public boolean isPasswordCorrect (char[] j1,char[] j2) {
		boolean valor = false;
		int i = 0;
			if (j1.length != j2.length){
				valor = false;
			}
			else{
				while((!valor)&&(i < j1.length)){
					if (j1[i] != j2[i]){
						valor = false;
				}
			else{
				valor = true;
				}
			}
		}
		return valor;
		}
	
	public String getUser() {
		return user;
	
	}
	
}
