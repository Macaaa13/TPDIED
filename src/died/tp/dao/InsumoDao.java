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
	private static final String update = "UPDATE Insumo SET unidadMedida = ?, costuUnidad = ? where id_insumo = ?";
	private static final String delete = "SET SQL_SAFE_UPDATES=0;"
			+ "delete FROMM Insumo WHERE id_insumo = ?";
	private static final String insert = " INSERT INTO Insumo (unidadMedida,costoUnidad) VALUES (?,?)";


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
			pr.setInt(3, i.getId());
			pr.executeUpdate();
				if(i.esGeneral()) {
					InsumoGeneral il = (InsumoGeneral) i;
					PreparedStatement s = con.prepareStatement("UPDATE Insumo SET peso = "+il.getPeso().toString()+" WHERE id_insumo = "+il.getId().toString());
					s.executeUpdate();
				}
				else {
					InsumoLiquido il = (InsumoLiquido) i;
					PreparedStatement s = con.prepareStatement("UPDATE Insumo SET densidad = "+il.getDensidad().toString()+" WHERE id_insumo = "+il.getId().toString());
					s.executeUpdate();
				}
			}
			else {
				pr = con.prepareStatement(insert);
				pr.setString(1, i.getuMedida());
				pr.setDouble(2, i.getCosto());
				pr.executeUpdate();
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
	
	public List<Insumo> buscarTodos() {
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
				}
				else {
					InsumoGeneral i = new InsumoGeneral();
					i.setId(rs.getInt("id_insumo"));
					i.setDescripcion(rs.getString("descripcion"));
					i.setuMedida(rs.getString("unidadMedida"));
					i.setCosto(rs.getInt("costoUnidad"));
					i.setPeso(rs.getDouble("peso"));
					lista.add(i);
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

}
