package died.tp.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ManejoSQL {

	private String crearTablaCamion = 	
			"CREATE TABLE Camion("
			+ "id_camion INT PRIMARY KEY AUTO_INCREMENT,"
			+ "patente VARCHAR(8),"
			+ "marca VARCHAR(15),"
			+ "modelo VARCHAR(25),"
			+ "kmRecorrios INT,"
			+ "costoHora DOUBLE,"
			+ "costoKM DOUBLE,"
			+ "fechaCompra DATE"
			+ ")";

	private String crearTablaInsumo =
			"CREATE TABLE Insumo("
			+ "id_insumo INT PRIMARY KEY AUTO_INCREMENT,"
			+ "unidadMedida VARCHAR(10),"
			+ "costoUnidad DOUBLE,"
			+ "peso DOUBLE,"
			+ "densidad DOUBLE"
			+ ")";

	private String crearTablaPlanta = 
			"CREATE TABLE Planta("
			+ "id_planta INT PRIMARY KEY AUTO_INCREMENT,"
			+ "nombrePlanta VARCHAR(15)"
			+ ")";
	private String crearTablaOrdenPedido = 
			"CREATE TABLE OrdenPedido("
			+ "id_ordenPedido INT PRIMARY KEY AUTO_INCREMENT,"
			+ "plantaOrigen INT REFERENCES Planta(id_planta),"
			+ "plantaDestino INT REFERENCES Planta(id_planta),"
			+ "estado ENUM ('creada','procesada','entregada','cancelado'),"
			+ "camionAsignado INT REFERENCES Camion(id_camion)"
			+ ")";

	public ManejoSQL() {

	}


	public void crearTablas() throws SQLException {
		Connection con = null;

		String sURL = "jdbc:mysql://localhost:3306/trabajopracticodied";

		Properties p = new Properties();
		p.put("user", "root");
		p.put("password","onestepcloser01");

		con = DriverManager.getConnection(sURL,p);
		PreparedStatement stmt = null;
		try {
			  stmt = con.prepareStatement(crearTablaCamion);  
			  stmt.execute(); 
			  stmt = con.prepareStatement(crearTablaInsumo);
			  stmt.execute();
			  stmt = con.prepareStatement(crearTablaPlanta);
			  stmt.execute();
			  stmt = con.prepareStatement(crearTablaOrdenPedido);
			  stmt.execute();
			  stmt.close();	           
			} catch (SQLException sqle) { 
			  System.out.println("Error en la ejecución: " 
			    + sqle.getErrorCode() + " " + sqle.getMessage());    
			}

	}
	
}
