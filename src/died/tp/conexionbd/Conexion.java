package died.tp.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String controlador = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/trabajopracticodied";
	private static final String usuario = "root";
	private static final String clave = "root";
	static{
		try {
			Class.forName(controlador);
		} catch(ClassNotFoundException e) {
			System.out.println("error al cargar el controlador");
			e.printStackTrace();
			}
	}
	
	public Connection conectar() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url,usuario,clave);			
			System.out.println("Conexión realizada");
			
			} catch(SQLException e) {
				System.out.println("Error en la conexión");
				e.printStackTrace();
			} 
		
		return c;
	}
}
