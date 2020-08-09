package died.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import died.tp.dominio.Insumo;
import died.tp.dominio.InsumoGeneral;
import died.tp.dominio.InsumoLiquido;
import died.tp.dominio.Planta;
import died.tp.dominio.Stock;

public class PlantaStockDao {

	private static String getPlanta = "select p.* from Planta p where p.nombrePlanta = ? ";
	private static String getInsumo = "select * from Insumo i where descripcion = ?";
	private static String selectAllInsumos = "select i.*,ps.* from PlantaStock ps ,Insumo i where ps.id_planta = ? and ps.id_insumo = i.id_insumo and ps.cantidad < ps.puntoPedido";
	private static String insertStock = "insert into plantastock (id_planta,id_insumo,cantidad,puntoPedido) values (?,?,?,?)";

		public Planta getPlanta(String planta) {
			
			Connection conn = null;
			PreparedStatement consulta = null;
			ResultSet rs = null;
			Planta p = new Planta();
			try {
				conn = Conexion.getConexion();
				consulta = conn.prepareStatement(getPlanta);
				consulta.setString(1, planta);
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
		
		public Insumo traerInsumo(String nombreI) {
			Connection conn = null;
			PreparedStatement consulta = null;
			ResultSet rs = null;
			Insumo i = null;
			try {
				conn = Conexion.getConexion();
				consulta = conn.prepareStatement(getInsumo);
				consulta.setString(1, nombreI);
				rs = consulta.executeQuery();
				if(rs.next()) {
					if(rs.getDouble("densidad") != 0) {
						i = new InsumoLiquido();
						i.setId(rs.getInt("id_insumo"));
						i.setDescripcion(rs.getString("descripcion"));
						i.setPesoDensidad(rs.getDouble("densidad"));
						i.setuMedida(rs.getString("unidadMedida"));
						i.setCosto(rs.getInt("costoUnidad"));
					}
					else {
						i = new InsumoGeneral();
						i.setId(rs.getInt("id_insumo"));
						i.setDescripcion(rs.getString("descripcion"));
						i.setPesoDensidad(rs.getDouble("peso"));
						i.setuMedida(rs.getString("unidadMedida"));
						i.setCosto(rs.getInt("costoUnidad"));
					}
				}
				
			}catch(SQLException e) {
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
			return i;
		}
		
		public void darAltaStock(Planta p, Stock s) {
			Connection conn = null;
			PreparedStatement consulta = null;
			try {
				conn = Conexion.getConexion();
				consulta = conn.prepareStatement(insertStock);
				consulta.setInt(1, p.getId());
				consulta.setInt(2, s.getProducto().getId());
				consulta.setInt(3, s.getCantidad());
				consulta.setInt(4, s.getPuntoPedido());
				consulta.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(consulta!=null) consulta.close();
					if(conn!=null) conn.close();				
				}catch(SQLException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		public void actualizarStock(Integer id_planta, Stock s) {
			Connection conn = null;
			PreparedStatement consulta = null;
			try {
				conn = Conexion.getConexion();
				consulta = conn.prepareStatement("update plantastock set cantidad = ? where id_planta = ? and id_insumo = ?");
				consulta.setInt(1, s.getCantidad());
				consulta.setInt(2, id_planta);
				consulta.setInt(3, s.getProducto().getId());
				consulta.execute();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(consulta!=null) consulta.close();
					if(conn!=null) conn.close();				
				}catch(SQLException e) {
					e.printStackTrace();
				}
			
			}
			
		}
		
		public boolean obtenerInsumo(String nombreInsumo, Integer id_planta) {
			Connection conn = null;
			PreparedStatement consulta = null;
			ResultSet rs = null;
			Boolean resultado = false;
			try {
				conn = Conexion.getConexion();
				consulta = conn.prepareStatement("select * from PlantaStock ps, Insumo i,Planta p where p.id_planta = ? and ps.id_planta = ? and ps.id_insumo = i.id_insumo and i.descripcion = ?");
				consulta.setInt(1, id_planta);
				consulta.setInt(2, id_planta);
				consulta.setString(3, nombreInsumo);
				rs = consulta.executeQuery();
				if(rs.next()) {
					resultado = true;
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
			return resultado; 
		}
		
		public List<Stock> traerTodos(Integer p, List<Integer> stocks) {
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
						if(stocks != null) {
							stocks.add(this.traerStock(il.getId()));
						}
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
						if(stocks != null) {
							stocks.add(this.traerStock(ig.getId()));
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
					p.setStockInsumos(this.traerTodos(p.getId(),null));
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
		