package died.tp.jpanel.insumo;

import javax.swing.table.AbstractTableModel;

import died.tp.dominio.Camion;
import died.tp.dominio.Insumo;
import died.tp.dominio.InsumoGeneral;
import died.tp.dominio.InsumoLiquido;

import java.util.*;

public class ModeloTablaInsumo extends AbstractTableModel {

	//Atributos
	private String[] columnNames = {"ID_Insumo","Descripcion","Unidad de Medida","Costo unidad", "Peso", "Densidad"};
	private List<Insumo> data = new ArrayList<Insumo>();
	
	public void mostrar(List<Insumo> lista) {
		if(lista!=null) {
			data = lista;
		}
	}
	
	public int eliminarFila(int fila) {
		int id = (int)getValueAt(fila,0);
		data.remove(fila);
		return id;
	}
	
	//Métodos
	void limpiar() {
		data.clear();
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
			case 4:
				if(i.esGeneral()) {
					InsumoGeneral ig = (InsumoGeneral) i;
					return ig.getPeso();
				} else {
					return "-";
				}
			case 5:
				if(i.esLiquido()) {
					InsumoLiquido il = (InsumoLiquido) i;
					return il.getDensidad();
				} else {
					return "-";
				}
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
