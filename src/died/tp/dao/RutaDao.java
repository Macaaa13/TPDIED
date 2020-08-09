package died.tp.dao;

import java.sql.*;
import java.util.*;

import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;
import died.tp.grafos.Arista;

public class RutaDao {

	private static String insert = "INSERT INTO RUTA (plantaOrigen, plantaDestino, distancia, horasEstimadas, pesoMaximo)" + "VALUES (?,?,?,?,?)";
	
	public void agregarRuta(Arista<Planta> a) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			pr = con.prepareStatement(insert);
			pr.setInt(1, a.getInicio().getValor().getId());
			pr.setInt(2, a.getFin().getValor().getId());
			pr.setInt(3, a.getDistancia());
			pr.setDouble(4, a.getDuracionEstimada());
			pr.setInt(5, a.getPesoMax());
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
