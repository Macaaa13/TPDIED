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

public class BuscarCamion extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public BuscarCamion() {
		setLayout(null);
		setSize(550,400);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(50, 30, 120, 20);
		add(lblPatente);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(180, 30, 150, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("Marca");
		label_1.setBounds(50, 65, 120, 20);
		add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 65, 150, 20);
		add(textField_1);
		
		JLabel label_2 = new JLabel("Modelo");
		label_2.setBounds(50, 100, 120, 20);
		add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 100, 150, 20);
		add(textField_2);
		
		JLabel label_3 = new JLabel("KM recorridos:");
		label_3.setBounds(50, 135, 120, 20);
		add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 135, 150, 20);
		add(textField_3);
		
		JLabel label_4 = new JLabel("Costo por KM:");
		label_4.setBounds(50, 170, 120, 20);
		add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(180, 170, 150, 20);
		add(textField_4);
		
		JLabel label_5 = new JLabel("Costo por hora:");
		label_5.setBounds(50, 205, 120, 20);
		add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(180, 205, 150, 20);
		add(textField_5);
		
		JLabel label_6 = new JLabel("Fecha de compra:");
		label_6.setBounds(50, 240, 120, 20);
		add(label_6);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(180, 240, 150, 20);
		add(dateChooser);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(365, 250, 120, 30);
		add(btnBuscar);
		
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
		btnVolver.setBounds(365, 300, 120, 30);
		add(btnVolver);
	}

}
