package died.tp.controllers;

import died.tp.dao.CamionDao;
import died.tp.dominio.*;
import died.tp.excepciones.*;
import died.tp.jpanel.camion.*;

import java.time.ZoneId;
import java.util.*;

public class CamionController {

	//Atributos
	private Camion c;
	private List<Camion> lista;
	private PanelCamiones pc;
	private CamionDao cd;
	
	//----- Constructores -----
	// Constructor para Agregar Cami�n
	public CamionController(PanelCamiones pc) {
		lista = new ArrayList<Camion>();
		this.pc = pc;
		c = new Camion();
		cd = new CamionDao();
	}
	
	//M�todos
	public void guardar() {
		this.actualizarModelo();
		cd.altaActualizacionCamion(c);
		lista.clear();
		lista.addAll(cd.buscarTodos());
	}
	
	public void actualizar(Integer id) {
		this.actualizarModelo();
		c.setId(id);
		cd.altaActualizacionCamion(c);
		lista.clear();
		lista.addAll(cd.buscarTodos());
	}
	
	public void actualizarModelo() {
			c.setPatente(pc.getTextFieldPatente().getText());
			c.setMarca(pc.getTextFieldMarca().getText());
			c.setModelo(pc.getTextFieldModelo().getText());
			c.setKmRecorridos(Double.valueOf(pc.getTextFieldKMRecorridos().getText()));
			c.setCostoKM(Double.valueOf(pc.getTextFieldCostoKM().getText()));
			c.setCostoHora(Double.valueOf(pc.getTextFieldCostoHora().getText()));
			c.setFechaCompra(pc.getDateChooserFechaCompra().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public List<Camion> traerDatos() {
		return cd.buscarTodos();
	}
	
	public void eliminarCamion(Integer id) {
		cd.eliminarCamion(id);
	}
	
	public boolean validacionVacios() {
		if(pc.getTextFieldPatente().getText().isEmpty()) {pc.informarValidacion("Error, el campo patente est� vacio"); return false;}
		if(pc.getTextFieldMarca().getText().isEmpty()) {pc.informarValidacion("Error, el campo marca est� vacio"); return false;}
		if(pc.getTextFieldModelo().getText().isEmpty()) {pc.informarValidacion("Error, el campo modelo est� vacio"); return false;}
		if(pc.getTextFieldKMRecorridos().getText().isEmpty()) {pc.informarValidacion("Error, el campo kilometros recorridos est� vacio"); return false;}
		if(pc.getTextFieldCostoKM().getText().isEmpty()) {pc.informarValidacion("Error, el campo costo por kilometro est� vacio"); return false;}
		if(pc.getTextFieldCostoHora().getText().isEmpty()) {pc.informarValidacion("Error, el campo costo por hora est� vacio"); return false;}
		if(pc.getDateChooserFechaCompra() == null) {pc.informarValidacion("Error, no seleccion� la fecha"); return false;}
		
		
		return true;
	}
	public boolean camposCorrectos() {
		if(!this.isDouble(pc.getTextFieldCostoHora().getText())){ pc.informarValidacion("Error, el campo costo Hora no es un n�mero"); return false;}
		if(!this.isDouble(pc.getTextFieldCostoKM().getText())){ pc.informarValidacion("Error, el campo no es un n�mero"); return false;}
		if(!this.isDouble(pc.getTextFieldKMRecorridos().getText())){ pc.informarValidacion("Error, el campo no es un n�mero"); return false;}
		
		return true;
	}
	
	public boolean isDouble(String numero){
	    try{
	        Double.parseDouble(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	
	//C�mo implementar la b�squeda por varios campos?
	public void buscar() {
		
	}
}
