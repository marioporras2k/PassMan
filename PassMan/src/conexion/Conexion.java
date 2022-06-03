package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Conexion {
	
	public static final String controlador = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/passman";
	public static final String usuario = "root";
	public static final String clave = "123456";
	 static {
	        try {
	            Class.forName(controlador);
	        } catch (ClassNotFoundException e) {
	            System.out.println("Error al cargar el controlador");
	            e.printStackTrace();
	        }
	    }
	    
	    public Connection conectar() {
	        Connection conexion = null;
	        
	        try {
	            conexion = DriverManager.getConnection(url, usuario, clave);
	            System.out.println("Conexión OK");

	        } catch (SQLException e) {
	            System.out.println("Error en la conexión");
	            e.printStackTrace();
	        }
	        
	        return conexion;
	    }
	    
	    public void cierraConexion() {
	    	Connection conexion = null;
	        try {
	            conexion = DriverManager.getConnection(url, usuario, clave);
	            conexion.close();
	        } catch (SQLException sqle) {
	           
	            //Logger.getLogger(ConexionDAO.class.getName()).log(Level.SEVERE, null, sqle);
	        }
	    }
}