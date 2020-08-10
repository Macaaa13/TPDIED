package died.tp.jpanel.pedido;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import died.tp.dominio.OrdenDePedido;

public class ModeloTablaInfoOrden extends AbstractTableModel {

	//Atributos
	private String[] columnNames = {"Numero de pedido","Planta Destino"};
	private List<OrdenDePedido> data = new ArrayList<OrdenDePedido>();
	
	
	//Métodos
	public void limpiar() {
		data.clear();
	}
	
	public int eliminarFila(int fila) {
		int id = (int)getValueAt(fila,0);
		data.remove(fila);
		return id;
	}
	
	public void mostrar(List<OrdenDePedido> odp) {
		if(odp != null) {
			data = odp;
		}
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

	@Override
	public Object getValueAt(int fila, int columna) {
		OrdenDePedido op = data.get(fila);
		switch(columna) {
			case 0: return op.getNroOrden();
			case 1: return op.getDestino().getNombrePlanta();
		}
		return null;
	}

}
