package died.tp.dao;

import java.sql.*;
import java.util.*;

import died.tp.dominio.Ruta;

public class RutaDao {

	private static String insert = "INSERT INTO RUTA (plantaOrigen, plantaDestino, distancia, horasEstimadas, pesoMaximo)" + "VALUES (?,?,?,?,?)";
	
	public void agregarRuta(Ruta r) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			pr = con.prepareStatement(insert);
			pr.setInt(1, r.getOrigen().getId());
			pr.setInt(2, r.getDestino().getId());
			pr.setInt(3, r.getDistancia());
			pr.setDouble(4, r.getDuracionEstimada());
			pr.setInt(5, r.getPesoMax());
			pr.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			pr.close();
			con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
