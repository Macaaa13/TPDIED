package died.tp.controllers;

import java.util.*;

import died.tp.dao.InsumoDao;
import died.tp.dominio.Insumo;
import died.tp.jpanel.insumo.PanelInsumos;

public class InsumoController {

	//Atributos
	private Insumo i;
	private List<Insumo> listaInsumos;
	private PanelInsumos pi;
	private InsumoDao id;
	
	
	//Constructor
	public InsumoController(PanelInsumos pi) {
		this.pi = pi;
		listaInsumos = new ArrayList<Insumo>();
		id = new InsumoDao();
	}
	
	
	//
	
}
