package died.tp.controllers;

import java.util.*;
import died.tp.dao.PlantaDao;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;
import died.tp.jpanel.planta.PanelPlantas;

public class PlantaController {

	//Atributos
	 private PlantaDao psd;
	 private PanelPlantas pp;
	 private List<Planta> lista;
	 private RutaController rc;
	 
	 //Constructor
	 public PlantaController(PanelPlantas ps) {
		 psd = new PlantaDao();
		 pp = ps;
		 lista = new ArrayList<Planta>();
		 rc = new RutaController();
	 }
	 
	 public List<Planta> getPlantas() {
			return psd.traerPlantas();
		}
	 
	 public void agregarPlanta() {
		 	String nombrePlanta = pp.getTextFieldPlanta().getText();
			psd.altaPlanta(nombrePlanta);
			lista = this.getPlantas();
			if(lista.size()>1) {
				Planta destino = lista.get(new Random().nextInt(lista.size()-1));
				Ruta r = new Ruta(lista.get(lista.size()-1), destino);
				rc.agregarRuta(r);
			}
		}
	 
}
