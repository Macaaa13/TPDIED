package died.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	private static final String controlador = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/trabajopracticodied";
	private static final String usuario = "root";
	private static final String clave = "root";
		
	private Properties pr;

	public Conexion() {
		pr = new Properties();
		pr.put("user", "root");
		pr.put("password",clave);
	}

	public Connection conectar() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url,usuario,clave);			
			System.out.println("Conexión realizada");
			c = DriverManager.getConnection(url,this.pr);			
			//System.out.println("Conexión realizada");

			} catch(SQLException e) {
				System.out.println("Error en la conexión");
				e.printStackTrace();
			} 
		
		return c;
	}
}