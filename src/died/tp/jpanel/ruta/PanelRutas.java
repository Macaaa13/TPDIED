package died.tp.jpanel.ruta;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import died.tp.controllers.PlantaController;
import died.tp.controllers.RutaController;
import died.tp.dominio.Planta;
import died.tp.jframes.MenuRutas;
import died.tp.jpanel.camion.PanelCamiones;
import died.tp.jpanel.planta.PanelPlantas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;

public class PanelRutas extends JPanel {
	private PlantaController pc;
	private RutaController rc;
	private JTextField textFieldDistancia;
	private JTextField textFieldDuracionEstimada;
	private JTextField textFieldPesoMaximo;
	private JComboBox comboBoxOrigen;
	private JComboBox comboBoxDestino;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	
	//Getters y Setters

	public JTextField getTextFieldDistancia() {
		return textFieldDistancia;
	}

	public void setTextFieldDistancia(JTextField textFieldDistancia) {
		this.textFieldDistancia = textFieldDistancia;
	}

	public JTextField getTextFieldDuracionEstimada() {
		return textFieldDuracionEstimada;
	}

	public void setTextFieldDuracionEstimada(JTextField textFieldDuracionEstimada) {
		this.textFieldDuracionEstimada = textFieldDuracionEstimada;
	}

	public JTextField getTextFieldPesoMaximo() {
		return textFieldPesoMaximo;
	}

	public void setTextFieldPesoMaximo(JTextField textFieldPesoMaximo) {
		this.textFieldPesoMaximo = textFieldPesoMaximo;
	}
	
	public JComboBox getComboBoxOrigen() {
		return comboBoxOrigen;
	}

	public void setComboBoxOrigen(JComboBox comboBoxOrigen) {
		this.comboBoxOrigen = comboBoxOrigen;
	}

	public JComboBox getComboBoxDestino() {
		return comboBoxDestino;
	}

	public void setComboBoxDestino(JComboBox comboBoxDestino) {
		this.comboBoxDestino = comboBoxDestino;
	}


	/**
	 * Create the panel.
	 */
	public PanelRutas() {

		setLayout(null);
		setSize(550, 400);
		
		pc = new PlantaController(this);
		rc = new RutaController(this); 
		
		JLabel lblPlantaOrigen = new JLabel("Planta Origen:");
		lblPlantaOrigen.setBounds(50, 50, 120, 20);
		add(lblPlantaOrigen);
		
		JLabel lblPlantaDestino = new JLabel("Planta Destino:");
		lblPlantaDestino.setBounds(50, 90, 120, 20);
		add(lblPlantaDestino);
		
		comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(240, 90, 160, 22);
		add(comboBoxDestino);
		
		comboBoxOrigen = new JComboBox();
		for(Planta p: pc.getPlantas()) {
			comboBoxOrigen.addItem(p.getNombrePlanta());
		}
		comboBoxOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOrigen.getSelectedIndex() != -1) {
					comboBoxDestino.setEnabled(true);
					List<Planta> lista = pc.getPlantas();
					lista.remove(comboBoxOrigen.getSelectedIndex());
					comboBoxDestino.removeAllItems();
					for(Planta p: lista) {
						comboBoxDestino.addItem(p.getNombrePlanta());
					}
					comboBoxDestino.setSelectedIndex(-1);
				}
			}
		});
		comboBoxOrigen.setBounds(240, 50, 160, 22);
		add(comboBoxOrigen);
		
		JLabel lblDistancia = new JLabel("Distancia [km]:");
		lblDistancia.setBounds(50, 130, 133, 20);
		add(lblDistancia);
		
		textFieldDistancia = new JTextField();
		textFieldDistancia.setBounds(240, 130, 160, 20);
		add(textFieldDistancia);
		textFieldDistancia.setColumns(10);
		
		JLabel lblDuracionEstimadahs = new JLabel("Duracion Estimada [hs]:");
		lblDuracionEstimadahs.setBounds(50, 170, 180, 20);
		add(lblDuracionEstimadahs);
		
		textFieldDuracionEstimada = new JTextField();
		textFieldDuracionEstimada.setColumns(10);
		textFieldDuracionEstimada.setBounds(240, 170, 160, 20);
		add(textFieldDuracionEstimada);
		
		JLabel lblPesoMximokg = new JLabel("Peso m\u00E1ximo [kg]:");
		lblPesoMximokg.setBounds(50, 210, 133, 20);
		add(lblPesoMximokg);
		
		textFieldPesoMaximo = new JTextField();
		textFieldPesoMaximo.setColumns(10);
		textFieldPesoMaximo.setBounds(240, 210, 160, 20);
		add(textFieldPesoMaximo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rc.camposVacios()) {
					if(rc.camposCorrectos()) {
						rc.agregarRuta(pc.traerPlanta(comboBoxOrigen.getSelectedItem().toString()), 
									   pc.traerPlanta(comboBoxDestino.getSelectedItem().toString()));
						limpiar();
					}
				}
			}
		});
		btnAgregar.setBounds(50, 280, 120, 30);
		add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta = JOptionPane.showConfirmDialog(null, "¿Desea volver? \n Los datos no guardados se perderán", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(rta == JOptionPane.OK_OPTION) {
					Window w = SwingUtilities.getWindowAncestor(PanelRutas.this);
					w.dispose();
					MenuRutas mr = new MenuRutas();
				}
			}
		});
		btnCancelar.setBounds(280, 280, 120, 30);
		add(btnCancelar);
		comboBoxDestino.setEnabled(false);
		comboBoxOrigen.setSelectedIndex(-1);
		
	}
	
	
	//Métodos
	public void limpiar() {
		comboBoxOrigen.setSelectedIndex(-1);
		comboBoxDestino.setSelectedIndex(-1);
		textFieldDistancia.setText(null);
		textFieldDuracionEstimada.setText(null);
		textFieldPesoMaximo.setText(null);
	}
	
	public void informarValidacion(String error) {
		JOptionPane.showMessageDialog(null, error);
	}
	
}
