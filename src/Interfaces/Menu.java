package Interfaces;

import javax.swing.*;

public class Menu extends JFrame {

	JButton menuCamiones;
	JButton menuPlantas;
	JButton menuInsumos;
	JButton menuStock;
	JButton menuPedidos;
	
	private void iniciar() {
		menuCamiones = new JButton("Camiones");
		menuPlantas = new JButton("Plantas");
		menuInsumos = new JButton("Insumos");
		menuStock = new JButton("Stock");
		menuPedidos = new JButton("Pedidos");
	}
	
}
