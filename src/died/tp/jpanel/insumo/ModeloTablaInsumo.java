package died.tp.jpanel.insumo;

import javax.swing.table.AbstractTableModel;

import died.tp.dominio.Camion;
import died.tp.dominio.Insumo;
import died.tp.dominio.InsumoGeneral;
import died.tp.dominio.InsumoLiquido;

import java.util.*;

public class ModeloTablaInsumo extends AbstractTableModel {

	//Atributos
	private String[] columnNames = {"ID_Insumo","Descripcion","Unidad de Medida","Costo unidad", "Peso", "Densidad", "Stock"};
	private List<Insumo> data = new ArrayList<Insumo>();
	private List<Integer> stockTotales = new ArrayList<Integer>();
	
	//Métodos
	void limpiar() {
		data.clear();
	}
	
	public void mostrar(List<Insumo> lista, List<Integer> stocks ) {
		if(lista!=null) {
			data = lista;
			stockTotales = stocks;
		}	
	}
	
	public int eliminarFila(int fila) {
		int id = (int)getValueAt(fila,0);
		data.remove(fila);
		return id;
	}
	
	@Override
	public Object getValueAt(int fila, int columna) {
		Insumo i = data.get(fila);
		switch(columna) {
			case 0:
				return i.getId();
			case 1:
				return i.getDescripcion();
			case 2:
				return i.getuMedida();
			case 3:
				return i.getCosto();
			case 4: {
				if(i.esGeneral() && i.getPesoDensidad() > 0) {
					return i.getPesoDensidad();	
				}
				return 0;
				
			}
			case 5: {
				if(i.esLiquido() && i.getPesoDensidad() > 0) {
					return i.getPesoDensidad();
				}
				return 0;
			}
			case 6: 
				if(!stockTotales.isEmpty()) {
						return stockTotales.get(fila);
				}
				return 0;
	}
		return null;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0,c).getClass();
	}

}
