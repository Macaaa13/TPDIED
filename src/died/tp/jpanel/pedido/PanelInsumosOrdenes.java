package died.tp.jpanel.pedido;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import died.tp.dominio.OrdenDePedido;
import died.tp.jframes.MenuPedidos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInsumosOrdenes extends JPanel {


	public PanelInsumosOrdenes(OrdenDePedido ordenPedido, MenuPedidos mp, PanelInformacionOrden pio) {
		setLayout(null);
		setSize(635, 450);
		setSize(635, 450);

		TablaParaInsumos tablaModelo = new TablaParaInsumos();
		JTable tablaDatos = new JTable(tablaModelo);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tablaDatos.setDefaultRenderer(String.class, centerRenderer);

		JScrollPane scrollPanel = new JScrollPane(tablaDatos);
		scrollPanel.setBounds(19, 25, 580, 329);
		add(scrollPanel, BorderLayout.CENTER);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.setContentPane(pio);
			}
		});
		btnVolver.setBounds(510, 365, 89, 23);
		add(btnVolver);
		tablaModelo.mostrar(ordenPedido.getInsumos(), ordenPedido.getNroOrden());

	}
}
