package died.tp.jframes;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import died.tp.jpanel.camion.AgregarCamion;
import died.tp.jpanel.camion.BuscarCamion;
import died.tp.jpanel.camion.EditarCamion;

import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCamiones extends JFrame {

	private JPanel PanelMenuCamiones;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public MenuCamiones() {
		this.setLocationRelativeTo(getRootPane());
		setTitle("Camiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		PanelMenuCamiones = new JPanel();
		PanelMenuCamiones.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelMenuCamiones);
		PanelMenuCamiones.setLayout(null);
		
		JButton agregarCamion = new JButton("Agregar");
		agregarCamion.addActionListener(e -> {
			this.setContentPane(new AgregarCamion());
			this.revalidate();
			this.repaint();
		});
		agregarCamion.setBounds(210, 70, 120, 30);
		PanelMenuCamiones.add(agregarCamion);
		
		JButton editarCamion = new JButton("Editar");
		editarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editarCamion.setBounds(210, 115, 120, 30);
		PanelMenuCamiones.add(editarCamion);
		
		JButton eliminarCamion = new JButton("Eliminar");
		eliminarCamion.setBounds(210, 160, 120, 30);
		PanelMenuCamiones.add(eliminarCamion);
		
		JButton buscarCamion = new JButton("Buscar");
		buscarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamion bc = new BuscarCamion();
				setContentPane(bc);
			}
		});
		buscarCamion.setBounds(210, 205, 120, 30);
		PanelMenuCamiones.add(buscarCamion);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(210, 250, 120, 30);
		PanelMenuCamiones.add(volver);
	}

}
