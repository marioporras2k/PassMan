package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import conexion.Conexion;
import modelos.Usuario;

abstract class AbstractDAO {
	
	protected Connection cn;
	
	public AbstractDAO() {
		conectar();
	}
	
	protected Connection conectar() {
		try {
			Class.forName(Conexion.controlador);
			cn = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cn;
	
		
			
			
	}

}