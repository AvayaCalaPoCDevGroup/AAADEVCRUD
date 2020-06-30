package service.AAADEVCRUD.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

//import com.avaya.collaboration.util.logger.Logger;

public class conexion {
//	private final Logger logger;
	
	public conexion(){
//		logger = Logger.getLogger(getClass());
	}
	
	public Connection conn() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		/*CONEXIÓN A POSTGRES*/
		String JDBC_DRIVER = "org.postgresql.Driver";
        String url = Constants.BASE_URL;
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");

        Connection conn = null;
        /*SELECCION DEL DRIVER POSTGRES*/
        try {
            Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
            Driver driver = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver(driver);

            /*LA VARIABLE CONN REALIZA LA CONEXION A LA BD*/
		
            conn = DriverManager.getConnection(url, props);
           

		}catch (Exception ex) {
//			logger.info("conexion.java  error: "+ex.getMessage());
	    }
        return conn;
	}

}
