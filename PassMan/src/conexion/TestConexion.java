package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM user");
            
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                
                System.out.println( username + " - " + password );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (rs!= null) {
                    rs.close();
                }
                
                if (stm != null) {
                    stm.close();
                }
                
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
}
