package service.AAADEVCRUD.NVP.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import service.AAADEVCRUD.util.Constants;

/**
 *
 * @author umansilla
 */
public class BaseDatosPG {

    String urlDataBase = Constants.BASE_URL;
    Connection conn = null;

    public BaseDatosPG(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.toString());
        }
        try {
            conn = DriverManager.getConnection(urlDataBase, Constants.JDBC_USER, Constants.JDBC_PASSWORD);
        } catch (SQLException ex) {
           System.out.println("Error: " + ex.toString());
        }
    }
    
    public Connection getconnection(){
        return this.conn;
    }

    public void desconectarBD() {
        System.out.println("Cerrar conexión a Base de Datos");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    }
}
