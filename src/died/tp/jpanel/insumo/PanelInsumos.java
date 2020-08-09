package died.tp.jpanel.insumo;

import java.awt.BorderLayout;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import died.tp.controllers.InsumoController;
import died.tp.dominio.Insumo;
import died.tp.excepciones.DatosObligatoriosException;
import died.tp.excepciones.FormatoNumeroException;
import died.tp.jframes.MenuPrincipal;
import died.tp.jpanel.camion.PanelCamiones;

public class PanelInsumos extends JPanel {

	//Atributos
	private JTextField textFieldUnidadMedida;
	private JTextField textFieldCosto;
	private JTextField textFieldPeso;
	private JTextField textFieldDensidad;
	private JComboBox<String> comboBoxTipo;
	private JTextField textFieldDescripcion;
	private InsumoController ic;
	
	
	//Getters y Setters
	public JTextField getTextFieldUnidadMedida() {
		return textFieldUnidadMedida;
	}

	public void setTextFieldUnidadMedida(JTextField textFieldUnidadMedida) {
		this.textFieldUnidadMedida = textFieldUnidadMedida;
	}

	public JTextField getTextFieldCosto() {
		return textFieldCosto;
	}

	public void setTextFieldCosto(JTextField textFieldCosto) {
		this.textFieldCosto = textFieldCosto;
	}

	public JTextField getTextFieldPeso() {
		return textFieldPeso;
	}

	public void setTextFieldPeso(JTextField textFieldPeso) {
		this.textFieldPeso = textFieldPeso;
	}

	public JTextField getTextFieldDensidad() {
		return textFieldDensidad;
	}

	public void setTextFieldDensidad(JTextField textFieldDensidad) {
		this.textFieldDensidad = textFieldDensidad;
	}

	public JComboBox<String> getComboBoxTipo() {
		return comboBoxTipo;
	}

	public void setComboBoxTipo(JComboBox<String> comboBoxTipo) {
		this.comboBoxTipo = comboBoxTipo;
	}

	public JTextField getTextFieldDescripcion() {
		return textFieldDescripcion;
	}

	public void setTextFieldDescripcion(JTextField textFieldDescripcion) {
		this.textFieldDescripcion = textFieldDescripcion;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelInsumos() {
		setLayout(null);
		setSize(1200,400);
		
		ic = new InsumoController(this);
		
		//Tabla
		ModeloTablaInsumo tablaModelo = new ModeloTablaInsumo();
		JTable tablaDatos = new JTable(tablaModelo);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if(tablaDatos.getSelectedRow() != -1) {
					cargarFilaSeleccionada(tablaModelo, tablaDatos.getSelectedRow());
				}
			}
		});
		
		JScrollPane scrollPanel = new JScrollPane(tablaDatos);
		scrollPanel.setBounds(421, 50, 729, 280);
		add(scrollPanel, BorderLayout.CENTER);
		
		// Botones
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ic.validacionVacios()) {
						if(ic.camposCorrectos()) {
							ic.guardar(null);
							JOptionPane.showMessageDialog(null, "Insumo agregado", "Acción exitosa", JOptionPane.PLAIN_MESSAGE);
							limpiar();
						}
					}
					} catch(DatosObligatoriosException | FormatoNumeroException ex) {
						System.out.println(ex.getMessage());;
					}
			}
		});
		btnAgregar.setBounds(50, 359, 120, 30);
		add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(440, 359, 120, 30);
		add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablaDatos.getSelectedRow() != -1) {
					int rta = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar el insumo?", "Advertencia", JOptionPane.YES_NO_OPTION);
					if(rta == JOptionPane.YES_OPTION) {
						if(ic.validacionVacios()) {
							if(ic.camposCorrectos()) {
								List<Integer> stocks = new ArrayList<Integer>();
								ic.actualizar((Integer)tablaModelo.getValueAt(tablaDatos.getSelectedRow(), 0),stocks);
								tablaModelo.mostrar(ic.traerDatos(stocks),stocks);
								tablaModelo.fireTableDataChanged();
								JOptionPane.showMessageDialog(null, "Insumo modificado", "Acción exitosa", JOptionPane.PLAIN_MESSAGE);
								limpiar();
							}
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el insumo que desea modificar", "Advertencia", JOptionPane.OK_OPTION);
				}
			}
		});
		JButton btnEliminar = new JButton("Eliminar");	
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaDatos.getSelectedRow() != -1) {
					int rta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el camión?", "Advertencia", JOptionPane.YES_NO_OPTION);
					if(rta == JOptionPane.YES_OPTION) {
						ic.eliminarInsumo(tablaModelo.eliminarFila(tablaDatos.getSelectedRow()));
						tablaModelo.fireTableDataChanged();
						limpiar();
						JOptionPane.showMessageDialog(null, "Camión eliminado", "Acción exitosa", JOptionPane.PLAIN_MESSAGE);
					} 
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el camión que desea eliminar", "Advertencia", JOptionPane.OK_OPTION);
				}
			}
		});
		btnEliminar.setBounds(310, 359, 120, 30);
		add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal? \n Los datos no guardados se perderán", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(rta == JOptionPane.OK_OPTION) {
					Window w = SwingUtilities.getWindowAncestor(PanelInsumos.this);
					w.dispose();
					MenuPrincipal mp = new MenuPrincipal();
					mp.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(1030, 359, 120, 30);
		add(btnVolver);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				tablaModelo.limpiar();
				tablaModelo.fireTableDataChanged();
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnAgregar.setEnabled(true);
			}
		});
		btnCancelar.setBounds(900, 359, 120, 30);
		add(btnCancelar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Integer> stocks = new ArrayList<Integer>();
				String[] campos = new String [8];
				Integer cantidad = ic.completarCampos(campos);
				if(cantidad == 0) {
					tablaModelo.mostrar(ic.traerDatos(stocks),stocks);
				}
				else {
					List<Insumo> insumos = ic.buscarPorCampos(campos, cantidad,stocks);
					if(!insumos.isEmpty()) {
						tablaModelo.mostrar(insumos,stocks);
					}
					else {
						JOptionPane.showMessageDialog(null, "No hay resultados que mostrar.");
					}
				}
				tablaModelo.fireTableDataChanged();
				btnCancelar.setEnabled(true);
				btnAgregar.setEnabled(false);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				limpiar();
			}
		});
		btnBuscar.setBounds(180, 359, 120, 30);
		add(btnBuscar);
		
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnCancelar.setEnabled(false);
		
		//TextFields
		textFieldUnidadMedida = new JTextField();
		textFieldUnidadMedida.setColumns(10);
		textFieldUnidadMedida.setBounds(180, 51, 120, 20);
		add(textFieldUnidadMedida);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(180, 86, 120, 20);
		add(textFieldCosto);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(180, 121, 120, 20);
		add(textFieldPeso);
		
		textFieldDensidad = new JTextField();
		textFieldDensidad.setColumns(10);
		textFieldDensidad.setBounds(180, 156, 120, 20);
		add(textFieldDensidad);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(180, 224, 210, 106);
		add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		//ComboBox
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxTipo.getSelectedItem().equals("General")) {
					textFieldDensidad.setEditable(false);
					textFieldPeso.setEditable(true);
				}
				else {
					textFieldPeso.setEditable(false);
					textFieldDensidad.setEditable(true);
				}
			
			
			}
		});
		comboBoxTipo.setEditable(false);
		comboBoxTipo.setBounds(180, 191, 120, 20);
		add(comboBoxTipo);
		comboBoxTipo.addItem("General");
		comboBoxTipo.addItem("Liquido");
		
		//JLabel
		JLabel lblUnidadMedida = new JLabel("Unidad de Medida");
		lblUnidadMedida.setBounds(50, 51, 120, 20);
		add(lblUnidadMedida);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(50, 86, 120, 20);
		add(lblCosto);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(50, 121, 120, 20);
		add(lblPeso);
		
		JLabel lblDensidad = new JLabel("Densidad");
		lblDensidad.setBounds(50, 156, 120, 20);
		add(lblDensidad);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(50, 226, 120, 20);
		add(lblDescripcion);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(50, 191, 120, 20);
		add(lblTipo);
		
	}

	public void informarValidacion(String error) {
		JOptionPane.showMessageDialog(null, error);
	}
	
	private void limpiar() {
		textFieldCosto.setText(null);
		textFieldPeso.setText(null);
		textFieldDensidad.setText(null);
		textFieldUnidadMedida.setText(null);
		textFieldDescripcion.setText(null);
	}
	
	public void cargarFilaSeleccionada(ModeloTablaInsumo mti, int fila) {
		textFieldDescripcion.setText(mti.getValueAt(fila, 1).toString());
		textFieldUnidadMedida.setText(mti.getValueAt(fila, 2).toString());
		textFieldCosto.setText(mti.getValueAt(fila, 3).toString());
		if(mti.getValueAt(fila, 4).equals(0)) {
			ic.cambiarInsumo("liquido");
			textFieldDensidad.setText(mti.getValueAt(fila, 5).toString());
			textFieldPeso.setEditable(false);
			textFieldDensidad.setEditable(true);
			textFieldPeso.setText("");
		}
		else {
			ic.cambiarInsumo("general");
			textFieldPeso.setText(mti.getValueAt(fila, 4).toString());
			textFieldDensidad.setEditable(false);
			textFieldPeso.setEditable(true);
			textFieldDensidad.setText("");
		}
	}

}
