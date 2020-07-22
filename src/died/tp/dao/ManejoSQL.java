package died.tp.dao;

import java.sql.*;
import java.util.*;

public class ManejoSQL {

	private String crearTablaCamion = 	
			"CREATE TABLE Camion("
			+ "ID_CAMION INT PRIMARY KEY AUTO_INCREMENT,"
			+ "PATENTE VARCHAR(8),"
			+ "MARCA VARCHAR(15),"
			+ "MODELO VARCHAR(25),"
			+ "KMRECORRIDOS INT,"
			+ "COSTOHORA DOUBLE,"
			+ "COSTOKM DOUBLE,"
			+ "FECHACOMPRA DATE"
			+ ")";
	
	private String crearTablaInsumo =
			"CREATE TABLE Insumo("
			+ "ID_INSUMO INT PRIMARY KEY AUTO_INCREMENT,"
			+ "UNIDAD VARCHAR(10),"
			+ "COSTOUNIDAD DOUBLE,"
			+ "PESO DOUBLE,"
			+ "DENSIDAD DOUBLE"
			+ ")";
	
	private String crearTablaPlanta = 
			"CREATE TABLE Planta("
			+ "ID_PLANTA INT PRIMARY KEY AUTO_INCREMENT,"
			+ "NOMBREPLANTA VARCHAR(15)"
			+ ")";
	private String crearTablaOrdenPedido = 
			"CREATE TABLE OrdenPedido("
			+ "ID_ORDENPEDIDO INT PRIMARY KEY AUTO_INCREMENT,"
			+ "PLANTAORIGEN INT REFERENCES Planta(id_planta),"
			+ "PLANTADESTINO INT REFERENCES Planta(id_planta),"
			+ "ESTADO ENUM ('creada','procesada','entregada','cancelado'),"
			+ "CAMIONASIGNADO INT REFERENCES Camion(id_camion)"
			+ ")";
	
	public ManejoSQL() {
		
	}
	
	public void crearTablas() throws SQLException {
		
		Conexion conexion = new Conexion();

		Connection con = null;

		PreparedStatement stmt = null;
		try {
			  con = conexion.conectar();
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
