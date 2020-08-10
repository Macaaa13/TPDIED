package died.tp.jpanel.pedido;

import javax.swing.JPanel;

import died.tp.controllers.InformacionOrdenController;
import died.tp.dominio.OrdenDePedido;
import died.tp.dominio.Planta;
import died.tp.jframes.MenuPedidos;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class RegistrarOrden extends JPanel {

	//Atributos
	private JComboBox<String> comboBoxPlantas;
	InformacionOrdenController ordenController;
	
	/**
	 * Create the panel.
	 */
	public RegistrarOrden(OrdenDePedido op, MenuPedidos mp, PanelInformacionOrden pio, InformacionOrdenController odc) {
		setLayout(null);
		setSize(635,450);
		setSize(635,450);

		this.ordenController = odc;

		comboBoxPlantas = new JComboBox<String>();
		comboBoxPlantas.setBounds(118, 32, 90, 22);
		add(comboBoxPlantas);
		cargarPlantas();
		
		JLabel lblNewLabel = new JLabel("Plantas con stock");
		lblNewLabel.setBounds(18, 36, 90, 14);
		add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 70, 90, 22);
		add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Tipo de ruta");
		lblNewLabel_1.setBounds(18, 74, 90, 14);
		add(lblNewLabel_1);
	}
	
	public void cargarPlantas() {
		if(ordenController.getPlantas() != null) {
			for(Planta s: ordenController.getPlantas()) {
				comboBoxPlantas.addItem(s.getNombrePlanta());
				}
			}
	}
	
	public JComboBox<String> getComboBoxPlantas() {
		return comboBoxPlantas;
	}


	public void setComboBoxPlantas(JComboBox<String> comboBoxPlantas) {
		this.comboBoxPlantas = comboBoxPlantas;
	}
}
