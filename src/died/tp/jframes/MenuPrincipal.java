package died.tp.jframes;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import died.tp.jpanel.camion.PanelCamiones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
	
	private JPanel PanelInicial;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("Men\u00FA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		PanelInicial = new JPanel();
		PanelInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelInicial);
		PanelInicial.setLayout(null);
		
		JButton botonMenuCamiones = new JButton("Camiones");
		botonMenuCamiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-600,dim.height/2-225, 1200, 450);
				setContentPane(new PanelCamiones());
				setTitle("Camiones");
			}
		});
		botonMenuCamiones.setBounds(210, 70, 120, 30);
		PanelInicial.add(botonMenuCamiones);
		
		JButton botonMenuPlantas = new JButton("Plantas");
		botonMenuPlantas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		botonMenuPlantas.setBounds(210, 115, 120, 30);
		PanelInicial.add(botonMenuPlantas);
		
		JButton botonMenuInsumos = new JButton("Insumos");
		botonMenuInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		botonMenuInsumos.setBounds(210, 160, 120, 30);
		PanelInicial.add(botonMenuInsumos);
		
		JButton botonMenuStock = new JButton("Stock");
		botonMenuStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		botonMenuStock.setBounds(210, 205, 120, 30);
		PanelInicial.add(botonMenuStock);
		
		JButton botonMenuPedidos = new JButton("Pedidos");
		botonMenuPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		botonMenuPedidos.setBounds(210, 250, 120, 30);
		PanelInicial.add(botonMenuPedidos);
	}
}
