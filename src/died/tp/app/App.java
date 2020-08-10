package died.tp.app;

import java.sql.SQLException;


import died.tp.dao.Conexion;

public class App {

	public static void main(String[] args) throws SQLException {
		
		Conexion.getConexion();
		
	}

}
