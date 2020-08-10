package died.tp.jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import died.tp.controllers.PlantaController;
import died.tp.jpanel.planta.PanelPlantas;
import died.tp.jpanel.ruta.PanelRutas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuRutas extends JFrame {
	
	//Atributos
	private JPanel panelInicial;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
	private PlantaController pc;

	/**
	 * Create the frame.
	 */
	public MenuRutas() {
		setVisible(true);
		setTitle("Rutas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		panelInicial = new JPanel();
		panelInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelInicial);
		panelInicial.setLayout(null);
		
		pc = new PlantaController();
		
		JButton btnCrearRuta = new JButton("Crear Ruta");
		btnCrearRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pc.getPlantas().size()>1) {
					setContentPane(new PanelRutas());
				} else {
					JOptionPane.showMessageDialog(null, "No existen suficientes plantas como para crear una ruta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCrearRuta.setBounds(210, 90, 120, 30);
		panelInicial.add(btnCrearRuta);
		
		JButton btnFlujoMax = new JButton("Flujo M\u00E1ximo");
		btnFlujoMax.setBounds(210, 130, 120, 30);
		panelInicial.add(btnFlujoMax);
		
		JButton btnPageRank = new JButton("Page Rank");
		btnPageRank.setBounds(210, 170, 120, 30);
		panelInicial.add(btnPageRank);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
			}
		});
		btnVolver.setBounds(210, 251, 120, 30);
		panelInicial.add(btnVolver);
	}

}
