package died.tp.controllers;

import died.tp.servicios.*;
import died.tp.dominio.*;
import died.tp.jpanel.camion.*;
import java.util.*;

public class CamionController {

	//Atributos
	private ServicioCamion cs;
	private Camion c;
	private List<Camion> lista;
	private AgregarCamion panelAgregar;
	
	//----- Constructores -----
	// Constructor para Agregar Camión
	public CamionController(AgregarCamion ac) {
		cs = new ServicioCamion();
		lista = new ArrayList<Camion>();
		panelAgregar = ac;
		c = new Camion();
	}
	
	//Métodos
	public Camion guardar() {
		return new Camion();
	}
	
	public void actualizarModelo() {

	}
	
}
