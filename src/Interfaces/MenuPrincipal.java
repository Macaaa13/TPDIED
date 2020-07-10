package Interfaces;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
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
		setBounds(100, 100, 450, 300);
		PanelInicial = new JPanel();
		PanelInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelInicial);
		PanelInicial.setLayout(null);
		
		JButton botonMenuCamiones = new JButton("Camiones");
		botonMenuCamiones.setBounds(164, 49, 89, 23);
		PanelInicial.add(botonMenuCamiones);
		
		JButton botonMenuPlantas = new JButton("Plantas");
		botonMenuPlantas.setBounds(164, 83, 89, 23);
		PanelInicial.add(botonMenuPlantas);
		
		JButton botonMenuInsumos = new JButton("Insumos");
		botonMenuInsumos.setBounds(164, 117, 89, 23);
		PanelInicial.add(botonMenuInsumos);
		
		JButton botonMenuStock = new JButton("Stock");
		botonMenuStock.setBounds(164, 151, 89, 23);
		PanelInicial.add(botonMenuStock);
		
		JButton botonMenuPedidos = new JButton("Pedidos");
		botonMenuPedidos.setBounds(164, 185, 89, 23);
		PanelInicial.add(botonMenuPedidos);
	}
}
