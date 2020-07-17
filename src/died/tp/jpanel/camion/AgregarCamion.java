package died.tp.jpanel.camion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import com.toedter.calendar.JDateChooser;

import died.tp.controllers.CamionController;
import died.tp.jframes.MenuCamiones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarCamion extends JPanel {
	private JTextField textFieldPatente;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldKMRec;
	private JTextField textFieldCostoKM;
	private JTextField textFieldCostoHora;

	/**
	 * Create the panel.
	 */
	public AgregarCamion() {
		setLayout(null);
		setSize(550, 400);
		
		//Controller
		CamionController cc = new CamionController(this);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(50, 30, 120, 20);
		add(lblPatente);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(50, 65, 120, 20);
		add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(50, 100, 120, 20);
		add(lblModelo);
		
		JLabel lblKmRecorridos = new JLabel("KM recorridos:");
		lblKmRecorridos.setBounds(50, 135, 120, 20);
		add(lblKmRecorridos);
		
		JLabel lblCostoPorKm = new JLabel("Costo por KM:");
		lblCostoPorKm.setBounds(50, 170, 120, 20);
		add(lblCostoPorKm);
		
		JLabel lblCostoPorHora = new JLabel("Costo por hora:");
		lblCostoPorHora.setBounds(50, 205, 120, 20);
		add(lblCostoPorHora);
		
		JLabel lblFechaDeCompra = new JLabel("Fecha de compra:");
		lblFechaDeCompra.setBounds(50, 240, 120, 20);
		add(lblFechaDeCompra);
		
		textFieldPatente = new JTextField();
		textFieldPatente.setBounds(180, 30, 150, 20);
		add(textFieldPatente);
		textFieldPatente.setColumns(10);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(180, 65, 150, 20);
		textFieldMarca.setColumns(10);
		add(textFieldMarca);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(180, 100, 150, 20);
		textFieldModelo.setColumns(10);
		add(textFieldModelo);
		
		textFieldKMRec = new JTextField();
		textFieldKMRec.setBounds(180, 135, 150, 20);
		textFieldKMRec.setColumns(10);
		add(textFieldKMRec);
		
		textFieldCostoKM = new JTextField();
		textFieldCostoKM.setBounds(180, 170, 150, 20);
		textFieldCostoKM.setColumns(10);
		add(textFieldCostoKM);
		
		textFieldCostoHora = new JTextField();
		textFieldCostoHora.setBounds(180, 205, 150, 20);
		textFieldCostoHora.setColumns(10);
		add(textFieldCostoHora);
		
		JDateChooser dateChooserFechaCompra = new JDateChooser();
		dateChooserFechaCompra.setBounds(180, 240, 150, 20);
		add(dateChooserFechaCompra);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(365, 300, 120, 30);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir? \n Los datos se perderán.", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(resp == JOptionPane.OK_OPTION) {
					MenuCamiones mc = new MenuCamiones();
					mc.setVisible(true);
				}
			}
		});
		add(btnVolver);
		
		JButton btnAgregarCamion = new JButton("Agregar");
		btnAgregarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAgregarCamion.setBounds(365, 250, 120, 30);
		add(btnAgregarCamion);

	}
}
