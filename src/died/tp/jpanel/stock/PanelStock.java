package died.tp.jpanel.stock;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import died.tp.controllers.StockController;
import died.tp.dominio.Stock;
import died.tp.jframes.MenuPrincipal;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
public class PanelStock extends JPanel {
	private JTextField textFieldInsumo;
	private JTextField textFieldCantidad;
	private JTextField textFieldPuntoPedido;
	private StockController sc;
	JComboBox<String> comboBoxPlanta;
	/**
	 * Create the panel.
	 */
	public PanelStock() {
		
		setLayout(null);
		setSize(900,400);
		setSize(900,400);
		
		sc = new StockController(this);
		
		
		
		ModeloTablaStock tablaModelo = new ModeloTablaStock();
		JTable tablaDatos = new JTable(tablaModelo);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tablaDatos.setDefaultRenderer(String.class, centerRenderer);
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if(tablaDatos.getSelectedRow() != -1) {
					cargarFilaSeleccionada(tablaModelo, tablaDatos.getSelectedRow());
				}
			}

			

		});

		JScrollPane scrollPanel = new JScrollPane(tablaDatos);
		scrollPanel.setBounds(298, 53, 567, 280);
		add(scrollPanel, BorderLayout.CENTER);
		
		
		
		//BOTONES
		JButton btnActualizarStock = new JButton("Actualizar Stock");
		btnActualizarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.setPlanta(comboBoxPlanta.getSelectedItem().toString());
				if(tablaDatos.getSelectedRow() != -1) {
					int rta = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar el camión?", "Advertencia", JOptionPane.YES_NO_OPTION);
					if(rta == JOptionPane.YES_OPTION) {
						if(sc.validacionVacios()) {
							if(sc.validarCampos()) {
								sc.actualizar();
								List<Integer> stocks = new ArrayList<Integer>();
								tablaModelo.mostrar(sc.traerTodos(stocks), comboBoxPlanta.getSelectedItem().toString(),stocks);
								tablaModelo.fireTableDataChanged();
								JOptionPane.showMessageDialog(null, "Stock Modificado", "Acción exitosa", JOptionPane.PLAIN_MESSAGE);
								limpiar();
							}
						}
					} 
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el stock que desea modificar", "Advertencia", JOptionPane.OK_OPTION);
				}
				
				
			}
		});
		btnActualizarStock.setBounds(47, 253, 145, 23);
		add(btnActualizarStock);
		
		JButton btnAgregarPlanta = new JButton("Agregar a Planta");
		btnAgregarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.setPlanta(comboBoxPlanta.getSelectedItem().toString());
				if(!sc.controlarInsumoPlanta(textFieldInsumo.getText())) {
					if(sc.validacionVacios()) {
						if(sc.validarCampos()) {
							sc.agregarInsumoPlanta();
							JOptionPane.showMessageDialog(null, "Agregado el insumo "+ textFieldInsumo.getText()+" a la planta "+comboBoxPlanta.getSelectedItem().toString());
							limpiar();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "El insumo ya existe en la planta"+comboBoxPlanta.getSelectedItem().toString());
				}
			}
		});
		btnAgregarPlanta.setBounds(47, 185, 145, 23);
		add(btnAgregarPlanta);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal? \n Los datos no guardados se perderán", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(rta == JOptionPane.OK_OPTION) {
					Window w = SwingUtilities.getWindowAncestor(PanelStock.this);
					w.dispose();
					MenuPrincipal mp = new MenuPrincipal();
					mp.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(745, 344, 120, 30);
		add(btnVolver);
		
		JButton btnCargarStocks = new JButton("Cargar stocks");
		btnCargarStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.setPlanta(comboBoxPlanta.getSelectedItem().toString());
				List<Integer> st = new ArrayList<Integer>();
				List<Stock> stocks = sc.traerTodos(st);
				if(!stocks.isEmpty()) {
					tablaModelo.mostrar(stocks, comboBoxPlanta.getSelectedItem().toString(),st);
					tablaModelo.fireTableDataChanged();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay resultados ");
				}
			}
		});
	
		btnCargarStocks.setBounds(47, 219, 145, 23);
		add(btnCargarStocks);
		
		//TextFields
	
		comboBoxPlanta = new JComboBox<String>();
		comboBoxPlanta.setBounds(132, 57, 156, 22);
		add(comboBoxPlanta);
		this.cargarComboBox();
		
		JLabel lblNewLabel = new JLabel("Planta");
		lblNewLabel.setBounds(47, 61, 48, 14);
		add(lblNewLabel);
		
		textFieldInsumo = new JTextField();
		textFieldInsumo.setBounds(132, 90, 156, 20);
		add(textFieldInsumo);
		textFieldInsumo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Insumo");
		lblNewLabel_1.setBounds(47, 93, 48, 14);
		add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(47, 124, 48, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Punto Pedido");
		lblNewLabel_3.setBounds(47, 152, 64, 14);
		add(lblNewLabel_3);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(132, 121, 156, 20);
		add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		textFieldPuntoPedido = new JTextField();
		textFieldPuntoPedido.setBounds(132, 149, 156, 20);
		add(textFieldPuntoPedido);
		textFieldPuntoPedido.setColumns(10);
		
		
	}
	
	private void cargarComboBox() {
		List<String> plantas = sc.traerPlantas();
		if(plantas != null) {
		for(String s: plantas) {
			comboBoxPlanta.addItem(s);
			}
		}
	}
	protected void limpiar() {
		textFieldCantidad.setText("");
		textFieldInsumo.setText("");
		textFieldPuntoPedido.setText("");
		
	}
	private void cargarFilaSeleccionada(ModeloTablaStock mts, int fila) {
		textFieldInsumo.setText(mts.getValueAt(fila, 1).toString());
		textFieldCantidad.setText(mts.getValueAt(fila, 2).toString());
		textFieldPuntoPedido.setText(mts.getValueAt(fila, 3).toString());
	}
	
	public void informarError(String error) {
		JOptionPane.showMessageDialog(null, error);
		
	}
	public JTextField getTextFieldInsumo() {
		return textFieldInsumo;
	}
	public void setTextFieldInsumo(JTextField textFieldInsumo) {
		this.textFieldInsumo = textFieldInsumo;
	}
	public JTextField getTextFieldCantidad() {
		return textFieldCantidad;
	}
	public void setTextFieldCantidad(JTextField textFieldCantidad) {
		this.textFieldCantidad = textFieldCantidad;
	}
	public JTextField getTextFieldPuntoPedido() {
		return textFieldPuntoPedido;
	}
	public void setTextFieldPuntoPedido(JTextField textFieldPuntoPedido) {
		this.textFieldPuntoPedido = textFieldPuntoPedido;
	}
	public StockController getSc() {
		return sc;
	}
	public void setSc(StockController sc) {
		this.sc = sc;
	}
	public JComboBox<String> getComboBoxPlanta() {
		return comboBoxPlanta;
	}
	public void setComboBoxPlanta(JComboBox<String> comboBoxPlanta) {
		this.comboBoxPlanta = comboBoxPlanta;
	}
	
}
