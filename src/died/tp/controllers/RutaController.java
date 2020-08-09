package died.tp.controllers;

import died.tp.dao.RutaDao;
import died.tp.dominio.Ruta;

public class RutaController {

	//Atributos
	private RutaDao rd;
	
	
	//Constructores
	public RutaController() {
		rd = new RutaDao();
	}
	
	
	//Métodos
	public void agregarRuta(Ruta r) {
		rd.agregarRuta(r);
	}
	
}
