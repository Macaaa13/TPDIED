package died.tp.jpanel.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;
import died.tp.dominio.Insumo;

public class TablaParaInsumos extends AbstractTableModel{

	private String[] columnNames = {"Numero orden","Insumo","Cantidad"};
	private List<Insumo> data = new ArrayList<Insumo>();
	private List<Integer> cantidades = new ArrayList<Integer>();
	private Integer nroped;
	public void mostrar(Map<Insumo,Integer> hmp, Integer nro ) {
		if(hmp != null) {
			data = hmp.keySet().stream().collect(Collectors.toList());
			cantidades = hmp.values().stream().collect(Collectors.toList());
			nroped = nro;
		}
	}

	public Object getValueAt(int fila, int columna) {
		Insumo i = data.get(fila);
		switch(columna) {
			case 0: return nroped;
			case 1: return i.getDescripcion();
			case 2: return cantidades.get(fila);
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