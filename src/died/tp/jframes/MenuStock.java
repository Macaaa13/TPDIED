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

public class MenuStock extends JFrame {

	private JPanel contentPane;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public MenuStock() {
		setTitle("Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton agregarStock = new JButton("Agregar");
		agregarStock.setBounds(210, 70, 120, 30);
		contentPane.add(agregarStock);
		
		JButton modificarStock = new JButton("Modificar");
		modificarStock.setBounds(210, 115, 120, 30);
		contentPane.add(modificarStock);
		
		JButton eliminarStock = new JButton("Eliminar");
		eliminarStock.setBounds(210, 160, 120, 30);
		contentPane.add(eliminarStock);
		
		JButton buscarStock = new JButton("Buscar");
		buscarStock.setBounds(210, 205, 120, 30);
		contentPane.add(buscarStock);
		
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
