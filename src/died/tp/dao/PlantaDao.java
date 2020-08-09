package died.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.tp.dominio.InsumoGeneral;
import died.tp.dominio.InsumoLiquido;
import died.tp.dominio.Planta;
import died.tp.dominio.Stock;

public class PlantaDao {

	private static String insert = "insert into planta (nombrePlanta) values (?)";
	private static String delete = "delete FROM camion WHERE id_camion = ?";
	private static String selectAllInsumos = "select i.*,ps.* from PlantaStock ps ,Insumo i where ps.id_planta = ? and ps.id_insumo = i.id_insumo and ps.cantidad < ps.puntoPedido";



	public void altaPlanta(String c) {
		Connection con = null;
		con = Conexion.conectar();
		PreparedStatement pr = null;
		try {
			pr = con.prepareStatement(insert);
			pr.setString(1, c);
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

	public List<Stock> traerTodos(Integer p) {
		List<Stock> lista = new ArrayList<Stock>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt= conn.prepareStatement(selectAllInsumos);
			pstmt.setInt(1, p);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getDouble("densidad") != 0){
					InsumoLiquido il = new InsumoLiquido();
					il.setId(rs.getInt("id_insumo"));
					il.setDescripcion(rs.getString("descripcion"));
					il.setDensidad(rs.getDouble("densidad"));
					il.setuMedida(rs.getString("unidadMedida"));
					il.setCosto(rs.getInt("costoUnidad"));
					Stock s = new Stock();
					s.setCantidad(rs.getInt("cantidad"));
					s.setPuntoPedido(rs.getInt("puntoPedido"));
					s.setProducto(il);
					lista.add(s);
				}
				else {
					InsumoGeneral ig = new InsumoGeneral();
					ig.setId(rs.getInt("id_insumo"));
					ig.setDescripcion(rs.getString("descripcion"));
					ig.setPeso(rs.getDouble("peso"));
					ig.setuMedida(rs.getString("unidadMedida"));
					ig.setCosto(rs.getInt("costoUnidad"));
					Stock s = new Stock();
					s.setCantidad(rs.getInt("cantidad"));
					s.setPuntoPedido(rs.getInt("puntoPedido"));
					s.setProducto(ig);
					lista.add(s);
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

	public void eliminarPlanta(Integer id) {
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
	public List<Planta> traerPlantas() {
		List<Planta> resultado = new ArrayList<Planta>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Conexion.getConexion();
			pstmt= conn.prepareStatement("select * from Planta");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Planta p = new Planta();
				p.setId(rs.getInt("id_planta"));
				p.setNombrePlanta(rs.getString("nombrePlanta"));
				p.setStockInsumos(this.traerTodos(p.getId()));
				resultado.add(p);
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
}