package died.tp.jpanel.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


import died.tp.dominio.Insumo;

public class ModeloTablaRegistrarOrden extends AbstractTableModel {
	
	private List<Insumo> data;
	private String[] columnNames;
	private boolean pr;
	private List<Integer> cantidad;
	

	
	public List<Insumo> getLista(){
		return this.data;
	}
	
	public ModeloTablaRegistrarOrden(boolean rta) {
		pr = rta;
		data = new ArrayList<Insumo>();
		cantidad = new ArrayList<Integer>();
		if(rta) {
			String[] m = {"Insumo","Unidad medida","Precio unitario"};
			columnNames = m;
		}
		else {
			 String[] m = {"Insumo","Unidad medida","Precio unitario","Cantidad"};
			 columnNames = m;
		}
	}
	
	
	public void limpiar() {
		data.clear();
	}

public void eliminarFila(int selectedRow) {
		data.remove(selectedRow);
		cantidad.remove(selectedRow);
	}
	
	public void mostrar(List<Insumo> lista) {
		if(lista!=null) {
			data = lista;
		}
	}
	
	public void agregar(Insumo i,Integer cant) {
		data.add(i);
		cantidad.add(cant);
	}
	
	@Override
	public Object getValueAt(int fila, int columna) {
		Insumo i = data.get(fila);
		switch(columna) {
		case 0: return i.getDescripcion();
		case 1: return i.getuMedida();
		case 2: return i.getCosto();
		case 3:{
			if(!pr) {
				return cantidad.get(fila);
			}
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
