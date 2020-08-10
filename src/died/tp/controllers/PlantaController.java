package died.tp.controllers;

import java.util.*;
import died.tp.dao.PlantaDao;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;
import died.tp.grafos.GrafoRutas;
import died.tp.jpanel.planta.PanelPlantas;
import died.tp.jpanel.ruta.PanelRutas;

public class PlantaController {

	//Atributos
	 private PlantaDao psd;
	 private PanelPlantas pp;
	 private List<Planta> lista;
	 private GrafoRutas gr;
	 private PanelRutas pr;
	 
	 //Constructor
	 public PlantaController(){
		 psd = new PlantaDao();
	 }
	 
	 public PlantaController(PanelPlantas ps) {
		 psd = new PlantaDao();
		 pp = ps;
		 lista = new ArrayList<Planta>();
		 gr = new GrafoRutas();
	 }
	 
	 public PlantaController(PanelRutas pr) {
		 psd = new PlantaDao();
		 this.pr = pr;
		 lista = new ArrayList<Planta>();
		 gr = new GrafoRutas();
	 }
	 
	 public List<Planta> getPlantas() {
			return psd.traerPlantas();
		}
	 
	 public void agregarPlanta() {
		 	String nombrePlanta = pp.getTextFieldPlanta().getText();
			psd.altaPlanta(nombrePlanta);
		}
	 
	 public Planta traerPlanta(String planta) {
		 for(Planta p: this.getPlantas()) {
			 if(p.getNombrePlanta().equals(planta)) {
				 return p;
			 }
		 }
		 return null;
	 }
	 
}
