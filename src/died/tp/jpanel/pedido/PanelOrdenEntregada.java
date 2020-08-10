package died.tp.jpanel.pedido;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import died.tp.controllers.OrdenPedidoController;
import died.tp.jframes.MenuPedidos;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Label;
import java.awt.Window;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class PanelOrdenEntregada extends JPanel{
	private JTextField textFieldPrecioTotal;
	private OrdenPedidoController opc;
	private JComboBox<String> comboBoxPlanta;
	JDateChooser dateChooserFechaMaxima;
	
	public PanelOrdenEntregada () {
		setLayout(null);
		setSize(900,450);
		setSize(900,450);
		
		opc = new OrdenPedidoController(this);
		
		//TABLA DE INSUMOS SELECCIONADOS
		ModeloTablaRegistrarOrden tablaModelo2 = new ModeloTablaRegistrarOrden(false);
		JTable tablaDatos2 = new JTable(tablaModelo2);
		tablaDatos2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment( JLabel.CENTER );
		tablaDatos2.setDefaultRenderer(String.class, centerRenderer2);
		
		JScrollPane scrollPanel_1 = new JScrollPane(tablaDatos2);
		scrollPanel_1.setBounds(416, 69, 474, 234);
		add(scrollPanel_1);
		
		// TABLA DE INSUMOS NO SELECCIONADOS
		ModeloTablaRegistrarOrden tablaModelo = new ModeloTablaRegistrarOrden(true);
		JTable tablaDatos = new JTable(tablaModelo);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tablaDatos.setDefaultRenderer(String.class, centerRenderer);
		
		JScrollPane scrollPanel = new JScrollPane(tablaDatos);
		scrollPanel.setBounds(26, 211, 380, 228);
		add(scrollPanel);
		tablaModelo.mostrar(opc.traerInsumos());
		tablaModelo.fireTableDataChanged();
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(tablaDatos.getSelectedRow() != -1) {
					String cantidad = JOptionPane.showInputDialog("Ingrese la cantidad a agregar");
					if(cantidad != null && cantidad.length() != 0) {		
						int fila = tablaDatos.getSelectedRow();
						tablaModelo2.agregar(opc.nuevoInsumo(Integer.valueOf(cantidad),tablaModelo.getValueAt(fila, 0).toString(),tablaModelo.getValueAt(fila, 1).toString(),Integer.valueOf(tablaModelo.getValueAt(fila, 2).toString())),Integer.valueOf(cantidad));
						tablaModelo2.fireTableDataChanged();
						opc.actualizarValorCompra(Integer.valueOf(cantidad),Integer.valueOf(tablaModelo.getValueAt(fila, 2).toString()));
					}
				}
			}
		});
		
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal? \n Los datos no guardados se perderán", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(rta == JOptionPane.OK_OPTION) {
					Window w = SwingUtilities.getWindowAncestor(PanelOrdenEntregada.this);
					w.dispose();
					MenuPedidos mp = new MenuPedidos();
					mp.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(801, 416, 89, 23);
		add(btnVolver);
		
		JButton btnRegistrarOrden = new JButton("Registrar Orden");
		btnRegistrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opc.agregarOrden()) {		
					JOptionPane.showMessageDialog(null, "Orden de pedido registrada");
					tablaModelo2.limpiar();
					tablaModelo2.fireTableDataChanged();
				}
			}
		});
		btnRegistrarOrden.setBounds(759, 314, 131, 23);
		add(btnRegistrarOrden);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaDatos2.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un insumo de la tabla para poder quitarlo");
				}
				else {
					Integer rta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres quitar este insumo?","Confirmación",JOptionPane.YES_NO_OPTION);
					if(rta.equals(JOptionPane.YES_OPTION)) {
					opc.actualizarValorCompra(Integer.valueOf(tablaModelo2.getValueAt(tablaDatos2.getSelectedRow(), 3).toString()), - Integer.valueOf(tablaModelo2.getValueAt(tablaDatos2.getSelectedRow(), 2).toString()));
					tablaModelo2.eliminarFila(tablaDatos2.getSelectedRow());
					tablaModelo2.fireTableDataChanged();
					}
				}
				
				
			}
		});
		btnQuitar.setBounds(661, 314, 89, 23);
		add(btnQuitar);
		
		
		//JtextFields
		comboBoxPlanta = new JComboBox<String>();
		comboBoxPlanta.setBounds(167, 66, 120, 22);
		add(comboBoxPlanta);
		cargarComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de entrega m\u00E1xima");
		lblNewLabel_1.setBounds(26, 114, 131, 14);
		add(lblNewLabel_1);
		
		dateChooserFechaMaxima = new JDateChooser();
		dateChooserFechaMaxima.setBounds(167, 108, 120, 20);
		add(dateChooserFechaMaxima);
		
		
		
		Label label = new Label("Seleccione los productos:");
		label.setBounds(26, 183, 155, 22);
		add(label);
		
		JLabel lblNewLabel_2 = new JLabel("Precio total:");
		lblNewLabel_2.setBounds(26, 154, 70, 14);
		add(lblNewLabel_2);
		
		textFieldPrecioTotal = new JTextField();
		textFieldPrecioTotal.setBounds(168, 151, 120, 20);
		add(textFieldPrecioTotal);
		textFieldPrecioTotal.setColumns(10);
		textFieldPrecioTotal.setEditable(false);
		textFieldPrecioTotal.setText("0");
		
		
		JLabel lblNewLabel = new JLabel("Seleccionar la planta:");
		lblNewLabel.setBounds(26, 74, 113, 14);
		add(lblNewLabel);
		
	

		
		
	}
	private void cargarComboBox() {
		List<String> plantas = opc.traerPlantas();
		if(plantas != null) {
		for(String s: plantas) {
			comboBoxPlanta.addItem(s);
			}
		}
	}
	public void actualizarCompra(Integer costo ) {
		textFieldPrecioTotal.setText(costo.toString());
		
	}
	public JTextField getTextFieldPrecioTotal() {
		return textFieldPrecioTotal;
	}
	public void setTextFieldPrecioTotal(JTextField textFieldPrecioTotal) {
		this.textFieldPrecioTotal = textFieldPrecioTotal;
	}
	public JComboBox<String> getComboBoxPlanta() {
		return comboBoxPlanta;
	}
	public void setComboBoxPlanta(JComboBox<String> comboBoxPlanta) {
		this.comboBoxPlanta = comboBoxPlanta;
	}
	public void informarSituacion(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}
	public JDateChooser getDateChooserFechaMaxima() {
		return dateChooserFechaMaxima;
	}
	public void setDateChooserFechaMaxima(JDateChooser dateChooserFechaMaxima) {
		this.dateChooserFechaMaxima = dateChooserFechaMaxima;
	}
	
	
	
	
	
}