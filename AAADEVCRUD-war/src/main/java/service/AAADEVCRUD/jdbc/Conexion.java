package service.AAADEVCRUD.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;



import com.avaya.collaboration.call.Call;
import com.avaya.collaboration.util.logger.Logger;

import service.AAADEVCRUD.util.Constants;

public class Conexion {
	private static Logger logger = Logger.getLogger(Conexion.class);
	public Connection conn = null;
	
	public Connection getConexion(Call call){
		String JDBC_DRIVER = "org.postgresql.Driver";
        String url = Constants.BASE_URL;
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");
        
        try {
            Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
            Driver driver = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(url, props);
        } catch (Exception e){
        	logger.info("Error al crear Conexion " + e.toString());
        }
        return conn;
	}
}
