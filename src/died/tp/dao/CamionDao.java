package died.tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import died.tp.dominio.Camion;

public class CamionDao {

	//Atributos
	private static final String SELECT_ALL_CAMION =
			"SELECT ID_CAMION, PATENTE, MARCA, MODELO, KMRECORRIDOS, COSTOHORA, COSTOKM, FECHACOMPRA FROM CAMION";
	
	private static final String INSERT_CAMION =
			"INSERT INTO CAMION (PATENTE,MARCA,MODELO,KMRECORRIDOS, COSTOHORA, COSTOKM, FECHACOMPRA) VALUES (?,?,?,?,?,?,?)";
	
	private static final String UPDATE_CAMION =
			" UPDATE CAMION SET PATENTE = ?, MARCA =? ,MODELO = ? , KMRECORRIDOS =? , COSTOHORA =? , COSTOKM =? , FECHACOMPRA =? "
			+ " WHERE ID_CAMION = ?";
	
	
	//Métodos
	public Camion insertOrUpdate(Camion c) {
		Connection con = Conexion.getConection();
		PreparedStatement ps = null;
		try {
			if(c.getId()!=null && c.getId()>0) {
				System.out.println("Ejecutando: Update");
				ps= con.prepareStatement(UPDATE_CAMION);
				ps.setString(1, c.getPatente());
				ps.setString(2, c.getMarca());
				ps.setInt(3, c.getModelo());
				ps.setInt(4, c.getKmRecorridos());
				ps.setDouble(5, c.getCostoHora());
				ps.setDouble(6, c.getCostoKM());
				ps.setDate(7, Date.valueOf(c.getFechaCompra()));
				ps.setInt(8, c.getId());
			}else {
				System.out.println("Ejecutando: Insert");
				ps= con.prepareStatement(INSERT_CAMION);
				ps.setString(1, c.getPatente());
				ps.setString(2, c.getMarca());
				ps.setInt(3, c.getModelo());
				ps.setInt(4, c.getKmRecorridos());
				ps.setDouble(5, c.getCostoHora());
				ps.setDouble(6, c.getCostoKM());
				ps.setDate(7, Date.valueOf(c.getFechaCompra()));
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public List<Camion> listarTodos(){
		List<Camion> lista = new ArrayList<Camion>();
		Connection con = Conexion.getConection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SELECT_ALL_CAMION);
			rs = ps.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setId(rs.getInt("ID_CAMION"));
				c.setPatente(rs.getString("PATENTE"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getInt("MODELO"));
				c.setKmRecorridos(rs.getInt("KMRECORRIDOS"));
				c.setCostoHora(rs.getDouble("COSTOHORA"));
				c.setCostoKM(rs.getDouble("COSTOKM"));
				c.setFechaCompra(rs.getDate("FECHACOMPRA").toLocalDate());
				lista.add(c);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
}
