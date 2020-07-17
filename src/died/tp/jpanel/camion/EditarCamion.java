package died.tp.jpanel.camion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import died.tp.jframes.MenuCamiones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCamion extends JPanel {
	private JTextField textFieldPatente;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldKMRec;
	private JTextField textFieldCostoKM;
	private JTextField textFieldCostoHora;

	/**
	 * Create the panel.
	 */
	public EditarCamion() {
		setLayout(null);
		setSize(550, 400);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(50, 30, 120, 20);
		add(lblPatente);
		
		textFieldPatente = new JTextField();
		textFieldPatente.setColumns(10);
		textFieldPatente.setBounds(180, 30, 150, 20);
		add(textFieldPatente);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(180, 65, 150, 20);
		add(textFieldMarca);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(50, 65, 120, 20);
		add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(50, 100, 120, 20);
		add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(180, 100, 150, 20);
		add(textFieldModelo);
		
		textFieldKMRec = new JTextField();
		textFieldKMRec.setColumns(10);
		textFieldKMRec.setBounds(180, 135, 150, 20);
		add(textFieldKMRec);
		
		JLabel lblKMRecorridos = new JLabel("KM recorridos:");
		lblKMRecorridos.setBounds(50, 135, 120, 20);
		add(lblKMRecorridos);
		
		JLabel lblCostoKM = new JLabel("Costo por KM:");
		lblCostoKM.setBounds(50, 170, 120, 20);
		add(lblCostoKM);
		
		textFieldCostoKM = new JTextField();
		textFieldCostoKM.setColumns(10);
		textFieldCostoKM.setBounds(180, 170, 150, 20);
		add(textFieldCostoKM);
		
		textFieldCostoHora = new JTextField();
		textFieldCostoHora.setColumns(10);
		textFieldCostoHora.setBounds(180, 205, 150, 20);
		add(textFieldCostoHora);
		
		JLabel lblCostoHora = new JLabel("Costo por hora:");
		lblCostoHora.setBounds(50, 205, 120, 20);
		add(lblCostoHora);
		
		JLabel lblFechaCompra = new JLabel("Fecha de compra:");
		lblFechaCompra.setBounds(50, 240, 120, 20);
		add(lblFechaCompra);
		
		JDateChooser dateChooserFechaCompra = new JDateChooser();
		dateChooserFechaCompra.setBounds(180, 240, 150, 20);
		add(dateChooserFechaCompra);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(375, 275, 120, 30);
		add(btnEditar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(resp == JOptionPane.OK_OPTION) {
					MenuCamiones mc = new MenuCamiones();
					mc.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(375, 325, 120, 30);
		add(btnVolver);
		
		
	}
}
