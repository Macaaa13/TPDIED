package died.tp.jpanel.planta;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import died.tp.controllers.PlantaController;
import died.tp.dominio.Planta;
import died.tp.grafos.GrafoRutas;
import died.tp.jframes.MenuPrincipal;
import died.tp.jpanel.camion.PanelCamiones;
import died.tp.jpanel.ruta.PanelRutas;

public class PanelPlantas extends JPanel {

	//Atributos
	private PlantaController pc;
	private JTextField textFieldPlanta;
	private GrafoRutas gp;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	//Getters y Setters
	public PlantaController getPc() {
		return pc;
	}

	public void setPc(PlantaController pc) {
		this.pc = pc;
	}

	public JTextField getTextFieldPlanta() {
		return textFieldPlanta;
	}

	public void setTextFieldPlanta(JTextField textFieldPlanta) {
		this.textFieldPlanta = textFieldPlanta;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelPlantas() {
		setLayout(null);
		setSize(1100,405);
		
		pc = new PlantaController(this);

		ModeloTablaPlanta tablaModelo = new ModeloTablaPlanta();
		JTable tablaDatos = new JTable(tablaModelo);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tablaDatos.setDefaultRenderer(String.class, centerRenderer);

		JScrollPane scrollPanel = new JScrollPane(tablaDatos);
		scrollPanel.setBounds(299, 59, 411, 280);
		add(scrollPanel, BorderLayout.CENTER);
		
		textFieldPlanta = new JTextField();
		textFieldPlanta.setBounds(161, 59, 128, 20);
		add(textFieldPlanta);
		textFieldPlanta.setColumns(10);
	
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(22, 64, 129, 14);
		add(lblNewLabel);	
		
		JButton btnAgregarPlanta = new JButton("Agregar Planta");
		btnAgregarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.agregarPlanta();
				JOptionPane.showMessageDialog(null, "Planta agregada");
				limpiar();
			}
		});
		btnAgregarPlanta.setBounds(22, 93, 139, 23);
		add(btnAgregarPlanta);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				btnAgregarPlanta.setEnabled(true);
				btnCancelar.setEnabled(false);
			}
		});
		btnCancelar.setBounds(522, 350, 89, 23);
		add(btnCancelar);
		
		JButton btnBuscar = new JButton("Mostrar Plantas");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Planta> plantas = pc.getPlantas();
				if(!plantas.isEmpty()) {
				tablaModelo.mostrar(plantas);
				btnAgregarPlanta.setEnabled(false);
				btnCancelar.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay resultados por mostrar");
				}
				tablaModelo.fireTableDataChanged();
			}
		});
		btnBuscar.setBounds(22, 122, 139, 23);
		add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal? \n Los datos no guardados se perderán", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(rta == JOptionPane.OK_OPTION) {
					Window w = SwingUtilities.getWindowAncestor(PanelPlantas.this);
					w.dispose();
					MenuPrincipal mp = new MenuPrincipal();
					mp.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(621, 350, 89, 23);
		add(btnVolver);
		
		btnCancelar.setEnabled(false);
		
	}
	
	public void limpiar() {
		textFieldPlanta.setText(null);
	}
	
	
}
