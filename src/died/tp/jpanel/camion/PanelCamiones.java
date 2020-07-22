package died.tp.jpanel.camion;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import died.tp.excepciones.*;
import died.tp.controllers.CamionController;
import died.tp.jframes.MenuPrincipal;
import java.awt.Component;
import javax.swing.Box;

public class PanelCamiones extends JPanel {
	
	//Atributos
	private JTextField textFieldPatente;
	private JTextField textFieldCostoHora;
	private JTextField textFieldCostoKM;
	private JTextField textFieldKMRecorridos;
	private JTextField textFieldModelo;
	private JTextField textFieldMarca;
	private JDateChooser dateChooserFechaCompra;
	private CamionController cc;

	
	//Getters y Setters
	public JTextField getTextFieldPatente() {
		return textFieldPatente;
	}

	public void setTextFieldPatente(JTextField textFieldPatente) {
		this.textFieldPatente = textFieldPatente;
	}

	public JTextField getTextFieldCostoHora() {
		return textFieldCostoHora;
	}

	public void setTextFieldCostoHora(JTextField textFieldCostoHora) {
		this.textFieldCostoHora = textFieldCostoHora;
	}

	public JTextField getTextFieldCostoKM() {
		return textFieldCostoKM;
	}

	public void setTextFieldCostoKM(JTextField textFieldCostoKM) {
		this.textFieldCostoKM = textFieldCostoKM;
	}

	public JTextField getTextFieldKMRecorridos() {
		return textFieldKMRecorridos;
	}

	public void setTextFieldKMRecorridos(JTextField textFieldKMRecorridos) {
		this.textFieldKMRecorridos = textFieldKMRecorridos;
	}

	public JTextField getTextFieldModelo() {
		return textFieldModelo;
	}

	public void setTextFieldModelo(JTextField textFieldModelo) {
		this.textFieldModelo = textFieldModelo;
	}
	public JTextField getTextFieldMarca() {
		return textFieldMarca;
	}

	public void setTextFieldMarca(JTextField textFieldMarca) {
		this.textFieldMarca = textFieldMarca;
	}

	public JDateChooser getDateChooserFechaCompra() {
		return dateChooserFechaCompra;
	}

	public void setDateChooserFechaCompra(JDateChooser dateChooserFechaCompra) {
		this.dateChooserFechaCompra = dateChooserFechaCompra;
	}

	
	/**
	 * Create the panel.
	 */
	public PanelCamiones() {
		setLayout(null);
		setSize(550,400);
		
		cc = new CamionController(this);
		
		JLabel lblPatente = new JLabel("Patente:");
		lblPatente.setBounds(50, 51, 120, 20);
		add(lblPatente);
		
		textFieldPatente = new JTextField();
		textFieldPatente.setColumns(10);
		textFieldPatente.setBounds(180, 51, 120, 20);
		add(textFieldPatente);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cc.guardar();
					JOptionPane.showMessageDialog(null, "Camión agregado", "Acción exitosa", JOptionPane.PLAIN_MESSAGE);
					limpiar();
				} catch(DatosObligatoriosException | FormatoNumeroException ex) {
					System.out.println(ex.getMessage());;
				}
			}
		});
		btnAgregar.setBounds(380, 51, 120, 30);
		add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(380, 91, 120, 30);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(380, 131, 120, 30);
		add(btnEliminar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(380, 171, 120, 30);
		add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir? \n Los datos se perderán.", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(resp == JOptionPane.OK_OPTION) {
					MenuPrincipal mp = new MenuPrincipal();
					mp.setVisible(true);
				}
			}
		});
		btnVolver.setBounds(380, 251, 120, 30);
		add(btnVolver);
		
		dateChooserFechaCompra = new JDateChooser();
		dateChooserFechaCompra.setBounds(180, 261, 120, 20);
		dateChooserFechaCompra.setDate(Date.valueOf(LocalDate.now()));
		add(dateChooserFechaCompra);
		
		textFieldCostoHora = new JTextField();
		textFieldCostoHora.setColumns(10);
		textFieldCostoHora.setBounds(180, 226, 120, 20);
		add(textFieldCostoHora);
		
		textFieldCostoKM = new JTextField();
		textFieldCostoKM.setColumns(10);
		textFieldCostoKM.setBounds(180, 191, 120, 20);
		add(textFieldCostoKM);
		
		textFieldKMRecorridos = new JTextField();
		textFieldKMRecorridos.setColumns(10);
		textFieldKMRecorridos.setBounds(180, 156, 120, 20);
		add(textFieldKMRecorridos);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(180, 121, 120, 20);
		add(textFieldModelo);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(180, 86, 120, 20);
		add(textFieldMarca);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(50, 86, 120, 20);
		add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(50, 121, 120, 20);
		add(lblModelo);
		
		JLabel label_3 = new JLabel("KM recorridos:");
		label_3.setBounds(50, 156, 120, 20);
		add(label_3);
		
		JLabel label_4 = new JLabel("Costo por KM:");
		label_4.setBounds(50, 191, 120, 20);
		add(label_4);
		
		JLabel label_5 = new JLabel("Costo por hora:");
		label_5.setBounds(50, 226, 120, 20);
		add(label_5);
		
		JLabel label_6 = new JLabel("Fecha de compra:");
		label_6.setBounds(50, 261, 120, 20);
		add(label_6);
		
	}
	
	public void limpiar() {
		textFieldPatente.setText("");
		textFieldMarca.setText("");
		textFieldModelo.setText("");
		textFieldKMRecorridos.setText("");
		textFieldCostoHora.setText("");
		textFieldCostoKM.setText("");
		dateChooserFechaCompra.setDate(Date.valueOf(LocalDate.now()));
	}
}
