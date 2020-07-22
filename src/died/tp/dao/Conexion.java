package died.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String url = "jdbc:mysql://localhost:3306/trabajopracticodied?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String clave = "root";

	public static Connection conectar() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url,usuario,clave);			
			//System.out.println("Conexión realizada");

			} catch(SQLException e) {
				System.out.println("Error en la conexión");
				e.printStackTrace();
			} 
		
		return c;
	}
	
	public static Connection getConection() {
		return conectar();
	}
}