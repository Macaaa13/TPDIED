package died.tp.controllers;

import java.util.*;

import died.tp.dao.InsumoDao;
import died.tp.dominio.Insumo;
import died.tp.dominio.*;
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
	
	
	//Métodos	
	public void guardar() {
		this.actualizarModelo();
		id.altaActualizacionInsumo(i);
		listaInsumos.clear();
		listaInsumos.addAll(id.buscarTodos());
	}
	
	public void setearInsumo(String opcion){
		if(opcion.contentEquals("General")){
			this.i = new InsumoGeneral();
		} else {
			this.i = new InsumoLiquido();
		}
	}
	
	public void actualizarModelo() {
		i.setCosto(Integer.valueOf(pi.getTextFieldCosto().getText()));
		i.setDescripcion(pi.getTextFieldDescripcion().getText());
		i.setuMedida(pi.getTextFieldUnidadMedida().getText());
		if(pi.getTextFieldDensidad().getText().isEmpty()) {
			i.setPesoDensidad(Double.valueOf(pi.getTextFieldPeso().getText()));
		}
		else {
			i.setPesoDensidad(Double.valueOf(pi.getTextFieldDensidad().getText()));
		}
	}
	
	public boolean validacionVacios() {
		if(pi.getTextFieldUnidadMedida().getText().isEmpty()) {pi.informarValidacion("El campo unidad de medida se encuentra vacio"); return false; }
		if(pi.getTextFieldCosto().getText().isEmpty()) {pi.informarValidacion("El campo Costo se encuentra vacio"); return false; }
		if(pi.getTextFieldDescripcion().getText().isEmpty()) {pi.informarValidacion("El campo Descripción está vacío (colocar '-' si no contiene la misma)"); return false;}
		if(pi.getComboBoxTipo().getSelectedItem().toString().equals("General")) {
			//pi.getComboBoxTipo().equals("General")
			if(pi.getTextFieldPeso().getText().isEmpty()) {pi.informarValidacion("El campo Peso se encuentra vacio"); return false; }
		}
		else {
			if(pi.getTextFieldDensidad().getText().isEmpty()) {pi.informarValidacion("El campo Densidad se encuentra vacio"); return false; }
		}
		return true;
	}
	
	public boolean camposCorrectos() {
		if(this.isDouble(pi.getTextFieldUnidadMedida().getText())) { pi.informarValidacion("Error, el campo nombre no debe contener números"); return false;}
		if(!this.isDouble(pi.getTextFieldCosto().getText())) { pi.informarValidacion("Error, el campo costo no es un número"); return false;}
		if(pi.getComboBoxTipo().getSelectedItem().toString().equals("General")) {
			if(!this.isDouble(pi.getTextFieldPeso().getText())) { pi.informarValidacion("Error, el campo peso no es un número"); return false;}
		}
		else {
			if(!this.isDouble(pi.getTextFieldDensidad().getText())) { pi.informarValidacion("Error, el campo densidad no es un número"); return false;}
		}
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
	
	public List<Insumo> traerDatos() {
		return id.buscarTodos();
	}
	
}
