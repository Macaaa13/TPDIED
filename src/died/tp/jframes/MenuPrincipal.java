package died.tp.jframes;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import died.tp.jpanel.camion.PanelCamiones;
import died.tp.jpanel.insumo.PanelInsumos;
import died.tp.jpanel.planta.PanelPlantas;
import died.tp.jpanel.stock.PanelStock;

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
		botonMenuCamiones.setBounds(210, 58, 120, 30);
		PanelInicial.add(botonMenuCamiones);
		
		JButton botonMenuPlantas = new JButton("Plantas");
		botonMenuPlantas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-350,dim.height/2-225, 750, 450);
				setContentPane(new PanelPlantas());
				setTitle("Plantas");
			}
		});
		botonMenuPlantas.setBounds(210, 103, 120, 30);
		PanelInicial.add(botonMenuPlantas);
		
		JButton botonMenuInsumos = new JButton("Insumos");
		botonMenuInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-600,dim.height/2-225, 1200, 450);
				setContentPane(new PanelInsumos());
				setTitle("Insumos");
			}
		});
		botonMenuInsumos.setBounds(210, 148, 120, 30);
		PanelInicial.add(botonMenuInsumos);
		
		JButton botonMenuStock = new JButton("Stock");
		botonMenuStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-450,dim.height/2-225, 900, 450);
				setContentPane(new PanelStock());
				setTitle("Stock");
			}
		});
		botonMenuStock.setBounds(210, 193, 120, 30);
		PanelInicial.add(botonMenuStock);
		
		JButton botonMenuPedidos = new JButton("Pedidos");
		botonMenuPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		botonMenuPedidos.setBounds(210, 238, 120, 30);
		PanelInicial.add(botonMenuPedidos);
		
		JButton btnRutas = new JButton("Rutas");
		btnRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuRutas mr = new MenuRutas();
				dispose();
			}
		});
		btnRutas.setBounds(210, 279, 120, 30);
		PanelInicial.add(btnRutas);
	}
}
