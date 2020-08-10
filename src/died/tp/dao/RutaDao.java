package died.tp.dao;

import java.sql.*;
import java.util.*;

import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;

public class RutaDao {

	private static final String selectAll = "SELECT * FROM ruta";
	private static String insert = "INSERT INTO RUTA (plantaOrigen, plantaDestino, distancia, horasEstimadas, pesoMaximo)" + "VALUES (?,?,?,?,?)";
	private static String getPlanta = "select p.* from Planta p where p.id_planta = ? ";
	
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
	
	public List<Ruta> traerRutas() {
		List<Ruta> lista = new ArrayList<Ruta>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt= conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Ruta r = new Ruta();
				r.setOrigen(getPlanta(rs.getInt("plantaOrigen")));
				r.setDestino(getPlanta(rs.getInt("plantaDestino")));
				r.setDistancia(rs.getInt("distancia"));
				r.setDuracionEstimada(rs.getDouble("horasEstimadas"));
				r.setPesoMax(rs.getInt("pesoMaximo"));
				lista.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public Planta getPlanta(Integer planta) {
		
		Connection conn = null;
		PreparedStatement consulta = null;
		ResultSet rs = null;
		Planta p = new Planta();
		try {
			conn = Conexion.getConexion();
			consulta = conn.prepareStatement(getPlanta);
			consulta.setInt(1, planta);
			rs = consulta.executeQuery();
			if(rs.next()) {
			p.setId(rs.getInt("id_planta"));
			p.setNombrePlanta(rs.getString("nombrePlanta"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(consulta!=null) consulta.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
		
	}
	
}
