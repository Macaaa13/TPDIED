package died.tp.jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import died.tp.jpanel.pedido.PanelInformacionOrden;
import died.tp.jpanel.pedido.PanelProcesarOrden;
import died.tp.jpanel.pedido.PanelOrdenEntregada;
import died.tp.jpanel.pedido.RegistrarOrden;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPedidos extends JFrame {

	private JPanel contentPane;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public MenuPedidos() {
		setTitle("Pedidos");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(dim.width/2-275,dim.height/2-200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal();
				mp.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(195, 234, 142, 30);
		contentPane.add(volver);
		
		JButton btnRegistrarOrden = new JButton("Registrar orden");
		btnRegistrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-450,dim.height/2-225, 950, 500);
				setContentPane(new PanelOrdenEntregada());
				setTitle("Registrar orden pedido");
			}
		});
		btnRegistrarOrden.setBounds(195, 75, 142, 30);
		contentPane.add(btnRegistrarOrden);
		
		JButton btnInformacionOrden =new JButton("Procesar orden");
		btnInformacionOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-310,dim.height/2-225, 635, 450);
				setContentPane(new PanelInformacionOrden(getMenu()));
				setTitle("Procesar Orden de pedido");
			}
		});
		btnInformacionOrden.setBounds(195, 127, 142, 30);
		contentPane.add(btnInformacionOrden);
		
		JButton btnNewButton = new JButton("Registrar entrega");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(dim.width/2-450,dim.height/2-225, 900, 450);
				setContentPane(new PanelProcesarOrden());
				setTitle("Registrar orden pedido");
				
			}
		});
		btnNewButton.setBounds(195, 179, 142, 30);
		contentPane.add(btnNewButton);
	}
	
	public MenuPedidos getMenu() {
		return this;
	}
}