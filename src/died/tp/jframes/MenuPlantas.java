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

public class MenuPlantas extends JFrame {

	private JPanel menuPlantas;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public MenuPlantas() {
		setTitle("Plantas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		menuPlantas = new JPanel();
		menuPlantas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(menuPlantas);
		menuPlantas.setLayout(null);
		
		JButton agregarPlanta = new JButton("Agregar");
		agregarPlanta.setBounds(210, 70, 120, 30);
		menuPlantas.add(agregarPlanta);
		
		JButton modificarPlanta = new JButton("Modificar");
		modificarPlanta.setBounds(210, 115, 120, 30);
		menuPlantas.add(modificarPlanta);
		
		JButton eliminarPlanta = new JButton("Eliminar");
		eliminarPlanta.setBounds(210, 160, 120, 30);
		menuPlantas.add(eliminarPlanta);
		
		JButton buscarPlanta = new JButton("Buscar");
		buscarPlanta.setBounds(210, 205, 120, 30);
		menuPlantas.add(buscarPlanta);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(210, 250, 120, 30);
		menuPlantas.add(volver);
	}

}
