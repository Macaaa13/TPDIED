package died.tp.jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuInsumos extends JFrame {

	private JPanel contentPane;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public MenuInsumos() {
		setTitle("Insumos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton agregarInsumo = new JButton("Agregar");
		agregarInsumo.setBounds(210, 70, 120, 30);
		contentPane.add(agregarInsumo);
		
		JButton modificarInsumo = new JButton("Modificar");
		modificarInsumo.setBounds(210, 115, 120, 30);
		contentPane.add(modificarInsumo);
		
		JButton eliminarInsumo = new JButton("Eliminar");
		eliminarInsumo.setBounds(210, 160, 120, 30);
		contentPane.add(eliminarInsumo);
		
		JButton buscarInsumo = new JButton("Buscar");
		buscarInsumo.setBounds(210, 205, 120, 30);
		contentPane.add(buscarInsumo);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(210, 250, 120, 30);
		contentPane.add(volver);
	}

}
