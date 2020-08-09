package died.tp.controllers;

import died.tp.dao.RutaDao;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;
import died.tp.grafos.Arista;

public class RutaController {

	//Atributos
	private RutaDao rd;
	
	
	//Constructores
	public RutaController() {
		rd = new RutaDao();
	}
	
	
	//Métodos
	public void agregarRuta(Arista<Planta> a) {
		rd.agregarRuta(a);
	}
	
}
