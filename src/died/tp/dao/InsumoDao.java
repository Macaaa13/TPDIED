package died.tp.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import died.tp.dominio.Camion;
import died.tp.dominio.Insumo;
import died.tp.dominio.InsumoGeneral;
import died.tp.dominio.InsumoLiquido;

public class InsumoDao {

	private static final String selectAll = "SELECT * FROM Insumo";
	private static final String update = "UPDATE Insumo SET unidadMedida = ?, costoUnidad = ?, descripcion = ? where id_insumo = ?";
	private static final String delete = "delete FROM insumo WHERE id_insumo = ?";
	private static final String insert = " INSERT INTO Insumo (unidadMedida,costoUnidad, descripcion) VALUES (?,?,?)";


	public InsumoDao() {}

	public void altaActualizacionInsumo(Insumo i) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			if(i.getId() != null && i.getId() > 0) {
			pr = con.prepareStatement(update);
			pr.setString(1, i.getuMedida());
			pr.setDouble(2, i.getCosto());
			pr.setString(3, i.getDescripcion());
			pr.setInt(4, i.getId());
			pr.executeUpdate();
			}
			else {
				pr = con.prepareStatement(insert);
				pr.setString(1, i.getuMedida());
				pr.setDouble(2, i.getCosto());
				pr.setString(3, i.getDescripcion());
				pr.executeUpdate();
				this.setId(i);
			}
			
			if(i.esGeneral()) {
				System.out.println(i.getPesoDensidad());
				PreparedStatement s = con.prepareStatement("UPDATE Insumo SET peso = "+i.getPesoDensidad().toString()+" WHERE id_insumo = "+i.getId().toString());
				s.executeUpdate();
				s.close();
			}
			else {
				PreparedStatement s = con.prepareStatement("UPDATE Insumo SET densidad = "+i.getPesoDensidad().toString()+" WHERE id_insumo = "+i.getId().toString());
				s.executeUpdate();
				s.close();
			}

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
	
	public void setId(Insumo i) {
		Connection con = null;
		PreparedStatement pr = null;
		ResultSet rs = null;
	
		try {
			con = Conexion.getConexion();
			pr = con.prepareStatement("SELECT MAX(id_insumo) AS id FROM insumo");
			rs = pr.executeQuery();
			if(rs.next()) {
			i.setId(rs.getInt("id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			pr.close();
			con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarI(Integer id) {
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
	
	public List<Insumo> buscarTodos(List<Integer> stocks) {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt= conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Double den = rs.getDouble("densidad");
				if(!(den == 0)) {
					InsumoLiquido i = new InsumoLiquido();
					i.setId(rs.getInt("id_insumo"));
					i.setDescripcion(rs.getString("descripcion"));
					i.setDensidad(den);
					i.setuMedida(rs.getString("unidadMedida"));
					i.setCosto(rs.getInt("costoUnidad"));
					lista.add(i);
					if(stocks != null) {
					stocks.add(this.traerStock(i.getId()));
					}
				}
				else {
					InsumoGeneral i = new InsumoGeneral();
					i.setId(rs.getInt("id_insumo"));
					i.setDescripcion(rs.getString("descripcion"));
					i.setuMedida(rs.getString("unidadMedida"));
					i.setCosto(rs.getInt("costoUnidad"));
					i.setPeso(rs.getDouble("peso"));
					lista.add(i);
					if(stocks != null) {
						stocks.add(this.traerStock(i.getId()));
						}
				}
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
	
	public List<Insumo> buscarPorCampos(String[] campos, Integer cant, List<Integer> stocks){
		List<Insumo> resultado = new ArrayList<Insumo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			if(cant != 0) {
				String string = "SELECT * FROM INSUMO WHERE "+campos[0]+" = '"+campos[1]+"'";
				for(int i = 2; i < cant; i= i+2) {
					string = string+" AND "+campos[i]+" = '"+campos[i+1]+"'";
				
				}
				pstmt= conn.prepareStatement(string);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Double den = rs.getDouble("densidad");
					if(!(den == 0)) {
						InsumoLiquido i = new InsumoLiquido();
						i.setId(rs.getInt("id_insumo"));
						i.setDescripcion(rs.getString("descripcion"));
						i.setDensidad(den);
						i.setuMedida(rs.getString("unidadMedida"));
						i.setCosto(rs.getInt("costoUnidad"));
						resultado.add(i);
						if(stocks != null) {
							stocks.add(this.traerStock(i.getId()));
						}
					}
					else {
						InsumoGeneral i = new InsumoGeneral();
						i.setId(rs.getInt("id_insumo"));
						i.setDescripcion(rs.getString("descripcion"));
						i.setuMedida(rs.getString("unidadMedida"));
						i.setCosto(rs.getInt("costoUnidad"));
						i.setPeso(rs.getDouble("peso"));
						resultado.add(i);
						if(stocks != null) {
							stocks.add(this.traerStock(i.getId()));
						}
					}
				}
			}
			
		}catch (SQLException e) {
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
		return resultado;
	}
	
	public Integer traerStock(Integer id) {
		Integer stock = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt = conn.prepareStatement("select sum(ps.cantidad) as cant from insumo i, plantastock ps where i.id_insumo = ? and i.id_insumo = ps.id_insumo group by i.id_insumo");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				stock = rs.getInt("cant");
			}
		}catch (SQLException e) {
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
		return stock;
	}
	
}
