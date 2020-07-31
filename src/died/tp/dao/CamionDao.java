package died.tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import died.tp.dominio.Camion;

public class CamionDao {

	private static final String selectAll = "SELECT * FROM CAMION";
	private static final String update = "UPDATE CAMION SET patente = ?, modelo = ?, marca = ?, kmRecorridos = ?, costoKM = ?,"
			+ " costoHora = ?, fechaCompra = ? where id_camion = ?";
	private static final String delete = "delete FROM camion WHERE id_camion = ?";
	private static final String insert = " INSERT INTO camion (patente,modelo,marca,kmRecorridos,costoHora,costoKM,fechaCompra)"
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String search = "SELECT * FROM CAMION WHERE ";
	
	public CamionDao() {}


	
	public void altaActualizacionCamion(Camion c) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			if(c.getId() != null && c.getId() > 0) {
			pr = con.prepareStatement(update);
			pr.setString(1, c.getPatente());
			pr.setString(2, c.getModelo());
			pr.setString(3, c.getMarca());
			pr.setDouble(4, c.getKmRecorridos());
			pr.setDouble(5, c.getCostoHora());
			pr.setDouble(6, c.getCostoKM());
			pr.setDate(7, Date.valueOf(c.getFechaCompra()));
			pr.setInt(8, c.getId());
			System.out.println("camion insertado");
			}
			else {
				pr = con.prepareStatement(insert);
				pr.setString(1, c.getPatente());
				pr.setString(2, c.getModelo());
				pr.setString(3, c.getMarca());
				pr.setDouble(4, c.getKmRecorridos());
				pr.setDouble(5, c.getCostoHora());
				pr.setDouble(6, c.getCostoKM());
				pr.setDate(7, Date.valueOf(c.getFechaCompra()));
			}
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

	public void eliminarCamion(Integer id) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			pr = con.prepareStatement(delete);
			pr.setInt(1, id);
			pr.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error al eliminar");
			e.printStackTrace();
		}
	}

	public List<Camion> buscarTodos() {
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt= conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setId(rs.getInt("id_camion"));
				c.setMarca(rs.getString("marca"));
				c.setModelo(rs.getString("modelo"));
				c.setPatente(rs.getString("patente"));
				c.setKmRecorridos(rs.getDouble("kmRecorridos"));
				c.setCostoHora(rs.getDouble("costoHora"));
				c.setCostoKM(rs.getDouble("costoKM"));
				c.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
				lista.add(c);
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

		public List<Camion> buscarPorCampos(String s){
			List<Camion> lista = new ArrayList<Camion>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = Conexion.getConexion();
				pstmt= conn.prepareStatement(search+s);
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Camion c = new Camion();
					c.setId(rs.getInt("id_camion"));
					c.setMarca(rs.getString("marca"));
					c.setModelo(rs.getString("modelo"));
					c.setPatente(rs.getString("patente"));
					c.setKmRecorridos(rs.getDouble("kmRecorridos"));
					c.setCostoHora(rs.getDouble("costoHora"));
					c.setCostoKM(rs.getDouble("costoKM"));
					c.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
					lista.add(c);
				}		
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch(SQLException e) {
				e.printStackTrace();
				}
			}
			return lista;
		}
		
}